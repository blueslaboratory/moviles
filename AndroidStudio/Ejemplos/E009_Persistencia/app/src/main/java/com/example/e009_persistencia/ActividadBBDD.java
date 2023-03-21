// 21/03/2023

package com.example.e009_persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class ActividadBBDD extends AppCompatActivity {

    EditText edtcodigo, edtdesc, edtprecio;
    Switch bt_switch;
    SQLiteDatabase MiBBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_bbdd);

        edtcodigo = findViewById(R.id.et1);
        edtdesc = findViewById(R.id.et2);
        edtprecio = findViewById(R.id.et3);
        bt_switch = findViewById(R.id.switch1);

        AdministradorBBDD miadministrador = new AdministradorBBDD(this, "BDarticulos", null, 1);

        MiBBDD = miadministrador.getWritableDatabase();
    }

    @Override
    protected void onStop(){
        super.onStop();
        MiBBDD.close();
    }

    public void alta_no_sql(View v){
        // si hemos abierto correctamente al base de datos
        if(MiBBDD != null){
            String cod = edtcodigo.getText().toString();
            String desc = edtdesc.getText().toString();
            String precio = edtprecio.getText().toString();

            ContentValues registro = new ContentValues();

            registro.put("codigo", cod);
            registro.put("descripcion", desc);
            registro.put("precio", precio);

            Long row = MiBBDD.insert("articulos", null, registro);

            if(row != -1){
                edtcodigo.setText("");
                edtdesc.setText("");
                edtprecio.setText("");
                Toast.makeText(this, "Se cargaron los datos del articulo",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error en el alta del articulo",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void consultaPorCodigo_SQL(View v){
        String cod = edtcodigo.getText().toString();
        Cursor fila = MiBBDD.rawQuery(
              "select descripcion, precio from articulos where codigo=" +cod, null);

        if(fila.moveToFirst()){
            edtdesc.setText(fila.getString(0));
            edtprecio.setText(fila.getString(1));
        }
        else{
            Toast.makeText(this, "No existe un articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void bajaPorCodigo(View v){
        String cod = edtcodigo.getText().toString();
        int cant = MiBBDD.delete("articulos", "codigo=" +cod, null);

        edtcodigo.setText("");
        edtdesc.setText("");
        edtprecio.setText("");

        if(cant == 1){
            Toast.makeText(this, "Se borro el articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "No existe un articulo con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        }
    }
}