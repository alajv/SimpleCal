package com.a2.william.simplecal;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by William on 2017-12-04.
 */

public class AddEventActivity extends AppCompatActivity implements  View.OnClickListener{
    private static final String TAG = "AddEventActivity";

    DateStore dateStore = DateStoreFactory.dateStore();
    EditText startTimeEditText, endTimeEditText;
    Button addEventButton;
    DatePicker eventDatePicker;
    Calendar cal;
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);

        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        eventDatePicker = findViewById(R.id.addEventDatePicker);
        addEventButton = findViewById(R.id.addEventButton);

        startTimeEditText.setOnClickListener(this);
        endTimeEditText.setOnClickListener(this);
        addEventButton.setOnClickListener(this);

        cal= Calendar.getInstance();
        eventDatePicker.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, 1);
        eventDatePicker.setMaxDate(cal.getTimeInMillis());
    }

            @Override
            public void onClick(View v){
                cal = Calendar.getInstance();
                mHour = cal.get(Calendar.HOUR_OF_DAY);
                mMinute = cal.get(Calendar.MINUTE);

                if(v == startTimeEditText) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            startTimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, true);
                    timePickerDialog.show();

                }else if(v == endTimeEditText){
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endTimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, true);
                    timePickerDialog.show();

                }else if(v == addEventButton){
                    int day=eventDatePicker.getDayOfMonth();
                    int year=eventDatePicker.getYear();
                    int month=eventDatePicker.getMonth();

                    for(int i = 0; i<dateStore.getList().size(); i++){
                        if(day==dateStore.getList().get(i).getDayOfMonth() && month==dateStore.getList().get(i).getMonth() && year==dateStore.getList().get(i).getYear()){

                            dateStore.getList().get(i).addDateEvent("Spela mambo", startTimeEditText.getText().toString(), endTimeEditText.getText().toString());
                            Log.d(TAG, "onClick: Event was added ");
                            finish();
                        }
                    }

                }
            };

}
