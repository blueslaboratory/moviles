package com.example.e010_firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FragmentConsulta extends Fragment {

    // 11/04/2023
    private static FirebaseFirestore my_db;
    Long curso, seleccion;
    Integer edad;


    public static FragmentConsulta newInstance(FirebaseFirestore db) {
        FragmentConsulta fragment = new FragmentConsulta();

        my_db = db;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista;
        vista = inflater.inflate(R.layout.fragment_consulta, container, false);

        EditText edtbuscod, edtEdad;
        TextView tvBusqueda, tvBusquedaCurso, tv_busqAlumnos;
        Button bt_buscar, bt_buscarCurso, bt_buscaralumnos, bt_buscaralumnosxcurso;

        edtbuscod = vista.findViewById(R.id.edtBusCod);
        tvBusqueda = vista.findViewById(R.id.tvBusqueda);
        bt_buscar = vista.findViewById(R.id.bt_buscar);
        bt_buscarCurso = vista.findViewById(R.id.bt_buscarCurso);
        tvBusquedaCurso = vista.findViewById(R.id.tv_busCurso);
        Spinner spCurso = vista.findViewById(R.id.spBusCurso);
        Spinner spseleccion = vista.findViewById(R.id.sp_comparacion);
        tv_busqAlumnos = vista.findViewById(R.id.tv_alumnos);
        edtEdad = vista.findViewById(R.id.edtBuscEdad);
        bt_buscaralumnos = vista.findViewById(R.id.bt_buscAlum);

        bt_buscaralumnosxcurso = vista.findViewById(R.id.bt_buscaralumnosxcurso);

        

        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = edtbuscod.getText().toString();
                DocumentReference docRef = my_db.collection("Clases").document(codigo);
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Clase miClase = document.toObject(Clase.class);
                                tvBusqueda.setText(miClase.getGrado() + "-" + miClase.getCurso().toString() + "-" + miClase.getNumeroalumnos().toString());

                                //tvBusqueda.setText(document.getString("grado"));
                                Log.d("PRUEBA", "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d("PRUEBA", "No such document");
                            }
                        } else {
                            Log.d("PRUEBA", "get failed with ", task.getException());
                        }
                    }
                });
            }
        });



        bt_buscarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curso = spCurso.getSelectedItemId();
                my_db.collection("Clases")
                        .whereEqualTo("curso", curso)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                tvBusqueda.setText("");
                                
                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot document : task.getResult()){
                                        Clase miclase = document.toObject(Clase.class);
                                        String texto = "";
                                        texto = tvBusqueda.getText().toString();
                                        
                                        texto = texto +document.getId() +"-" +miclase.getGrado() +"-" +miclase.getNumeroalumnos() +System.getProperty("line.separator");
                                        tvBusqueda.setText(texto);
                                    }
                                }
                                else {
                                    Log.d("PRUEBA", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });



        bt_buscaralumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seleccion = spseleccion.getSelectedItemId();
                edad = Integer.valueOf(edtEdad.getText().toString());

                if(seleccion==1){
                    my_db.collectionGroup("Alumnos")
                            .whereLessThan("edad", edad)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    tv_busqAlumnos.setText("");
                                    if(task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Alumno mialumno = document.toObject(Alumno.class);
                                            String texto = "";
                                            texto = tv_busqAlumnos.getText().toString();
                                            texto = texto + document.getId() + "-" + mialumno.getNombre() + "-" + mialumno.getEdad() + System.getProperty("line.separator");
                                            tv_busqAlumnos.setText(texto);
                                        }
                                    }
                                    else {
                                        Log.d("PRUEBA", "Error getting documents: ", task.getException());
                                    }
                                }

                            });
                }
                else {
                    my_db.collectionGroup("Alumnos")
                            .whereGreaterThan("edad", edad)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    tv_busqAlumnos.setText("");
                                    if(task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Alumno mialumno = document.toObject(Alumno.class);
                                            String texto = "";
                                            texto = tv_busqAlumnos.getText().toString();
                                            texto = texto + document.getId() + "-" + mialumno.getNombre() + "-" + mialumno.getEdad() + System.getProperty("line.separator");
                                            tv_busqAlumnos.setText(texto);
                                        }
                                    }
                                    else {
                                        Log.d("PRUEBA", "Error getting documents: ", task.getException());
                                    }
                                }

                            });
                }
            }
        });



        bt_buscaralumnosxcurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = edtbuscod.getText().toString();
                edad = Integer.valueOf(edtEdad.getText().toString());
                my_db.collection("Clases").document(codigo).collection("Alumnos")
                        .whereLessThan("edad", edad)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                tv_busqAlumnos.setText("");

                                if(task.isSuccessful()){
                                    for(QueryDocumentSnapshot document : task.getResult()){
                                        Alumno mialumno = document.toObject(Alumno.class);
                                        String texto = "";

                                        texto = tv_busqAlumnos.getText().toString();
                                        texto = texto +document.getId() +"-" +mialumno.getNombre() +"-" +mialumno.getEdad() +System.getProperty("line.separator");
                                        tv_busqAlumnos.setText(texto);
                                    }
                                }
                                else {
                                    Log.d("PRUEBA", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });

        return vista;
    }
}
