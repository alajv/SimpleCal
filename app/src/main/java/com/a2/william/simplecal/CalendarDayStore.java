package com.a2.william.simplecal;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by William on 2017-11-30.
 */

public class CalendarDayStore implements DayStore {
    private static final String TAG = "CalendarDayStore";
    private LinkedList<Day> calendarDayList;

    public CalendarDayStore() {
        calendarDayList = new LinkedList<>();
        fillList();
    }

    /*
    Fills calendarDayList with 365 days, starting from this day.
    If the month of the Day that is about to be added to the list
    is not equal to the month of the last added day, two day objects
    will be added to the list. One with realDay: false, and one
    with realDay: true. The one with realDay: false will be shown as
    month and year header in the listView.
     */
    private void fillList() {
        Calendar cal = Calendar.getInstance();

        for (int i = 0; i < 365; i++) {

            if (calendarDayList.size() == 0) {
                calendarDayList.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            } else if (calendarDayList.getLast().getMonth() != cal.get(Calendar.MONTH)) {
                calendarDayList.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), false));
                calendarDayList.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            } else {
                calendarDayList.add(new Day(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            }
        }
    }

    public List<Day> getListOfDays() {
        return calendarDayList;
    }
}
