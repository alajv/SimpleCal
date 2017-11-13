package com.a2.william.simplecal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

        CustomDateAdapter adapter;
        DateListHandler dlh = new DateListHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adapter = new CustomDateAdapter(this, dlh.getList());
        ListView listView = (ListView) findViewById(R.id.DateList);
        listView.setAdapter(adapter);
    }
}
