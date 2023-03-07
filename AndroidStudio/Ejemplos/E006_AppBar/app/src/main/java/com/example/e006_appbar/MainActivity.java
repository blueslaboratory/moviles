/*
1/3/2023

ALT + ENTER
*/

package com.example.e006_appbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView mitv1;
    Toolbar mitoolbar;
    Spinner misp1;
    // hay que importar la segunda,
    // import androidx.appcompat.widget.Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mitv1 = (TextView) findViewById(R.id.tv1);
        mitoolbar = (Toolbar) findViewById(R.id.toolbar);
        misp1 = (Spinner) findViewById(R.id.spinner);

        setSupportActionBar(mitoolbar);

        setContentView(R.layout.activity_main);

        // ocultar la action bar
        //getSupportActionBar().hide();
    }

    // en el onclick de la imagen()

    /*
    public void mostrar(View v){
        getSupportActionBar().show();
    }
     */


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mi_menu, menu);

        misp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cadena = misp1.getSelectedItem().toString();
                mitv1.setText(cadena);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mitv1.setText("nada seleccionado");
            }
        });


        return true;
    }


    //AppBar
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return true;
    }
    */

    //AppBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        String cadena = "";

        if (id == R.id.action_roja){
            cadena="Tomas la pildora roja... te queda el País "
                    + "de las Maravillas, y te enseño lo profunda que es la madriguera "
                    + "del conejo";
        }
        else if (id == R.id.action_azul){
            cadena="Tomas la pildora azul... la historia termina, "
                    +"te despiertas en tu casma y crees lo que quieras creer";
        }
        else if (id == R.id.action_opciones){
            this.finish();
        }

        // si no casteas aquí sale null el mitv1 y da nulo
        mitv1 = (TextView) findViewById(R.id.tv1);

        mensaje_morfeo(cadena);
        return super.onOptionsItemSelected(item);
    }

    //AppBar
    public void mensaje_morfeo(String texto){
        mitv1.setText(texto);
    }

}