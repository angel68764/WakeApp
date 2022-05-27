package com.google.codelabs.mdc.java.wakeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    private ArrayList<Boolean> listDays = new ArrayList<>();
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            String toastText = "Alarm Reboot";
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            startRescheduleAlarmsService(context);
        }
        else {
            String toastText = "Alarm Received";
            Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
            listDays = (ArrayList<Boolean>) intent.getSerializableExtra("days");
            boolean recurrent = false;
            for (boolean value: listDays) {
                if (value){
                    recurrent = true;
                }
            }
            if (!recurrent) {
                startAlarmService(context, intent);
            } {
                if (alarmIsToday(intent)) {
                    startAlarmService(context, intent);
                }
            }
        }
    }

    private boolean alarmIsToday(Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int today = calendar.get(Calendar.DAY_OF_WEEK);

        switch(today) {
            case Calendar.MONDAY:
                if(listDays.get(0))
                    return true;
                return false;
            case Calendar.TUESDAY:
                if(listDays.get(1))
                    return true;
                return false;
            case Calendar.WEDNESDAY:
                if(listDays.get(2))
                    return true;
                return false;
            case Calendar.THURSDAY:
                if(listDays.get(3))
                    return true;
                return false;
            case Calendar.FRIDAY:
                if(listDays.get(4))
                    return true;
                return false;
            case Calendar.SATURDAY:
                if(listDays.get(5))
                    return true;
                return false;
            case Calendar.SUNDAY:
                if(listDays.get(6))
                    return true;
                return false;
        }
        return false;
    }

    private void startAlarmService(Context context, Intent intent) {
        Intent intentService = new Intent(context, AlarmService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

    private void startRescheduleAlarmsService(Context context) {
        Intent intentService = new Intent(context, RescheduleAlarmsService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intentService);
        } else {
            context.startService(intentService);
        }
    }

}
