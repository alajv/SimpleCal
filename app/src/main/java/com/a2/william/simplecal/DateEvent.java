package com.a2.william.simplecal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by William on 2017-12-01.
 */

@Entity
public class DateEvent {

    @PrimaryKey(autoGenerate = true)
    int id;
    int year;
    int month;
    int dayOfMonth;
    private String eventName;
    private String startTime;
    private String endTime;

    public DateEvent(int year, int month, int dayOfMonth, String eventName, String startTime, String endTime){

        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getYear(){return year;}
    public int getMonth(){return month;}
    public int getDayOfMonth(){return dayOfMonth;}
    public String getEventName(){
        return eventName;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){return endTime;}
}
