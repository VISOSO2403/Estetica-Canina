package com.example.esteticacanina;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class LoginActivity extends AppCompatActivity {

    //Esta vez usaremos variables para asignarles un objeto
    private EditText email, pass;
    private Button ingresar, registrarse;

    //Variable para FireBase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //enlaces del front con el back para: EditText
        email = findViewById(R.id.etxtemail);
        pass = findViewById(R.id.etxtpassword);

        //enlaces del front con el back para: Button
        ingresar = findViewById(R.id.btningresar);
        registrarse = findViewById(R.id.btnregistrar);

        mAuth = FirebaseAuth.getInstance();

        ingresar.setOnClickListener(view -> {
            login();
        });

        //Iniciamos el primer metodo
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerActivity();
            }
        });

    }//aqui termina tu onCreate

    public void registerActivity(){
        Intent i = new Intent(this, SigInActivity.class);
        startActivity(i);
        finish();
    }//Aqui termina tu RegisterActivity

    public void login(){
        String correo = email.getText().toString();
        String contra = pass.getText().toString();

        if (TextUtils.isEmpty(correo)){
            email.setError("Correo invalido");
            email.requestFocus();
        }else if (TextUtils.isEmpty(contra)){
            Toast.makeText(LoginActivity.this, "Contraseña invalida", Toast.LENGTH_LONG).show();
            pass.requestFocus();
        }else{
           mAuth.signInWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
               @Override
               public void onComplete (@NonNull Task<AuthResult> task){
                   if (task.isSuccessful()){
                       Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                       finish();
                   }else{
                       Toast.makeText(LoginActivity.this, "Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();

                   }
               }
           });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}//Aqui termina tu Login activity