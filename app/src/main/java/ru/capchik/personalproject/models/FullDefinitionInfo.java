package ru.capchik.personalproject.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;
import java.util.Locale;

public class FullDefinitionInfo implements Parcelable {
    private static final PrettyTime prettyTime =  new PrettyTime(Locale.US);

    private final String authorAvatarUrl;
    private final String authorName;
    private final String ownerAvatarUrl;
    private final String repoLink;
    private final String defaultBranch;
    private final String path;
    private final Date date;
    private final String webLink;
    private final CompactBuildInfo latestBuild;
    private final CompactBuildInfo latestCompletedBuild;

    public FullDefinitionInfo(String authorAvatarUrl, String authorName, String ownerAvatarUrl, String repoLink, String defaultBranch, String path, Date date, String webLink, CompactBuildInfo latestBuild, CompactBuildInfo latestCompletedBuild) {
        this.authorAvatarUrl = authorAvatarUrl;
        this.authorName = authorName;
        this.ownerAvatarUrl = ownerAvatarUrl;
        this.repoLink = repoLink;
        this.defaultBranch = defaultBranch;
        this.path = path;
        this.date = date;
        this.webLink = webLink;
        this.latestBuild = latestBuild;
        this.latestCompletedBuild = latestCompletedBuild;
    }


    public String getAuthorAvatarUrl() {
        return authorAvatarUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public String getRepoLink() {
        return repoLink;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public String getPath() {
        return path;
    }

    public String getCreatedDatePretty() {
        return prettyTime.format(date);
    }

    public String getWebLink() {
        return webLink;
    }

    public CompactBuildInfo getLatestBuild() {
        return latestBuild;
    }

    public CompactBuildInfo getLatestCompletedBuild() {
        return latestCompletedBuild;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    private FullDefinitionInfo(Parcel in) {
        authorAvatarUrl = in.readString();
        authorName = in.readString();
        ownerAvatarUrl = in.readString();
        repoLink = in.readString();
        defaultBranch = in.readString();
        path = in.readString();
        date = new Date(in.readLong());
        webLink = in.readString();
        latestBuild = in.readParcelable(CompactBuildInfo.class.getClassLoader());
        latestCompletedBuild = in.readParcelable(CompactBuildInfo.class.getClassLoader());
    }

    public static final Creator<FullDefinitionInfo> CREATOR = new Creator<FullDefinitionInfo>() {
        @Override
        public FullDefinitionInfo createFromParcel(Parcel in) {
            return new FullDefinitionInfo(in);
        }

        @Override
        public FullDefinitionInfo[] newArray(int size) {
            return new FullDefinitionInfo[size];
        }
    };
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(authorAvatarUrl);
        parcel.writeString(authorName);
        parcel.writeString(ownerAvatarUrl);
        parcel.writeString(repoLink);
        parcel.writeString(defaultBranch);
        parcel.writeString(path);
        parcel.writeLong(date.getTime());
        parcel.writeString(webLink);
        parcel.writeParcelable(latestBuild, i);
        parcel.writeParcelable(latestCompletedBuild, i);
    }
}
