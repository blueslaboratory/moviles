package com.example.e009_persistencia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText myEditText;
    TextView myTextview;

    Integer progreso, progresoTotal = 0;
    SharedPreferences myPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = findViewById(R.id.editTextNumber);
        myTextview = findViewById(R.id.TextView);

        myPrefs = getSharedPreferences("PreferenciasDAM", Context.MODE_PRIVATE);
    }

    // hay que setear estos metodos en los botones, en el onclick
    // Esquina inferior derecha del Device File Explorer y buscamos nuestras preferencias:
    // /data/data/com.example.e009_persistencia/shared_prefs/PreferenciasDAM.xml
    // Se puede escribir a saco en el Device File Explorer: "PreferenciasDAM"
    public void addPref(View v){
        String email = myEditText.getText().toString();

        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("email", email);
        editor.commit();
    }

    public void recuperarEmail(View v){
        String correo = myPrefs.getString("email", "por_defecto@email.com");
        myTextview.setText(correo);

    }


    // progreso numerico
    public void addProgreso(View v){
        progreso = Integer.parseInt(myEditText.getText().toString());

        progresoTotal = progresoTotal + progreso;
    }

    public void verProgreso(View v){
        myTextview.setText(progresoTotal.toString());
    }


    // UT5.1 - Persistencia y reconstruccion de una actividad
    // Bundle: objeto para pasarse informacion a si mismo

    // guardar progreso
    @Override
    protected void onSaveInstanceState (@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("mi_progreso_total", progresoTotal);
    }

    // restaurar progreso
    @Override
    protected void onRestoreInstanceState (@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        progresoTotal = savedInstanceState.getInt( "mi_progreso_total" );
        myTextview.setText( progresoTotal .toString());
    }


    // crear una actividad:
    // boton derecho encima del paquete: New --> Activity --> Settings Activity
    // Hay que ponerlo en el boton que hemos creado (ver actividad onClick)
    public void abrirSettingsActivity(View v){
        Intent miIntento = new Intent(this, SettingsActivity.class);

        startActivity(miIntento);
    }


    // (ver actividad onClick)
    public void abrir_activity2(View Vista){
        Intent my_intent = new Intent(this, ActividadBBDD.class);

        startActivity(my_intent);
    }
}