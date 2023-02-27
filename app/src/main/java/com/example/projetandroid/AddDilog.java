package com.example.projetandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import database.DbHelper;
import model.Tache;

public class AddDilog extends AppCompatDialogFragment {
    private EditText edit_title;
    private Spinner spinnerEnt;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("AJOUTER UNE TACHE")
                .setPositiveButton("AJOUTER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DbHelper myDB = new DbHelper(getActivity());

                        String title = edit_title.getText().toString().trim();
                        int ent = spinnerEnt.getSelectedItemPosition();
                        System.out.println(title + " -- " + ent);
                        switch (ent) {
                            case 0:
                                Tache t0 = new Tache(title,1);
                                myDB.insertUser(t0);
                                System.out.println(title + " -- entered in db " + ent);
                                break;
                            case 1:
                                Tache t1 = new Tache(title,2);
                                myDB.insertUser(t1);
                                System.out.println(title + " -- entered in db " + ent);
                                break;
                            case 2:
                                Tache t2 = new Tache(title,3);
                                myDB.insertUser(t2);
                                System.out.println(title + " -- entered in db " + ent);
                                break;
                        }
                        Intent in = new Intent(getContext(),MainActivity.class);
                        startActivity(in);
                    }
                });

        edit_title = view.findViewById(R.id.editText);
        spinnerEnt = view.findViewById(R.id.projects_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.projects_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item);
        spinnerEnt.setAdapter(adapter);

        return builder.create();
    }
}
