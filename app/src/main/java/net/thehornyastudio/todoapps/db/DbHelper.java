package net.thehornyastudio.todoapps.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import net.thehornyastudio.todoapps.model.Tugas;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "db_Tugas";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLES_STD = "tugas";
    private static final String KEY_ID = "id";
    private static final String KEY_JUDUL = "judul";
    private static final String KEY_TUGAS = "tugas";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_TUGAS = "CREATE TABLE "
            + TABLES_STD + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_JUDUL + " TEXT, "
            + KEY_TUGAS + " TEXT" + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TUGAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS '"+ TABLES_STD +"'");
        onCreate(db);
    }

    public void addUser(String judul, String tugas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JUDUL, judul);
        values.put(KEY_TUGAS, tugas);

        db.insert(TABLES_STD, null, values);
    }

    public int updateUser(int id, String judul, String tugas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JUDUL, judul);
        values.put(KEY_TUGAS, tugas);

        return db.update(TABLES_STD, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public ArrayList<Tugas> getAllUser(){
        ArrayList<Tugas> userModel = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLES_STD;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                Tugas tugas = new Tugas();
                tugas.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                tugas.setJudul(c.getString(c.getColumnIndex(KEY_JUDUL)));
                tugas.setTugas(c.getString(c.getColumnIndex(KEY_TUGAS)));
                userModel.add(tugas);
            } while (c.moveToNext());
        }
        return userModel;
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLES_STD, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }
}
