package com.google.codelabs.mdc.java.wakeapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Alarm {
    private int id;
    private String name;
    private String time;
    private boolean active;
    private ArrayList<Boolean> daysActive;

    public Alarm(String name, String time, boolean active, ArrayList<Boolean> daysActive) {
        this.name = name;
        this.time = time;
        this.active = active;
        this.daysActive = daysActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Boolean> getDaysActive() {
        return daysActive;
    }

    public void setDaysActive(ArrayList<Boolean> daysActive) {
        this.daysActive = daysActive;
    }

    public void cancelAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);
        alarmManager.cancel(alarmPendingIntent);
        this.active = false;

        String toastText = String.format("Alarm cancelled for %s with id %d", time, id);
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
        Log.i("cancel", toastText);
    }

    public void scheduleAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("days", (Serializable) daysActive);
        intent.putExtra("name", name);

        PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(context, id, intent, 0);
        String[] times = time.split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(times[0]));
        calendar.set(Calendar.MINUTE, Integer.valueOf(times[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // if alarm time has already passed, increment day by 1
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        }

        boolean recurrent = false;
        for (boolean value: daysActive) {
            if (value){
                recurrent = true;
            }
        }

        if (!recurrent) {
            String toastText = null;
            try {
                toastText = String.format("One Time Alarm %s scheduled for %s at %02d:%02d", name, calendar.getDisplayName(Calendar.DAY_OF_WEEK,Calendar.LONG, Locale.getDefault()), Integer.valueOf(times[0]), Integer.valueOf(times[1]), id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();

            alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    alarmPendingIntent
            );
        } else {
            String toastText = String.format("Recurring Alarm %s scheduled for %s at %02d:%02d", name, getRecurringDaysText(), Integer.valueOf(times[0]), Integer.valueOf(times[1]), id);
            Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();

            final long RUN_DAILY = 24 * 60 * 60 * 1000;
            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    RUN_DAILY,
                    alarmPendingIntent
            );
        }

    }

    public String getRecurringDaysText() {

        String days = "";
        if (daysActive.get(0)) {
            days += "Mo ";
        }
        if (daysActive.get(1)) {
            days += "Tu ";
        }
        if (daysActive.get(2)) {
            days += "We ";
        }
        if (daysActive.get(3)) {
            days += "Th ";
        }
        if (daysActive.get(4)) {
            days += "Fr ";
        }
        if (daysActive.get(5)) {
            days += "Sa ";
        }
        if (daysActive.get(6)) {
            days += "Su ";
        }

        return days;
    }
}
