package com.example.esteticacanina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIBLES
    TextView nom;
    Button cancelar, confirmar;
    TextView diaselect;
    Intent i;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        String id = getIntent().getStringExtra("id_users");
        //mfirestore = FirebaseFirestore.getInstance();
        //Query query = mfirestore.collection("users");

        diaselect = findViewById(R.id.etxtdiacita);
        cancelar = findViewById(R.id.btncancelar);
        cancelar.setOnClickListener(this);
        nom = findViewById(R.id.etxtnom);

        //esto es para jalar datos
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        usuario = firebaseAuth.getCurrentUser().getUid();

        getUser();
    }
    private void getUser() {
        db.collection("users").document(usuario).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //String Nombre = ""+ snapshot.child("Nombre").getValue();
                String Nombre = documentSnapshot.getString("Nombre");
                String apellido = documentSnapshot.getString("Apellido");
                nom.setText(Nombre +" "+ apellido );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
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
                String fecha = dayOfMonth + "/" + month+1 + "/" + year;
                diaselect.setText(fecha);
            }
        }, anio, mes, dia); dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dpd.show();
    }
}

