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

public class ServiciosActivity extends AppCompatActivity implements View.OnClickListener {

    //VARIABLES
    TextView txtnomserv, txtinfoserv, txtcosto;
    Button agendar, cancelar;
    Intent i;

    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        String id = getIntent().getStringExtra("id_servicio");
        mfirestore = FirebaseFirestore.getInstance();

        txtnomserv = (TextView) findViewById(R.id.txtnomserv);
        txtinfoserv = (TextView)findViewById(R.id.txtinfoserv);
        txtcosto = (TextView)findViewById(R.id.txtcosto);

        agendar = findViewById(R.id.btnagendar);
        cancelar = findViewById(R.id.btncancelar);

        agendar.setOnClickListener(this);
        cancelar.setOnClickListener(this);
        getServicio(id);
    }

    private void getServicio(String id) {
        mfirestore.collection("servicios").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nombreserv = documentSnapshot.getString("nombre");
                String descripserv = documentSnapshot.getString("descripcion");
                String precioserv = documentSnapshot.getString("precio");

                txtnomserv.setText("Servicio: "+nombreserv);
                txtinfoserv.setText("Descripcion: "+descripserv);
                txtcosto.setText("$"+precioserv);
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
        switch (view.getId()){
            case R.id.btnagendar:
                i = new Intent(ServiciosActivity.this, AgendarActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.btncancelar:
                i = new Intent(ServiciosActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    // Vuelve a una pantalla anterior al proceso actual
    // mediante el boton onBack del telefono
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ServiciosActivity.this, MenuActivity.class));
        finish();
    }
}