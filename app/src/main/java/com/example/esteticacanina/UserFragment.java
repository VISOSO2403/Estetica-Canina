package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.esteticacanina.adapter.PetAdapter;
import com.example.esteticacanina.model.Pet;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class UserFragment extends Fragment {

    Button agremasc, cerrarSesion;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

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

        petAdapter = new PetAdapter(firestoreRecyclerOptions);
        petAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(petAdapter);

        //Variables de usuario
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //Variables de botones
        agremasc = root.findViewById(R.id.btnagrmasc);
        cerrarSesion = root.findViewById(R.id.btncerrarsesion);

        agremasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AgregarMascotaActivity.class);
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

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        petAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    //Metodo Cerrar Sesión
    private void CerrarSesion (){
        mAuth.signOut();
        Toast.makeText(getActivity(), "Sesión Cerrada", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }

}