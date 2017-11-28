package com.a2.william.simplecal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by William on 2017-11-10.
 */

public class FakeDateStore implements DateStore{

    ArrayList<Date> dummyDate;



    public FakeDateStore(){
        dummyDate = new ArrayList<>();



        dummyDate.add(new Date("Januari", "0", "2017",1, 0));
        dummyDate.add(new Date("Januari", "1", "2017",1, 1));
        dummyDate.add(new Date("Januari", "2", "2017",1, 2));
        dummyDate.add(new Date("Januari", "3", "2017",1, 3));
        dummyDate.add(new Date("Januari", "4", "2017",1, 4));
        dummyDate.add(new Date("Januari", "5", "2017",1, 5));
        dummyDate.add(new Date("Januari", "6", "2017",1, 6));
        dummyDate.add(new Date("Januari", "7", "2017",1, 7));
        dummyDate.add(new Date("Januari", "8", "2017",1, 8));
        dummyDate.add(new Date("Januari", "9", "2017",1, 9));
        dummyDate.add(new Date("Februari", "0", "2017",2, 0));
        dummyDate.add(new Date("Februari", "1", "2017",2, 1));
        dummyDate.add(new Date("Februari", "2", "2017",2, 2));
        dummyDate.add(new Date("Februari", "3", "2017",2, 3));
        dummyDate.add(new Date("Februari", "5", "2017",2, 5));
        dummyDate.add(new Date("Februari", "6", "2017",2, 6));
        dummyDate.add(new Date("Februari", "7", "2017",2, 7));
        dummyDate.add(new Date("Februari", "8", "2017",2, 8));
        dummyDate.add(new Date("Februari", "9", "2017",2, 9));
        dummyDate.add(new Date("Februari", "10", "2017",2, 10));
        dummyDate.add(new Date("Februari", "11", "2017",2, 11));
        dummyDate.add(new Date("Februari", "12", "2017",2, 12));
        dummyDate.add(new Date("Februari", "13", "2017",2, 13));

    }

    public ArrayList<Date> getList(){

        return dummyDate;
    }

}
