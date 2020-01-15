package com.dam.damtreb.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dam.damtreb.domain.Location;

import java.util.List;

@Dao
public interface LocationDao {
@Insert(onConflict = OnConflictStrategy.ABORT)
    public  void  insertarLocation(Location location);

@Update
    public  void  actualizarLocation(Location location);

@Delete
    public  void deleteLocation(Location location);

@Query("SELECT * FROM Locations")
    public List<Location> buscarLocation();



}
