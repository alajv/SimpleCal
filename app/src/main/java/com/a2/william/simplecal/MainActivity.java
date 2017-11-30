package com.a2.william.simplecal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
        int lastExpandedPosition = -1;
        ExpandableDateAdapter adapter;
        ExpandableListView expListView;
        HashMap<Date, List<String>> dummyChildren;
        DateStore dateStore = DateStoreFactory.dateStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expListView = (ExpandableListView) findViewById(R.id.dateList);

        prepareListData();
        adapter = new ExpandableDateAdapter(this, dateStore.getList(), dummyChildren);

        expListView.setAdapter(adapter);

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition=groupPosition;
            }
        });

        expListView.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState){
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
                    getSupportActionBar().setTitle(dateStore.getList().get(firstVisibleItem).getMonthString());
            }
        });
    }


    public void prepareListData(){
        dummyChildren = new HashMap<Date, List<String>>();

        List<String> sl = new ArrayList<>();

        sl.add("Add event");

        for(int i=0; i<dateStore.getList().size();i++) {

            if(dateStore.getList().get(i).getRealDate()==false){
                dummyChildren.put(dateStore.getList().get(i), new ArrayList<String>());
            }else {
                dummyChildren.put(dateStore.getList().get(i), sl);
            }
        }
    }
}
