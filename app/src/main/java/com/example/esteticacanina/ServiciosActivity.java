package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiciosActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIABLES
    Button agendar, cancelar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        agendar = findViewById(R.id.btnagendar);
        cancelar = findViewById(R.id.btncancelar);

        agendar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnagendar:
                i = new Intent(ServiciosActivity.this, AgendarActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btncancelar:
                i = new Intent(ServiciosActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    // Vuelve a una pantalla anterior al proceso actual
    // mediante el boton onBack del telefono
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ServiciosActivity.this, MenuActivity.class));
        finish();
    }
}