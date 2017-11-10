package com.a2.william.simplecal;

import android.app.LauncherActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        ListAdapter adapter;
        DateListHandler dlh = new DateListHandler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.DateList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dlh.getList());


        listView.setAdapter(adapter);
    }
}
