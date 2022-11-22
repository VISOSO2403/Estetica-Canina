package com.example.esteticacanina.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacanina.R;
import com.example.esteticacanina.ServiciosActivity;
import com.example.esteticacanina.model.Servicio;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ServicioAdapter extends FirestoreRecyclerAdapter<Servicio, ServicioAdapter.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ServicioAdapter(@NonNull FirestoreRecyclerOptions<Servicio> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Servicio Servicio) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.txtnomserv.setText(Servicio.getNombre());
        viewHolder.txtprecio.setText("$" + Servicio.getPrecio());

        viewHolder.vermas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, ServiciosActivity.class);
                i.putExtra("id_servicio",id);
                activity.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_serv_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnomserv, txtprecio;
        Button vermas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vermas = itemView.findViewById(R.id.btnvermas);
            txtnomserv = itemView.findViewById(R.id.txtnomserv);
            txtprecio = itemView.findViewById(R.id.txtprecio);
        }
    }
}
