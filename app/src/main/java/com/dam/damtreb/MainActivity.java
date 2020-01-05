package com.dam.damtreb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private  Button btnLeerBilletes;
private  Button btnAgregarUbicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeerBilletes = (Button) findViewById(R.id.btnLeerBilletes);
        btnAgregarUbicacion = (Button) findViewById(R.id.btnAgregarUbicacion);

        btnLeerBilletes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"en desarrollo",Toast.LENGTH_LONG).show();

            }
        });
        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"en desarrollo",Toast.LENGTH_LONG).show();

            }
        });
    }
}


