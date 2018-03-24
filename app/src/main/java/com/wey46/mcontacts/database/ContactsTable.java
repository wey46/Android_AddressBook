package com.wey46.mcontacts.database;

public class ContactsTable {
    public static final String TABLE_ITEMS = "contacts";
    public static final String COLUMN_ID = "uid";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_ADDRESS1 = "address1";
    public static final String COLUMN_ADDRESS2 = "address2";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIP = "zip";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static final String[] ALL_COLUMNS = {
            COLUMN_ID, COLUMN_LNAME, COLUMN_FNAME, COLUMN_ADDRESS1, COLUMN_ADDRESS2, COLUMN_CITY,
            COLUMN_STATE, COLUMN_ZIP, COLUMN_COUNTRY, COLUMN_PHONE, COLUMN_EMAIL
    };

    public static final String[] FULL_NAME = {
            COLUMN_LNAME, COLUMN_FNAME
    };

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_ITEMS + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_LNAME + " TEXT," +
                    COLUMN_FNAME + " TEXT," +
                    COLUMN_ADDRESS1 + " TEXT," +
                    COLUMN_ADDRESS2 + " TEXT," +
                    COLUMN_CITY + " TEXT," +
                    COLUMN_STATE + " TEXT," +
                    COLUMN_ZIP + " INTEGER," +
                    COLUMN_COUNTRY + " TEXT," +
                    COLUMN_PHONE + " TEXT," +
                    COLUMN_EMAIL + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + TABLE_ITEMS;
}

