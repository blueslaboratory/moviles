package com.example.waydo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FragmentEventos extends Fragment {

    AdministradorBBDD myAdminBBDD;
    SQLiteDatabase myBBDD;

    public FragmentEventos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_eventos, container, false);

        ArrayList<Evento> eventos = new ArrayList<>();
        RecyclerView myRecyclerViewEventos = vista.findViewById(R.id.RecyclerViewEventos);
        myAdapter adapter;

        myAdminBBDD = new AdministradorBBDD(getContext(), "eventos", null, 1);
        myBBDD = myAdminBBDD.getWritableDatabase();


        // Recuperar eventos de la BBDD y almacenarlos en una lista para su uso posterior
        // QUERIES
        Cursor row = myBBDD.rawQuery(
                "select codigo, fecha, tipo, modulo, estado from eventos", null
        );

        if(row.moveToFirst()){
            do{
                Evento evento = new Evento(row.getInt(0),
                        row.getString(1),
                        row.getString(2),
                        row.getString(3),
                        row.getString(4));

                if(evento.getEstado().equalsIgnoreCase("vigente")){
                    eventos.add(evento);
                }
                // Si quieres ver los finalizados descomenta este else if
                /*
                else if(evento.getEstado().equalsIgnoreCase("finalizado")){
                    eventos.add(evento);
                }
                */
                else{
                    // hacer algo o no hacer nada, esa es la cuestion
                }
            } while (row.moveToNext());
        }
        else{
            Toast.makeText(getContext(), "No hay eventos", Toast.LENGTH_SHORT).show();
        }


        // Establecer un diseño de lista en un RecyclerView, adaptar un conjunto de datos
        // al adaptador del RecyclerView
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getContext());
        myRecyclerViewEventos.setLayoutManager(myLinearLayoutManager);
        adapter = new myAdapter(eventos, getContext());
        myRecyclerViewEventos.setAdapter(adapter);

        // separador entre cada elemento de la lista
        DividerItemDecoration myDividerItemDecoration = new DividerItemDecoration(myRecyclerViewEventos.getContext(), myLinearLayoutManager.getOrientation());
        myRecyclerViewEventos.addItemDecoration(myDividerItemDecoration);

        return vista;
    }


}