package com.dam.damtreb.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dam.damtreb.domain.Location;

@Database(entities = {Location.class}, version = 1)
public abstract class MyDataBase extends RoomDatabase {
public  abstract  LocationDao locationDao();
}
