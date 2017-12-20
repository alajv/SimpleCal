package com.a2.william.simplecal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final Context context = this;

    int lastExpandedPosition = -1;
    ExpandableDayAdapter adapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    DayStore dayStore = DayStoreFactory.dayStore();
    static AppDatabase database;
    List<DayEvent> databaseEventList;
    Calendar cal;

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

    public void deleteDayEventFromDB(final int year, final int month, final int dayOfMonth, final String eventName) {
        Log.d(TAG, "deleteDayEventFromDB: i try to delete dis ok");
        cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage("Do you want to remove the event '" + eventName + "' from " + cal.get(Calendar.DAY_OF_MONTH) +
                " " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) +
                " " + cal.get(Calendar.YEAR)+ ".")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.dayEventDao().deleteDayEvent(year, month, dayOfMonth, eventName);
                        getListFromDatabase();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Is deleted", Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

       /* database.dayEventDao().deleteDayEvent(year, month, dayOfMonth, eventName);
        getListFromDatabase();
        adapter.notifyDataSetChanged();*/
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
