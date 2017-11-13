package com.a2.william.simplecal;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-10.
 */

public class FakeDateStore implements DateStore{

    ArrayList<Date> dummyDate;

    public FakeDateStore(){
        dummyDate = new ArrayList();

        dummyDate.add(new Date("Januari", "1"));
        dummyDate.add(new Date("Januari", "2"));
        dummyDate.add(new Date("Januari", "3"));
        dummyDate.add(new Date("Januari", "4"));
        dummyDate.add(new Date("Januari", "5"));
        dummyDate.add(new Date("Januari", "6"));
        dummyDate.add(new Date("Januari", "7"));
        dummyDate.add(new Date("Januari", "8"));
        dummyDate.add(new Date("Januari", "9"));
        dummyDate.add(new Date("Januari", "10"));
        dummyDate.add(new Date("Januari", "11"));
        dummyDate.add(new Date("Januari", "12"));
        dummyDate.add(new Date("Januari", "13"));
    }

    public ArrayList<Date> getList(){

        return dummyDate;
    }
}
