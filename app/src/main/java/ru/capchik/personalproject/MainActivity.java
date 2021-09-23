package ru.capchik.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;

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

        Call<ApiListResponse<BuildResponse>> pipelines = AzureDevopsApiSingleton.getInstance().getBuilds();
        pipelines.enqueue(new Callback<ApiListResponse<BuildResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ApiListResponse<BuildResponse>> call, @NonNull Response<ApiListResponse<BuildResponse>> response) {
                ApiListResponse<BuildResponse> body = response.body();
                assert body != null;
                RecyclerView list = MainActivity.this.findViewById(R.id.pipelines_list);
                list.setAdapter(new PipelinesAdapter(body, (build) -> {
                    Intent intent = new Intent(MainActivity.this, PipelineInfoActivity.class);
                    intent.putExtra("definition_id", build.getDefinition().getId());
                    intent.putExtra("definition_name", build.getDefinition().getName());
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

            @Override
            public void onFailure(@NonNull Call<ApiListResponse<BuildResponse>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "error while loading", Toast.LENGTH_LONG).show();
            }
        });
    }
}