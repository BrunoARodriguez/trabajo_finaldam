package com.dam.damtreb.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dam.damtreb.domain.Ticket;

import java.util.List;

@Dao
public interface TicketDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public  void  insertarTicket(Ticket ticket);

    @Update
    public  void  actualizarTicket(Ticket ticket);

    @Delete
    public  void  eliminarTicket(Ticket ticket);

@Query("SELECT * FROM tickets")
    public List<Ticket> buscarTickets();

}
