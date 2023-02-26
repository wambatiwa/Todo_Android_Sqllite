package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
    TextView no_data,no_cat;
    ListView ls;
    SwipeRefreshLayout swipeRefreshLayout;

//    FloatingActionButton add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DbHelper(this);
        empty_imageview =findViewById(R.id.emptyDataImage);
        no_data = findViewById(R.id.emptyDataText);
        no_cat = findViewById(R.id.emptyDataCat);
        storeDataInArrays();
        swipeRefreshLayout = findViewById(R.id.swipeContainer);
        storeDataInArrays();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                storeDataInArrays();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        ls = findViewById(R.id.list);

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tache selectedItem = (Tache) parent.getItemAtPosition(position);
                String iddelete = selectedItem.getTitle();
                System.out.println(iddelete);
                dialog(iddelete);
            }
        });
    }
    public void dialog( String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Are you sure you want to delete this task ?");

        builder.setTitle("Delete Task ?");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            myDB.deleteByTitle(id);
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
                displayTaskByCat(1);
                Toast.makeText(this, "Projet AKAI", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.p2:
                displayTaskByCat(2);
                Toast.makeText(this, "Projet MEETI", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.p3:
                displayTaskByCat(3);
                Toast.makeText(this, "Projet Circus", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    void displayTaskByCat(int cat) {
        int images[] = {R.color.AKAI, R.color.Meeti, R.color.Circus};
        int image;
        ArrayList<Tache> arrayList = new ArrayList<>();
        Cursor cursor = myDB.getTask(cat);
        if (cursor.getCount() == 0) {
            ls.setVisibility(View.GONE);
            empty_imageview.setVisibility(View.VISIBLE);
            no_cat.setVisibility(View.VISIBLE);
        } else {
            ls.setVisibility(View.VISIBLE);
            no_cat.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                String title = cursor.getString(1);
                String id = cursor.getString(0);
                String cath = cursor.getString(2);
                int categorie = Integer.valueOf(cath);
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
                arrayList.add(new Tache(title, image));
                //Toast.makeText(this, "le titre est"+title+" l'id est : "+ id + " la categorie est: "+cat, Toast.LENGTH_SHORT).show();
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
            TaskAdapter adapter = new TaskAdapter(this, R.layout.items, arrayList);
            ls.setAdapter(adapter);
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
            ls.setVisibility(View.VISIBLE);
            no_cat.setVisibility(View.GONE);
            while (cursor.moveToNext()){
                String title = cursor.getString(1);
                String id = cursor.getString(0);
                //System.out.println(id);
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
        addActivity.show(getSupportFragmentManager(), "exampledialog");
    }
}