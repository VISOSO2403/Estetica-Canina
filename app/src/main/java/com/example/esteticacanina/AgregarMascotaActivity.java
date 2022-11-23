package com.example.esteticacanina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarMascotaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button cancelar, agregar;
    EditText nombre, peso, edad;
    RadioButton perro, gato, macho, hembra;
    String tipo, nom, sex, pes, eda;
    Spinner tamaniop;
    String tamano = "";



    private FirebaseFirestore firebaseFirestore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        //FirebaseFireStore
        firebaseFirestore = FirebaseFirestore.getInstance();

        //EditText
        nombre = findViewById(R.id.etxtnombremasc);

        peso = findViewById(R.id.etxtpeso);
        edad = findViewById(R.id.etxtedad);

        //RadioButtons
        perro = findViewById(R.id.rbtnperro);
        gato = findViewById(R.id.rbntgato);
        macho = findViewById(R.id.rbtnmacho);
        hembra = findViewById(R.id.rbtnhembra);

        //Spinners
        tamaniop = findViewById(R.id.spnrtamanio);


        //lista de tamanos
        List<String> tamanos = new ArrayList<String>();
        tamanos.add("Mini");
        tamanos.add("Pequeño");
        tamanos.add("Mediano");
        tamanos.add("Grande");
        tamanos.add("Enorme");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, tamanos);

        tamaniop.setAdapter(adapter);
        tamaniop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                tamano = tamanos.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //Buttons
        agregar = findViewById(R.id.btnagremasc);
        cancelar = findViewById(R.id.btncanc);
        cancelar.setOnClickListener(this);
        agregar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        nom = nombre.getText().toString().trim();
        eda = edad.getText().toString().trim();
        pes = peso.getText().toString().trim();

        if (perro.isChecked() == true){
            tipo = "Perro";
        }
        else if (gato.isChecked() == true){
            tipo = "Gato";
        }

        if(hembra.isChecked() == true) {
            sex = "Hembra";
        }
        else if (macho.isChecked() == true){
            sex = "Macho";
        }

        switch (view.getId()) {
            case R.id.btnagremasc:
                if (nom.isEmpty() || sex.equals("") || pes.isEmpty() || eda.isEmpty() || tipo.equals("")) {
                    Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
                else {
                    postPet(nom, eda, pes, sex, tipo);
                    Toast.makeText(this, "Mascota agregada", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btncanc:
                startActivity(new Intent(AgregarMascotaActivity.this, MenuActivity.class));
                finish();
                Toast.makeText(AgregarMascotaActivity.this, "Proceso cancelado", Toast.LENGTH_LONG).show();
                break;
        }
    }

    private void postPet(String nom, String eda, String pes, String sex, String tipo) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nom);
        map.put("Edad", eda);
        map.put("Peso", pes + "kg");
        map.put("Sexo", sex);
        map.put("Tipo", tipo);
        map.put("Tamano",tamano);

        firebaseFirestore.collection("pet").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AgregarMascotaActivity.this, "Mascota guardada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AgregarMascotaActivity.this, MenuActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarMascotaActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}