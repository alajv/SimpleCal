package com.a2.william.simplecal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by William on 2017-11-13.
 */

public class CustomDateAdapter extends ArrayAdapter<Date> {

    public CustomDateAdapter(Context context, ArrayList<Date> date){
        super(context, 0, date);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        Date date = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.date_list_item, parent, false);
        }
        TextView monthTextView = (TextView) convertView.findViewById(R.id.monthTextView);
        TextView dayTextView = (TextView) convertView.findViewById(R.id.dayTextView);

        monthTextView.setText(date.getMonth());
        dayTextView.setText(date.getDay());

        return convertView;
    }
}
