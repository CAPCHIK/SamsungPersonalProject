package ru.capchik.personalproject.azureDevOpsModels;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class FullDefinitionResponse {
    private ApiLinksObject _links;
    private BuildResponse latestBuild;
    private BuildResponse latestCompletedBuild;
    private String jobAuthorizationScope;
    private int jobTimeoutInMinutes;
    private int jobCancelTimeoutInMinutes;
    private boolean badgeEnabled;
    private Repository repository;
    private String quality;
    private AuthoredBy authoredBy;
    private int id;
    private String name;
    private String url;
    private String uri;
    private String path;
    private String type;
    private String queueStatus;
    private int revision;
    private Date createdDate;

    public ApiLinksObject get_links() {
        return _links;
    }

    public void set_links(ApiLinksObject _links) {
        this._links = _links;
    }

    public BuildResponse getLatestBuild() {
        return latestBuild;
    }

    public void setLatestBuild(BuildResponse latestBuild) {
        this.latestBuild = latestBuild;
    }

    public BuildResponse getLatestCompletedBuild() {
        return latestCompletedBuild;
    }

    public void setLatestCompletedBuild(BuildResponse latestCompletedBuild) {
        this.latestCompletedBuild = latestCompletedBuild;
    }

    public String getJobAuthorizationScope() {
        return jobAuthorizationScope;
    }

    public void setJobAuthorizationScope(String jobAuthorizationScope) {
        this.jobAuthorizationScope = jobAuthorizationScope;
    }

    public int getJobTimeoutInMinutes() {
        return jobTimeoutInMinutes;
    }

    public void setJobTimeoutInMinutes(int jobTimeoutInMinutes) {
        this.jobTimeoutInMinutes = jobTimeoutInMinutes;
    }

    public int getJobCancelTimeoutInMinutes() {
        return jobCancelTimeoutInMinutes;
    }

    public void setJobCancelTimeoutInMinutes(int jobCancelTimeoutInMinutes) {
        this.jobCancelTimeoutInMinutes = jobCancelTimeoutInMinutes;
    }

    public boolean isBadgeEnabled() {
        return badgeEnabled;
    }

    public void setBadgeEnabled(boolean badgeEnabled) {
        this.badgeEnabled = badgeEnabled;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public AuthoredBy getAuthoredBy() {
        return authoredBy;
    }

    public void setAuthoredBy(AuthoredBy authoredBy) {
        this.authoredBy = authoredBy;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQueueStatus() {
        return queueStatus;
    }

    public void setQueueStatus(String queueStatus) {
        this.queueStatus = queueStatus;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
