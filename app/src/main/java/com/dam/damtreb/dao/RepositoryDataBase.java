package com.dam.damtreb.dao;

import android.content.Context;
import android.location.Location;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public class RepositoryDataBase {
private   static RepositoryDataBase repo = null;
private MyDataBase myDataBase;
private LocationDao locationDao;
private  TicketDao ticketDao;
private  RepositoryDataBase(Context ctx){
    myDataBase = Room.databaseBuilder(ctx, MyDataBase.class, "Data base DamTreb")
            .fallbackToDestructiveMigration().build();
    locationDao = myDataBase.locationDao();
    ticketDao = myDataBase.ticketDao();
}

public  static RepositoryDataBase getInstance(Context ctx) {
    if (repo == null){
        repo = new RepositoryDataBase(ctx);
    }
return  repo;
}

    public MyDataBase getMyDataBase() {
        return myDataBase;
    }


}
