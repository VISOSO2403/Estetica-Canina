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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AgendarActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIBLES
    TextView nom, nomservi;
    EditText spnrselecmasc,etxttel;
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


        String valor = getIntent().getStringExtra( "nomser");
        String id = getIntent().getStringExtra("id_pet");

        diaselect = findViewById(R.id.etxtdiacita);
        confirmar = (Button)findViewById(R.id.btnagendar);
        confirmar.setOnClickListener(this);
        cancelar = findViewById(R.id.btncancelar);
        cancelar.setOnClickListener(this);

        //esto es para jalar datos
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        usuario = firebaseAuth.getCurrentUser().getUid();

        getUser();
        //getPet(id);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Cnombre = nom.getText().toString().trim();
                String Cnumero = etxttel.getText().toString().trim();
                String Cnompet = spnrselecmasc.getText().toString().trim();
                String Cservic = nomservi.getText().toString().trim();
                String Cfecha = diaselect.getText().toString();

                if(Cnumero.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else if (Cnompet.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else if(Cfecha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else {
                    postCitas(Cnombre,Cnumero,Cnompet,Cservic,Cfecha);
                }
            }
        });

    }

    private void postCitas(String cnombre, String cnumero, String cnompet, String cservic, String cfecha) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", cnombre);
        map.put("numero", cnumero);
        map.put("mascota", cnompet);
        map.put("servicio", cservic);
        map.put("fecha", cfecha);


        db.collection("citas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Creado Exitosamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AgendarActivity.this, MenuActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al ingresar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
/*
    private void getPet(String id) {
        db.collection("pet").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombremasc = documentSnapshot.getString("Nombre");

                nom.setText("Nombre: " + nombremasc);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

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
    int mes = cal.get(Calendar.MONTH)+1;
    int dia = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(AgendarActivity.this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + month+ "/" + year;
                diaselect.setText(fecha);
            }
        }, anio, mes, dia); dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
        dpd.show();
    }
}

