package com.example.softwarecontrolleddrone.FlightPatternData;

/**
 * Created by Roy on 2016-12-12.
 */

public class ItemsTable {

    public static final String TABLE_ITEMS_FPattern = "FlightPattern";
    public static final String COLUMN_ID = "itemId";
    public static final String COLUMN_NAME = "itemName";
//    public static final String COLUMN_DESCRIPTION = "description";
//    public static final String COLUMN_CATEGORY = "category";
//    public static final String COLUMN_POSITION = "sortPosition";
//    public static final String COLUMN_PRICE = "price";
//    public static final String COLUMN_IMAGE = "image";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS_FPattern + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT" +
                    /*COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_CATEGORY + " TEXT," +
                    COLUMN_POSITION + " INTEGER," +
                    COLUMN_PRICE + " REAL," +
                    COLUMN_IMAGE + " TEXT" +*/ ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS_FPattern;
}
