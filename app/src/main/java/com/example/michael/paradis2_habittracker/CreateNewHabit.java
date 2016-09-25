package com.example.michael.paradis2_habittracker;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class CreateNewHabit extends AppCompatActivity {
    private boolean SnClicked = false;
    private boolean MnClicked = false;
    private boolean TClicked = false;
    private boolean WClicked = false;
    private boolean ThClicked = false;
    private boolean FClicked = false;
    private boolean SClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date habitdate = new Date();
        super.onCreate(savedInstanceState);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        setContentView(R.layout.activity_create_new_habit);
        // http://stackoverflow.com/questions/2008558/displaying-a-default-date-in-a-edittext-widget
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        DateText.setText(formats.format(new Date()) );
    }
    public void showDatePickerDialog(View v){
        Date habitdate = new Date();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "habitDatePicker");


    }
    public void cancelButtonPressed(View v){
//        this.finish();

    }
    public void confirmButtonPressed(View v){
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        //Calendar habitDate = Calendar.getInstance(DateText.getText().toString());
        //habitdate.setTime(DateText.getText().toString());
    }
    //TODO Save into masater.sav and create the habit.sav
}
