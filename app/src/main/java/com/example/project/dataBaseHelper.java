package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataBaseHelper extends SQLiteOpenHelper {
    String DataBaseName = "TaskDetails.db";
    String TableName = "Task";
    String Col1 = "ID";
    String Col2 = "TaskName";
    String Col3 = "Date";
    String Col4 = "Time";

    public dataBaseHelper(Context context) {
        super(context, "TaskDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Task(ID INTEGER PRIMARY KEY AUTOInCREMENT,TASKNAME TEXT,DATE TEXT,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists Task");
        onCreate(db);
    }

    //inserting Data
    public void InsertData(String TaskName, String Date, String Time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2, TaskName);
        contentValues.put(Col3, Date);
        contentValues.put(Col4, Time);
        this.getWritableDatabase().insert("Task", null, contentValues);

    }

    public Cursor getTaskData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor task = db.rawQuery("Select * from TASK", null);
        return task;
    }
    public void DeleteData(String task)
    {
        SQLiteDatabase db=this.getWritableDatabase();
       db.execSQL("DELETE FROM TASK WHERE TaskNAME=?", new String[]{task});
    }
}
