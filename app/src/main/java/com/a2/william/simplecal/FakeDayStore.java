package com.a2.william.simplecal;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-10.
 * Used for test in beginning of project.
 */

public class FakeDayStore implements DayStore {

    ArrayList<Day> dummyDay;


    public FakeDayStore() {
        dummyDay = new ArrayList<>();

        dummyDay.add(new Day(2017, 0, 0, false));
        dummyDay.add(new Day(2017, 0, 1, true));
        dummyDay.add(new Day(2017, 0, 2, true));
        dummyDay.add(new Day(2017, 0, 3, true));
        dummyDay.add(new Day(2017, 0, 4, true));
        dummyDay.add(new Day(2017, 0, 5, true));
        dummyDay.add(new Day(2017, 0, 6, true));
        dummyDay.add(new Day(2017, 1, 0, false));
        dummyDay.add(new Day(2017, 1, 1, true));
        dummyDay.add(new Day(2017, 1, 2, true));
        dummyDay.add(new Day(2017, 1, 3, true));
        dummyDay.add(new Day(2017, 1, 4, true));
        dummyDay.add(new Day(2017, 1, 5, true));
        dummyDay.add(new Day(2017, 1, 6, true));
        dummyDay.add(new Day(2017, 1, 7, true));
        dummyDay.add(new Day(2017, 1, 8, true));
        dummyDay.add(new Day(2017, 1, 9, true));
        dummyDay.add(new Day(2017, 1, 10, true));
        dummyDay.add(new Day(2017, 1, 11, true));


    }

    public ArrayList<Day> getListOfDays() {
        return dummyDay;
    }

}
