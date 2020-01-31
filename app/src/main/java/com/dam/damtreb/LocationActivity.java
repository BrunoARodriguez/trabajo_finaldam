package com.dam.damtreb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dam.damtreb.dao.LocationDao;
import com.dam.damtreb.dao.RepositoryDataBase;
import com.dam.damtreb.domain.Location;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;

import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity {
    //flags
    private  static  final int AUTO_COMPLETE_REQUEST = 1;
    private  static  final int MY_LOCATION_REQUEST = 0;
    //widget
    private Button btnMyLocation;
    private Button btnLocationDest;
    private Button btnAgregarFavorito;
    //variables
    private Location locationStart;
    private Location locationFinish;
    private String nombre;
private Double lat;
private  Double lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        btnMyLocation = (Button) findViewById(R.id.btnMyLocation);
        btnLocationDest = (Button) findViewById(R.id.btnLocationDest);
        btnAgregarFavorito = (Button) findViewById(R.id.btnAgregarFav);



        btnMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LocationActivity.this, MapsActivity.class);
                startActivityForResult(intent, MY_LOCATION_REQUEST);
            }
        });
        btnLocationDest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
btnAgregarFavorito.setVisibility(View.VISIBLE);

Intent intent = new Intent(LocationActivity.this, AutocompleteActivity.class);
startActivityForResult(intent, AUTO_COMPLETE_REQUEST);

            }
        });
        btnAgregarFavorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveLocation tareaLocation = new SaveLocation();
                tareaLocation.execute(locationFinish);

            }
        });
    }

@Override
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode) {
    case  AUTO_COMPLETE_REQUEST:
    if (resultCode == RESULT_OK && data != null) {
         lat = data.getDoubleExtra("latitude", 0.0);
         lng = data.getDoubleExtra("longitude", 0.0);
        nombre = data.getStringExtra("name");

        locationFinish = new Location(nombre, lng, lat);

    }
else  {
    Toast.makeText(LocationActivity.this,"Error: no se seleccionó el lugar destino", Toast.LENGTH_LONG).show();

    }
    break;
    case  MY_LOCATION_REQUEST:

        if (resultCode == RESULT_OK){
lat = data.getDoubleExtra("latitude", 0.0);
lng = data.getDoubleExtra("longitude", 0.0);
nombre = "Ubicación actual";
Toast.makeText(LocationActivity.this,"nombre: "+ nombre + ", la latitud: "+ lat, Toast.LENGTH_LONG).show();
locationStart = new Location(nombre,lng,lat);


}
        break;
}
    }


    class SaveLocation extends AsyncTask<Location, Void, Void> {

        @Override
        protected Void doInBackground(Location... locations) {
            LocationDao dao = RepositoryDataBase.getInstance(LocationActivity.this)
                    .getMyDataBase().locationDao();
            if (locations[0].getId() != null) {
                dao.actualizarLocation(locations[0]);
            } else {
                dao.insertarLocation(locations[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(LocationActivity.this, "Se guardó la ubicación en favoritos", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LocationActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }
}

