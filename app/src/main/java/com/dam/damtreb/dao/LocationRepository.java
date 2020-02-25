package com.dam.damtreb.dao;

import android.os.Handler;
import android.os.Message;

import com.dam.damtreb.dao.rest.LocationRest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocationRepository {
public  static  String _SERVER = "https://nominatim.openstreetmap.org/";

private List lista;

public   static final int CONSULTA = 1;
public  static final int ERROR = -1;

private  static  LocationRepository instancia;

private  LocationRepository(){
}

public  static  LocationRepository getInstans(){
    if (instancia == null){
        instancia = new LocationRepository();
        instancia.configurarRetrofit();
        instancia.lista = new ArrayList();
    }
    return  instancia;
}

private Retrofit rf;
private LocationRest locationRest;

private  void  configurarRetrofit(){
    this.rf = new  Retrofit.Builder()
            .baseUrl(_SERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    this.locationRest = this.rf.create(LocationRest.class);

}

//Repositorio get

public void  buscar(String nombre, final Handler h){
    Call<List> llamada = this.locationRest.getLocations(nombre,"json",1,1);
    llamada.enqueue(new Callback<List>() {
        @Override
        public void onResponse(Call<List> call, Response<List> response) {
if (response.isSuccessful()){
    lista.clear();
    lista.addAll(response.body());
    Message m = new Message();
    m.arg1 = CONSULTA;
    h.sendMessage(m);



}
        }

        @Override
        public void onFailure(Call<List> call, Throwable t) {
Message m = new Message();
m.arg1 = ERROR;
h.sendMessage(m);
        }
    });

}


}
