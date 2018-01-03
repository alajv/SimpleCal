package com.a2.william.simplecal;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

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
    TimePickerDialog endTimePickerDialog;
    int startPickerHour;
    int startPickerMinute;
    int endPickerHour;
    int endPickerMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_activity);
        database = AppDatabase.getDatabase(getApplicationContext());

        getSupportActionBar().setTitle("Add Event");
        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);
        eventDatePicker = findViewById(R.id.addEventDatePicker);
        addEventButton = findViewById(R.id.addEventButton);
        eventNameEditText = findViewById(R.id.eventNameEditText);

        startTimeEditText.setOnClickListener(this);
        endTimeEditText.setOnClickListener(this);
        addEventButton.setOnClickListener(this);

        cal = Calendar.getInstance();
        startPickerHour = cal.get(Calendar.HOUR_OF_DAY);
        startPickerMinute = cal.get(Calendar.MINUTE);
        endPickerHour = cal.get(Calendar.HOUR_OF_DAY);
        endPickerMinute = cal.get(Calendar.MINUTE);

        startTimeEditText.setText(String.format(Locale.getDefault(),"%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
        endTimeEditText.setText(String.format(Locale.getDefault(),"%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)));
        eventNameEditText.setText("");
        eventDatePicker.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.YEAR, 1);
        eventDatePicker.setMaxDate(cal.getTimeInMillis());
    }

    @Override
    public void onClick(View v) {
        if (v == startTimeEditText) {
             TimePickerDialog startTimePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    startTimeEditText.setText(String.format(Locale.getDefault(),"%02d:%02d", hourOfDay, minute));
                    startPickerHour=hourOfDay;
                    startPickerMinute=minute;
                    timePickerCheck();
                }
            }, startPickerHour, startPickerMinute, true);
            startTimePickerDialog.show();

        } else if (v == endTimeEditText) {
            endTimePickerDialog = new TimePickerDialog(this, 2, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    endTimeEditText.setText(String.format(Locale.getDefault(),"%02d:%02d", hourOfDay, minute));
                    endPickerHour=hourOfDay;
                    endPickerMinute=minute;
                    timePickerCheck();
                }
            }, endPickerHour, endPickerMinute, true);
            endTimePickerDialog.show();

        } else if (v == addEventButton) {
            if(eventNameEditText.getText().toString().matches("")){
                Toast.makeText(AddEventActivity.this, "Name the event", Toast.LENGTH_LONG).show();
            }else {
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
    };

    public void timePickerCheck(){

        if(endPickerHour<startPickerHour && endPickerMinute>=startPickerMinute){
            endPickerHour=startPickerHour;
            endTimeEditText.setText(String.format(Locale.getDefault(),"%02d:%02d", endPickerHour, endPickerMinute));
            Toast.makeText(AddEventActivity.this, "Event must start before it ends", Toast.LENGTH_LONG).show();
        }else if(endPickerHour>=startPickerHour && endPickerMinute<startPickerMinute){
            endPickerMinute=startPickerMinute;
            endTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d", endPickerHour, endPickerMinute));
            Toast.makeText(AddEventActivity.this, "Event must start before it ends", Toast.LENGTH_LONG).show();
        }else if(endPickerHour<startPickerHour && endPickerMinute<startPickerMinute){
            endPickerHour=startPickerHour;
            endPickerMinute=startPickerMinute;
            endTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d", endPickerHour, endPickerMinute));
            Toast.makeText(AddEventActivity.this, "Event must start before it ends", Toast.LENGTH_LONG).show();
        }
    }

}
