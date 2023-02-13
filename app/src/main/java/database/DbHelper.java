package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        db.insert("user",null,cv);
        db.close();
    }
}
