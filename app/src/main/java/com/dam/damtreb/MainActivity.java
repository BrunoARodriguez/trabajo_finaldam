package com.dam.damtreb;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dam.damtreb.dao.RepositoryDataBase;
import com.dam.damtreb.dao.TicketDao;
import com.dam.damtreb.domain.Ticket;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private Button btnLeerBilletes;
    private Button btnAgregarUbicacion;
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
                Intent i1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(i1, REQUEST_IMAGE_CAPTURE);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, 9999);
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        startActivityForResult(i1, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });
        btnAgregarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(i2);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] imagen = stream.toByteArray();
            String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
            //String imagenString = new String(imagen);
//Toast.makeText(MainActivity.this,"volvio a la pantalla",Toast.LENGTH_LONG).show();
           Ticket villete = new Ticket(imagenString,0);
SaveTicket tast = new SaveTicket();
tast.execute(villete);


        }
    }

    //tarea de guardar

    public  class  SaveTicket extends AsyncTask<Ticket,Void,Void>{


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(MainActivity.this, TicketActivity.class);
            startActivity(intent);

        }

        @Override
        protected Void doInBackground(Ticket... tickets) {
            TicketDao dao = RepositoryDataBase.getInstance(MainActivity.this).getMyDataBase().ticketDao();
            if (tickets[0].getId() != null){
                dao.actualizarTicket(tickets[0]);
            }
            else {
                dao.insertarTicket(tickets[0]);
            }

            return null;
        }
    } //cierra tarea
}


