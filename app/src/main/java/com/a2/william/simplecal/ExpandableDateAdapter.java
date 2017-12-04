package com.a2.william.simplecal;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by William on 2017-11-13.
 */

public class ExpandableDateAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableDateAdapter";
    
   private Context _context;
   private List<Date> _listDateHeader;
   private Map<Date, List<DateEvent>> _listDateChild;
   Date date;
   DateEvent dateEvent;

    public ExpandableDateAdapter(Context context, List<Date> listDateHeader,
                                 Map<Date, List<DateEvent>> listDateChild){
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

        final DateEvent dateEvent = (DateEvent) getChild(groupPosition, childPosition);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.date_event_layout, null);
        }
        TextView addStartHour = (TextView) convertView.findViewById(R.id.startTimeTextView);
        TextView addEventName = (TextView) convertView.findViewById(R.id.eventName);

        addStartHour.setText(dateEvent.getStartTime());
        addEventName.setText(dateEvent.getEventName());

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

        if(date.getRealDate()==false){
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
            TextView dayOfMonthTextView = (TextView) convertView.findViewById(R.id.day_of_month);
            TextView dayOfWeekTextView = (TextView) convertView.findViewById(R.id.day_of_week);
            dayOfMonthTextView.setTypeface(null, Typeface.BOLD);
            dayOfMonthTextView.setText(date.getDayOfMonthString());
            dayOfWeekTextView.setText(date.getDayOfWeekString());

            return convertView;

            case 0: Log.d(TAG, "getGroupView: case 0, Headers");
                if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.month_list_item, null);
                }
                TextView monthHeader = (TextView) convertView.findViewById(R.id.monthHeader);
                TextView yearHeader = (TextView) convertView.findViewById(R.id.yearHeader);
                monthHeader.setText(date.getMonthString());
                yearHeader.setText(date.getYearString());
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
