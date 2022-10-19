package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        cancelar = (Button) findViewById(R.id.btnCancelar);

        cancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCancelar:
                Intent a = new Intent(AgendarActivity.this, MenuActivity.class);
                startActivity(a);
                //Finaliza la pantalla para no generar más memoria
                finish();
        }
    }
}