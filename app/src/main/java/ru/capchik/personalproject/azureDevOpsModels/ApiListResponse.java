package ru.capchik.personalproject.azureDevOpsModels;

import java.util.ArrayList;

public class ApiListResponse<T> {
    private int count;
    private ArrayList<T> value;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<T> getValue() {
        return value;
    }

    public void setValue(ArrayList<T> value) {
        this.value = value;
    }
}
