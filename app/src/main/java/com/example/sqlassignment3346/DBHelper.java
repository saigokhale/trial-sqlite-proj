package com.example.sqlassignment3346;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    long flag;

    public DBHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Studentdetails (roll_no TEXT primary key, name TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Studentdetails");

    }

    public boolean insertdata(String roll, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("roll_no",roll);
        contentValues.put("name",name);
        long flag=db.insert("Studentdetails", null, contentValues);
        if(flag==-1)
            return false;
        return true;
    }

    public boolean updatedata(String roll, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        //contentValues.put("roll_no",roll);
        contentValues.put("name",name);

        Cursor cursor=db.rawQuery("Select * from Studentdetails where roll_no=?",new String[]{roll});
        if(cursor.getCount()>0)
        {
            flag=db.update("Studentdetails",  contentValues, "roll_no=?",new String[] {roll});
        }
        if(flag==-1)
            return false;
        return true;
    }

    public boolean deletedata(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("delete from Studentdetails where name=?",new String[]{name});
        if(cursor.getCount()>0)
        {
            flag=db.delete("Studentdetails", "name=?",new String[] {name});
        }
        if(flag==-1)
            return false;
        return true;
    }

    public Cursor getdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from Studentdetails",null);
        return cursor;
    }
}
