package com.example.esteticacanina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarMascotaActivity extends AppCompatActivity {

    Button cancelar, agregar;
    EditText nombre, peso, edad;
    RadioButton perro, gato, macho, hembra, mini, pequeno, mediano, grande, enorme;
    String tipo, nom, sex, pes, eda, tamano;

    private FirebaseFirestore firebaseFirestore;
    //para vereficar el pet es del usuario
    private FirebaseAuth firebaseAuth;
    private String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_mascota);

        String id = getIntent().getStringExtra("id_pet");

        //FirebaseFireStore
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();//para colaborar usuario
        usuario = firebaseAuth.getCurrentUser().getUid();//coloborar usuario

        //EditText
        nombre = findViewById(R.id.etxtnombremasc);
        peso = findViewById(R.id.etxtpeso);
        edad = findViewById(R.id.etxtedad);

        //RadioButtons
        perro = findViewById(R.id.rbtnperro);
        gato = findViewById(R.id.rbntgato);
        macho = findViewById(R.id.rbtnmacho);
        hembra = findViewById(R.id.rbtnhembra);
        mini = findViewById(R.id.rbtnmini);
        pequeno = findViewById(R.id.rbtnpequeno);
        mediano = findViewById(R.id.rbtnmediano);
        grande = findViewById(R.id.rbtngrande);
        enorme = findViewById(R.id.rbtnenorme);

        //Buttons
        cancelar = findViewById(R.id.btncancagrmasc);
        agregar = findViewById(R.id.btnagremasc);

        if (id == null || id == "") {
            agregar.setOnClickListener(new View.OnClickListener() {
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

                    if (mini.isChecked() == true) {
                        tamano = "Mini";
                    } else if (pequeno.isChecked() == true) {
                        tamano = "Pequeño";
                    } else if (mediano.isChecked() == true) {
                        tamano = "Mediano";
                    } else if (grande.isChecked() == true) {
                        tamano = "Grande";
                    } else if (enorme.isChecked() == true) {
                        tamano = "Enorme";
                    } else {
                        tamano = "";
                    }

                    if (nom.isEmpty() || sex.equals("") || pes.isEmpty()
                            || eda.isEmpty() || tipo.equals("") || tamano.equals("")) {
                        Toast.makeText(AgregarMascotaActivity.this, "Debes llenar todos los campos",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        postPet(nom, eda, pes, sex, tipo, tamano);
                        Toast.makeText(AgregarMascotaActivity.this, "Mascota agregada", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            agregar.setText("Actualizar");
            getPet(id);
            agregar.setOnClickListener(new View.OnClickListener() {
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

                    if (mini.isChecked() == true) {
                        tamano = "Mini";
                    } else if (pequeno.isChecked() == true) {
                        tamano = "Pequeño";
                    } else if (mediano.isChecked() == true) {
                        tamano = "Mediano";
                    } else if (grande.isChecked() == true) {
                        tamano = "Grande";
                    } else if (enorme.isChecked() == true) {
                        tamano = "Enorme";
                    } else {
                        tamano = "";
                    }

                    if (nom.isEmpty() || sex.equals("") || pes.isEmpty()
                            || eda.isEmpty() || tipo.equals("") || tamano.equals("")) {
                        Toast.makeText(AgregarMascotaActivity.this, "Debes llenar todos los campos",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
                        updatePet(nom, eda, pes, sex, tipo, tamano, id);
                        Toast.makeText(AgregarMascotaActivity.this, "Datos actualizados",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AgregarMascotaActivity.this, MenuActivity.class));
                finish();
            }
        });
    }

    private void updatePet(String nom, String eda, String pes, String sex, String tipo, String tamano, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nom);
        map.put("Edad", eda);
        map.put("Peso", pes);
        map.put("Sexo", sex);
        map.put("Tipo", tipo);
        map.put("Tamano", tamano);

        firebaseFirestore.collection("pet").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                startActivity(new Intent(AgregarMascotaActivity.this, MenuActivity.class));
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarMascotaActivity.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postPet(String nom, String eda, String pes, String sex, String tipo, String tamano) {
        usuario = firebaseAuth.getCurrentUser().getUid();//agregar id del user al pet
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nom);
        map.put("Edad", eda);
        map.put("Peso", pes);
        map.put("Sexo", sex);
        map.put("Tipo", tipo);
        map.put("Tamano",tamano);
        map.put("uid", usuario);

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

    private void getPet(String id) {
        firebaseFirestore.collection("pet").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nom = documentSnapshot.getString("Nombre");
                String eda = documentSnapshot.getString("Edad");
                String pes = documentSnapshot.getString("Peso");
                String sex = documentSnapshot.getString("Sexo");
                String tam = documentSnapshot.getString("Tamano");
                String tip = documentSnapshot.getString("Tipo");

                nombre.setText(nom);
                edad.setText(eda);
                peso.setText(pes);

                if (sex.equals("Hembra")) {
                    hembra.setChecked(true);
                }else {
                    macho.setChecked(true);
                }

                if (tip.equals("Perro")) {
                    perro.setChecked(true);
                }else{
                    gato.setChecked(true);
                }

                if (tam.equals("Mini")) {
                    mini.setChecked(true);
                } else if (tam.equals("Pequeño")) {
                    pequeno.setChecked(true);
                } else if (tam.equals("Mediano")) {
                    mediano.setChecked(true);
                } else if (tam.equals("Grande")) {
                    grande.setChecked(true);
                } else {
                    enorme.setChecked(true);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AgregarMascotaActivity.this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AgregarMascotaActivity.this, MenuActivity.class));
    }
}