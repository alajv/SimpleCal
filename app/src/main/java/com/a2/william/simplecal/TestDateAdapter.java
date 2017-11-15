package com.a2.william.simplecal;

import android.content.Context;
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

    public TestDateAdapter(Context context, ArrayList<Date> date){
        super(context, 0, date);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        ViewHolderDay viewHolderDay;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.date_list_item, parent, false);

            viewHolderDay = new ViewHolderDay();
            viewHolderDay.dayTextView = (TextView) convertView.findViewById(R.id.dayTextView);
            viewHolderDay.monthTextView = (TextView) convertView.findViewById(R.id.monthTextView);

            convertView.setTag(viewHolderDay);
        }else{

            viewHolderDay = (ViewHolderDay) convertView.getTag();
        }

        Date date = getItem(position);

        if(date != null){
            viewHolderDay.dayTextView.setText(date.getDay());
            viewHolderDay.monthTextView.setText(date.getMonth());
        }
        return convertView;
    }

    static class ViewHolderDay{
        TextView dayTextView;
        TextView monthTextView;
    }
}



