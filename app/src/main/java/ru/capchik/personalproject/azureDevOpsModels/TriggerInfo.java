package ru.capchik.personalproject.azureDevOpsModels;

import com.google.gson.annotations.SerializedName;

public class TriggerInfo {
    @SerializedName("ci.sourceBranch")
    private String ciSourceBranch;
    @SerializedName("ci.sourceSha")
    private String ciSourceSha;
    @SerializedName("ci.message")
    private String ciMessage;
    @SerializedName("ci.triggerRepository")
    private String ciTriggerRepository;

    public String getCiSourceBranch() {
        return ciSourceBranch;
    }

    public void setCiSourceBranch(String ciSourceBranch) {
        this.ciSourceBranch = ciSourceBranch;
    }

    public String getCiSourceSha() {
        return ciSourceSha;
    }

    public void setCiSourceSha(String ciSourceSha) {
        this.ciSourceSha = ciSourceSha;
    }

    public String getCiMessage() {
        return ciMessage;
    }

    public void setCiMessage(String ciMessage) {
        this.ciMessage = ciMessage;
    }

    public String getCiTriggerRepository() {
        return ciTriggerRepository;
    }

    public void setCiTriggerRepository(String ciTriggerRepository) {
        this.ciTriggerRepository = ciTriggerRepository;
    }
}
