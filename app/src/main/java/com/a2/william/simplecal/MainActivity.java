package com.a2.william.simplecal;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int lastExpandedPosition = -1;
    View lastDeleteLayoutShown;
    ExpandableDayAdapter adapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    DayStore dayStore = DayStoreFactory.dayStore();
    AppDatabase database;
    List<DayEvent> databaseEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());

        //database.dayEventDao().removeAllDayEvents();
        database.dayEventDao().removeExpiredEvents(dayStore.getList().get(0).getYear(),
                dayStore.getList().get(0).getMonth(),
                dayStore.getList().get(0).getDayOfMonth());

        fab = findViewById(R.id.fab);

        adapter = new ExpandableDayAdapter(this, dayStore.getList());
        expListView = findViewById(R.id.dateList);
        expListView.setAdapter(adapter);

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                getSupportActionBar().setTitle(dayStore.getList().get(firstVisibleItem).getMonthString());

            }
        });

       /*expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Log.d(TAG, "onChildClick: Körs");
                if (lastDeleteLayoutShown == null){
                    lastDeleteLayoutShown = view.findViewById(R.id.deleteLayout);
                }


                if(view.findViewById(R.id.deleteLayout).getVisibility() == View.VISIBLE){

                    view.findViewById(R.id.deleteLayout).setVisibility(View.INVISIBLE);
                }else{
                    lastDeleteLayoutShown.setVisibility(View.INVISIBLE);
                    view.findViewById(R.id.deleteLayout).setVisibility(View.VISIBLE);
                }
                lastDeleteLayoutShown = view.findViewById(R.id.deleteLayout);
                view.setClickable(true);
                lastDeleteLayoutShown.setClickable(true);

                return true;
            }
        });*/
    }

    @Override
    public void onResume() {
        super.onResume();
        getListFromDatabase();
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onResume: I resumed");
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    private void getListFromDatabase() {

        for (int i = 0; i < dayStore.getList().size(); i++) {
            dayStore.getList().get(i).getDayEventList().clear();
            databaseEventList = database.dayEventDao().getDayEventsFromDB(dayStore.getList().get(i).getYear(),
                    dayStore.getList().get(i).getMonth(),
                    dayStore.getList().get(i).getDayOfMonth());
            if (databaseEventList.size() > 0) {
                for (int j = 0; j < databaseEventList.size(); j++) {
                    if (dayStore.getList().get(i).isRealDay()) {
                        dayStore.getList().get(i).addDayEvent(databaseEventList.get(j).getYear(),
                                databaseEventList.get(j).getMonth(),
                                databaseEventList.get(j).getDayOfMonth(),
                                databaseEventList.get(j).getEventName(),
                                databaseEventList.get(j).getStartTime(),
                                databaseEventList.get(j).getEndTime());
                    }
                }
                databaseEventList.clear();
            }
        }
    }
}
