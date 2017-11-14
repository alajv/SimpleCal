package com.a2.william.simplecal;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-10.
 */

public class FakeDateStore implements DateStore{

    ArrayList<Date> dummyDate;

    public FakeDateStore(){
        dummyDate = new ArrayList();

        dummyDate.add(new Date("Januari", "1", "2017"));
        dummyDate.add(new Date("Januari", "2", "2017"));
        dummyDate.add(new Date("Januari", "3", "2017"));
        dummyDate.add(new Date("Januari", "4", "2017"));
        dummyDate.add(new Date("Januari", "5", "2017"));
        dummyDate.add(new Date("Januari", "6", "2017"));
        dummyDate.add(new Date("Januari", "7", "2017"));
        dummyDate.add(new Date("Januari", "8", "2017"));
        dummyDate.add(new Date("Januari", "9", "2017"));
        dummyDate.add(new Date("Februari", "1", "2017"));
        dummyDate.add(new Date("Februari", "2", "2017"));
        dummyDate.add(new Date("Februari", "3", "2017"));
        dummyDate.add(new Date("Februari", "4", "2017"));
    }

    public ArrayList<Date> getList(){

        return dummyDate;
    }
}
