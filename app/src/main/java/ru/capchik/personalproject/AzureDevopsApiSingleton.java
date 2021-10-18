package ru.capchik.personalproject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AzureDevopsApiSingleton {
    private static AzureDevOpsApi instance;
    public static AzureDevOpsApi getInstance() {
        return instance;
    }
    public static void Init(AzureDevOpsConfiguration configuration) {
        instance = new AzureDevOpsApi("https://dev.azure.com/" +
                configuration.getOrganization() + "/" +
                configuration.getProject() +"/_apis/");
    }
}
