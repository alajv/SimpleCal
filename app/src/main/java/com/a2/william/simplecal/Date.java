package com.a2.william.simplecal;


/**
 * Created by William on 2017-11-13.
 */

public class Date {

    private String month;
    private String day;
    private String year;
    private int monthNr;
    private int dayNr;

    public Date(String month, String day, String year, int monthNr, int dayNr){

        this.month=month;
        this.day=day;
        this.year=year;
        this.monthNr =monthNr;
        this.dayNr= dayNr;
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
    public int getMonthNr(){
        return monthNr;
    }
    public int getDayNr(){
        return dayNr;
    }
}


