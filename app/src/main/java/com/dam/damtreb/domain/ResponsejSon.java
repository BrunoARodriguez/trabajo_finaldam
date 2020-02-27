package com.dam.damtreb.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsejSon {

@SerializedName("place_id")
    @Expose
    private  Integer id;
@SerializedName("lat")
@Expose
    private  String lat;
@SerializedName("lon")
    @Expose
private  String lon;
@SerializedName("display_name")
@Expose
    private  String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
