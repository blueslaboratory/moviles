package com.example.e003_recycledviewanimales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder>{

    List<String> misdatos;
    LayoutInflater miInflador;
    // desde el activity main le diremos al adapter que ese es su contexto
    Context miContexto;


    // Constructor
    public Adapter(List<String> datos, Context contexto){
        misdatos = datos;
        miContexto = contexto;
        miInflador = LayoutInflater.from(contexto);
        // miInflador: se encarga de pegar las filas en el RecycledView
    }


    // Metodos creados automaticamente
    @NonNull
    @Override
    // Infla cada fila del layout que hemos hecho para cada fila
    // este metodo lo que hace es construir las filas
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = miInflador.inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    // Recupera la posicion del array en el que estamos y asigna en TextView el nombre del animal
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String animal = misdatos.get(position);
        holder.myTextView.setText(animal);
    }

    // total numero de filas
    // cuenta el tamano del array
    @Override
    public int getItemCount() {
        return misdatos.size();
    }




    // Intern class: clase dentro de clase
    // Solo pueden ser llamadas dentro de la propia clase
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView myTextView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.tv1);
        }
    }
}
