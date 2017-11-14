package com.a2.william.simplecal;


/**
 * Created by William on 2017-11-13.
 */

public class Date {

    private String month;
    private String day;
    private String year;

    public Date(String month, String day, String year){

        this.month=month;
        this.day=day;
        this.year=year;
    }

    public String getMonth(){
        return month;
    }
    public String getDay(){
        return day;
    }
    public String getYear(){
        return year;
    }
}


