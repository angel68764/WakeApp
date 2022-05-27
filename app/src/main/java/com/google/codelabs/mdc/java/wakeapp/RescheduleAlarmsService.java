package com.google.codelabs.mdc.java.wakeapp;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleService;

import java.util.ArrayList;

public class RescheduleAlarmsService extends LifecycleService {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        AlarmDB alarmDB = new AlarmDB(getApplication());
        ArrayList<Alarm> alarms = alarmDB.findAlarms();

        for (Alarm a : alarms) {
            if (a.isActive()) {
                a.scheduleAlarm(getApplicationContext());
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        super.onBind(intent);
        return null;
    }
}
