package com.example.p002_conversordivisas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
Es un combo de: dados, recycled view, toast y bisiesto (switch)
*/


public class MainActivity extends AppCompatActivity {

    ArrayList<Divisas> divisas = new ArrayList<>();

    Adapter adapter;
    RecyclerView myrvMonedas;
    Button mybtnConvertir;
    EditText myetndCantidad;
    TextView mytvMostrarCantidad;
    Switch mySwitchVip;

    int defaultValue = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] divisasNames = getResources().getStringArray(R.array.nombres_divisas);
        String[] divisasCodigos = getResources().getStringArray(R.array.codigo_divisas);
        String[] divisasCambio = getResources().getStringArray(R.array.divisas_cambio);
        String[] divisasSimbolo = getResources().getStringArray(R.array.divisas_simbolo);


        for(int i=0; i<divisasNames.length; i++){
            Divisas divisa = new Divisas(divisasNames[i], divisasCodigos[i], divisasCambio[i], divisasSimbolo[i]);
            divisas.add(divisa);
        }


        // Recycled View (ver la lista de codigos de monedas)
        myrvMonedas = findViewById(R.id.rvMonedas);
        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        myrvMonedas.setLayoutManager(myLayout);
        adapter = new Adapter(divisas, this);
        myrvMonedas.setAdapter(adapter);


        // Creando el boton Convertir
        mybtnConvertir = (Button) findViewById(R.id.btnConvertir);
        myetndCantidad = (EditText) findViewById(R.id.etndCantidad);
        mytvMostrarCantidad = (TextView) findViewById(R.id.tvMostrarCantidad);


        //Switch
        mySwitchVip = (Switch) findViewById(R.id.switchVip);


        mybtnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float switchComision = 0.99F;

                if(mySwitchVip.isChecked()){
                    switchComision = 1F;
                }

                if(myetndCantidad.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"No hay cantidad",
                                    Toast.LENGTH_SHORT).show();
                }
                // cuando no hay ninguna posicion te lanza el -1
                else if (adapter.getPosicionSeleccionada() == -1){
                    Toast.makeText(MainActivity.this,"No hay cambio seleccionado",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    int posicionSeleccionada = adapter.getPosicionSeleccionada();

                    float cambioActual = Float.parseFloat(divisas.get(posicionSeleccionada).getValue());
                    float cantDecimal = Float.parseFloat(myetndCantidad.getText().toString());

                    mytvMostrarCantidad.setText(String.valueOf(cantDecimal*cambioActual*switchComision)
                                               +String.valueOf(divisas.get(posicionSeleccionada).getSimbolo()));
                }


            }
        });



        // Listener para el boton del switch
        // habria que poner que el boton no esta seleccionado porque funciona como un buton normal?
        mySwitchVip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                float switchComision = 0.99F;

                if(mySwitchVip.isChecked()){
                    switchComision = 1F;
                }

                if(defaultValue == adapter.getPosicionSeleccionada()){
                    Toast.makeText(MainActivity.this,"No se ha realizado la conversion",
                            Toast.LENGTH_SHORT).show();
                }
                else if(myetndCantidad.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"No hay cantidad",
                            Toast.LENGTH_SHORT).show();
                }
                // cuando no hay ninguna posicion te lanza el -1
                else if (adapter.getPosicionSeleccionada() == -1){
                    Toast.makeText(MainActivity.this,"No hay cambio seleccionado",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    int posicionSeleccionada = adapter.getPosicionSeleccionada();

                    float cambioActual = Float.parseFloat(divisas.get(posicionSeleccionada).getValue());
                    float cantDecimal = Float.parseFloat(myetndCantidad.getText().toString());

                    mytvMostrarCantidad.setText(String.valueOf(cantDecimal*cambioActual*switchComision)
                                               +String.valueOf(divisas.get(posicionSeleccionada).getSimbolo()));
                }
            }
        });
    }
}