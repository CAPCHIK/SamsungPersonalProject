package ru.capchik.personalproject.azureDevOpsModels;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BuildResponse {
    @SerializedName("_links")
    private ApiLinksObject links;


    private TriggerInfo triggerInfo;
    private int id;
    private String buildNumber;
    private String status;
    private String result;
    private Date queueTime;
    private Date startTime;
    private Date finishTime;
    private String url;
    private Definition definition;
    private String uri;
    private String sourceBranch;
    private String sourceVersion;
    private String priority;
    private String reason;
    private Date lastChangedDate;
    private Repository repository;
    private boolean keepForever;
    private boolean retainedByRelease;

    public ApiLinksObject getLinks() {
        return links;
    }

    public void setLinks(ApiLinksObject links) {
        this.links = links;
    }

    public TriggerInfo getTriggerInfo() {
        return triggerInfo;
    }

    public void setTriggerInfo(TriggerInfo triggerInfo) {
        this.triggerInfo = triggerInfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Date getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(Date queueTime) {
        this.queueTime = queueTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getSourceBranch() {
        return sourceBranch;
    }

    public void setSourceBranch(String sourceBranch) {
        this.sourceBranch = sourceBranch;
    }

    public String getSourceVersion() {
        return sourceVersion;
    }

    public void setSourceVersion(String sourceVersion) {
        this.sourceVersion = sourceVersion;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getLastChangedDate() {
        return lastChangedDate;
    }

    public void setLastChangedDate(Date lastChangedDate) {
        this.lastChangedDate = lastChangedDate;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public boolean isKeepForever() {
        return keepForever;
    }

    public void setKeepForever(boolean keepForever) {
        this.keepForever = keepForever;
    }

    public boolean isRetainedByRelease() {
        return retainedByRelease;
    }

    public void setRetainedByRelease(boolean retainedByRelease) {
        this.retainedByRelease = retainedByRelease;
    }
}
