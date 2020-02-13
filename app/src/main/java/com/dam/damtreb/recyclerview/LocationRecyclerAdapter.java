package com.dam.damtreb.recyclerview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.damtreb.AutocompleteActivity;
import com.dam.damtreb.ListaLocationsActivity;
import com.dam.damtreb.LocationActivity;
import com.dam.damtreb.R;
import com.dam.damtreb.dao.LocationDao;
import com.dam.damtreb.dao.RepositoryDataBase;
import com.dam.damtreb.domain.Location;
import  com.dam.damtreb.ListaLocationsActivity;
import  com.dam.damtreb.ListaLocationsActivity.DeleteTast;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

public class LocationRecyclerAdapter extends RecyclerView.Adapter<LocationRecyclerAdapter.LocationViewHolder> {
private List<Location> locations = new ArrayList<>();
private Context miContexto;
private  static  final  int RESULTADO = 1;
public  LocationRecyclerAdapter(List<Location> locations, Context context) {
this.locations = locations;
this.miContexto = context;
}

    @Override
    public void onBindViewHolder(@NonNull LocationRecyclerAdapter.LocationViewHolder holder, int position) {
final Location location = this.locations.get(position);
holder.tvTitulo.setText(location.getName());
holder.btnSeleccionar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent data = new Intent(miContexto, AutocompleteActivity.class);
        data.putExtra("name", location.getName());
        data.putExtra("latitude", location.getLatitude());
        data.putExtra("longitude", location.getLongitude());
        ((Activity) miContexto).setResult(Activity.RESULT_OK, data);
        ((Activity) miContexto).finish();
    }
});
holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(miContexto,"En desarrollo",Toast.LENGTH_LONG).show();
     }
});
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @NonNull
    @Override
    public LocationRecyclerAdapter.LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_location, parent, false);
        LocationViewHolder lh = new  LocationViewHolder(v);

        return lh;

    }


    public  class  LocationViewHolder extends  RecyclerView.ViewHolder{
    TextView tvTitulo;
    Button btnSeleccionar;
Button btnEliminar;
public  LocationViewHolder(View base){
    super(base);
    this.tvTitulo = (TextView) base.findViewById(R.id.tvNombreFila);
    this.btnSeleccionar = (Button) base.findViewById(R.id.btnSeleccionarLocationFila);
this.btnEliminar = (Button) base.findViewById(R.id.btnEliminarLocationFila);
}
    } //cierra clase LocationViewHolder


    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}

