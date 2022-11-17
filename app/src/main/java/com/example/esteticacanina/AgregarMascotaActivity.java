package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AgregarMascotaActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancelar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        cancelar = findViewById(R.id.btncancagrmasc);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncancagrmasc:
                i = new Intent(AgregarMascotaActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(AgregarMascotaActivity.this, "Proceso cancelado", Toast.LENGTH_LONG).show();
                break;
        }
    }

}