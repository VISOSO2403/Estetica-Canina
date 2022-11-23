package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MascotaActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancelar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        cancelar = findViewById(R.id.btncanc);

        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncanc:
                i = new Intent(MascotaActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                break;
        }

    }
}
