package com.google.codelabs.mdc.java.wakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class NewAlarmActivity extends AppCompatActivity {
    private TimePicker alarmPicker;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("New alarm");

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmPicker = (TimePicker) findViewById(R.id.timePicker);
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
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmPicker.getHour());
                calendar.set(Calendar.MINUTE, alarmPicker.getMinute());
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

                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}