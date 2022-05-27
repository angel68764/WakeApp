package com.google.codelabs.mdc.java.wakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    private ArrayList<Alarm> alarms = new ArrayList<>();
    private BottomNavigationView bottomNavigationView;
    private AlarmDB alarmDB;
    private AlarmAdapter alarmAdapter;
    private RecyclerView rwAlarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        initDatabase();

        rwAlarms = findViewById(R.id.recyclerAlarmList);
        alarmAdapter = new AlarmAdapter(alarms, this);
        rwAlarms.setAdapter(alarmAdapter);
        rwAlarms.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.alarmScreen);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected( MenuItem item) {
                switch (item.getItemId()){
                    case R.id.chronoScreen:
                        finish();
                        startActivity(new Intent(getApplicationContext(),ChronoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.alarmScreen:
                        finish();
                        startActivity(new Intent(getApplicationContext(),AlarmActivity.class));
                        overridePendingTransition(0,0);

                        return true;

                }
                return false;
            }
        });
    }

    private void initDatabase() {
        alarmDB = new AlarmDB(getApplicationContext());
        alarms = alarmDB.findAlarms();

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

    @Override
    protected void onResume() {
        super.onResume();

        initDatabase();

        alarmAdapter.setmAlarms(alarms);

        alarmAdapter.notifyDataSetChanged();

    }
}