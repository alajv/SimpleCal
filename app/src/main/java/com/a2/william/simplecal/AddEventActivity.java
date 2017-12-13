package com.a2.william.simplecal;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by William on 2017-12-04.
 */

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddEventActivity";

    private AppDatabase database;
    EditText startTimeEditText, endTimeEditText, eventNameEditText;
    Button addEventButton;
    DatePicker eventDatePicker;
    Calendar cal;
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);
        database = AppDatabase.getDatabase(getApplicationContext());

        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        eventDatePicker = findViewById(R.id.addEventDatePicker);
        addEventButton = findViewById(R.id.addEventButton);
        eventNameEditText = findViewById(R.id.eventNameEditText);

        startTimeEditText.setOnClickListener(this);
        endTimeEditText.setOnClickListener(this);
        addEventButton.setOnClickListener(this);

        cal = Calendar.getInstance();
        eventDatePicker.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, 1);
        eventDatePicker.setMaxDate(cal.getTimeInMillis());
    }

    @Override
    public void onClick(View v) {
        cal = Calendar.getInstance();
        mHour = cal.get(Calendar.HOUR_OF_DAY);
        mMinute = cal.get(Calendar.MINUTE);

        if (v == startTimeEditText) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();

        } else if (v == endTimeEditText) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTimeEditText.setText(String.format("%02d:%02d", hourOfDay, minute));
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();

        } else if (v == addEventButton) {
            int day = eventDatePicker.getDayOfMonth();
            int year = eventDatePicker.getYear();
            int month = eventDatePicker.getMonth();
            String startTime = startTimeEditText.getText().toString();
            String endTime = endTimeEditText.getText().toString();
            String eventName = eventNameEditText.getText().toString();

            database.dayEventDao().addDayEvent(new DayEvent(year, month, day, eventName, startTime, endTime));
            finish();
        }
    }

    ;

}
