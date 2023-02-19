package com.example.projetandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Tache;

public class TaskAdapter extends ArrayAdapter<Tache> {
    private Context mcontext;
    private int mResource;
    public TaskAdapter(@NonNull Context context, int resource, ArrayList<Tache> objects) {
        super(context, resource,objects);
        this.mcontext =context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(mcontext);

        convertView = inf.inflate(mResource,parent,false);

        ImageView img = convertView.findViewById(R.id.image1);

        TextView nom = convertView.findViewById(R.id.textName);

        img.setImageResource(getItem(position).getCat());

        nom.setText(getItem(position).getTitle());
        return convertView;
    }
}
