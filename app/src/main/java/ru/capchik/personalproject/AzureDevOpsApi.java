package ru.capchik.personalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.azureDevOpsModels.FullDefinitionResponse;

public interface AzureDevOpsApi {
    @GET("build/builds?api-version=5.0&maxBuildsPerDefinition=1")
    Call<ApiListResponse<BuildResponse>> getBuilds();
    @GET("build/definitions/{definitionId}?api-version=5.0&maxBuildsPerDefinition=1&includeLatestBuilds=true")
    Call<FullDefinitionResponse> getDefinition(@Path("definitionId") int definitionId);
}