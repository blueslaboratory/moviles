package com.example.mammamia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.mammamia.R;
import com.example.mammamia.activities.FullView;
import com.example.mammamia.model.Constant;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    int condition;
    Context context;
    List<Constant> constantList;

    public CustomAdapter(Context context, List<Constant> constantList, int condition) {
        this.context = context;
        this.constantList = constantList;
        this.condition = condition;
    }

    // METODOS DE OVERRIDE AL HACER EN LA CLASE EL EXTENDS BASEADAPTER (modificados)
    @Override
    public int getCount() {
        return constantList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);

        String title = constantList.get(position).getTitle();
        String ingredientes = constantList.get(position).getIngredientes();
        String elaboracion = constantList.get(position).getElaboracion();
        int image = constantList.get(position).getImage();

        ImageView imageView = (ImageView) view.findViewById(R.id.ivItemListThumbnail);
        TextView textView = (TextView) view.findViewById(R.id.tvItemListTitle);
        CardView cardView = (CardView) view.findViewById(R.id.cardViewItemList);

        imageView.setImageResource(image);
        textView.setText(title);

        // Cuando hagamos click en el cardView, pasale los datos extra a la Actividad FullView
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullView.class);

                intent.putExtra("image", image);
                intent.putExtra("title", title);
                intent.putExtra("ingredientes", ingredientes);
                intent.putExtra("elaboracion", elaboracion);
                intent.putExtra("condition", condition);

                context.startActivity(intent);
            }
        });

        return view;
    }
}
