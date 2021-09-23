package ru.capchik.personalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.azureDevOpsModels.Pipeline;

public interface AzureDevOpsApi {
    @GET("{organization}/{project}/_apis/build/builds?api-version=5.0&maxBuildsPerDefinition=1")
    Call<ApiListResponse<BuildResponse>> getBuilds(
            @Path("organization") String organization,
            @Path("project") String project);
}