package com.dicka.spring.javacore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dicka.spring.javacore.R;
import com.dicka.spring.javacore.entities.Kategori;

import java.util.List;

/**
 * Created by java-spring on 06/12/17.
 */

public class KategoriAdapter extends ArrayAdapter<Kategori>{

    private List<Kategori> kategoris;
    private Context context;

    public KategoriAdapter(List<Kategori> kategoris, Context context){
        super(context, R.layout.kategori_layout, kategoris);
        this.kategoris = kategoris;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.kategori_layout, parent, false);
        Kategori kategori = this.kategoris.get(position);

        TextView textViewId = view.findViewById(R.id.textViewId);
        TextView textViewNama = view.findViewById(R.id.textViewNama);
        TextView textViewDeskripsi = view.findViewById(R.id.textViewDeskripsi);

        textViewId.setText(String.valueOf(kategori.getIdkategori()));
        textViewNama.setText(String.valueOf(kategori.getNama()));
        textViewDeskripsi.setText(String.valueOf(kategori.getDeskripsi()));
        return view;
    }
}
