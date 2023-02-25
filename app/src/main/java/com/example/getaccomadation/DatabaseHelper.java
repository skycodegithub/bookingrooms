package com.example.getaccomadation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper( Context context,  String dbname,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table user(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("Email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("user",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        //String select;
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?",str);
        if (c.moveToFirst()){
            result = 1;
        }
        return result;
    }
}
