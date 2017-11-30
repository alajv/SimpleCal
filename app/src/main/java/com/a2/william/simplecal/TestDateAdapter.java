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
 */

public class TestDateAdapter extends ArrayAdapter<Date>{

    private static final String TAG = "TestDateAdapter";

    public TestDateAdapter(Context context, ArrayList<Date> date){
        super(context, 0, date);
    }

    Date date;

    @Override
    public int getItemViewType(int position){
        Log.d(TAG, "getItemViewType: "+position);

        date = getItem(position);

        if(position==0||date.getDayOfMonth()==0){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int getViewTypeCount(){
        return 2;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int type = getItemViewType(position);

        switch (type){
            case 0: Log.d(TAG, "getView: case 0");

                ViewHolderMonth  viewHolderMonth;
                if (convertView == null) {

                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.month_list_item, parent, false);
                    viewHolderMonth = new ViewHolderMonth();
                    viewHolderMonth.monthHeaderTextView = (TextView) convertView.findViewById(R.id.monthHeader);
                    viewHolderMonth.yearHeaderTextView = (TextView) convertView.findViewById(R.id.yearHeader);

                    convertView.setTag(viewHolderMonth);

                } else {
                    viewHolderMonth = (ViewHolderMonth) convertView.getTag();
                }
                date = getItem(position);

                if (date != null) {
                    viewHolderMonth.monthHeaderTextView.setText(date.getMonthString());
                    viewHolderMonth.yearHeaderTextView.setText(date.getYearString());
                }
                return convertView;

            case 1: Log.d(TAG, "getView: Case 1");

                ViewHolderDay viewHolderDay;
                if (convertView == null) {

                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.date_list_item, parent, false);
                    viewHolderDay = new ViewHolderDay();
                    viewHolderDay.dayTextView = (TextView) convertView.findViewById(R.id.day_of_month);
                    viewHolderDay.monthTextView = (TextView) convertView.findViewById(R.id.day_of_week);

                    convertView.setTag(viewHolderDay);

                } else {
                    viewHolderDay = (ViewHolderDay) convertView.getTag();
                }
                date = getItem(position);
                if (date != null) {
                    viewHolderDay.dayTextView.setText(date.getDayOfWeekString());
                    viewHolderDay.monthTextView.setText(date.getMonthString());
                }
                return convertView;

            default: Log.d(TAG, "getView: default");

                break;
        }
        return convertView;
    }

    static class ViewHolderDay{
        TextView dayTextView;
        TextView monthTextView;
    }
    static class ViewHolderMonth{
        TextView monthHeaderTextView;
        TextView yearHeaderTextView;
    }
}



