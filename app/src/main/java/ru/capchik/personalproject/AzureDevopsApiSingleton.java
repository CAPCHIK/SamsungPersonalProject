package ru.capchik.personalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AzureDevopsApiSingleton {
    private static AzureDevOpsApi instance;
    public static AzureDevOpsApi getInstance() {
        return instance;
    }
    public static void Init(AzureDevOpsConfiguration configuration) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dev.azure.com/" +
                          configuration.getOrganization() + "/" +
                          configuration.getProject() +"/_apis/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        instance = retrofit.create(AzureDevOpsApi.class);

    }
}
