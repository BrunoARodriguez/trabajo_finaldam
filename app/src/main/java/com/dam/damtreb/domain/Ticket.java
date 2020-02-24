package com.dam.damtreb.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "tickets")
public class Ticket {
@PrimaryKey(autoGenerate = true)
@NonNull
    private Integer id;
private  String imagenString;
private LocalDateTime fechaTicket;
private  Integer valor;

public  Ticket(String imagenString, Integer valor){
    this.imagenString = imagenString;
    this.fechaTicket = LocalDateTime.now();
    this.valor = valor;

}

    public String getImagenString() {
        return imagenString;
    }

    public void setImagenString(String imagenString) {
        this.imagenString = imagenString;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public LocalDateTime getFechaTicket() {
        return fechaTicket;
    }

    public void setFechaTicket(LocalDateTime fechaTicket) {
        this.fechaTicket = fechaTicket;
    }

    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }
}
