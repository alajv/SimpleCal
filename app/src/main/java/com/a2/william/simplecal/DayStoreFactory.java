package com.a2.william.simplecal;

/**
 * Created by William on 2017-11-13.
 */

public class DayStoreFactory {

    public static DayStore dayStore() {
        return new CalendarDayStore();
    }
}
