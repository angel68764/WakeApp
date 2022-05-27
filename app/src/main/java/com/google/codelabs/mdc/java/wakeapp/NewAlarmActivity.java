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
import android.widget.EditText;
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
    private GridView recyclerView;
    private EditText alarmTitle;
    private RepeatAdapter repeatAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_alarm);

        this.setTitle(R.string.newAlarmTitle);

        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        alarmPicker = (TimePicker) findViewById(R.id.timePicker);
        alarmTitle = (EditText) findViewById(R.id.alarmTitle);
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
        switch (item.getItemId()){
            case R.id.saveAlarm:

                AlarmDB alarmDB = new AlarmDB(getApplicationContext());
                Alarm alarm = new Alarm(alarmTitle.getText().toString(), alarmPicker.getHour() + ":" + alarmPicker.getMinute(),true,repeatAdapter.getSelectedWeekDays());
                long alarmId = alarmDB.insertAlarm(alarm);

                alarm.setId((int) alarmId);
                alarm.scheduleAlarm(getApplicationContext());
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}