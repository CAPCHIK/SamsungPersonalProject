package ru.capchik.personalproject.azureDevOpsModels;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class PipelineCompactResponse {
    @SerializedName("_links")
    private ApiLinksObject links;
    private Pipeline pipeline;
    private String state;
    private String result;
    private Date createdDate;
    private Date finishedDate;
    private String url;
    private int id;
    private String name;

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ApiLinksObject getLinks() {
        return links;
    }

    public void setLinks(ApiLinksObject links) {
        this.links = links;
    }
}
