package com.example.e014_apidog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

// 25/04/2023

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.ViewHolder> {

    private DogRespuesta my_dogRespuesta;
    private LayoutInflater myInflater;
    Context mi_contexto;

    // Constructor
    public MiAdaptador(Context contexto, DogRespuesta data){
        this.myInflater = LayoutInflater.from(contexto);
        this.my_dogRespuesta = data;
        this.mi_contexto = contexto;
    }


    @NonNull
    @Override
    public MiAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myInflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiAdaptador.ViewHolder holder, int position) {
        String url = my_dogRespuesta.getImage(position);
        Glide.with(mi_contexto)
                .load(url)
                .into(holder.imV1);
    }

    @Override
    public int getItemCount() {
        return my_dogRespuesta.getCountImage();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imV1; // castea la ImageView del row
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imV1 = (ImageView) itemView.findViewById(R.id.imV1);
        }
    }
}