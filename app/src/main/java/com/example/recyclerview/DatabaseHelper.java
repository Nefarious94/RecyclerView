package com.example.recyclerview;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database";
    public static String TABLE_NAME = "items";
    public static String COL1 = "id";
    public static String COL2 = "name";
    private ArrayList<String> list = new ArrayList<String>();
    Context c;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR)");
        //db.execSQL("CREATE TABLE if not exists items(id INTEGER PRIMARY KEY AUTOINCREMENT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addProduct(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, "");
        contentValues.put(COL2,item);
        long exito = db.insert(TABLE_NAME,null,contentValues);
        Log.d("exit",String.valueOf(exito));
        if(exito==-1){
            return false;
        }
        return true;
        //db.close();
    }

    // update
    public void updateProduct(String item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        db.insert("items", null, contentValues);
        try {
            String[] args = {"Word 3"};
            db.update("items", contentValues, COL1 + " = ? ", args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
    }
}