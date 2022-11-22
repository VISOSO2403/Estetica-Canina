package com.example.esteticacanina;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserFragment extends Fragment {

    Button agremasc, cerrarSesion, vermasc;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        //Variables de usuario
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();

        //Variables de botones
        vermasc = root.findViewById(R.id.btnavermasc);
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

        vermasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MascotaActivity.class);
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

    //Metodo Cerrar Sesión
    private void CerrarSesion (){
        mAuth.signOut();
        Toast.makeText(getActivity(), "Sesión Cerrada", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(),LoginActivity.class));
    }

}