package com.google.codelabs.mdc.java.wakeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RepeatActivity extends AppCompatActivity {
    private GridView recyclerView;
    private RepeatAdapter repeatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Repeat");

        final String[] repeat_days = getApplicationContext().getResources().getStringArray(R.array.days);

        recyclerView = (GridView) findViewById(R.id.weekDays);
        repeatAdapter = new RepeatAdapter(repeat_days, this);
        recyclerView.setAdapter((ListAdapter) repeatAdapter);
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