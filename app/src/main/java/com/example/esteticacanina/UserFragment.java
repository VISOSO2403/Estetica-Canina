package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacanina.adapter.PetAdapter;
import com.example.esteticacanina.model.Pet;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class UserFragment extends Fragment {

    Button agremasc, cerrarSesion, acercaDe, priPoli;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView user;

    String usuario;
    RecyclerView recyclerView;
    PetAdapter petAdapter;
    FirebaseFirestore firebaseFirestore;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        //Conexion a la base de datos
        firebaseFirestore = FirebaseFirestore.getInstance();


        recyclerView = root.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query query = firebaseFirestore.collection("pet");

        FirestoreRecyclerOptions<Pet> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Pet>().setQuery(query, Pet.class).build();
        petAdapter = new PetAdapter(firestoreRecyclerOptions, getActivity());
        petAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(petAdapter);

        //Variables de usuario
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        usuario = mAuth.getCurrentUser().getUid();

        //Variables de botones
        agremasc = root.findViewById(R.id.btnagrmasc);
        cerrarSesion = root.findViewById(R.id.btncerrarsesion);
        acercaDe = root.findViewById(R.id.btnacerde);
        priPoli = root.findViewById(R.id.btnpripoli);

        //Variables de Textview
        user = root.findViewById(R.id.txtnomusu);

        agremasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AgregarMascotaActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        acercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AcercaDeActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        priPoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PrivacidadPoliticas2Activity.class);
                startActivity(i);
                getActivity().finish();
            }
        });


        //Acción de Botón Cerrar Sesión
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion ();
            }
        });

        //funcion para que los datos del usuario se impriman
        getUser();
        return root;
    }

    private void getUser() {
        firebaseFirestore.collection("users").document(usuario).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //String Nombre = ""+ snapshot.child("Nombre").getValue();
                String Nombre = documentSnapshot.getString("Nombre");
                String apellido = documentSnapshot.getString("Apellido");
                user.setText(Nombre +" "+ apellido );
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        petAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        petAdapter.stopListening();
    }

    //Metodo Cerrar Sesión
    private void CerrarSesion (){
        mAuth.signOut();
        Toast.makeText(getActivity(), "Sesión Cerrada", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }

}