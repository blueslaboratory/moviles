package com.example.e010_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ClaseFragment extends Fragment {


    // 28/03/2023
    private static FirebaseFirestore my_db;
    String grado, codigo;
    Integer numalum;
    Long curso;

    public static ClaseFragment newInstance(FirebaseFirestore db) {
        ClaseFragment fragment = new ClaseFragment();
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