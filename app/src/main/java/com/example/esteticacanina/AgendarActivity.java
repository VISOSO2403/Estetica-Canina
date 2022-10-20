package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIBLES
    Button cancelar, confirmar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        cancelar = findViewById(R.id.btnCancelar);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancelar:
                i = new Intent(AgendarActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(AgendarActivity.this, "Poceso cancelado", Toast.LENGTH_LONG).show();
                break;
        }
    }
    
    // Vuelve a una pantalla anterior al proceso actual
    // mediante el boton onBack del telefono
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AgendarActivity.this, ServiciosActivity.class));
        finish();
    }
}