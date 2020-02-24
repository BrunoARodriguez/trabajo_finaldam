package com.dam.damtreb.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.dam.damtreb.domain.Location;
import com.dam.damtreb.domain.Ticket;

@Database(entities = {Location.class, Ticket.class}, version = 2)
@TypeConverters(ConvertirFecha.class)
public abstract class MyDataBase extends RoomDatabase {
public  abstract  LocationDao locationDao();
public  abstract  TicketDao ticketDao();
}
