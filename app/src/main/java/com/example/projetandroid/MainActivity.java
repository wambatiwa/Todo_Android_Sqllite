package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import database.DbHelper;
import model.Tache;

public class MainActivity extends AppCompatActivity {
    DbHelper myDB ;
    ImageView empty_imageview;
    TextView no_data;
    ListView ls;


//    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DbHelper(this);
        empty_imageview =findViewById(R.id.emptyDataImage);
        no_data = findViewById(R.id.emptyDataText);
        storeDataInArrays();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.p1:
                Toast.makeText(this, "Projet AKAI", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.p2:
                Toast.makeText(this, "Projet MEETI", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.p3:
                Toast.makeText(this, "Projet Circus", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void storeDataInArrays(){
        int images[] = {R.color.AKAI,R.color.Meeti,R.color.Circus};
        int image;
        ArrayList<Tache> arrayList = new ArrayList<>();
        ls = findViewById(R.id.list);
        Cursor cursor = myDB.getAllTasks();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                String title = cursor.getString(1);
                String id = cursor.getString(0);
                String cat = cursor.getString(2);
                int categorie = Integer.valueOf(cat);
                switch (categorie) {
                    case 1:
                         image = images[0];
                        break;
                    case 2:
                         image = images[1];
                        break;
                    case 3:
                        image = images[2];
                        break;
                    default:
                        image = R.color.white;
                }
                arrayList.add(new Tache(title,image));
                //Toast.makeText(this, "le titre est"+title+" l'id est : "+ id + " la categorie est: "+cat, Toast.LENGTH_SHORT).show();
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            TaskAdapter adapter = new TaskAdapter(this, R.layout.items,arrayList);
            ls.setAdapter(adapter);
        }
    }
    public void ajouter(View v) {
        AddDilog addActivity = new AddDilog();
        addActivity.show(getSupportFragmentManager(), "example dialog");
    }
}