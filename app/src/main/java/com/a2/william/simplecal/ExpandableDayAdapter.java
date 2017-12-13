package com.a2.william.simplecal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
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

                int color = getColor(groupPosition);
                switch (color) {
                    case 0:
                        convertView.setBackgroundColor(Color.parseColor("#B2EBF2"));
                        return convertView;
                    case 1:
                        convertView.setBackgroundColor(Color.parseColor("#B2DFDB"));
                        return convertView;
                    case 2:
                        convertView.setBackgroundColor(Color.parseColor("#C8E6C9"));
                        return convertView;
                    case 3:
                        convertView.setBackgroundColor(Color.parseColor("#DCEDC8"));
                        return convertView;
                    case 4:
                        convertView.setBackgroundColor(Color.parseColor("#F0F4C3"));
                        return convertView;
                    case 5:
                        convertView.setBackgroundColor(Color.parseColor("#FFF9C4"));
                        return convertView;
                    case 6:
                        convertView.setBackgroundColor(Color.parseColor("#FFECB3"));
                        return convertView;
                    case 7:
                        convertView.setBackgroundColor(Color.parseColor("#FFE0B2"));
                        return convertView;
                    case 8:
                        convertView.setBackgroundColor(Color.parseColor("#FFCCBC"));
                        return convertView;
                    case 9:
                        convertView.setBackgroundColor(Color.parseColor("#D7CCC8"));
                        return convertView;
                    case 10:
                        convertView.setBackgroundColor(Color.parseColor("#F5F5F5"));
                        return convertView;
                    case 11:
                        convertView.setBackgroundColor(Color.parseColor("#CFD8DC"));
                        return convertView;
                    default:
                        break;
                }
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

    public int getColor(int groupPosition) {
        day = (Day) getGroup(groupPosition);

        return day.getMonth();
    }
}
