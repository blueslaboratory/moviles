package com.example.e014_apidog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String raza;
    // MiTareaAsincrona tarea1;
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


    private class MiTareaAsincrona extends AsyncTask<Void, Void, DogRespuesta>{
        DogRespuesta dogRespuesta;

        @Override
        protected DogRespuesta doInBackground(Void... params){
            String cadena = "https://dog.ceo/api/bread/"+raza+"/";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(cadena)
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().serializeNulls().create()
                    )).build();

            DogsAPIService dogsAPIService = retrofit.create(DogsAPIService.class);
            Call<DogRespuesta> call = dogsAPIService.getDogs();

            try{
                dogRespuesta = call.execute().body();

                // ME QUEDO COPIANDO POR AQUI

                //Log.d("Carga")
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}