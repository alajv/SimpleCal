package com.a2.william.simplecal;

import java.util.Calendar;
import java.util.Locale;


/**
 * Created by William on 2017-11-13.
 */

public class Date {

    Calendar cal = Calendar.getInstance();

    private boolean realDate;

    public Date( int year, int month, int dayOfMonth, boolean realDate){

        cal.set(year, month, dayOfMonth);
        this.realDate=realDate;
    }

    public String getMonthString(){
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }
    public String getDayOfWeekString(){
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
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
}


