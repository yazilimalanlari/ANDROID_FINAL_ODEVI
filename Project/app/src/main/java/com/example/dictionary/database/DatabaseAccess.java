package com.example.dictionary.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;


    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }

        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db != null){
            this.db.close();
        }
    }

    public List<String> getWords(String word){
        c = db.rawQuery("SELECT * FROM words WHERE tr LIKE '" + word + "%' OR en LIKE '" + word + "%'", new String[]{});
        List<String> result = new ArrayList();
        while(c.moveToNext()){
            result.add(c.getString(1) + " : " + c.getString(2));
        }

        return result;
    }
}
