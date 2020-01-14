package com.dam.damtreb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
private  Button btnLeerBilletes;
private  Button btnAgregarUbicacion;
//variables
private static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeerBilletes = (Button) findViewById(R.id.btnLeerBilletes);
        btnAgregarUbicacion = (Button) findViewById(R.id.btnAgregarUbicacion);

        btnLeerBilletes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i1.resolveActivity(getPackageManager()) != null)
                startActivityForResult(i1,REQUEST_IMAGE_CAPTURE);
            }
        });
        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"en desarrollo",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
byte[] imagen = stream.toByteArray();
String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
            //String imagenString = new String(imagen);
Toast.makeText(MainActivity.this,"volvio a la pantalla",Toast.LENGTH_LONG).show();
Intent intent= new Intent(MainActivity.this, TicketActivity.class);
startActivity(intent);


        }
    }
}


