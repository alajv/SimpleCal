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
        dummyDate.add(new Date("Februari", "4", "2017",2, 4));
    }

    public ArrayList<Date> getList(){

        return dummyDate;
    }

}
