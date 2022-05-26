package com.google.codelabs.mdc.java.wakeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RepeatAdapter extends RecyclerView.Adapter<RepeatAdapter.ViewHolder> {

    private ArrayList<DayOfWeek> mDays = new ArrayList<>();

    public RepeatAdapter(ArrayList<DayOfWeek> mDays) {
        this.mDays = mDays;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View repeatView = inflater.inflate(R.layout.repeat_list,parent,false);

        ViewHolder viewHolder = new ViewHolder(repeatView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepeatAdapter.ViewHolder holder, int position) {
        DayOfWeek dayOfWeek = mDays.get(position);
        holder.dayName.setText(dayOfWeek.getName());
        holder.dayCheck.setChecked(dayOfWeek.isActive());

        holder.dayCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mDays.get(holder.getAdapterPosition()).setActive(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView dayName;
        private CheckBox dayCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dayName = (TextView) itemView.findViewById(R.id.dayName);
            dayCheck = (CheckBox) itemView.findViewById(R.id.dayCheck);


        }
    }
}
