package ru.capchik.personalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.Pipeline;
import ru.capchik.personalproject.azureDevOpsModels.PipelineCompactResponse;

public interface AzureDevOpsApi {
    @GET("{organization}/{project}/_apis/pipelines?api-version=6.0-preview.1")
    Call<ApiListResponse<Pipeline>> getPipelines(
            @Path("organization") String organization,
            @Path("project") String project);
}
