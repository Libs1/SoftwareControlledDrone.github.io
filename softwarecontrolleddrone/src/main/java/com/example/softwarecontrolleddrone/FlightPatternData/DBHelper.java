package com.example.softwarecontrolleddrone.FlightPatternData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Roy on 2016-12-12.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_FILE_NAME = "patterns.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_FILE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ItemsTable.SQL_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ItemsTable.SQL_DELETE);
        onCreate(db);

    }
}
