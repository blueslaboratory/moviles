package com.example.waydo;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter <myAdapter.ViewHolder> {


    ArrayList<Evento> misEventos;
    Context myContext;
    LayoutInflater myInflater;

    int posicionSeleccionada = RecyclerView.NO_POSITION;

    AdministradorBBDD myAdminBBDD;
    SQLiteDatabase myBBDD;


    // CONSTRUCTOR
    public myAdapter(ArrayList<Evento> misEventos, Context myContext) {
        this.misEventos = misEventos;
        this.myContext = myContext;
        this.myInflater = LayoutInflater.from(myContext);
    }


    // GETTERS Y SETTERS
    public int getPosicionSeleccionada() {
        return posicionSeleccionada;
    }
    public void setPosicionSeleccionada(int posicionSeleccionada) {
        this.posicionSeleccionada = posicionSeleccionada;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = myInflater.inflate(R.layout.myrow, parent, false);
        myAdminBBDD = new AdministradorBBDD(myContext, "eventos", null, 1);

        myBBDD = myAdminBBDD.getWritableDatabase();
        return new ViewHolder(vista);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String fecha = misEventos.get(position).getFecha();
        String modulo = misEventos.get(position).getModulo();
        String tipo = misEventos.get(position).getTipo();
        String estado = misEventos.get(position).getEstado();

        holder.mytextViewFecha.setText(fecha);
        holder.mytextViewModulo.setText(modulo);
        holder.mytextViewTipo.setText(tipo);
        holder.mytextViewVigencia.setText(estado);


        // Cambiar el color de la posicion seleccionada
        if(posicionSeleccionada == position){

            int orangeColor = ContextCompat.getColor(myContext, R.color.orange);

            holder.mytextViewFecha.setBackgroundColor(orangeColor);
            holder.mytextViewFecha.setTextColor(Color.BLACK);

            holder.mytextViewModulo.setBackgroundColor(orangeColor);
            holder.mytextViewModulo.setTextColor(Color.BLACK);

            holder.mytextViewTipo.setBackgroundColor(orangeColor);
            holder.mytextViewTipo.setTextColor(Color.BLACK);

            holder.mytextViewVigencia.setBackgroundColor(orangeColor);
            holder.mytextViewVigencia.setTextColor(Color.BLACK);
        }
        // Color por defecto de los no seleccionados
        else{
            coloresPorDefecto(holder);
        }


        // Short click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemChanged(posicionSeleccionada);
                posicionSeleccionada=position;
                // Cambio a la nueva posicion:
                notifyItemChanged(posicionSeleccionada);
            }
        });


        // Long click
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder MyAlert = new AlertDialog.Builder(holder.itemView.getContext());
                MyAlert.setTitle("Vigencia del Evento");
                MyAlert.setMessage("¿Desea declarar el evento como finalizado?");

                int codigo = misEventos.get(position).getCodigo();
                ContentValues registro = new ContentValues();

                MyAlert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        registro.put("estado", "Finalizado");
                        int cantidad = myBBDD.update("eventos", registro, "codigo='" +codigo +"'", null);

                        if (cantidad == 1){
                            Toast.makeText(myContext, "El evento se ha modificado en la base de datos", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(myContext, "El evento no existe en la base de datos", Toast.LENGTH_SHORT).show();
                        }

                        // Quitar el evento marcado como finalizado:
                        //misEventos.remove(posicionSeleccionada);
                        //notifyItemRemoved(posicionSeleccionada);

                        notifyItemChanged(0, misEventos.size());
                    }
                });

                MyAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        registro.put("estado", "Vigente");
                        int cantidad = myBBDD.update("eventos", registro, "codigo='" +codigo +"'", null);

                        if (cantidad == 1){
                            Toast.makeText(myContext, "El evento se ha modificado en la base de datos", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(myContext, "El evento no existe en la base de datos", Toast.LENGTH_SHORT).show();
                        }

                        notifyItemChanged(0, misEventos.size());
                    }
                });


                AlertDialog dialog = MyAlert.create();
                dialog.show();

                return true;
            }
        });
    }


    public void coloresPorDefecto(ViewHolder holder){

        holder.mytextViewModulo.setBackgroundColor(Color.WHITE);
        holder.mytextViewModulo.setTextColor(Color.BLACK);

        holder.mytextViewTipo.setBackgroundColor(Color.WHITE);
        holder.mytextViewTipo.setTextColor(Color.BLACK);

        holder.mytextViewVigencia.setBackgroundColor(Color.WHITE);


        String vigencia = holder.mytextViewVigencia.getText().toString();

        if(vigencia.equalsIgnoreCase("vigente")){
            holder.mytextViewFecha.setBackgroundColor(Color.GREEN);
            holder.mytextViewFecha.setTextColor(Color.WHITE);

            holder.mytextViewVigencia.setTextColor(Color.GREEN);
        }
        else if(vigencia.equalsIgnoreCase("finalizado")){
            holder.mytextViewFecha.setBackgroundColor(Color.RED);
            holder.mytextViewFecha.setTextColor(Color.WHITE);

            holder.mytextViewVigencia.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return misEventos.size();
    }


    // Mi clase ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mytextViewFecha;
        TextView mytextViewModulo;
        TextView mytextViewTipo;
        TextView mytextViewVigencia;

        // CONSTRUCTOR
        public ViewHolder (View itemView){

            super(itemView);

            mytextViewFecha = itemView.findViewById(R.id.textViewFecha);
            mytextViewModulo = itemView.findViewById(R.id.textViewModulo);
            mytextViewTipo = itemView.findViewById(R.id.textViewTipo);
            mytextViewVigencia = itemView.findViewById(R.id.textViewVigencia);
        }
    }
}