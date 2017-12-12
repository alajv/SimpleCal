package com.a2.william.simplecal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int lastExpandedPosition = -1;
    ExpandableDateAdapter adapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    DateStore dateStore = DateStoreFactory.dateStore();
    AppDatabase database;
    List<DateEvent> databaseEventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());

        database.dateEventDao().removeAllDateEvents();



        expListView = findViewById(R.id.dateList);
        fab = findViewById(R.id.fab);

        adapter = new ExpandableDateAdapter(this, dateStore.getList());

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
                getSupportActionBar().setTitle(dateStore.getList().get(firstVisibleItem).getMonthString());
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
        getListFromDatabase();
        Log.d(TAG, "onResume: I resumed");
    }

    public void addEvent(View view){
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

   private void getListFromDatabase(){

       for (int i = 0; i < dateStore.getList().size(); i++) {
           dateStore.getList().get(i).getDateEventList().clear();
           databaseEventList = database.dateEventDao().getEventsWhere(dateStore.getList().get(i).getYear(), dateStore.getList().get(i).getMonth(), dateStore.getList().get(i).getDayOfMonth());
           if (databaseEventList.size() > 0) {
               for (int j = 0; j < databaseEventList.size(); j++) {
                   dateStore.getList().get(i).addDateEvent(databaseEventList.get(j).getYear(), databaseEventList.get(j).getMonth(), databaseEventList.get(j).getDayOfMonth(), databaseEventList.get(j).getEventName(), databaseEventList.get(j).getStartTime(), databaseEventList.get(j).getEndTime());
               }
               databaseEventList.clear();
           }
       }
    }
}
