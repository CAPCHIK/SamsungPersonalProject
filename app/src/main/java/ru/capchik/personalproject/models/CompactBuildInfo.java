package ru.capchik.personalproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class CompactBuildInfo implements Parcelable {
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

    public static final Creator<CompactBuildInfo> CREATOR = new Creator<CompactBuildInfo>() {
        @Override
        public CompactBuildInfo createFromParcel(Parcel in) {
            return new CompactBuildInfo(in);
        }

        @Override
        public CompactBuildInfo[] newArray(int size) {
            return new CompactBuildInfo[size];
        }
    };
    private CompactBuildInfo(Parcel in) {
        definitionId = in.readInt();
        succeeded = in.readByte() != 0;
        definitionName = in.readString();
        buildNumber = in.readString();
        commitMessage = in.readString();
        finishTime = new Date(in.readLong());
    }

    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(definitionId);
        parcel.writeByte((byte) (succeeded ? 1 : 0));
        parcel.writeString(definitionName);
        parcel.writeString(buildNumber);
        parcel.writeString(commitMessage);
        parcel.writeLong(finishTime.getTime());
    }
}
