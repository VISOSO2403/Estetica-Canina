package com.example.esteticacanina.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.esteticacanina.AgendarActivity;
import com.example.esteticacanina.R;
import com.example.esteticacanina.ServiciosActivity;
import com.example.esteticacanina.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserAdapter extends FirestoreRecyclerAdapter<User, UserAdapter.ViewHolder> {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UserAdapter(@NonNull FirestoreRecyclerOptions<User> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position, @NonNull User User) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        String id = documentSnapshot.getId();

        holder.nom.setText(User.getNombre());
        holder.agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, AgendarActivity.class);
                i.putExtra("id_users",id);
                activity.startActivity(i);
            }
        });
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_agendar, parent, false);
        return new UserAdapter.ViewHolder(v);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        Button agendar;
        EditText nom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            agendar = itemView.findViewById(R.id.btnagendar);
            nom = itemView.findViewById(R.id.etxtnom);

        }
    }
}
