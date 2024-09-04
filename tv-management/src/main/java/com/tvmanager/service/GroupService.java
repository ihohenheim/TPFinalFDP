package com.tvmanager.service;

import com.tvmanager.model.TV;
import com.tvmanager.model.TVGroup;
import java.util.HashMap;
import java.util.Map;

public class GroupService {
    private Map<String, TVGroup> groups;

    public GroupService() {
        this.groups = new HashMap<>();
        // Agregar grupo por defecto
        groups.put("#G00", new TVGroup("#G00", "Todos los televisores"));
    }

    public void addGroup(TVGroup group) {
        groups.put(group.getGroupId(), group);
    }

    public void removeGroup(String groupId) {
        groups.remove(groupId);
    }

    public TVGroup findGroupById(String groupId) {
        return groups.get(groupId);
    }

    public Map<String, TVGroup> getAllGroups() {
        return groups;
    }

    public void addTVToGroup(TV tv, String groupId) {
        TVGroup group = findGroupById(groupId);
        if (group != null) {
            group.addTV(tv);
        }
    }

    public void removeTVFromGroup(TV tv, String groupId) {
        TVGroup group = findGroupById(groupId);
        if (group != null) {
            group.removeTV(tv);
        }
    }

    public void changeGroupDescription(String groupId, String newDescription) {
        TVGroup group = findGroupById(groupId);
        if (group != null) {
            group.setDescription(newDescription);
        }
    }
}
