package com.a2.william.simplecal;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Created by William on 2017-12-01.
 */

@Entity
public class DayEvent {
    /*
    id is PrimaryKey, Room autogenerates
    id for DayEvent in DB.
     */
    @PrimaryKey(autoGenerate = true)
    int id;
    int year;
    int month;
    int dayOfMonth;
    private String eventName;
    private String startTime;
    private String endTime;

    /*
    This constructor is called when adding
    DayEvents for dayEventList in Day.
    Room ignores this constructor.
     */
    @Ignore
    public DayEvent(int year, int month, int dayOfMonth, String eventName, String startTime, String endTime, int id){

        this.id = id;
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    /*
    This constructor is used when adding
    DayEvents to DB. Id is autogenerated.
     */
    public DayEvent(int year, int month, int dayOfMonth, String eventName, String startTime, String endTime) {

        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        Log.d(TAG, "DayEvent:"+id);
    }

    /*
    Method was supposed to be named getId(), but room
    didn't like that name. Don't know why.
     */
    public int idPlease(){
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public String getEventName() {
        return eventName;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
