package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIABLES
    Button registrar, iniciar;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrar = findViewById(R.id.btnregistrar);
        iniciar = findViewById(R.id.btningresar);

        registrar.setOnClickListener(this);
        iniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnregistrar:
                i = new Intent(LoginActivity.this, SigInActivity.class);
                startActivity(i);
                break;
            case R.id.btningresar:
                i = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(i);
                break;
        }
    }
}