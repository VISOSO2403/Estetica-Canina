package com.example.esteticacanina.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacanina.MascotaActivity;
import com.example.esteticacanina.R;
import com.example.esteticacanina.model.Pet;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PetAdapter extends FirestoreRecyclerAdapter<Pet, PetAdapter.ViewHolder> {

    //private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PetAdapter(@NonNull FirestoreRecyclerOptions<Pet> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position, @NonNull Pet Pet) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.nombre.setText(Pet.getNombre());
        holder.sexo.setText(Pet.getSexo());
        holder.peso.setText(Pet.getPeso());
        holder.vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, MascotaActivity.class);
                i.putExtra("id_pet", id);
                activity.startActivity(i);
                activity.finish();
            }
        });

    }


    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pet_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //VARIABLES A RELLENAR
        TextView nombre, sexo, peso;
        Button vermas, btneliminar, btneditar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtnompet);
            sexo = itemView.findViewById(R.id.txtsexo);
            peso = itemView.findViewById(R.id.txtpeso);
            vermas = itemView.findViewById(R.id.btnvermas);

        }
    }
}
