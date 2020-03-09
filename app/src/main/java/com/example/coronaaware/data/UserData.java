package com.example.coronaaware.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

/**
 * @author andreyverbovskiy
 * @version 1.0
 * References: https://www.youtube.com/watch?v=cp2rL3sAFmI
 * References: https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 */
public class UserData extends SQLiteOpenHelper {

    private static final String DataBase_NAME = "CoronaUser.db";
    private static final String Table_Name = "CORONA_USER_TABLE";
    private static final String COL_1 = "NUMBER";
    private static final String COL_2 = "DAY";
    private static final String COL_3 = "TEMPERATURE";
    private static final String COL_4 = "RATE";

    public UserData(@Nullable Context context) {
        super(context, DataBase_NAME, null, 1);
    }

    /**
     * Create SQLite data table
     * @param db current table
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(NUMBER INTEGER PRIMARY KEY AUTOINCREMENT, DAY TEXT, TEMPERATURE INTEGER, RATE INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    /**
     * Add data to the User database
     * @param day Date at which user added data
     * @param temperature Users current temperature
     * @param rate Possile symptoms and overall health rating
     * @return Was the data added ot not
     */

    public boolean addData(String day, String temperature, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, day);
        contentValues.put(COL_3, temperature);
        contentValues.put(COL_4,  rate);

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
     * Delete data by ID
     * @param ID of the row
     * @return the number of deleted rows
     */

    public Integer deleteData(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_Name, "NUMBER = ? ", new String[] {ID});
    }
}
