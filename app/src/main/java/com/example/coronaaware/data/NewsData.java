package com.example.coronaaware.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NewsData extends SQLiteOpenHelper {

    private static final String DataBase_NAME = "CoronaNews.db";
    private static final String Table_Name = "NEWS_CORONA_TABLE";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TOPIC";

    public NewsData(@Nullable Context context) {
        super(context, DataBase_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean addData(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, topic);

        long result = db.insert(Table_Name, null, contentValues);
        return result != -1;
    }

    public Cursor showData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }
}
