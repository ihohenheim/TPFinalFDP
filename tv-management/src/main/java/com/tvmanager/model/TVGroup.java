package com.tvmanager.model;

import java.util.ArrayList;
import java.util.List;

public class TVGroup {
    private String groupId;
    private String description;
    private List<TV> tvs;

    public TVGroup(String groupId, String description) {
        this.groupId = groupId;
        this.description = description;
        this.tvs = new ArrayList<>();
    }

    public String getGroupId() {
        return groupId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TV> getTvs() {
        return tvs;
    }

    public void addTV(TV tv) {
        tvs.add(tv);
    }

    public void removeTV(TV tv) {
        tvs.remove(tv);
    }
}
