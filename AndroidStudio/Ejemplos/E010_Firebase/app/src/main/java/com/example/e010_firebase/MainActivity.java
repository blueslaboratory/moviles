package com.example.e010_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;

// Los iconos estan sacados de material design
// 27/03/2023

// Firebase: Coleccion -> Documento -> Coleccion -> Documento

// 28/03/2023
// Esta practica no funciona sin internet: la DB esta en internet
// Dentro de Tools hay un iconito de Firebase --> Cloud Firestore --> Get started with Cloud Firestore
//      -> Hacer el paso 1 y luego el paso 2 (Add the Cloud Firestore SDK to your app)

// Formatear codigo: CTRL + ALT + L
// Code --> Reformat Code

// URLS Firebase
// https://console.firebase.google.com/project/
// https://console.firebase.google.com/project/e010-firebase/firestore/data/~2FClases~2FSI?hl=es

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore my_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_db = FirebaseFirestore.getInstance();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        // Paso 1: Obtener la instancia del administrador de fragmentos
        FragmentManager my_fragmentManager = getSupportFragmentManager();
        // Paso 2: Crear una nueva transaccion
        FragmentTransaction my_transaction = my_fragmentManager.beginTransaction();
        if(id == R.id.clase){
            //Paso 3: Crear un nuevo fragmento y añadirlo
            FragmentClase fragment1;
            fragment1 = FragmentClase.newInstance(my_db);
            my_transaction.replace(R.id.myFrameLayout, fragment1);
        }
        else if(id == R.id.buscar){
            //Paso 3: Crear un nuevo fragmento y añadirlo
            FragmentConsulta fragment2;
            fragment2 = FragmentConsulta.newInstance(my_db);
            my_transaction.replace(R.id.myFrameLayout, fragment2);
        }
        else if(id == R.id.alumno){
            //Paso 3: Crear un nuevo fragmento y añadirlo
            FragmentAlumno fragment3;
            fragment3 = FragmentAlumno.newInstance(my_db);
            my_transaction.replace(R.id.myFrameLayout, fragment3);
        }



        // Paso 4: Confirmar el cambio
        my_transaction.commit();
        return super.onOptionsItemSelected(item);
    }
}