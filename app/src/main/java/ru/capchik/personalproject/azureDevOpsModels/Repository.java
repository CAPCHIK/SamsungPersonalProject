package ru.capchik.personalproject.azureDevOpsModels;

public class Repository {
    private String id;
    private String type;
    private boolean checkoutSubmodules;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCheckoutSubmodules() {
        return checkoutSubmodules;
    }

    public void setCheckoutSubmodules(boolean checkoutSubmodules) {
        this.checkoutSubmodules = checkoutSubmodules;
    }
}
