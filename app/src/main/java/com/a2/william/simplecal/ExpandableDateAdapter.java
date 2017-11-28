package com.a2.william.simplecal;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by William on 2017-11-13.
 */

public class ExpandableDateAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableDateAdapter";
    
   private Context _context;
   private List<Date> _listDateHeader;
   private HashMap<Date, List<String>> _listDateChild;
   Date date;

    public ExpandableDateAdapter(Context context, List<Date> listDateHeader,
                                 HashMap<Date, List<String>> listDateChild){
        this._context = context;
        this._listDateHeader = listDateHeader;
        this._listDateChild = listDateChild;
    }



    @Override
    public Object getChild(int groupPosition, int childPosition){
        return this._listDateChild.get(this._listDateHeader.get(groupPosition)).get(childPosition);
    }


    @Override
    public long getChildId(int groupPosition, int childPosition){
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent){
        Log.d(TAG, "getChildView: I go here");

        final String addEventButtonText = (String) getChild(groupPosition, childPosition);

        if(convertView == null){
            Log.d(TAG, "getChildView: convertView = null");
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.add_date_event_layout, null);
        }
        Log.d(TAG, "getChildView: convertView != null");
        Button addEvent = (Button) convertView.findViewById(R.id.addEventButton);
        if (addEvent == null) {
            Log.d(TAG, "Knappen är null!");
        }
        addEvent.setText(addEventButtonText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition){
       return this._listDateChild.get(this._listDateHeader.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition){
        return this._listDateHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount(){
        return this._listDateHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition){
        return groupPosition;
    }

    @Override
    public int getGroupTypeCount(){
        return 2;
    }
    @Override
    public int getGroupType(int groupPosition){
        date = (Date) getGroup(groupPosition);

        if(groupPosition==0||date.getDayNr()==0){
            return 0;
        }else{
            return 1;
        }
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent){
        Log.d(TAG, "getGroupView: I go here");
        
        date = (Date) getGroup(groupPosition);
        int type = getGroupType(groupPosition);

        switch(type) {
            case 1: Log.d(TAG, "getGroupView: Case 1, Dagar");

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_group, null);
            }
            TextView dayTextView = (TextView) convertView.findViewById(R.id.dayTextView);
            TextView monthTextView = (TextView) convertView.findViewById(R.id.monthTextView);
            dayTextView.setTypeface(null, Typeface.BOLD);
            dayTextView.setText(date.getDay());
            monthTextView.setText(date.getMonth());

            return convertView;

            case 0: Log.d(TAG, "getGroupView: case 0, Headers");
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.month_list_item, null);
                }
                TextView monthHeader = (TextView) convertView.findViewById(R.id.monthHeader);
                TextView yearHeader = (TextView) convertView.findViewById(R.id.yearHeader);
                monthHeader.setText(date.getMonth());
                yearHeader.setText(date.getYear());
                convertView.setClickable(true);
                convertView.setFocusable(false);
                return convertView;
            default:
                Log.d(TAG, "getGroupView: Default, WTF....");
                break;
        }
        return convertView;

    }

    @Override
    public boolean hasStableIds(){
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition){
        return true;
    }


}
