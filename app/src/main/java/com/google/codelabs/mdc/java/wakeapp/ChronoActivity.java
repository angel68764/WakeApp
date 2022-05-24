package com.google.codelabs.mdc.java.wakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class ChronoActivity extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private boolean started = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                /*if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(ChronoActivity.this, "Bing!", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

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