package com.example.esteticacanina;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    //VARIABLES
    Button vermas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_home, container, false);

       vermas = root.findViewById(R.id.btnVerMas);

       vermas.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent()
           }
        });

        return root;
    }
}