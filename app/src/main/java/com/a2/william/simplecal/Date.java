package com.a2.william.simplecal;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;


/**
 * Created by William on 2017-11-13.
 */

public class Date {

    Calendar cal = Calendar.getInstance();
    List dateEventList;

    private boolean realDate;

    public Date( int year, int month, int dayOfMonth, boolean realDate){

        dateEventList = new ArrayList<DateEvent>();
        cal.set(year, month, dayOfMonth);
        this.realDate=realDate;
    }

    public String getMonthString(){
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }
    public String getShortMonthString(){
        return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
    }
    public String getDayOfWeekString(){
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }
    public String getShortDayOfWeekString(){
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    }
    public String getDayOfMonthString(){
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }
    public String getYearString(){
        return String.valueOf(cal.get(Calendar.YEAR));
    }
    public boolean getRealDate(){
        return realDate;
    }
    public int getDayOfMonth(){
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    public int getMonth(){
        return cal.get(Calendar.MONTH);
    }
    public List getDateEventList(){
        return dateEventList;
    }
    public void addDateEvent(String eventName, String startTime){
        dateEventList.add(new DateEvent(eventName, startTime));
    }
    @Override
    public String toString(){
        if(!getRealDate()){
            return ""+getShortMonthString()+" "+getYearString()+"";
        }else {
            return "" + getShortDayOfWeekString() + " " + getDayOfMonthString() + " " + getShortMonthString() + ". " + getYearString() + "";
        }
    }
}


