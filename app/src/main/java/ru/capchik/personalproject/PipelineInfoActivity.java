package ru.capchik.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.capchik.personalproject.azureDevOpsModels.FullDefinitionResponse;

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
        Intent intent = getIntent();

        int definitionId = intent.getIntExtra("definition_id", -1);
        String definitionTitle = intent.getStringExtra("definition_name");
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

        Call<FullDefinitionResponse> definition = AzureDevopsApiSingleton.getInstance().getDefinition(definitionId);
        definition.enqueue(new Callback<FullDefinitionResponse>() {
            @Override
            public void onResponse(@NonNull Call<FullDefinitionResponse> call, @NonNull Response<FullDefinitionResponse> response) {
                FullDefinitionResponse definition = response.body();
                assert definition != null;

                Picasso.get()
                        .load(definition.getAuthoredBy().get_links().getAvatar().getHref())
                        .into(authorImage);

                Picasso.get()
                        .load(definition.getRepository().getProperties().getOwnerAvatarUrl())
                        .into(ownerImage, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                ownerImage.setBackgroundColor(getResources().getColor(R.color.repoOwnerBackground));
                            }

                            @Override
                            public void onError(Exception e) {

                            }
                        });

                authorName.setText(definition.getAuthoredBy().getDisplayName());

                repoTitle.setText(Html.fromHtml(definition.getRepository().getProperties().getSafeRepositoryLink()));
                repoTitle.setMovementMethod(LinkMovementMethod.getInstance());

                mainBranch.setText(definition.getRepository().getProperties().getDefaultBranch());
                path.setText(definition.getPath());
                created.setText(definition.getCreatedDatePretty());


                openButton.setOnClickListener(view -> {
                    Uri uri = Uri.parse(definition.get_links().getWeb().getHref());
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

                if (definition.getLatestBuild().getId() != definition.getLatestCompletedBuild().getId()) {
                    findViewById(R.id.latest_completed_build_row).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<FullDefinitionResponse> call, @NonNull Throwable t) {
                Toast.makeText(PipelineInfoActivity.this, getResources().getString(R.string.cantLoadDataFromNetwork), Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
}