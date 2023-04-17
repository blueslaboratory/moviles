package com.example.e011_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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