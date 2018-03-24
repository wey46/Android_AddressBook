package com.wey46.mcontacts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.wey46.mcontacts.model.ContactItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void open(){
        mDatabase = mDbHelper.getReadableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }

    public ContactItem createItem(ContactItem item){
        ContentValues values = item.toValues();
        mDatabase.insert(ContactsTable.TABLE_ITEMS, null, values);
        return item;
    }

    public int updateItem(ContactItem item){
        ContentValues values = item.toValues();
        return mDatabase.update(ContactsTable.TABLE_ITEMS, values, ContactsTable.COLUMN_ID + " = ?", new String[] { item.getUId() });
    }

    public int deleteItem(String id){
        return mDatabase.delete(ContactsTable.TABLE_ITEMS, ContactsTable.COLUMN_ID + " = ?", new String[]{id});
    }

    public boolean noDupName(String fname, String lname){
        String whereClause = ContactsTable.COLUMN_LNAME + " = ? AND " + ContactsTable.COLUMN_FNAME + " = ?";
        String[] whereArgs = {
                lname, fname
        };
        Cursor cursor = mDatabase.query(ContactsTable.TABLE_ITEMS, ContactsTable.FULL_NAME,whereClause,whereArgs,null,null,null);
        int count = cursor.getCount();
        //Toast.makeText(mContext, count+"", Toast.LENGTH_SHORT).show();
        return count==0;
    }

    public long getDataItemsCount(){
        return DatabaseUtils.queryNumEntries(mDatabase, ContactsTable.TABLE_ITEMS);
    }

    public void seedDatabase(List<ContactItem> contactItemList){
        long numItems = getDataItemsCount();
        if(numItems==0) {
            for (ContactItem item : contactItemList) {
                try {
                    createItem(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public List<ContactItem> getAllItems(){
        List<ContactItem> contactItems=new ArrayList<>();
        Cursor cursor = mDatabase.query(ContactsTable.TABLE_ITEMS, ContactsTable.ALL_COLUMNS,
                null, null, null, null, ContactsTable.COLUMN_LNAME);
        while (cursor.moveToNext()) {
            ContactItem item = new ContactItem();
            item.setUId(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_ID)));
            item.setLName(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_LNAME)));
            item.setFName(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_FNAME)));
            item.setAddress1(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_ADDRESS1)));
            item.setAddress2(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_ADDRESS2)));
            item.setCity(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_CITY)));
            item.setState(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_COUNTRY)));
            item.setZip(cursor.getInt(
                    cursor.getColumnIndex(ContactsTable.COLUMN_ZIP)));
            item.setCountry(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_COUNTRY)));
            item.setPhone(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_PHONE)));
            item.setEmail(cursor.getString(
                    cursor.getColumnIndex(ContactsTable.COLUMN_EMAIL)));
            contactItems.add(item);
        }
        cursor.close();

        return contactItems;
    }


}
