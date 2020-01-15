package com.dam.damtreb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dam.damtreb.domain.Location;

public class LocationActivity extends AppCompatActivity {
private EditText etNombre;
private Button btnCrear;
private  Button btnAgregarFavorito;
    //variables
    private Location location;
    private  String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    etNombre = (EditText) findViewById(R.id.etNombre);
    btnCrear = (Button) findViewById(R.id.btnCrearLocation);
    btnAgregarFavorito = (Button) findViewById(R.id.btnAgregarFav);

btnCrear.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
nombre = etNombre.getText().toString();
location = new Location(nombre,0.0,0.0);


    }
});
    }
}
