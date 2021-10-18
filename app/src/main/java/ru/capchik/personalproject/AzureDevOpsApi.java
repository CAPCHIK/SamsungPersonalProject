package ru.capchik.personalproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.capchik.personalproject.azureDevOpsModels.ApiListResponse;
import ru.capchik.personalproject.azureDevOpsModels.BuildResponse;
import ru.capchik.personalproject.azureDevOpsModels.FullDefinitionResponse;
import ru.capchik.personalproject.mappers.CompactBuildInfoMapper;
import ru.capchik.personalproject.models.CompactBuildInfo;

public class AzureDevOpsApi {

    public static final int ERROR_CODE = 1;

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final String baseUrl;

    private static final Type GET_BUILDS_LIST_TYPE = new TypeToken<ApiListResponse<BuildResponse>>() {
    }.getType();

    public AzureDevOpsApi(String baseUrl) {
        httpClient = new OkHttpClient();
        gson = new Gson();
        this.baseUrl = baseUrl;
    }

    void startGetBuilds(Handler handler) {
        new Thread() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(baseUrl + "build/builds?api-version=5.0&maxBuildsPerDefinition=1")
                        .build();

                okhttp3.Call call = httpClient.newCall(request);
                try {
                    Response response = call.execute();
                    if (!response.isSuccessful()) {
                        throw new Exception("Response code is " + response.code());
                    }
                    if (response.body() == null) {
                        throw new Exception("Response body is null");
                    }
                    ApiListResponse<BuildResponse> responseApiModel = gson.fromJson(response.body().string(), GET_BUILDS_LIST_TYPE);

                    ArrayList<CompactBuildInfo> compactBuildInfo = CompactBuildInfoMapper.map(responseApiModel);

                    Message msg = new Message();
                    Bundle data = new Bundle();
                    data.putParcelableArrayList("compactBuildInfo", compactBuildInfo);
                    msg.setData(data);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    handler.sendEmptyMessage(ERROR_CODE);
                }
            }
        }.start();
    }

    @GET("build/definitions/{definitionId}?api-version=5.0&maxBuildsPerDefinition=1&includeLatestBuilds=true")
    Call<FullDefinitionResponse> getDefinition(@Path("definitionId") int definitionId) {
        return null;
    }
}