package com.example.studyplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.CaseMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final String DBName = "Events";
    private static final String tableName = "Events_Table";
    private static final String ID = "ID";
    private static final String title = "Title";
    private static final String time = "Time";
    private static final String date = "Date";
    private static final String type = "Type";
    private static final String description = "Description";



    public MyDBHandler(@Nullable Context context)
    {
        super(context, DBName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "(ID Integer Primary Key AutoIncrement, TITLE Text, DATE Text, TIME Text, TYPE Text, DESCRIPTION Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean add_events(String title,String event,String date,String time,String des)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(MyDBHandler.title,title);
        content.put(MyDBHandler.time,time);
        content.put(MyDBHandler.date,date);
        content.put(MyDBHandler.type,event);
        content.put(MyDBHandler.description,des);

        long row = db.insert(tableName, null, content);
        db.close();

        if(row == -1 || title.isEmpty() || date.isEmpty() || time.isEmpty() ||des.isEmpty())
        {
            return false;
        }
        else
            return true;
    }

    public Cursor get_all_events()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("select * from " + tableName,null);
        return result;
    }

    public int remove_event(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID=?", new String[]{Integer.toString(id)});
    }
}
