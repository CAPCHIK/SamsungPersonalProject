package ru.capchik.personalproject.azureDevOpsModels;

import com.google.gson.annotations.SerializedName;

public class ApiLinksObject {
        private ApiLink self;
        private ApiLink web;
        @SerializedName("pipeline.web")
        private ApiLink pipelineWeb;
        private ApiLink pipeline;

}
