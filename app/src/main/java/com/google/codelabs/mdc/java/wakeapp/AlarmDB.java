package com.google.codelabs.mdc.java.wakeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class AlarmDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Alarm.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AlarmContract.AlarmEntry.TABLE_NAME + " (" +
                    AlarmContract.AlarmEntry._ID + " INTEGER PRIMARY KEY," +
                    AlarmContract.AlarmEntry.NAME + " TEXT," +
                    AlarmContract.AlarmEntry.TIME + " TEXT," +
                    AlarmContract.AlarmEntry.ACTIVE + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[0] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[1] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[2] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[3] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[4] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[5] + " BOOLEAN, " +
                    AlarmContract.AlarmEntry.DAYWEEK[6] + " BOOLEAN)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AlarmContract.AlarmEntry.TABLE_NAME;

    public AlarmDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    public long insertAlarm(Alarm newAlarm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AlarmContract.AlarmEntry.NAME,newAlarm.getName());
        values.put(AlarmContract.AlarmEntry.TIME,newAlarm.getTime());
        values.put(AlarmContract.AlarmEntry.ACTIVE,newAlarm.isActive());

        for (int i = 0; i < newAlarm.getDaysActive().size(); i++)
            values.put(AlarmContract.AlarmEntry.DAYWEEK[i],newAlarm.getDaysActive().get(i));

        long newRowId = db.insert(AlarmContract.AlarmEntry.TABLE_NAME,null,values);

        return newRowId;
    }

    public Cursor findAlarms(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( "select * from "+ AlarmContract.AlarmEntry.TABLE_NAME, null );

        return cursor;
    }
}
