package com.example.esteticacanina;

import static android.util.Patterns.EMAIL_ADDRESS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SigInActivity extends AppCompatActivity{
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[a-zA-Z])" +      //para cualquier letra
                    "(?=.*[@#$%^&+=])" +    //un simbolo
                    "(?=\\S+$)" +           //sin espacios
                    ".{4,}" +               //hasta 4 caracteres
                    "$");
    private EditText nombre, apellido, email, contraseña;
    private Button ingresar, inicio, terminos;
    CheckBox termCond;
    private Intent i;

    private String usuario;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);

        nombre = findViewById(R.id.etxtnombre);
        apellido = findViewById(R.id.etxtapellido);
        email = findViewById(R.id.etxtemail);
        contraseña = findViewById(R.id.etxtpassword);
        ingresar = findViewById(R.id.btnregistrarse);
        inicio = findViewById(R.id.btniniciar);
        terminos = findViewById(R.id.btntermcond);

        termCond = findViewById(R.id.cb_terminos);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        ingresar.setOnClickListener(view -> {
            crearUsuario();
        });

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opLogin();
            }
        });

        terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btntermcond:
                        i = new Intent(SigInActivity.this, PrivacidadPoliticasActivity.class);
                        startActivity(i);
                        finish();
                        break;
                }
            }
        });
    }

    public void opLogin(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SigInActivity.this, LoginActivity.class));
        finish();
    }
    
    public void crearUsuario(){
        String passwordInput = contraseña.getText().toString().trim();
        String emailInput = email.getText().toString().trim();

        String nom = nombre.getText().toString();
        String apell = apellido.getText().toString();
        String mail = email.getText().toString();
        String contra = contraseña.getText().toString();

        if (termCond.isChecked()){
            if (nom.equals("") || apell.equals("") || mail.equals("") || contra.equals("")){
                Toast.makeText(SigInActivity.this, "Llena cada uno de los campos", Toast.LENGTH_LONG).show();
                }else if(!mail.equals("") && !EMAIL_ADDRESS.matcher(emailInput).matches()){
                email.setError("Correo invalido");
                email.requestFocus();
                Toast.makeText(SigInActivity.this, "Ingrese un correo valido", Toast.LENGTH_SHORT).show();
                    }else if (!contra.equals("") && !PASSWORD_PATTERN.matcher(passwordInput).matches()){
                Toast.makeText(SigInActivity.this, "Ingrese una contraseña valida", Toast.LENGTH_SHORT).show();
                contraseña.setError("Contraseña invalida");
                contraseña.requestFocus();
            }else{
                mAuth.createUserWithEmailAndPassword(mail, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            usuario = mAuth.getCurrentUser().getUid();
                            DocumentReference dcReference = db.collection("users").document(usuario);

                            Map<String,Object> user = new HashMap<>();
                            user.put("Nombre", nom);
                            user.put("Correo", mail);
                            user.put("Apellido", apell);
                            user.put("Contraseña", contra);
                            user.put("uid", usuario);


                            dcReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("TAG", "onSucces: Datos registrados" + usuario);
                                }
                            });
                            Toast.makeText(SigInActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SigInActivity.this, LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(SigInActivity.this, "Usuario no registrado" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else {
            Toast.makeText(this, "Debes aceptar los terminos y condiciones", Toast.LENGTH_LONG).show();
        }

    }
}