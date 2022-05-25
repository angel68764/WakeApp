package com.google.codelabs.mdc.java.wakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RepeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);

        final String[] repeat_days = getApplicationContext().getResources().getStringArray(R.array.days);
        ArrayList<DayOfWeek> dayList = new ArrayList<>();

        for (String day : repeat_days) {
            DayOfWeek dayOfWeek = new DayOfWeek(day,false);
            dayList.add(dayOfWeek);
        }
    }
}