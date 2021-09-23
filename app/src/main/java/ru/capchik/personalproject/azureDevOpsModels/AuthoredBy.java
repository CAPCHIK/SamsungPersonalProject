package ru.capchik.personalproject.azureDevOpsModels;

public class AuthoredBy {
    private String displayName;
    private String url;
    private ApiLinksObject _links;
    private String id;
    private String descriptor;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ApiLinksObject get_links() {
        return _links;
    }

    public void set_links(ApiLinksObject _links) {
        this._links = _links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }
}
