package com.example.rocketfuel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoginDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserData.db";
    public static final String TABLE_NAME = "LoginCredentials.db";
    public static final String FULL_NAME = "FULLNAME.db";
    public static final String USERNAME = "USERNAME.db";
    public static final String PASSWORD = "PASSWORD.db";


    public LoginDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + TABLE_NAME + " (" + USERNAME + " INTEGER PRIMARY KEY, " + FULL_NAME + " TEXT, " + PASSWORD + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData (String fullName, String userName, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FULL_NAME, fullName);
        contentValues.put(USERNAME, userName);
        contentValues.put(PASSWORD, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
}
