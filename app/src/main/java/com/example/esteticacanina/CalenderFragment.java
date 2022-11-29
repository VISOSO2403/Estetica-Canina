package com.example.esteticacanina;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class CalenderFragment extends Fragment {

    Button reporte;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calender, container, false);

        reporte = root.findViewById(R.id.btnreporte);
        reporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ReporteActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        return root;
    }
}