package ru.capchik.personalproject;

public class AzureDevOpsConfiguration {

    private final String organization;
    private final String project;


    public AzureDevOpsConfiguration(String organization, String project) {
        this.organization = organization;
        this.project = project;
    }

    public String getOrganization() {
        return organization;
    }

    public String getProject() {
        return project;
    }
}
