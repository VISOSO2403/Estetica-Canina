package com.example.esteticacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIBLES
    Button cancelar, confirmar;
    TextView diaselect;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);
        diaselect = findViewById(R.id.etxtdiacita);

        cancelar = findViewById(R.id.btncancelar);


        cancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btncancelar:
                i = new Intent(AgendarActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(AgendarActivity.this, "Proceso cancelado", Toast.LENGTH_LONG).show();
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


    public void abrirCalendario(View view) {
    Calendar  cal = Calendar.getInstance();
    int anio = cal.get(Calendar.YEAR);
    int mes = cal.get(Calendar.MONTH);
    int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(AgendarActivity.this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month + "/" + year;
                diaselect.setText(fecha);
            }
        }, anio, mes, dia); dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dpd.show();
    }
}

