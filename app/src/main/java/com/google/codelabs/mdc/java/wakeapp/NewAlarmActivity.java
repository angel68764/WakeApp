package com.google.codelabs.mdc.java.wakeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class NewAlarmActivity extends AppCompatActivity {
    private TimePicker alarmPicker;
    private AlarmManager alarmManager;
    //private PendingIntent pendingIntent;
    private GridView recyclerView;
    private RepeatAdapter repeatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        this.setTitle(R.string.newAlarmTitle);

        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmPicker = (TimePicker) findViewById(R.id.timePicker);
        final String[] repeat_days = getApplicationContext().getResources().getStringArray(R.array.days);

        recyclerView = (GridView) findViewById(R.id.weekDays);
        repeatAdapter = new RepeatAdapter(repeat_days, this);
        recyclerView.setAdapter(repeatAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_alarm_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        long time;
        switch (item.getItemId()){
            case R.id.saveAlarm:

                AlarmDB alarmDB = new AlarmDB(getApplicationContext());
                Alarm alarm = new Alarm("alarma", alarmPicker.getHour() + ":" + alarmPicker.getMinute(),true,repeatAdapter.getSelectedWeekDays());
                long alarmId = alarmDB.insertAlarm(alarm);

                alarm.setId((int) alarmId);
                alarm.schedule(getApplicationContext());
                finish();
                /*
                Calendar calendar = Calendar.getInstance();
                //calendar.setTimeInMillis(System.currentTimeMillis());
                calendar.set(Calendar.HOUR_OF_DAY, alarmPicker.getHour());
                calendar.set(Calendar.MINUTE, alarmPicker.getMinute());
                calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Intent intent = new Intent(this,AlarmReceiver.class);

                pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

                String toastText = null;
                try {
                    toastText = String.format("One Time Alarm scheduled for %s at %02d:%02d", calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.getDefault()), alarmPicker.getHour(), alarmPicker.getMinute());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();

                //time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                /*if (System.currentTimeMillis() > time) {
                    // setting time as AM and PM
                    if (calendar.AM_PM == 0)
                        time = time + (1000 * 60 * 60 * 12);
                    else
                        time = time + (1000 * 60 * 60 * 24);
                }*/

/*
                AlarmDB alarmDB = new AlarmDB(getApplicationContext());
                alarmDB.insertAlarm(new Alarm("alarma", alarmPicker.getHour() + ":" + alarmPicker.getMinute(),true,repeatAdapter.getSelectedWeekDays()));

                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);
                finish();
                */
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}