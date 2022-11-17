package com.example.esteticacanina.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacanina.R;
import com.example.esteticacanina.model.Servicio;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ServicioAdapter extends FirestoreRecyclerAdapter<Servicio, ServicioAdapter.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ServicioAdapter(@NonNull FirestoreRecyclerOptions<Servicio> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Servicio Servicio) {
        viewHolder.txtnomserv.setText(Servicio.getNombre());
        viewHolder.txtprecio.setText("$"+Servicio.getPrecio());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_serv_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtnomserv, txtprecio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtnomserv = itemView.findViewById(R.id.txtnomserv);
            txtprecio = itemView.findViewById(R.id.txtprecio);
        }
    }
}
