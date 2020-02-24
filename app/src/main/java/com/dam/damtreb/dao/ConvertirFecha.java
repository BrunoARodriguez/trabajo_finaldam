package com.dam.damtreb.dao;

import android.text.format.Time;

import androidx.room.TypeConverter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Date;

public class ConvertirFecha {

@TypeConverter
    public  static LocalDateTime toDate(String valor){
    return  valor == null ? null : LocalDateTime.parse(valor);
}

@TypeConverter
    public  static  String toDateString(LocalDateTime localDateTime){

return  localDateTime == null ? null : localDateTime.toString();
}
}
