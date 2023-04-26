package com.example.e014_apidog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String raza;
    MiTareaAsincrona tarea1;
    LinearLayoutManager myLayout;
    RecyclerView myRecycler;
    MiAdaptador MyAdapter;
    EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edt2);

        myLayout = new LinearLayoutManager(this);
        myRecycler = (RecyclerView) findViewById(R.id.rvPerros);
        myRecycler.setLayoutManager(myLayout);
    }

    //26/04/2023
    public void cargar(View v){
        raza = edt1.getText().toString();
        tarea1 = new MiTareaAsincrona();
        tarea1.execute();
    }


    private class MiTareaAsincrona extends AsyncTask<Void, Void, DogRespuesta>{
        DogRespuesta dogRespuesta;

        // 26/04/2023
        @Override
        protected DogRespuesta doInBackground(Void... params){
            String cadena = "https://dog.ceo/api/breed/"+raza+"/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(cadena)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().serializeNulls().create()
                    )).build();

            DogsAPIService dogsAPIService = retrofit.create(DogsAPIService.class);
            Call<DogRespuesta> call = dogsAPIService.getDogs();

            try{
                dogRespuesta = call.execute().body();

                Log.d("Carga", "Cargado dogRespuesta");
            } catch (IOException e) {
                Log.d("Error", "Error cargando dogRespuesta");
            } catch (RuntimeException r){
                Log.d("Error", "Error cargando dogRespuesta");
            }

            return dogRespuesta;
        }


        // 26/04/2023
        @Override
        protected void onPostExecute(DogRespuesta data){
            if(data==null){
                Toast.makeText(MainActivity.this, "No se han encontrado imágenes", Toast.LENGTH_SHORT).show();
            }
            else{
                MyAdapter = new MiAdaptador(MainActivity.this, data);
                myRecycler.setAdapter(MyAdapter);
            }
        }
    }
}