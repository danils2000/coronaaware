package com.example.coronaaware.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserData extends SQLiteOpenHelper {

    private static final String DataBase_NAME = "CoronaUser.db";
    private static final String Table_Name = "CORONA_USER_TABLE";
    public static final String COL_1 = "NUMBER";
    public static final String COL_2 = "DAY";
    public static final String COL_3 = "TEMPERATURE";
    public static final String COL_4 = "RATE";

    public UserData(@Nullable Context context) {
        super(context, DataBase_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, DAY TEXT, TEMPERATURE INTEGER, RATE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    public boolean Add_Data(String day, String temperature, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, day);
        contentValues.put(COL_3, temperature);
        contentValues.put(COL_4, rate);

        long result = db.insert(Table_Name, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor Show_Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;
    }
}
