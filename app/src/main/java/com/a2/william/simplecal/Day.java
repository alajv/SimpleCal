package com.a2.william.simplecal;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Locale;
import java.util.List;


/**
 * Created by William on 2017-11-13.
 */

public class Day {

    private Calendar cal = Calendar.getInstance();
    private List dayEventList;
    private boolean realDay;

    public Day(int year, int month, int dayOfMonth, boolean realDay) {

        dayEventList = new ArrayList<DayEvent>();
        cal.set(year, month, dayOfMonth);
        this.realDay = realDay;
    }

    public String getMonthString() {
        return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }

    public String getShortMonthString() {
        return cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
    }

    public String getDayOfWeekString() {
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault());
    }

    public String getShortDayOfWeekString() {
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault());
    }

    public String getDayOfMonthString() {
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public String getYearString() {
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    public boolean isRealDay() {
        return realDay;
    }

    public int getDayOfMonth() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return cal.get(Calendar.MONTH);
    }

    public int getYear() {
        return cal.get(Calendar.YEAR);
    }

    public List getDayEventList() {
        return dayEventList;
    }

    public void addDayEvent(int year, int month, int dayOfMonth, String eventName, String startTime, String endTime) {
        dayEventList.add(new DayEvent(year, month, dayOfMonth, eventName, startTime, endTime));
    }

    @Override
    public String toString() {
        if (!isRealDay()) {
            return "" + getShortMonthString() + " " + getYearString() + "";
        } else {
            return "" + getShortDayOfWeekString() + " " + getDayOfMonthString() + " " + getShortMonthString() + ". " + getYearString() + "";
        }
    }
}


