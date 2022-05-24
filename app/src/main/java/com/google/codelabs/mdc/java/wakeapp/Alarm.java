package com.google.codelabs.mdc.java.wakeapp;

public class Alarm {
    private String name;
    private String time;
    private boolean active;

    public Alarm(String name, String time, boolean active) {
        this.name = name;
        this.time = time;
        this.active = active;
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
}
