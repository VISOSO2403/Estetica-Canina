package com.example.esteticacanina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MascotaActivity extends AppCompatActivity implements View.OnClickListener {

    Button canc, elimniar, editar;
    TextView nombre, edad, peso, tipo, tamanio, sexo;

    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota);

        String id = getIntent().getStringExtra("id_pet");
        firebaseFirestore = FirebaseFirestore.getInstance();

        //TextView
        nombre = findViewById(R.id.txtnompet);
        edad = findViewById(R.id.txtedadpet);
        peso = findViewById(R.id.txtpesomasc);
        tipo = findViewById(R.id.txttipomasc);
        tamanio = findViewById(R.id.txttammasc);
        sexo = findViewById(R.id.txtsexmasc);

        canc = findViewById(R.id.btncanc);
        canc.setOnClickListener(this);

        getPet(id);
    }

    private void getPet(String id) {
        firebaseFirestore.collection("pet").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombremasc = documentSnapshot.getString("Nombre");
                String edadmasc = documentSnapshot.getString("Edad");
                String pesomasc = documentSnapshot.getString("Peso");
                String sexomasc = documentSnapshot.getString("Sexo");
                String tipomasc = documentSnapshot.getString("Tipo");
                String tamanomasc = documentSnapshot.getString("Tamano");

                nombre.setText("Nombre: " + nombremasc);
                edad.setText("Edad: " + edadmasc);
                peso.setText("Peso: " + pesomasc);
                sexo.setText("Sexo: " + sexomasc);
                tipo.setText("Tipo: " + tipomasc);
                tamanio.setText("Tamaño: " + tamanomasc);

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
            case R.id.btncanc:
                startActivity(new Intent(MascotaActivity.this, MenuActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }
}
