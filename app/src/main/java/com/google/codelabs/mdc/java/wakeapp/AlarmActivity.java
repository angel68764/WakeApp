package com.google.codelabs.mdc.java.wakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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
    ArrayList<Alarm> alarms = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
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


        RecyclerView rwAlarms = (RecyclerView) findViewById(R.id.recyclerAlarmList);

        for (int i = 1; i < 5; i++) {
            Alarm alarm = new Alarm( "Alarma " + i, i + " : " + i, true);
            alarms.add(alarm);
        }

        AlarmAdapter alarmAdapter = new AlarmAdapter(alarms);

        rwAlarms.setAdapter(alarmAdapter);

        rwAlarms.setLayoutManager(new LinearLayoutManager(this));

    }

    /*private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //this is a helper class that replaces the container with the fragment. You can replace or add fragments.
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null); //if you add fragments it will be added to the backStack. If you replace the fragment it will add only the last fragment
        transaction.commit(); // commit() performs the action
    }*/

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