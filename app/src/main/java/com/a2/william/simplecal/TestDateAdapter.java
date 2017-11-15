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

    int type;
    Date date;

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolderDay viewHolderDay;
        ViewHolderMonth viewHolderMonth;
        date = getItem(position);

        if(position>0) {
            if (!date.getMonth().equalsIgnoreCase(getItem(position--).getMonth())) {
                type = 1;
            } else {
                type = 2;
            }
        }
        if(position==0){
            type = 1;
        }

        Log.d(TAG, "getView: "+position);
        switch(type){
            case 1:
                Log.d(TAG, "getView: In case 1");
                if (convertView == null||convertView.getTag().getClass()!=ViewHolderMonth.class) {
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
                    viewHolderMonth.monthHeaderTextView.setText(date.getMonth());
                    viewHolderMonth.yearHeaderTextView.setText(date.getYear());
                }
                return convertView;

            case 2:
                Log.d(TAG, "getView: Case 2");
            if (convertView == null||convertView.getTag().getClass()!=ViewHolderDay.class) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.date_list_item, parent, false);

                viewHolderDay = new ViewHolderDay();
                viewHolderDay.dayTextView = (TextView) convertView.findViewById(R.id.dayTextView);
                viewHolderDay.monthTextView = (TextView) convertView.findViewById(R.id.monthTextView);

                convertView.setTag(viewHolderDay);
            } else {
                viewHolderDay = (ViewHolderDay) convertView.getTag();
            }

            date = getItem(position);

            if (date != null) {
                viewHolderDay.dayTextView.setText(date.getDay());
                viewHolderDay.monthTextView.setText(date.getMonth());
            }
            return convertView;

            default:
                Log.d(TAG, "getView: Default");
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



