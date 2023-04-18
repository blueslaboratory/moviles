package com.example.e011_login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class MainActivity extends AppCompatActivity {

    // 17/04/2023
    // UT6.1 Firebase 2º Parte

    // Login Firebase
    private FirebaseAuth mAuth;
    FirebaseUser UsuarioConectado;
    private ImageView photoImageView;
    private TextView nombreTextView, emailTextView, idTextView;


    // Login de google
    View loginGoogle;
    // Esta es la propia ventana de Google de loguearse
    GoogleSignInClient myGoogleSignInClient;
    // Opciones de login
    GoogleSignInOptions gso;
    ActivityResultLauncher<Intent> myActivityResultLauncher;
    boolean logueadoGoogle = false;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photoImageView = (ImageView) findViewById(R.id.iVPhoto);
        nombreTextView = (TextView) findViewById(R.id.tVnombre);
        emailTextView = (TextView) findViewById(R.id.tVemail);
        idTextView = (TextView) findViewById(R.id.tvId);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        // Google
        loginGoogle = findViewById(R.id.signInButton);

        // Configuraramos las opciones de Google Sign In (estándar al menos para nosotros)
        gso = new GoogleSignInOptions .Builder(GoogleSignInOptions .DEFAULT_SIGN_IN).
                requestIdToken(getString( R.string.default_web_client_id)).
                requestEmail().build();

        // Crear la ventana de Login con las opciones especificadas por gso.
        myGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        // Implementamos Login con Google
        loginGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = myGoogleSignInClient .getSignInIntent();
                myActivityResultLauncher .launch(signInIntent );
            }
        });


        // Codificamos la captura del resultado de la ventana de Login
        myActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                            if(task.isSuccessful()){
                                try{
                                    // Capturamos la cuenta y se la pasamos a un metodo que hara el login en firebase
                                    GoogleSignInAccount account = task.getResult(ApiException.class);
                                    firebaseAuthWithGoogle(account.getIdToken());
                                    logueadoGoogle = true;
                                } catch (ApiException e){
                                    Log.w("PRUEBA", "Error en el login de Google", e);
                                }
                            }
                            else{
                                Log.w("PRUEBA", "Error en el login de Google 2");
                                Toast.makeText(getApplicationContext(), "Ocurrio un error" +task.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Log.d("PRUEBA", "Error, login no exitoso:");
                        }
                    }
                }
        );
    }


    // Firebas con google
    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Usuario: " +mAuth.getCurrentUser(), Toast.LENGTH_LONG).show();
                            setDatosUsuario(user);
                        }
                        else{
                            Log.w("PRUEBA", "signInWithCredential:failure", task.getException());
                        }
                    }
                });
    }


    // Firebase
    @Override
    public void onStart() {
        super.onStart();
        // Comprobamos si está logado, si lo está, vemos sus datos
        UsuarioConectado = mAuth.getCurrentUser();
        if (UsuarioConectado != null) {
            setDatosUsuario(UsuarioConectado);
        }
    }

    private void setDatosUsuario (FirebaseUser user) {
        nombreTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        idTextView.setText(user.getUid());
        Glide.with(this).load(user.getPhotoUrl()).into(photoImageView);
    }

    public void LoginConEmail (View v){
        Intent my_intent = new Intent(this, LoginActivity.class);
        startActivity(my_intent);
    }


    public void LogOut(View v){
        mAuth.signOut();
        nombreTextView.setText(null);
        emailTextView.setText(null);
        idTextView.setText(null);
        photoImageView.setImageResource(0);

        if(logueadoGoogle){
            myGoogleSignInClient.signOut();
        }
    }



    /*
    // 12/04/2023


    EditText et1;
    String mensaje;

    ActivityResultLauncher<Intent> myActivityResultLauncher;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent my_intent_vuelta = result.getData();
                        String mensaje_vuelta = my_intent_vuelta.getStringExtra("extra_vuelta").toString();
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje_vuelta, duration);
                        toast.show();
                    }
                    else if(result.getResultCode() == Activity.RESULT_CANCELED){
                        String mensaje_vuelta = "Sin mensaje de vuelta";
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje_vuelta, duration);
                        toast.show();
                    }
                }
        });



    }

    // Lanzar el mensaje desde el MainActivity a LoginActivity
    // Bundle: contenedor de parejas clave-valor
    // lo asignamos al boton

    public void lanzar_actividad(View vista){
        et1 = findViewById(R.id.editTextText);
        mensaje = et1.getText().toString();

        Intent my_intent = new Intent(this, LoginActivity.class);

        // pareja clave-valor en el objeto intent:
        my_intent.putExtra("extra_mensaje", mensaje);


        /*
        Tienes que elegir uno de los 2:
        - Si no esperas ningun resultado con startActivity te vale
         */
        // startActivity(my_intent);
    /*
        myActivityResultLauncher.launch(my_intent);
    }

    */

}