package com.giogen.imagenesurl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.giogen.imagenesurl.Heroe;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

public class AdaptadorHereos  extends RecyclerView.Adapter<AdaptadorHereos.ViewHolder>{
    ArrayList<Heroe> heroesList;
    private Context contexto;
    private LayoutInflater inflador;
    private static final String TAG = "VollHero";

    public AdaptadorHereos(Context contexto, ArrayList<Heroe> heroesList) {
        inflador = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.heroesList = heroesList;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.heroes_model, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Heroe heroe = heroesList.get(position);
        Picasso.get().load(heroe.getImageurl()).into(holder.hero_image);
        holder.hero_name.setText(heroe.getName());
    }

    @Override
    public int getItemCount() {
        return heroesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView hero_image;
        public TextView hero_name;

        public ViewHolder(View itemView)
        {
            super(itemView);
            hero_image = (ImageView) itemView.findViewById(R.id.hero_image);
            hero_image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            hero_name = (TextView) itemView.findViewById(R.id.hero_name);
        }
    }

}
