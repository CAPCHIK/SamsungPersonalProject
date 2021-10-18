package ru.capchik.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ru.capchik.personalproject.models.CompactBuildInfo;
import ru.capchik.personalproject.models.FullDefinitionInfo;

public class PipelineInfoActivity extends AppCompatActivity {

    private ImageView authorImage;
    private TextView authorName;
    private ImageView ownerImage;
    private TextView repoTitle;
    private TextView mainBranch;
    private TextView path;
    private TextView created;
    private Button openButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent activityIntent = getIntent();

        int definitionId = activityIntent.getIntExtra("definition_id", -1);
        String definitionTitle = activityIntent.getStringExtra("definition_name");
        if (definitionId == -1 || definitionTitle == null) {
            Toast.makeText(this, "Can't open info without definition id and name", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        setContentView(R.layout.activity_pipeline_info);
        setTitle(definitionTitle);

        authorImage = findViewById(R.id.definition_author_image);
        ownerImage = findViewById(R.id.repo_owner_image);
        authorName = findViewById(R.id.definition_author_name);
        repoTitle = findViewById(R.id.repo_title);
        mainBranch = findViewById(R.id.repo_main_branch);
        path = findViewById(R.id.definition_path);
        created = findViewById(R.id.definition_created);
        openButton = findViewById(R.id.open_definition_button);

        DataFromServerHandler handler = new DataFromServerHandler(Looper.getMainLooper());
        AzureDevopsApiSingleton.getInstance().startGetDefinition(definitionId,
                definition -> handler.sendMessage(handler.prepareMessage(definition)),
                error -> handler.sendEmptyMessage(DataFromServerHandler.ERROR_CODE));
    }

    private void fillFromData(ru.capchik.personalproject.models.FullDefinitionInfo definition) {
        Picasso.get()
                .load(definition.getAuthorAvatarUrl())
                .into(authorImage);

        Picasso.get()
                .load(definition.getOwnerAvatarUrl())
                .into(ownerImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        ownerImage.setBackgroundColor(getResources().getColor(R.color.repoOwnerBackground));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });

        authorName.setText(definition.getAuthorName());

        repoTitle.setText(Html.fromHtml(definition.getRepoLink()));
        repoTitle.setMovementMethod(LinkMovementMethod.getInstance());

        mainBranch.setText(definition.getDefaultBranch());
        path.setText(definition.getPath());
        created.setText(definition.getCreatedDatePretty());


        openButton.setOnClickListener(view -> {
            Uri uri = Uri.parse(definition.getWebLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        BuildItemFragment latestBuildFragment = (BuildItemFragment) fragmentManager.findFragmentById(R.id.latest_build_fragment);
        BuildItemFragment latestCompletedBuildFragment = (BuildItemFragment) fragmentManager.findFragmentById(R.id.latest_completed_build_fragment);

        assert latestBuildFragment != null;
        assert latestCompletedBuildFragment != null;

        latestBuildFragment.fillInfo(definition.getLatestBuild());
        latestCompletedBuildFragment.fillInfo(definition.getLatestCompletedBuild());

        if (definition.getLatestBuild().getBuildId() != definition.getLatestCompletedBuild().getBuildId()) {
            findViewById(R.id.latest_completed_build_row).setVisibility(View.VISIBLE);
        }
    }

    private class DataFromServerHandler extends Handler {

        public static final int ERROR_CODE = 1;


        public DataFromServerHandler(@NonNull Looper looper) {
            super(looper);
        }

        public Message prepareMessage(FullDefinitionInfo fullDefinitionInfo){
            Bundle bundle = new Bundle();
            bundle.putParcelable("fullDefinitionInfo", fullDefinitionInfo);
            Message msg = new Message();
            msg.setData(bundle);
            return msg;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == ERROR_CODE) {
                Toast.makeText(PipelineInfoActivity.this, getResources().getString(R.string.cantLoadDataFromNetwork), Toast.LENGTH_LONG).show();
                finish();
            } else {
                FullDefinitionInfo fullDefinitionInfo = msg.getData().getParcelable("fullDefinitionInfo");
                fillFromData(fullDefinitionInfo);
            }
        }
    }
}