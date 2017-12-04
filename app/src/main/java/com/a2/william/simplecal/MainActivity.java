package com.a2.william.simplecal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int lastExpandedPosition = -1;
    ExpandableDateAdapter adapter;
    ExpandableListView expListView;
    //List dummyChildren;
    DateStore dateStore = DateStoreFactory.dateStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.dateList);

        prepareDummyEventListData();
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


   public void prepareDummyEventListData(){

        dateStore.getList().get(1).addDateEvent("Spela pingis","21.00" );
        dateStore.getList().get(1).addDateEvent("Bowling", "06.15");
        dateStore.getList().get(5).addDateEvent("Quiz på GG Bar", "19.00");
    }
}
