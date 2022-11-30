package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacidadPoliticas2Activity extends AppCompatActivity implements View.OnClickListener {
    Button cancelar;
    Intent i;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad_politicas2);

        cancelar = findViewById(R.id.btncancelar);


        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncancelar:
                i = new Intent(PrivacidadPoliticas2Activity.this, MenuActivity.class);
                startActivity(i);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}