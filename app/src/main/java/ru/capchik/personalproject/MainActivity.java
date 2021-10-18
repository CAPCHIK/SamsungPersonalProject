package ru.capchik.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.database.CacheDb;
import ru.capchik.personalproject.mappers.CompactBuildInfoMapper;
import ru.capchik.personalproject.models.CompactBuildInfo;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AzureDevOpsConfiguration configuration = new AzureDevOpsConfiguration(
                getResources().getString(R.string.azureDevOpsOrganization),
                getResources().getString(R.string.azureDevOpsProject)
        );
        AzureDevopsApiSingleton.Init(configuration);

        setTitle(getResources().getString(R.string.azureDevOpsProject) + " builds");

        CacheDb db = new CacheDb(this);
        loadDataFromCache(db);

        DataFromServerHandler handler = new DataFromServerHandler(Looper.getMainLooper(), db);
        AzureDevopsApiSingleton.getInstance().startGetBuilds(data -> {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("compactBuildInfo", data);
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                },
                (error) -> handler.sendEmptyMessage(DataFromServerHandler.ERROR_CODE));
    }

    private void loadDataFromCache(CacheDb db) {
        ArrayList<CompactBuildInfo> cachedBuildItems = db.getCachedBuildItems();
        fillListWithData(cachedBuildItems);
    }

    private void fillListWithData(ArrayList<CompactBuildInfo> compactBuildInfo) {
        RecyclerView list = MainActivity.this.findViewById(R.id.pipelines_list);
        list.setAdapter(new PipelinesAdapter(compactBuildInfo, (build) -> {
            Intent intent = new Intent(MainActivity.this, PipelineInfoActivity.class);
            intent.putExtra("definition_id", build.getDefinitionId());
            intent.putExtra("definition_name", build.getDefinitionName());
            startActivity(intent);
        }));
        list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        TextView loadingText = findViewById(R.id.loading_placeholder);
        loadingText.setVisibility(View.GONE);

        list.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    private class DataFromServerHandler extends Handler {

        public static final int ERROR_CODE = 1;

        private final CacheDb cacheDb;

        public DataFromServerHandler(@NonNull Looper looper, CacheDb cacheDb) {
            super(looper);
            this.cacheDb = cacheDb;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == ERROR_CODE) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.cantLoadDataFromNetwork), Toast.LENGTH_LONG).show();
            } else {
                ArrayList<CompactBuildInfo> compactBuildInfo = msg.getData().getParcelableArrayList("compactBuildInfo");
                cacheDb.updateCompactBuildInfo(compactBuildInfo);
                fillListWithData(compactBuildInfo);
            }
        }
    }
}