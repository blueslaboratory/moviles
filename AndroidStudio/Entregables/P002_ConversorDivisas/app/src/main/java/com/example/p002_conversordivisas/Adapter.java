package com.example.p002_conversordivisas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter <Adapter.ViewHolder>{

    private int posicionSeleccionada = RecyclerView.NO_POSITION;
    List<Divisas> misdatos;
    LayoutInflater miInflador;
    // desde el activity main le diremos al adapter que ese es su contexto
    Context miContexto;

    boolean onClickLong = false;

    // Constructor
    public Adapter(List<Divisas> datos, Context contexto){
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
        View view = miInflador.inflate(R.layout.my_row_divisa, parent, false);
        return new ViewHolder(view);
    }

    // Recupera la posicion del array en el que estamos y asigna en TextView el nombre de la divisa
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String divisasCodigo = misdatos.get(position).getCodigo();
        holder.myTextView.setText(divisasCodigo);

        // si esta posicionado ahi y onClickLong es false
        if(posicionSeleccionada == position && !onClickLong){
            holder.myTextView.setBackgroundColor(Color.BLUE);
            holder.myTextView.setTextColor(Color.WHITE);
            // Cogiendo el nombre largo de la divisa (no solo por el codigo)
            holder.myTextView.setText(misdatos.get(position).getName());
        }
        else if(posicionSeleccionada == position && onClickLong){
            holder.myTextView.setBackgroundColor(Color.LTGRAY);
            holder.myTextView.setTextColor(Color.WHITE);
            // Cogiendo el valor del tipo de cambio
            holder.myTextView.setText(misdatos.get(position).getValue());

        }
        else{
            holder.myTextView.setBackgroundColor(Color.WHITE);
            holder.myTextView.setTextColor(Color.BLACK);
        }


        // pequeno pulso
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickLong = false;

                // guardar la posicion seleccionada
                notifyItemChanged(posicionSeleccionada);
                posicionSeleccionada=position;
                notifyItemChanged(posicionSeleccionada);


                // holder.myTextView.setBackgroundColor(Color.RED);
                // holder.myTextView.setTextColor(Color.WHITE);
            }
        });

        // manteniendo pulsado
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onClickLong = true;

                // guardar la posicion seleccionada
                notifyItemChanged(posicionSeleccionada);
                posicionSeleccionada=position;
                notifyItemChanged(posicionSeleccionada);



                return true;
            }
        });
    }


    // total numero de filas
    // cuenta el tamanio del array
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
            myTextView = itemView.findViewById(R.id.tvDivisa);
        }
    }



    // 22/02/2023
    public int getPosicionSeleccionada(){
        return posicionSeleccionada;
    }
    // reiniciar la posicion original seleccionada:
    public void setPosicionSeleccionada() {
        posicionSeleccionada = RecyclerView.NO_POSITION;
    }

}