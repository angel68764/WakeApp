package com.google.codelabs.mdc.java.wakeapp;

import android.content.Context;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RepeatAdapter extends BaseAdapter {

    private String[] mDays;
    private Context context;
    private LayoutInflater inflater = null;
    private ArrayList<Boolean> selectedWeekDays = new ArrayList<>(Arrays.asList(new Boolean[7]));

    public RepeatAdapter(String[] mDays, Context context) {
        this.mDays = mDays;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        Collections.fill(selectedWeekDays, Boolean.FALSE);
    }

    public ArrayList<Boolean> getSelectedWeekDays() {
        return selectedWeekDays;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(holder == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.day_button, null);
            holder.weekDay = (Button) convertView.findViewById(R.id.dayButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String dayOfWeek = mDays[position];
        holder.weekDay.setText(dayOfWeek);
        holder.weekDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonDay = v.findViewById(R.id.dayButton) ;
                if(selectedWeekDays.get(position) == false){
                    selectedWeekDays.set(position, true);
                    buttonDay.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.white));
                } else {
                    selectedWeekDays.set(position, false);
                    buttonDay.setTextColor(ContextCompat.getColor(context.getApplicationContext(), R.color.white_transparent));

                }
            }


        });

        return convertView;
    }

    public static class ViewHolder{
        private Button weekDay;
    }
}
