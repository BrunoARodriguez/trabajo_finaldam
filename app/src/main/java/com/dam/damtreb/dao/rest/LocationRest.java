package com.dam.damtreb.dao.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocationRest {

@GET("search/")
        Call<List> getLocations(@Query("q_like") String nombre,
                                @Query("format_like") String formato,
                                @Query("poligon") Integer poligono,
                                @Query("addressdetails") Integer detalles);


}
