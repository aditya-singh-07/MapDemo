package com.aditya.mapdemo.Api;

import com.aditya.mapdemo.model.Mapmodel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class users {
    @SerializedName("newarrival")
    private List<Mapmodel> newarrival;

    public List<Mapmodel> getNewarrival() {
        return newarrival;
    }

    public void setNewarrival(List<Mapmodel> newarrival) {
        this.newarrival = newarrival;
    }
}
