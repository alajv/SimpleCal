package com.a2.william.simplecal;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by William on 2017-11-30.
 */

public class CalendarDateStore implements DateStore{
    private static final String TAG = "CalendarDateStore";
    LinkedList<Date> CalendarDateList;

    public CalendarDateStore(){
        CalendarDateList = new LinkedList<>();
        fillList();
    }

    private void fillList(){

        Calendar cal = Calendar.getInstance();

        for(int i = 0; i<365; i++){

            if(CalendarDateList.size()==0){
                CalendarDateList.add(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            }
            else if(CalendarDateList.getLast().getMonth()!=cal.get(Calendar.MONTH)){
                CalendarDateList.add(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH), false ));
                CalendarDateList.add(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            }else {
                CalendarDateList.add(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), true));
                cal.add(Calendar.DATE, 1);
            }
        }
    }
    public List<Date> getList(){
        return CalendarDateList;
    }
}
