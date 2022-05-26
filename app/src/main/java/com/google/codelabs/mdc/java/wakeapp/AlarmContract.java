package com.google.codelabs.mdc.java.wakeapp;

import android.provider.BaseColumns;

public class AlarmContract {
    public AlarmContract() {
    }

    public static class AlarmEntry implements BaseColumns{
        public static final String TABLE_NAME = "alarm";
        public static final String NAME = "name";
        public static final String TIME = "time";
        public static final String ACTIVE = "active";
        public static final String [] DAYWEEK = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        };
    }
}
