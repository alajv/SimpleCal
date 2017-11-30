package com.a2.william.simplecal;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by William on 2017-11-30.
 */

public class CalendarDateStore implements DateStore{
    private static final String TAG = "CalendarDateStore";
    ArrayList<Date> CalendarDateList;

    public CalendarDateStore(){
        CalendarDateList = new ArrayList<>();

        fillList();

    }
    private void fillList(){

        Calendar cal = Calendar.getInstance();

        for(int i = 0; i<365; i++){
            CalendarDateList.add(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
            cal.add(Calendar.DATE, 1);
        }
    }
    public ArrayList<Date> getList(){
        return CalendarDateList;
    }
}
