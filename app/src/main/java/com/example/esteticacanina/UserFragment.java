package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class UserFragment extends Fragment {

    Button agremasc;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);


        agremasc = root.findViewById(R.id.btnagrmasc);

        agremasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AgregarMascotaActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return root;
    }

}