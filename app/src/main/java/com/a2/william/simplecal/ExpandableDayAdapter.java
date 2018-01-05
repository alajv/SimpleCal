package com.a2.william.simplecal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import static com.a2.william.simplecal.MyUtil.*;

import java.util.List;


/**
 * Created by William on 2017-11-13.
 */

public class ExpandableDayAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableDayAdapter";

    private Context _context;
    private List<Day> _dayList;

    public ExpandableDayAdapter(Context context, List<Day> listDayHeader) {
        this._context = context;
        this._dayList = listDayHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._dayList.get(groupPosition).getDayEventList().get(childPosition);
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
        final ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setVisibility(View.INVISIBLE);
        deleteButton.setClickable(true);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: deletelayout onClick! i do");
                if (_context instanceof MainActivity) {
                    ((MainActivity) _context).deleteDayEventFromDB(dayEvent.getYear(),
                            dayEvent.getMonth(),
                            dayEvent.getDayOfMonth(),
                            dayEvent.getEventName(),
                            dayEvent.idPlease());
                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (deleteButton.getVisibility() == View.INVISIBLE) {
                    Log.d(TAG, "onClick: Now you see me");
                    deleteButton.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, "onClick: Now you dont");
                    deleteButton.setVisibility(View.INVISIBLE);
                }
            }
        });
        addStartHour.setText(dayEvent.getStartTime());
        addEventName.setText(dayEvent.getEventName());
        endTime.setText(dayEvent.getEndTime());
        convertView.setBackgroundColor(Color.parseColor(getMonthColor(groupPosition, _dayList)));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._dayList.get(groupPosition).getDayEventList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._dayList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._dayList.size();
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
        if (_dayList.get(groupPosition).isRealDay()) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Log.d(TAG, "getGroupView: I go here");

        switch (getGroupType(groupPosition)) {
            default:
                Log.d(TAG, "getGroupView: default, Day");

                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.day_list_group, null);
                }
                TextView dayOfMonthTextView = convertView.findViewById(R.id.day_of_month);
                TextView dayOfWeekTextView = convertView.findViewById(R.id.day_of_week);
                /*
                Supposed to set bold text on groups in listview that have events in their dayEventList.
                Commented out because it doenst work properly. Some items that have getChildrenCount = 0
                also show as bold after i add a DayEvent to a Day.
                */
                /*
                if (getChildrenCount(groupPosition) > 0) {
                    dayOfMonthTextView.setTypeface(null, Typeface.BOLD);
                    dayOfWeekTextView.setTypeface(null, Typeface.BOLD);
                }
                */
                dayOfMonthTextView.setText(_dayList.get(groupPosition).getDayOfMonthString());
                dayOfWeekTextView.setText(_dayList.get(groupPosition).getShortDayOfWeekString());

                convertView.setBackgroundColor(Color.parseColor(getMonthColor(groupPosition, _dayList)));
                ExpandableListView expListView = (ExpandableListView) parent;
                expListView.expandGroup(groupPosition);

                return convertView;

            case 1:
                Log.d(TAG, "getGroupView: case 1, Headers");
                if (convertView == null) {
                    LayoutInflater inflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.month_list_item, null);
                }
                TextView monthHeader = convertView.findViewById(R.id.monthHeader);
                TextView yearHeader = convertView.findViewById(R.id.yearHeader);
                monthHeader.setText(_dayList.get(groupPosition).getMonthString());
                yearHeader.setText(_dayList.get(groupPosition).getYearString());
                monthHeader.setTypeface(null, Typeface.BOLD);
                yearHeader.setTypeface(null, Typeface.BOLD);

                convertView.setBackgroundColor(Color.parseColor(getMonthColor(groupPosition, _dayList)));

                return convertView;
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private boolean hasChildren(int year, int month, int dayOfMonth) {

        for (int i = 0; i < _dayList.size(); i++) {
            if (_dayList.get(i).getYear() == year && _dayList.get(i).getMonth() == month && _dayList.get(i).getDayOfMonth() == dayOfMonth) {
                if (_dayList.get(i).getDayEventList().size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
