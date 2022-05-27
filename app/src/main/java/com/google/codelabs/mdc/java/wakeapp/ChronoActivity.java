package com.google.codelabs.mdc.java.wakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ChronoActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private BottomNavigationView bottomNavigationView;

    private boolean started = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.chronoScreen);

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

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        View pauseButton = findViewById(R.id.button2);
        View restoreButton = findViewById(R.id.button3);
        restoreButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
    }

    public void startChronometer(View v) {
        if (!running) {
            View startButton = findViewById(R.id.button);
            startButton.setVisibility(View.INVISIBLE);
            View pauseButton = findViewById(R.id.button2);
            pauseButton.setVisibility(View.VISIBLE);
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();

            running = true;
        }
        if(started == false){
            started = true;
            View restore = findViewById(R.id.button3);
            restore.setVisibility(View.VISIBLE);
        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            View startButton = findViewById(R.id.button);
            startButton.setVisibility(View.VISIBLE);
            View pauseButton = findViewById(R.id.button2);
            pauseButton.setVisibility(View.INVISIBLE);
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }

    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        View restore = findViewById(R.id.button3);
        restore.setVisibility(View.INVISIBLE);
        View startButton = findViewById(R.id.button);
        startButton.setVisibility(View.VISIBLE);
        View pausebutton = findViewById(R.id.button2);
        pausebutton.setVisibility(View.INVISIBLE);

        chronometer.stop();
        running = false;
        started = false;
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
    }
}