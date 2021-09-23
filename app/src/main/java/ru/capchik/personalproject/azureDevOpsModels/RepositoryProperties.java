package ru.capchik.personalproject.azureDevOpsModels;

import android.annotation.SuppressLint;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RepositoryProperties {

    private static PrettyTime prettyTime = new PrettyTime(Locale.US);

    private String apiUrl;
    private String branchesUrl;
    private String cloneUrl;
    private String connectedServiceId;
    private String defaultBranch;
    private String fullName;
    private String hasAdminPermissions;
    private String isFork;
    private String isPrivate;
    private String lastUpdated;
    private String manageUrl;
    private String nodeId;
    private String ownerId;
    private String orgName;
    private String refsUrl;
    private String safeRepository;
    private String shortName;
    private String ownerAvatarUrl;
    private String archived;
    private String externalId;
    private String ownerIsAUser;
    private String reportBuildStatus;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getBranchesUrl() {
        return branchesUrl;
    }

    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    public String getConnectedServiceId() {
        return connectedServiceId;
    }

    public void setConnectedServiceId(String connectedServiceId) {
        this.connectedServiceId = connectedServiceId;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHasAdminPermissions() {
        return hasAdminPermissions;
    }

    public void setHasAdminPermissions(String hasAdminPermissions) {
        this.hasAdminPermissions = hasAdminPermissions;
    }

    public String getIsFork() {
        return isFork;
    }

    public void setIsFork(String isFork) {
        this.isFork = isFork;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getManageUrl() {
        return manageUrl;
    }

    public void setManageUrl(String manageUrl) {
        this.manageUrl = manageUrl;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRefsUrl() {
        return refsUrl;
    }

    public void setRefsUrl(String refsUrl) {
        this.refsUrl = refsUrl;
    }

    public String getSafeRepositoryLink() {
        return "<a href=\"" + getCloneUrl() + "\">" + safeRepository + "</a>";
    }

    public String getSafeRepository() {
        return safeRepository;
    }

    public void setSafeRepository(String safeRepository) {
        this.safeRepository = safeRepository;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public void setOwnerAvatarUrl(String ownerAvatarUrl) {
        this.ownerAvatarUrl = ownerAvatarUrl;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getOwnerIsAUser() {
        return ownerIsAUser;
    }

    public void setOwnerIsAUser(String ownerIsAUser) {
        this.ownerIsAUser = ownerIsAUser;
    }

    public String getReportBuildStatus() {
        return reportBuildStatus;
    }

    public void setReportBuildStatus(String reportBuildStatus) {
        this.reportBuildStatus = reportBuildStatus;
    }}
