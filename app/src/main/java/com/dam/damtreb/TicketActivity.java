package com.dam.damtreb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicketActivity extends AppCompatActivity {
private TextView tvBillete;
private Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        tvBillete= (TextView) findViewById(R.id.tvBillete);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        tvBillete.setText("el billete es: 5 pesos");
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TicketActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
