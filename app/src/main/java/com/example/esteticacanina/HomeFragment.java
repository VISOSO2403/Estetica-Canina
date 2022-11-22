package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.esteticacanina.adapter.ServicioAdapter;
import com.example.esteticacanina.model.Servicio;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class HomeFragment extends Fragment {

    RecyclerView mRecycler;
    ServicioAdapter mAdapter;
    FirebaseFirestore mFirestore;
    //Button vermas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = root.findViewById(R.id.recycle);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query query = mFirestore.collection("servicios");

        FirestoreRecyclerOptions<Servicio> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Servicio>().setQuery(query,Servicio.class).build();
        mAdapter = new ServicioAdapter(firestoreRecyclerOptions, getActivity());
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        /*
        vermas = root.findViewById(R.id.btnvermas);

        vermas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getActivity(), ServiciosActivity.class);
               startActivity(i);
               getActivity().finish();
           }
        });*/

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}