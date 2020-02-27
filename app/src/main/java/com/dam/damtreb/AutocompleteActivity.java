package com.dam.damtreb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dam.damtreb.dao.LocationRepository;
import com.dam.damtreb.domain.ResponsejSon;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import  com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class AutocompleteActivity extends AppCompatActivity {
    //flags
    private  static  final  int CODIGO_REQUEST = 1;
    //widget
    //private AutocompleteSupportFragment autocompleteFragment;
private EditText etNombreDestino;
private  Button btnBuscar;
    private Button btnFavorito;
    private Spinner spinner;
    private ArrayAdapter adapter;
//variables
private  String nombre;
private List<ResponsejSon> lista;
private  String[] nombres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        /*
        String apyKey = getString(R.string.clave_apy_5);

        if (!Places.isInitialized()){
        Places.initialize(getApplicationContext(),apyKey);
        }
// Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);




autocompleteFragment =                 (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);


//assert  autocompleteFragment != null;
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS));

        //autocompleteFragment.setTypeFilter(TypeFilter.ADDRESS);
autocompleteFragment.setCountry("AR");

        autocompleteFragment.setOnPlaceSelectedListener(placeSelectionListener);
todo lo de places se comenta ya que trabajo con openStreepMaps
*/
etNombreDestino = (EditText) findViewById(R.id.etDestino);
btnBuscar = (Button) findViewById(R.id.btnBuscar);
btnFavorito = (Button) findViewById(R.id.btnSeleccionarFav);
spinner = (Spinner) findViewById(R.id.spinerAutocomplete);

btnBuscar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        nombre = etNombreDestino.getText().toString();
        nombres = new  String[6];
        LocationRepository.getInstans().buscar(nombre, myHandler);

    }
});
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
    /*
    esto tambien esta comentado por ser de apy places
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
*/

    Handler myHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
        switch (msg.arg1){
            case  LocationRepository.CONSULTA:
                lista = LocationRepository.getInstans().getLista();
int i = 0;
                for (ResponsejSon rj: lista){
                    nombres[i] = rj.getName();
                    i++;
                    if (i == 6){
                        break;
                    }
                }
                adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, nombres);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

i =                spinner.getSelectedItemPosition();

ResponsejSon responsejSon = lista.get(i);
Double lat = Double.parseDouble(responsejSon.getLat());
Double lng = Double.parseDouble(responsejSon.getLon());

Intent intent = new Intent(AutocompleteActivity.this, LocationActivity.class);

intent.putExtra("latitude",lat);
intent.putExtra("longitude",lng);
intent.putExtra("name",responsejSon.getName());
setResult(RESULT_OK,intent);
finish();

                break;
            case  LocationRepository.ERROR:
                Toast.makeText(AutocompleteActivity.this,"No se encontraron ubicaciones con ese nombre", Toast.LENGTH_LONG).show();
                break;
        }
        }
    };
}

