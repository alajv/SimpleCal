package com.a2.william.simplecal;

/**
 * Created by William on 2017-12-01.
 */

public class DateEvent {

    private String eventName;
    private String startTime;
    private String endTime;

    public DateEvent(String eventName, String startTime, String endTime){

        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventName(){
        return eventName;
    }
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){return endTime;}
}
