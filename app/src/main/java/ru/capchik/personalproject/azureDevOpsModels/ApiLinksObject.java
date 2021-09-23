package ru.capchik.personalproject.azureDevOpsModels;

import com.google.gson.annotations.SerializedName;

public class ApiLinksObject {
        private ApiLink self;
        private ApiLink web;
        @SerializedName("pipeline.web")
        private ApiLink pipelineWeb;
        private ApiLink pipeline;
        private ApiLink badge;
        private ApiLink avatar;

        public ApiLink getSelf() {
                return self;
        }

        public void setSelf(ApiLink self) {
                this.self = self;
        }

        public ApiLink getWeb() {
                return web;
        }

        public void setWeb(ApiLink web) {
                this.web = web;
        }

        public ApiLink getPipelineWeb() {
                return pipelineWeb;
        }

        public void setPipelineWeb(ApiLink pipelineWeb) {
                this.pipelineWeb = pipelineWeb;
        }

        public ApiLink getPipeline() {
                return pipeline;
        }

        public void setPipeline(ApiLink pipeline) {
                this.pipeline = pipeline;
        }

        public ApiLink getBadge() {
                return badge;
        }

        public void setBadge(ApiLink badge) {
                this.badge = badge;
        }

        public ApiLink getAvatar() {
                return avatar;
        }

        public void setAvatar(ApiLink avatar) {
                this.avatar = avatar;
        }
}
