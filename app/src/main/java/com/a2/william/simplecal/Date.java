package com.a2.william.simplecal;


/**
 * Created by William on 2017-11-13.
 */

public class Date {

    private String month;
    private String day;

    public Date(String month, String day){

        this.month=month;
        this.day=day;
    }

    public String getMonth(){
        return month;
    }
    public String getDay(){
        return day;
    }
}


