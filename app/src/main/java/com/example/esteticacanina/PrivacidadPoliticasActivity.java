package com.example.esteticacanina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PrivacidadPoliticasActivity extends AppCompatActivity implements View.OnClickListener {
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad_politicas);

        cancelar = findViewById(R.id.btncancelar);
        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncancelar:
                startActivity(new Intent(PrivacidadPoliticasActivity.this, SigInActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SigInActivity.class));
        finish();
    }
}