package com.example.michael.paradis2_habittracker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.util.Date;


public class CreateNewHabit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_habit);
        // http://stackoverflow.com/questions/2008558/displaying-a-default-date-in-a-edittext-widget
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        DateText.setText(DateFormat.getDateInstance().format(new Date()) );
    }
    public void showDatePickerDialog(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "habitDatePicker");
    }
}
