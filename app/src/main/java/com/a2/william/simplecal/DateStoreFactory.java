package com.a2.william.simplecal;

/**
 * Created by William on 2017-11-13.
 */

public class DateStoreFactory {

    public static DateStore dateStore(){
        return new FakeDateStore();
    }
}
