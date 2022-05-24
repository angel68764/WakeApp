package com.google.codelabs.mdc.java.wakeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {
    private List<Alarm> mAlarms;

    public AlarmAdapter(List<Alarm> mAlarms) {
        this.mAlarms = mAlarms;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View alarmView = inflater.inflate(R.layout.alarm_list,parent,false);

        ViewHolder viewHolder = new ViewHolder(alarmView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlarmAdapter.ViewHolder holder, int position) {
        Alarm alarm = mAlarms.get(position);

        holder.alarmName.setText(alarm.getName());
        holder.alarmTime.setText(alarm.getTime());
        holder.alarmActive.setChecked(alarm.isActive());
    }

    @Override
    public int getItemCount() {
        return mAlarms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView alarmName;
        private TextView alarmTime;
        private Switch alarmActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            alarmName = (TextView) itemView.findViewById(R.id.alarmName);
            alarmTime = (TextView) itemView.findViewById(R.id.alarmTime);
            alarmActive = (Switch) itemView.findViewById(R.id.alarmActive);
        }
    }
}
