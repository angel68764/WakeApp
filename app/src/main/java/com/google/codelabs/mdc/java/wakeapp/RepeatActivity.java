package com.google.codelabs.mdc.java.wakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class RepeatActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RepeatAdapter repeatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle("Repeat");

        final String[] repeat_days = getApplicationContext().getResources().getStringArray(R.array.days);
        ArrayList<DayOfWeek> dayList = new ArrayList<>();

        for (String day : repeat_days) {
            DayOfWeek dayOfWeek = new DayOfWeek(day,false);
            dayList.add(dayOfWeek);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerRepeatList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repeatAdapter = new RepeatAdapter(dayList);
        recyclerView.setAdapter(repeatAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}