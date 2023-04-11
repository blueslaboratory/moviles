package com.example.e010_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentClase#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentClase extends Fragment {


    // 28/03/2023
    private static FirebaseFirestore my_db;
    String grado, codigo;
    Integer numalum;
    Long curso;

    public static FragmentClase newInstance(FirebaseFirestore db) {
        FragmentClase fragment = new FragmentClase();
        my_db = db;
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Casteos
        View vista;
        vista = inflater.inflate(R.layout.fragment_clase, container, false);
        EditText edtGrado, edtCodigo, edtNumalum;
        edtGrado = vista.findViewById(R.id.edtGrado);
        edtCodigo = vista.findViewById(R.id.edtCod);
        edtNumalum = vista.findViewById(R.id.edtNumAlum);
        Spinner spCurso = vista.findViewById(R.id.sp_curso);
        Button boton_insertM, boton_insertO, boton_updateM, boton_updateO, boton_pararEsc, boton_Esc1;
        boton_insertM = vista.findViewById(R.id.bt_insertM);
        boton_insertO = vista.findViewById(R.id.bt_insertO);
        boton_updateM = vista.findViewById(R.id.bt_updateM);
        boton_updateO = vista.findViewById(R.id.bt_updateO);
        boton_pararEsc = vista.findViewById(R.id.bt_paraEsc);
        boton_Esc1 = vista.findViewById(R.id.bt_esc1);

        TextView tv_documento, tv_atributo;
        tv_documento = vista.findViewById(R.id.tv_documento);
        tv_atributo = vista.findViewById(R.id.tv_campo);


        // Escuchar lo que pasa en la base de datos firebase:
        // (esto esta pensado para que cuando 2 tios esten atacando el mismo documento a ti se te actualice)
        // sobre un documento que exista: ASIR
        final DocumentReference docRef = my_db.collection("Clases").document("ASIR");

        ListenerRegistration registration = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.w("PRUEBAS", "Listen failed.", error);
                    return;
                }

                if(snapshot != null && snapshot.exists()){
                    Log.d("PRUEBAS", "Current data: " +snapshot.getData());
                    tv_documento.setText(snapshot.getData().toString());
                    String cambio = snapshot.getString("grado");
                    tv_atributo.setText(cambio);
                }
                else {
                    Log.d("PRUEBAS", "Current data: null");
                }
            }
        });

        // boton para parar la escucha
        boton_pararEsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration.remove();
            }
        });


        // Escuchar todos los documentos de la coleccion clase donde curso es igual a 1
        // Esto es un chat: sala = coleccion; mensajes = documentos; y lo metes en un recycled view
        // Escuchar 1ºS
        boton_Esc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_db.collection("Clases")
                        .whereEqualTo("curso", 1) // de la coleccion clases el curso == 1
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value,
                                                @Nullable FirebaseFirestoreException error) {
                                if (error != null){
                                    Log.w("PRUEBAS", "Listen failed.", error);
                                    return;
                                }

                                tv_atributo.setText("");
                                for(QueryDocumentSnapshot doc:value){
                                    Clase miclase = doc.toObject(Clase.class);
                                    String atributo = tv_atributo.getText().toString();
                                    atributo = atributo +doc.getId()+'-'+ miclase.getGrado()+'-'
                                                        +miclase.getNumeroalumnos()
                                                        // meter un salto de linea al textview
                                                        +System.getProperty("line.separator");
                                    tv_atributo.setText(atributo);
                                }
                            }
                        });
            }
        });


        // Boton INSERT (MAPAS)
        // Inserta en firebase
        // https://console.firebase.google.com/project/
        // https://console.firebase.google.com/project/e010-firebase/firestore/data/~2FClases~2FSI?hl=es
        boton_insertM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grado = edtGrado.getText().toString();
                codigo = edtCodigo.getText().toString();
                numalum = Integer.parseInt(edtNumalum.getText().toString());
                curso = spCurso.getSelectedItemId();

                // Los mapas son arrays de 2 filas emparejadas
                Map<String, Object> clase = new HashMap<>();
                clase.put("grado", grado);
                clase.put("numeroalumnos", numalum);
                clase.put("curso", curso);

                // Hacemos el set de la clase
                my_db.collection("Clases").document(codigo).set(clase)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Guardada clase: " + codigo,
                                        Toast.LENGTH_SHORT).show();
                                edtGrado.setText("");
                                edtCodigo.setText("");
                                edtNumalum.setText("");
                                spCurso.setSelection(0);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Error al guardar la clase: " + codigo,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        // Boton INSERT (OBJETOS)
        // Inserta en firebase
        // https://console.firebase.google.com/project/
        boton_insertO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grado = edtGrado.getText().toString();
                codigo = edtCodigo.getText().toString();

                numalum = Integer.parseInt(edtNumalum.getText().toString());
                curso = spCurso.getSelectedItemId();

                Clase clase = new Clase(grado, curso, numalum);

                // Hacemos el set de la clase
                my_db.collection("Clases").document(codigo).set(clase)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Guardada clase: " + codigo,
                                        Toast.LENGTH_SHORT).show();
                                edtGrado.setText("");
                                edtCodigo.setText("");
                                edtNumalum.setText("");
                                spCurso.setSelection(0);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Error al guardar la clase: " + codigo,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        return vista;
    }
}