package com.example.esteticacanina;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeFragment extends Fragment implements View.OnClickListener{
    Button vermas;
    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_home, container, false);

        vermas = (Button) vista.findViewById(R.id.btnVerMas);
        vermas.setOnClickListener(this);

        return vista;



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnVerMas:
                Intent i = new Intent(getActivity(), ServiciosActivity.class);
                startActivity(i);
        }
    }
}