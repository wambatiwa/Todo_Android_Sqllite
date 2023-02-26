package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import model.Tache;

public class DbHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Tache";
    private static final String DATABASE_NAME = "dbTache";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tache(id INTEGER PRIMARY KEY autoincrement,title TEXT,cat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllTasks() {
        String query = "SELECT * FROM "+ TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public void insertUser(Tache t1) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("title",t1.getTitle());
        cv.put("cat",t1.getCat());

        db.insert(TABLE_NAME,null,cv);
        db.close();
    }
    public void deleteByTitle(String titre) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "title=?",new String[] {String.valueOf(titre)});
        if(result == -1) {
            System.out.println("echec de la suppresion");
        } else {
            System.out.println("suppresion reussie");
        }
        db.close();
    }
    public Cursor getTask(int cat) {
        //HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "select * from  " + TABLE_NAME + " where cat =" + "'" + cat + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Tache user = null;
//        if (cursor.getCount() == 0) {
//            return null;
//        } else {
            if(db != null){
                cursor = db.rawQuery(selectQuery, null);
            }
        //}
        return cursor;
    }
}
