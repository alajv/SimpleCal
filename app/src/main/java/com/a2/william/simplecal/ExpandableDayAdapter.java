package com.a2.william.simplecal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Created by William on 2017-11-13.
 */

public class ExpandableDayAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableDayAdapter";

    private Context _context;
    private List<Day> _listDayHeader;
    Day day;

    public ExpandableDayAdapter(Context context, List<Day> listDayHeader) {
        this._context = context;
        this._listDayHeader = listDayHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDayHeader.get(groupPosition).getDayEventList().get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.d(TAG, "getChildView: I go here");

        final DayEvent dayEvent = (DayEvent) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.event_layout, null);
        }
        TextView addStartHour = convertView.findViewById(R.id.startTimeTextView);
        TextView endTime = convertView.findViewById(R.id.endTimeTextView);
        TextView addEventName = convertView.findViewById(R.id.eventName);
        final FrameLayout deleteLayout = convertView.findViewById(R.id.deleteLayout);
        deleteLayout.setVisibility(View.INVISIBLE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: I hear u m8");

                if(deleteLayout.getVisibility() == View.INVISIBLE){
                    deleteLayout.setVisibility(View.VISIBLE);
                }else{
                    deleteLayout.setVisibility(View.INVISIBLE);
                }

            }
        });


        addStartHour.setText(dayEvent.getStartTime());
        addEventName.setText(dayEvent.getEventName());
        endTime.setText(dayEvent.getEndTime());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDayHeader.get(groupPosition).getDayEventList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDayHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDayHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupTypeCount() {
        return 2;
    }

    @Override
    public int getGroupType(int groupPosition) {
        day = (Day) getGroup(groupPosition);

        if (!day.isRealDay()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d(TAG, "getGroupView: I go here");

        //day = (Day) getGroup(groupPosition);
        int type = getGroupType(groupPosition);


        switch (type) {
            case 1:
                Log.d(TAG, "getGroupView: Case 1, Dagar");

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.day_list_group, null);
                }
                TextView dayOfMonthTextView = convertView.findViewById(R.id.day_of_month);
                TextView dayOfWeekTextView = convertView.findViewById(R.id.day_of_week);
                dayOfMonthTextView.setTypeface(null, Typeface.BOLD);
                dayOfMonthTextView.setText(day.getDayOfMonthString());
                dayOfWeekTextView.setText(day.getShortDayOfWeekString());

                convertView.setBackgroundColor(Color.parseColor(getColor(groupPosition)));

                return convertView;

            case 0:
                Log.d(TAG, "getGroupView: case 0, Headers");
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.month_list_item, null);
                }
                TextView monthHeader = convertView.findViewById(R.id.monthHeader);
                TextView yearHeader = convertView.findViewById(R.id.yearHeader);
                monthHeader.setText(day.getMonthString());
                yearHeader.setText(day.getYearString());
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
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public String getColor(int groupPosition) {
        day = (Day) getGroup(groupPosition);

        switch(day.getMonth()){
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
                //break;
        }
    }
}
