package com.a2.william.simplecal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

        //CustomDateAdapter adapter;
        //TestDateAdapter adapter;
        TestAdapterTwo adapter;
        DateStore dateStore = DateStoreFactory.dateStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //adapter = new CustomDateAdapter(this, dateStore.getList());
        //adapter = new TestDateAdapter(this, dateStore.getList());
        adapter = new TestAdapterTwo(this, dateStore.getList());
        ListView listView = (ListView) findViewById(R.id.DateList);
        listView.setAdapter(adapter);
    }
}
