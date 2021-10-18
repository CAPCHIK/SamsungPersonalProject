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
        this.definitionName = definitionName;
        this.buildNumber = buildNumber;
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

    public String getPrettyFinishTime() {
        return prettyTime.format(finishTime);
    }
}
