package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Serv_single extends AppCompatActivity implements View.OnClickListener {

    Button vermas;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_single);

        vermas = findViewById(R.id.btnvermas);

        vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent i = new Intent(getActivity(), ServiciosActivity.class);
                Intent i = new Intent(Serv_single.this, LoginActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}