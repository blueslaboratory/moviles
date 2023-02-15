package com.example.e003_recycledviewanimales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animalNames.add("Caballo");
        animalNames.add("Vaca");
        animalNames.add("Perro");
        animalNames.add("Gato");
        animalNames.add("León");
        animalNames.add("Tigre");
        animalNames.add("Rinoceronte");
        animalNames.add("Elefante");
        animalNames.add("Águila");
        animalNames.add("Mariposa");
        animalNames.add("Serpiente");
        animalNames.add("Oso");

        RecyclerView recyclerView = findViewById(R.id.rvAnimales);
        LinearLayoutManager myLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayout);
        adapter = new Adapter(animalNames, this);
        recyclerView.setAdapter(adapter);

        // Metemos una division entre las filas
        DividerItemDecoration midividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), myLayout.getOrientation());
        recyclerView.addItemDecoration(midividerItemDecoration);

    }
}