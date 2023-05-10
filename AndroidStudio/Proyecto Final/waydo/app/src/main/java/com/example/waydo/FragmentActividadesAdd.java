package com.example.waydo;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentActividadesAdd #newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentActividadesAdd extends Fragment {

    AdministradorBBDD myAdminDDBB;
    SQLiteDatabase myDDBB;


    public FragmentActividadesAdd() {
        // Required empty public constructor
    }

    @Override
    public void onStop(){
        super.onStop();
        myDDBB.close();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_actividades_add, container, false);

        EditText myEditTextDate = vista.findViewById(R.id.edtEventoAddFecha);

        Spinner mySpinnerTipo = vista.findViewById(R.id.spEventoAddDistrito);
        Spinner mySpinnerModulo = vista.findViewById(R.id.spEventoAddBarrio);
        Spinner mySpinnerVigencia = vista.findViewById(R.id.spEventoAddVigencia);

        Button myButtonGuardar = vista.findViewById(R.id.btnAddEvento);


        myAdminDDBB = new AdministradorBBDD(getContext(), "eventos", null, 1);
        myDDBB = myAdminDDBB.getWritableDatabase();


        // DatePicker (Calendario)
        // https://es.stackoverflow.com/questions/45289/como-generar-un-datepicker-desde-un-fragment
        /*
            fragment_add.xml
            android:clickable="true"
            android:focusable="false"
        */
        myEditTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String fecha = String.valueOf(year) +"/" +String.valueOf(monthOfYear) +"/" +String.valueOf(dayOfMonth);
                        myEditTextDate.setText(fecha);
                    }
                }, year, month, day);

                // Mostrar dialogo
                datePicker.show();

            }
        });


        // Guardando en la BBDD
        myButtonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha = myEditTextDate.getText().toString();
                String tipo = mySpinnerTipo.getSelectedItem().toString();
                String modulo = mySpinnerModulo.getSelectedItem().toString();
                String estado = mySpinnerVigencia.getSelectedItem().toString();

                if(fecha.equals("") || tipo.equals("") || modulo.equals("") || estado.equals("")) {
                    Toast.makeText(getContext(), "Por favor, rellene todos los campos para añadir el evento", Toast.LENGTH_SHORT).show();
                }
                else{
                    ContentValues registroEvento = new ContentValues();

                    registroEvento.put("fecha", fecha);
                    registroEvento.put("tipo", tipo);
                    registroEvento.put("modulo", modulo);
                    registroEvento.put("estado", estado);

                    Long row = myDDBB.insert("eventos", null, registroEvento);

                    // Si entra en la BBDD: reiniciamos los valores de los campos
                    if(row != -1){
                        myEditTextDate.setText("");
                        mySpinnerTipo.setSelection(0);
                        mySpinnerModulo.setSelection(0);
                        mySpinnerVigencia.setSelection(0);

                        Toast.makeText(getContext(), "Se ha añadido el evento", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getContext(), "Error al añadir el evento", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return vista;
    }


}