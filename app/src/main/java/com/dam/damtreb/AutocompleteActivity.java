package com.dam.damtreb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import  com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AutocompleteActivity extends AppCompatActivity {
    //flags
    private  static  final  int CODIGO_REQUEST = 1;
    //variables
    private AutocompleteSupportFragment autocompleteFragment;
private Button btnFavorito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        String apyKey = getString(R.string.clave_apy_5);

        if (!Places.isInitialized()){
        Places.initialize(getApplicationContext(),apyKey);
        }
// Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);

btnFavorito = (Button) findViewById(R.id.btnSeleccionarFav);



autocompleteFragment =                 (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);


//assert  autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        //autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);
autocompleteFragment.setCountry("AR");

        autocompleteFragment.setOnPlaceSelectedListener(placeSelectionListener);

btnFavorito.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(AutocompleteActivity.this,ListaLocationsActivity.class);
startActivityForResult(intent,CODIGO_REQUEST);

    }
});
    }

    @Override
            protected   void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        switch (requestCode){
            case  CODIGO_REQUEST:
            if (resultCode == RESULT_OK && data != null)
        //Toast.makeText(AutocompleteActivity.this,"nombre: "+ data.getStringExtra("name")+ " , la latitud es: " + data.getDoubleExtra("latitude", 0.0), Toast.LENGTH_LONG).show();
                setResult(resultCode, data);
            finish();
        }
    } //cierra onActivityResult
    PlaceSelectionListener placeSelectionListener = new PlaceSelectionListener() {
        @Override
        public void onPlaceSelected(@NonNull Place place) {

            Toast.makeText(AutocompleteActivity.this, "place: " + place.getName(), Toast.LENGTH_LONG).show();
            Intent data = new Intent(AutocompleteActivity.this, LocationActivity.class);
            data.putExtra("latitude", place.getLatLng().latitude);
            data.putExtra("longitude", place.getLatLng().longitude);
            data.putExtra("name", place.getName());
            setResult(Activity.RESULT_OK, data);
            finish();

        }

        @Override
        public void onError(@NonNull Status status) {
            Log.i("damTreb", "onError: " + status.getStatusMessage());

        }
    };

}

