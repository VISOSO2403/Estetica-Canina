package com.example.esteticacanina;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FormAgendarFragment extends Fragment implements View.OnClickListener{
    EditText diaCita;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_agendar, container, false);
    }

    @Override
    public void onClick(View view) {

    }
}