package ru.capchik.personalproject.models;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class CompactBuildInfo {
    private static final PrettyTime prettyTime =  new PrettyTime(Locale.US);

    private final int definitionId;
    private final boolean succeeded;
    private final String definitionName;
    private final String buildNumber;
    private final String commitMessage;
    private final Date finishTime;

    public CompactBuildInfo(int definitionId, boolean succeeded, String definitionName, String buildNumber, String commitMessage, Date finishTime) {
        this.definitionId = definitionId;
        this.succeeded = succeeded;
        if (definitionName == null) {
            definitionName = "NO MESSAGE";
        }
        this.definitionName = definitionName;
        if (buildNumber == null) {
            buildNumber = "NO MESSAGE";
        }
        this.buildNumber = buildNumber;
        if (commitMessage == null) {
            commitMessage = "NO MESSAGE";
        }
        this.commitMessage = commitMessage;
        this.finishTime = finishTime;
    }

    public int getDefinitionId() {
        return definitionId;
    }

    public boolean isSucceeded() {
        return succeeded;
    }


    public String getDefinitionName() {
        return definitionName;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public String getPrettyFinishTime() {
        return prettyTime.format(finishTime);
    }
}
