package com.dam.damtreb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class AutocompleteActivity extends AppCompatActivity {
    private AutocompleteSupportFragment autocompleteSupportFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Toast.makeText(AutocompleteActivity.this,"hola estoy en el fragment, el lugar es: " + place.getName(),Toast.LENGTH_LONG).show();

                Intent data = new Intent(AutocompleteActivity.this, LocationActivity.class);
                data.putExtra("latitude", place.getLatLng().latitude);
data.putExtra("longitude", place.getLatLng().longitude);
data.putExtra("name", place.getName());
setResult(Activity.RESULT_OK, data);
finish();
            }

            @Override
            public void onError(@NonNull Status status) {
Toast.makeText(AutocompleteActivity.this,"A ocurrido un error: "+ status.getStatusMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
