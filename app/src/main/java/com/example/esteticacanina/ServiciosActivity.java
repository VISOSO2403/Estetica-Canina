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

        agendar = findViewById(R.id.btnAgendar);
        cancelar = findViewById(R.id.btnCancelar);

        agendar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAgendar:
                i = new Intent(ServiciosActivity.this, AgendarActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnCancelar:
                i = new Intent(ServiciosActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}