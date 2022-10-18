package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ServiciosActivity extends AppCompatActivity implements View.OnClickListener {
    Button agendar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        agendar = (Button) findViewById(R.id.btnAgendar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        agendar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAgendar:
                Intent i = new Intent(ServiciosActivity.this, AgendarActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btnCancelar:
                Intent a = new Intent(ServiciosActivity.this, MenuActivity.class);
                startActivity(a);
                //Finaliza la pantalla para no generar más memoria
                finish();
                break;

                //Me gusta, pero checa eso de finalizar un proceso
                //Para no generar memoria y esas pantallas dobles
            
        }

    }
}