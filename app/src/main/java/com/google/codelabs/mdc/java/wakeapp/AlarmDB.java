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

    public long insertAlarm(Alarm newAlarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AlarmContract.AlarmEntry.NAME, newAlarm.getName());
        values.put(AlarmContract.AlarmEntry.TIME, newAlarm.getTime());
        values.put(AlarmContract.AlarmEntry.ACTIVE, newAlarm.isActive());

        for (int i = 0; i < newAlarm.getDaysActive().size(); i++)
            values.put(AlarmContract.AlarmEntry.DAYWEEK[i], newAlarm.getDaysActive().get(i));

        long newRowId = db.insert(AlarmContract.AlarmEntry.TABLE_NAME, null, values);

        return newRowId;
    }

    public ArrayList<Alarm> findAlarms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + AlarmContract.AlarmEntry.TABLE_NAME, null);

        final int idIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry._ID);
        final int nameIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.NAME);
        final int timeIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.TIME);
        final int activeIndex = cursor.getColumnIndex(AlarmContract.AlarmEntry.ACTIVE);

        ArrayList<Alarm> alarms = new ArrayList<>();
        //alarms.clear();

        while (cursor.moveToNext()) {
            int idAlarm = cursor.getInt(idIndex);
            String nameAlarm = cursor.getString(nameIndex);
            String timeAlarm = cursor.getString(timeIndex);

            ArrayList<Boolean> daysWeek = new ArrayList<>();
            for (int i = cursor.getColumnIndex(AlarmContract.AlarmEntry.DAYWEEK[0]);
                 i <= cursor.getColumnIndex(AlarmContract.AlarmEntry.DAYWEEK[AlarmContract.AlarmEntry.DAYWEEK.length - 1]);
                 i++) {
                daysWeek.add(cursor.getInt(i) > 0);
            }

            boolean activeAlarm = cursor.getInt(activeIndex) > 0;

            Alarm alarm = new Alarm(nameAlarm, timeAlarm, activeAlarm, daysWeek);
            alarm.setId(idAlarm);
            alarms.add(alarm);
        }

        return alarms;
    }

    public int updateAlarm(Alarm alarmUpdate) {
        SQLiteDatabase db = this.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        ContentValues updateReport = new ContentValues();
        updateReport.put(AlarmContract.AlarmEntry.NAME, alarmUpdate.getName());
        updateReport.put(AlarmContract.AlarmEntry.TIME, alarmUpdate.getTime());

        for (int i = 0; i < alarmUpdate.getDaysActive().size(); i++)
            updateReport.put(AlarmContract.AlarmEntry.DAYWEEK[i], alarmUpdate.getDaysActive().get(i));

        updateReport.put(AlarmContract.AlarmEntry.ACTIVE, alarmUpdate.isActive());

// Filter results WHERE "title" = 'My Title'
        String selection = AlarmContract.AlarmEntry._ID + " = ?";
        String[] selectionArgs = {String.valueOf(alarmUpdate.getId())};


        int count = db.update(
                AlarmContract.AlarmEntry.TABLE_NAME,   // The table to query
                updateReport,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs
        );

        return count;
    }
}
