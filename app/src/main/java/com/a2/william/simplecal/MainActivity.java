package com.a2.william.simplecal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ExpandableDayAdapter adapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    DayStore dayStore = DayStoreFactory.dayStore();
    AppDatabase database;
    List<DayEvent> tempDayEventList;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());

        //database.dayEventDao().removeAllDayEvents();
        database.dayEventDao().removeExpiredEvents(dayStore.getListOfDays().get(0).getYear(),
                dayStore.getListOfDays().get(0).getMonth(),
                dayStore.getListOfDays().get(0).getDayOfMonth());

        fab = findViewById(R.id.fab);

        adapter = new ExpandableDayAdapter(this, dayStore.getListOfDays());
        expListView = findViewById(R.id.dateList);
        expListView.setAdapter(adapter);

        /*
        When a group in expListView is expanded that groups groupPosition is saved in
        lastExpandedPosition, when another group then is expanded, lastExpandedPosition is
        collapsed.
        This listener is commented out for now, because you probably want to be able to
        see your schedule on more then one day at a time, and that makes this listener
        stupid.
         */
       /* expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });*/

        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            /*
            Checks firstVisibleItem in expListView on scroll and sets Actionbar title to show month and year of firstVisibleItem.
            Does not work as intended. When groups are expanded, first visibleVisibleItem does not return correct value.
             */
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                getSupportActionBar().setTitle(dayStore.getListOfDays().get(firstVisibleItem).getMonthString() + " " + dayStore.getListOfDays().get(firstVisibleItem).getYearString());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        fillDayEventList();
        adapter.notifyDataSetChanged();
        Log.d(TAG, "onResume: I resumed");
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

    /*
    Deletes dayEvent from DB. Also creates a AlertDialog to confirm or deny the deletion.
     */
    public void deleteDayEventFromDB(final int year, final int month, final int dayOfMonth, final String eventName, final int id) {
        Log.d(TAG, "deleteDayEventFromDB: i try to delete dis ok");
        cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want to remove the event " + eventName + "' from " + cal.get(Calendar.DAY_OF_MONTH) +
                " " + cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) +
                " " + cal.get(Calendar.YEAR) + ".")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        database.dayEventDao().deleteDayEvent(year, month, dayOfMonth, eventName, id);
                        fillDayEventList();
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Removed", Toast.LENGTH_LONG).show();
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
    }

    /*
    Fills a Day's dayEventList with dayEvents from DB
     */
    private void fillDayEventList() {

        for (int i = 0; i < dayStore.getListOfDays().size(); i++) {
            dayStore.getListOfDays().get(i).getDayEventList().clear();
            tempDayEventList = database.dayEventDao().getDayEventsFromDB(dayStore.getListOfDays().get(i).getYear(),
                    dayStore.getListOfDays().get(i).getMonth(),
                    dayStore.getListOfDays().get(i).getDayOfMonth());
            if (tempDayEventList.size() > 0) {
                for (int j = 0; j < tempDayEventList.size(); j++) {
                    if (dayStore.getListOfDays().get(i).isRealDay()) {
                        dayStore.getListOfDays().get(i).addDayEvent(tempDayEventList.get(j).getYear(),
                                tempDayEventList.get(j).getMonth(),
                                tempDayEventList.get(j).getDayOfMonth(),
                                tempDayEventList.get(j).getEventName(),
                                tempDayEventList.get(j).getStartTime(),
                                tempDayEventList.get(j).getEndTime(),
                                tempDayEventList.get(j).idPlease());
                    }
                }
                tempDayEventList.clear();
            }
        }
    }
}
