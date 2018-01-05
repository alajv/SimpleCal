package com.a2.william.simplecal;

import java.util.List;

/**
 * Created by William on 2018-01-05.
 */

public class MyUtil {

    /*
    Returns hex color code based on month.
     */
    public static String getMonthColor(int groupPosition, List<Day> listOfDays) {

        switch (listOfDays.get(groupPosition).getMonth()) {
            case 0:
                return "#B2EBF2";
            case 1:
                return "#B2DFDB";
            case 2:
                return "#C8E6C9";
            case 3:
                return "#DCEDC8";
            case 4:
                return "#F0F4C3";
            case 5:
                return "#FFF9C4";
            case 6:
                return "#FFECB3";
            case 7:
                return "#FFE0B2";
            case 8:
                return "#FFCCBC";
            case 9:
                return "#D7CCC8";
            case 10:
                return "#F5F5F5";
            case 11:
                return "#CFD8DC";
            default:
                return "";
        }
    }
}
