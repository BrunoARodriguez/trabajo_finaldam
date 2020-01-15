package com.dam.damtreb.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;
@Entity(tableName = "Locations")
public class Location {
@PrimaryKey(autoGenerate = true)
@NonNull
private  Integer id;

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    private  String name;
private  Double length;
private  Double latitude;

    public Location(String name,Double length, Double latitude) {
        this.name = name;
        this.length = length;
        this.latitude = latitude;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    //equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(name, location.name) &&
                Objects.equals(length, location.length) &&
                Objects.equals(latitude, location.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


