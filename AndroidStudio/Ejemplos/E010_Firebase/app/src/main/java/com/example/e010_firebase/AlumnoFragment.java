package com.example.e010_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlumnoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlumnoFragment extends Fragment {

   String Documento, Nombre, Clase;
   Integer Edad;
   private static FirebaseFirestore my_db;


    public AlumnoFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AlumnoFragment newInstance(FirebaseFirestore db) {
        AlumnoFragment fragment = new AlumnoFragment();
        my_db = db;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View vista;
        vista = inflater.inflate(R.layout.fragment_alumno, container, false);
        EditText edtDocu, edtEdad, edtNombre,edtClase;
        edtDocu = vista.findViewById(R.id.edtDoc);
        edtEdad = vista.findViewById(R.id.edtEdad);
        edtNombre = vista.findViewById(R.id.edtNombre);
        edtClase = vista.findViewById(R.id.edtCodClase);
        Button boton_insertOAlu;
        boton_insertOAlu = vista.findViewById(R.id.bt_InsertAlum);

        boton_insertOAlu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Documento = edtDocu.getText().toString();
                Nombre = edtNombre.getText().toString();
                Edad = Integer.parseInt(edtEdad.getText().toString());
                Clase = edtClase.getText().toString();

                Alumno MiAlumno = new Alumno(Nombre, Documento,Edad);

                my_db.collection("Clases").document(Clase).collection("Alumnos").document(Documento)
                        .set(MiAlumno)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Guardada alumno: "+Documento,
                                        Toast.LENGTH_SHORT).show();
                                edtDocu.setText("");
                                edtNombre.setText("");
                                edtEdad.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(
                                        getActivity().getApplicationContext(),
                                        "Error al guardar alumno: "+Documento,
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        return vista;
    }
}