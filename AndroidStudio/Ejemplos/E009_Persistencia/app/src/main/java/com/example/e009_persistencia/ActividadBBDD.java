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


    // 22/03/2023
    public void alta_sql(View v) {
        // Si hemos abierto correctamente la base de datos
        if(MiBBDD != null){
            String cod = edtcodigo.getText().toString();
            String desc = edtdesc.getText().toString();
            String precio = edtprecio.getText().toString();
            String sql;

            // Insertamos los datos en la tabla Usuarios
            sql = "INSERT INTO articulos VALUES (" +cod +", '" +desc +"', " +precio +")";
            MiBBDD.execSQL(sql);
            edtcodigo.setText("");
            edtdesc.setText("");
            edtprecio.setText("");

            Toast.makeText(this, "Se cargaron los datos del articulo", Toast.LENGTH_SHORT).show();
        }
    }


    // 22/03/2023
    public void alta(View v){
        if(bt_switch.isChecked()){
            alta_sql(v);
        }
        else{
            alta_no_sql(v);
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

    //22/03/2023
    public void consultaPorCodigo_no_SQL(View v){
        String cod = edtcodigo.getText().toString();
        String[] campos = new String[] {"descripcion", "precio"};
        String[] args = new String[] {cod};

        Cursor fila = MiBBDD.query("articulos", campos, "codigo=?", args, null, null, null);

        if(fila.moveToFirst()){
            edtdesc.setText(fila.getString(0));
            edtprecio.setText(fila.getString(1));
        }
        else{
            Toast.makeText(this, "No existe un articulo con dicho codigo", Toast.LENGTH_SHORT).show();
        }
    }


    public void consulta_cod(View v){
        if(bt_switch.isChecked()){
            consultaPorCodigo_SQL(v);
        }
        else{
            consultaPorCodigo_no_SQL(v);
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


    //23/03/2023
    public void modificacion(View v){
        String cod = edtcodigo.getText().toString();
        String desc = edtdesc.getText().toString();
        String precio = edtprecio.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", cod);
        registro.put("descripcion", desc);
        registro.put("precio", precio);

        int cant = MiBBDD.update("articulos", registro, "codigo=" +cod, null);
        if(cant==1){
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "no existe un articulo con el codigo ingresado", Toast.LENGTH_SHORT).show();
        }
    }


    //23/03/2023
    public  void consultaPorDescripcion(View v){
        String desc = edtdesc.getText().toString();
        String cadena = "";
        Cursor c = MiBBDD.rawQuery(
                "select codigo, descripcion, precio from articulos where descripcion like '%" +desc + "%'", null
        );


        // Nos aseguramos de que existe al menos un registro
        if(c.moveToFirst()){
            // Recorremos el cursor hasta que no haya más registros
            do{
                String codigo = c.getString(0);
                String descripcion = c.getString(1);
                cadena += codigo +"-" +descripcion +System.getProperty("line.separator");
            } while(c.moveToNext());

            edtcodigo.setText(cadena);
        }
        else{
            Toast.makeText(this, "No existe un articulo con dicha descripcion", Toast.LENGTH_SHORT).show();
        }
    }
}