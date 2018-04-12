package com.foohyfooh.comp3275.project.server;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberManager extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "PhoneNums";
    private static final String COLUMN_NUMBER = "number";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NUMBER + " TEXT NOT NULL);";

    public PhoneNumberManager(Context context) {
        super(context, "Server", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public long insertPhoneNumber(String phoneNumber){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, phoneNumber);

        return database.insert(TABLE_NAME, null, values);
    }

    public List<String> getPhoneNumbers(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);
        List<String> phoneNumbers = new ArrayList<>();
        while (cursor.moveToNext()){
            String phoneNumber = cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER));
            phoneNumbers.add(phoneNumber);
        }
        cursor.close();
        return phoneNumbers;
    }

    public int deletePhoneNumber( String phoneNumber){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, COLUMN_NUMBER + "=?", new String[]{phoneNumber});
    }

}
