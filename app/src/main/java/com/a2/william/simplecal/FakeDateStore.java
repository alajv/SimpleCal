package com.a2.william.simplecal;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-10.
 */

public class FakeDateStore implements DateStore{

    ArrayList<Date> dummyDate;



    public FakeDateStore(){
        dummyDate = new ArrayList<>();

        dummyDate.add(new Date(2017, 0, 0,false));
        dummyDate.add(new Date(2017, 0, 1,true));
        dummyDate.add(new Date(2017, 0, 2,true));
        dummyDate.add(new Date(2017, 0, 3,true));
        dummyDate.add(new Date(2017, 0, 4,true));
        dummyDate.add(new Date(2017, 0, 5,true));
        dummyDate.add(new Date(2017, 0, 6,true));
        dummyDate.add(new Date(2017, 1, 0,false));
        dummyDate.add(new Date(2017, 1, 1,true));
        dummyDate.add(new Date(2017, 1, 2,true));
        dummyDate.add(new Date(2017, 1, 3,true));
        dummyDate.add(new Date(2017, 1, 4,true));
        dummyDate.add(new Date(2017, 1, 5,true));
        dummyDate.add(new Date(2017, 1, 6,true));
        dummyDate.add(new Date(2017, 1, 7,true));
        dummyDate.add(new Date(2017, 1, 8,true));
        dummyDate.add(new Date(2017, 1, 9,true));
        dummyDate.add(new Date(2017, 1, 10,true));
        dummyDate.add(new Date(2017, 1, 11,true));


    }

    public ArrayList<Date> getList(){
        return dummyDate;
    }

}
