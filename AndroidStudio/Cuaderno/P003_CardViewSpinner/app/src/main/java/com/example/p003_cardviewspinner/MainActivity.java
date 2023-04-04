// UT2.5 Spinner

package com.example.p003_cardviewspinner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String paises [] = {"Seleccione", "Italia", "China", "Japon", "Rusia"};
    Spinner mySpinner;
    private ArrayAdapter myAdapter;
    public TextView myTextViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextViewResultado = (TextView) findViewById(R.id.tv1);
        mySpinner = (Spinner) findViewById(R.id.sp2);

        myAdapter = new ArrayAdapter<>( this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, paises);
        myAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccionado = mySpinner.getSelectedItem().toString();
                myTextViewResultado.setText("Seleccionado: " + seleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myTextViewResultado.setText("No hay seleccion");
            }
        });

    }
}