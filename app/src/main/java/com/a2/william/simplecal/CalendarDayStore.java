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

    private CalendarDayStore() {
        calendarDayList = new LinkedList<>();
        fillList();
    }

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

    public List<Day> getList() {
        return calendarDayList;
    }
}
