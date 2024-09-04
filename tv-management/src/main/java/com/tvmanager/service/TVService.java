package com.tvmanager.service;

import com.tvmanager.model.TV;
import java.util.ArrayList;
import java.util.List;

public class TVService {
    private List<TV> tvList;

    public TVService() {
        this.tvList = new ArrayList<>();
    }

    public void addTV(TV tv) {
        tvList.add(tv);
    }

    public void removeTV(TV tv) {
        tvList.remove(tv);
    }

    public TV findTVById(String id) {
        for (TV tv : tvList) {
            if (tv.getId().equals(id)) {
                return tv;
            }
        }
        return null;
    }

    public List<TV> getAllTVs() {
        return tvList;
    }

    public void togglePower(TV tv) {
        tv.togglePower();
    }

    public void increaseVolume(TV tv) {
        if (tv.getVolume() < 15) {
            tv.setVolume(tv.getVolume() + 1);
        }
    }

    public void decreaseVolume(TV tv) {
        if (tv.getVolume() > 0) {
            tv.setVolume(tv.getVolume() - 1);
        }
    }

    public void changeChannel(TV tv, int channel) {
        if (channel >= 1 && channel <= 999) {
            tv.setChannel(channel);
        }
    }

    public void increaseChannel(TV tv) {
        if (tv.getChannel() < 999) {
            tv.setChannel(tv.getChannel() + 1);
        }
    }

    public void decreaseChannel(TV tv) {
        if (tv.getChannel() > 1) {
            tv.setChannel(tv.getChannel() - 1);
        }
    }
}
