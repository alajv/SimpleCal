package com.a2.william.simplecal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-15.
 *
 * This class is not used. Was made when learning how to make a custom adapter.
 * Might not work properly with current code, have not tested/used after refactoring and alot of
 * other things have changed.
 */

public class TestDateAdapter extends ArrayAdapter<Day> {

    private static final String TAG = "TestDateAdapter";

    public TestDateAdapter(Context context, ArrayList<Day> day) {
        super(context, 0, day);
    }

    Day day;

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: " + position);

        day = getItem(position);

        if (position == 0 || day.getDayOfMonth() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        switch (type) {
            case 0:
                Log.d(TAG, "getView: case 0");

                ViewHolderMonth viewHolderMonth;
                if (convertView == null) {

                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.month_list_item, parent, false);
                    viewHolderMonth = new ViewHolderMonth();
                    viewHolderMonth.monthHeaderTextView = (TextView) convertView.findViewById(R.id.monthHeader);
                    viewHolderMonth.yearHeaderTextView = (TextView) convertView.findViewById(R.id.yearHeader);

                    convertView.setTag(viewHolderMonth);

                } else {
                    viewHolderMonth = (ViewHolderMonth) convertView.getTag();
                }
                day = getItem(position);

                if (day != null) {
                    viewHolderMonth.monthHeaderTextView.setText(day.getMonthString());
                    viewHolderMonth.yearHeaderTextView.setText(day.getYearString());
                }
                return convertView;

            case 1:
                Log.d(TAG, "getView: Case 1");

                ViewHolderDay viewHolderDay;
                if (convertView == null) {

                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.day_list_item, parent, false);
                    viewHolderDay = new ViewHolderDay();
                    viewHolderDay.dayTextView = (TextView) convertView.findViewById(R.id.day_of_month);
                    viewHolderDay.monthTextView = (TextView) convertView.findViewById(R.id.day_of_week);

                    convertView.setTag(viewHolderDay);

                } else {
                    viewHolderDay = (ViewHolderDay) convertView.getTag();
                }
                day = getItem(position);
                if (day != null) {
                    viewHolderDay.dayTextView.setText(day.getDayOfWeekString());
                    viewHolderDay.monthTextView.setText(day.getMonthString());
                }
                return convertView;

            default:
                Log.d(TAG, "getView: default");

                break;
        }
        return convertView;
    }

    static class ViewHolderDay {
        TextView dayTextView;
        TextView monthTextView;
    }

    static class ViewHolderMonth {
        TextView monthHeaderTextView;
        TextView yearHeaderTextView;
    }
}



