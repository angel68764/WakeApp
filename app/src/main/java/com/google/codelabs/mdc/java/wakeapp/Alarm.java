package com.google.codelabs.mdc.java.wakeapp;

import java.util.ArrayList;

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
}
