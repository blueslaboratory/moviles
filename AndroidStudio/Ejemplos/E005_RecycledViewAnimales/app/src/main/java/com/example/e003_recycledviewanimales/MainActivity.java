package com.example.e003_recycledviewanimales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Adapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    EditText myedtAnimal;
    RecyclerView myrvAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myedtAnimal = (EditText) findViewById(R.id.edtAnimal);
        myrvAnimal = (RecyclerView) findViewById(R.id.rvAnimales);


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


        //22/02/2023
        // Metemos una division entre las filas (finas lineas entre los animales)
        DividerItemDecoration midividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),
                myLayout.getOrientation());
        recyclerView.addItemDecoration(midividerItemDecoration);

    }


    // 22/02/2023
    // ponerlo en el onClick de activity_main.xml btnBorrar
    public void borrar(View v){
        int posicion = adapter.getPosicionSeleccionada();
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;

        if(posicion<0){
            String text = "Debe seleccionar un animal para borrar";
            Toast toast = Toast.makeText(context, text, duracion);
            toast.show();
            return;
        }

        animalNames.remove(posicion);
        adapter.notifyItemRemoved(posicion);
        adapter.setPosicionSeleccionada();
        adapter.notifyItemChanged(0, animalNames.size());
    }


    // 22/02/2023
    // ponerlo en el onClick de activity_main.xml btnAnadir
    // Si no tengo uno seleccionado me lo agrega al ppio
    // Si tengo uno seleccionado me lo agrega detras del seleccionado
    public void agregar(View v){
        int insertIndex, posicion = adapter.getPosicionSeleccionada();
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;

        if(posicion<0){
            posicion=-1;
        }

        String item;
        item = myedtAnimal.getText().toString();

        if(item.isEmpty()){
            String text = "Debe introducir un animal";;
            Toast toast = Toast.makeText(context, text, duracion);
            toast.show();
            return;
        }

        insertIndex = posicion+1;
        animalNames.add(insertIndex, item);
        adapter.notifyItemInserted(insertIndex);
        adapter.notifyItemChanged(0,animalNames.size());
        myrvAnimal.scheduleLayoutAnimation();
    }
}