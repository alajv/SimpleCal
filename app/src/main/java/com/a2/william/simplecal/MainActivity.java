package com.a2.william.simplecal;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int lastExpandedPosition = -1;
    ExpandableDateAdapter adapter;
    ExpandableListView expListView;
    FloatingActionButton fab;
    DateStore dateStore = DateStoreFactory.dateStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = findViewById(R.id.dateList);
        fab = findViewById(R.id.fab);

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
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: I resumed");
        
        adapter.notifyDataSetChanged();
    }

    public void addEvent(View view){
        Intent intent = new Intent(this, AddEventActivity.class);
        startActivity(intent);
    }

   public void prepareDummyEventListData(){

        dateStore.getList().get(1).addDateEvent("Spela pingis","21.00" , "23.10");
        dateStore.getList().get(1).addDateEvent("Bowling", "06.15", "23.10");
        dateStore.getList().get(5).addDateEvent("Quiz på GG Bar", "19.00", "23.40");
    }
}
