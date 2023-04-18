package com.example.e011_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {

    // UT6.1 Firebase 2º Parte

    String email, password;
    EditText edt_email, edt_password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_email = findViewById(R.id.username);
        edt_password = findViewById(R.id.password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    public void LoginRegistro(View v){
        email = edt_email.getText().toString();
        password = edt_password.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Usuario creado: " +user.getEmail(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // If sign in fails, display a message to the user
                            Toast.makeText(getApplicationContext(), "Ha fallado el login", Toast.LENGTH_SHORT).show();
                            Log.w("PRUEBAS", "signInWithEmail:failure", task.getException());
                        }
                    }
                });
    }



    /*
    // 12/04/2023

    TextView tv2;
    EditText et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        tv2 = findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        String mensaje = extras.getString("extra_mensaje");
        tv2.setText(mensaje);


    }


    public void salir(View vista){
        et2 = findViewById(R.id.editTextEnviar);
        String mensaje_vuelta = et2.getText().toString();
        Intent my_resultado = new Intent();
        my_resultado.putExtra("extra_vuelta", mensaje_vuelta);
        setResult(RESULT_OK, my_resultado);
        this.finish();
    }

    /*
    // asociamos salir al onclick
    public void salir(View vista){
        this.finish();
    }


    @Override
    protected void onStop(){
        // call the superclass method first
        super.onStop();
        setResult(RESULT_CANCELED, null);
    }
*/
}