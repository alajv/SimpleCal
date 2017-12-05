package com.a2.william.simplecal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 2017-12-04.
 */

public class AddEventActivity extends AppCompatActivity {

    DateStore dateStore = DateStoreFactory.dateStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);



        ArrayAdapter<Date> dateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dateStore.getList());

        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner dateSpinner = findViewById(R.id.dateSpinner);
        dateSpinner.setAdapter(dateAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prepareDummyTimeArray());

        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner startTimeSpinner = findViewById(R.id.startTimeSpinner);
        startTimeSpinner.setAdapter(timeAdapter);
        Spinner endTimeSpinner = findViewById(R.id.endTimeSpinner);
        endTimeSpinner.setAdapter(timeAdapter);

    }

    private List prepareDummyTimeArray(){

        List<String> hourList = new ArrayList<>();

        hourList.add("00.00");
        hourList.add("01.00");
        hourList.add("02.00");
        hourList.add("03.00");
        hourList.add("04.00");
        hourList.add("05.00");
        hourList.add("06.00");
        hourList.add("07.00");
        hourList.add("08.00");
        hourList.add("09.00");
        hourList.add("10.00");
        hourList.add("11.00");
        hourList.add("12.00");
        hourList.add("13.00");
        hourList.add("14.00");
        hourList.add("15.00");

        return hourList;
    }
}
