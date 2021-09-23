package ru.capchik.personalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.azureDevOpsModels.Pipeline;

public class MainActivity extends AppCompatActivity {
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);


        AzureDevOpsConfiguration configuration = new AzureDevOpsConfiguration(
                getResources().getString(R.string.azureDevOpsOrganization),
                getResources().getString(R.string.azureDevOpsProject)
        );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.azure.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AzureDevOpsApi azureDevOpsApi = retrofit.create(AzureDevOpsApi.class);
        Call<ApiListResponse<BuildResponse>> pipelines = azureDevOpsApi.getBuilds(configuration.getOrganization(), configuration.getProject());
        pipelines.enqueue(new Callback<ApiListResponse<BuildResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ApiListResponse<BuildResponse>> call, @NonNull Response<ApiListResponse<BuildResponse>> response) {
                ApiListResponse<BuildResponse> body = response.body();
                assert body != null;
                Toast.makeText(MainActivity.this, "found " + body.getCount() + " items, first is " + body.getValue().get(0).getDefinition().getName(), Toast.LENGTH_LONG).show();
                RecyclerView list = MainActivity.this.findViewById(R.id.pipelines_list);
                list.setAdapter(new PipelinesAdapter(body));
                list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(@NonNull Call<ApiListResponse<BuildResponse>> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "error while loading", Toast.LENGTH_LONG).show();
            }
        });
    }
}