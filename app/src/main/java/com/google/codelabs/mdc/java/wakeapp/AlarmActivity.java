package com.google.codelabs.mdc.java.wakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    ArrayList<Alarm> alarms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Button button = (Button) findViewById(R.id.buttonPanel);

        RecyclerView rwAlarms = (RecyclerView) findViewById(R.id.recyclerAlarmList);

        for (int i = 1; i < 5; i++) {
            Alarm alarm = new Alarm( "Alarma " + i, i + " : " + i, true);
            alarms.add(alarm);
        }

        AlarmAdapter alarmAdapter = new AlarmAdapter(alarms);

        rwAlarms.setAdapter(alarmAdapter);

        rwAlarms.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChronoActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alarm_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newAlarm:
                startActivity(new Intent(getApplicationContext(),NewAlarmActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}