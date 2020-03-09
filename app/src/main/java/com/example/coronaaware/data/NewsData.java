package com.example.coronaaware.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author DedUndead
 * @version 1.0
 * References: https://www.youtube.com/watch?v=cp2rL3sAFmI
 * References: https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 */

public class NewsData extends SQLiteOpenHelper {

    private static final String DataBase_NAME = "CoronaNews.db";
    private static final String Table_Name = "NEWS_CORONA_TABLE";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TOPIC";

    public NewsData(@Nullable Context context) {
        super(context, DataBase_NAME, null, 1);
    }

    /**
     * Create SQLite data table
     * @param db current table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TOPIC TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    /**
     * Add data to the News database
     * @param topic Article topic
     * @return Was the data added ot not
     */
    public boolean addData(String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, topic);

        long result = db.insert(Table_Name, null, contentValues);
        return result != -1;
    }

    /**
     *
     * @return SQLite table with all the columns
     */
    public Cursor showData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + Table_Name, null);
    }

    /**
     * Delete a raw by ID
     * @param ID of the raw
     * @return the number of deleted rows
     */
    public Integer deleteData(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name, "ID = ? ", new String[] {ID});
    }
}