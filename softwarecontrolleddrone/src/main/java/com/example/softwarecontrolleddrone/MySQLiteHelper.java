/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/


package com.example.softwarecontrolleddrone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TabLayout;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DroneDB";
    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_CREATE =
            "CREATE TABLE " + TableData.DroneInfo.TABLE_NAME + "(" + TableData.DroneInfo.CURRENT_DATE
                    + " TEXT," + TableData.DroneInfo.FLIGHT_DURATION + " TEXT);";


    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MySQLiteHelper", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.d("MySQLiteHelper", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(SQLiteDatabase db, String date, String flightDuration){

        ContentValues contentValues = new ContentValues();

        contentValues.put(TableData.DroneInfo.CURRENT_DATE, date);
        contentValues.put(TableData.DroneInfo.FLIGHT_DURATION, flightDuration);

        db.insert(TableData.DroneInfo.TABLE_NAME, null, contentValues);
        Log.d("MySQLiteHelper", "one row inserted");

    }

    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;

        String[] columnNames = {TableData.DroneInfo.CURRENT_DATE, TableData.DroneInfo.FLIGHT_DURATION};

        cursor = db.query(TableData.DroneInfo.TABLE_NAME, columnNames, null, null, null, null, null);

        return cursor;
    }

    public void deleteInformation(SQLiteDatabase db)
    {
        db.delete(TableData.DroneInfo.TABLE_NAME, null, null);
    }
}