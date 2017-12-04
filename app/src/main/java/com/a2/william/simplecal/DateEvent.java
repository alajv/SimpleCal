package com.a2.william.simplecal;

/**
 * Created by William on 2017-12-01.
 */

public class DateEvent {

    private String eventName;
    private String startTime;

    public DateEvent(String eventName, String startTime){

        this.eventName = eventName;
        this.startTime = startTime;
    }

    public String getEventName(){
        return eventName;
    }
    public String getStartTime(){
        return startTime;
    }
}
