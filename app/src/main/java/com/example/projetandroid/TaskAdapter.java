package com.example.projetandroid;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

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
}
