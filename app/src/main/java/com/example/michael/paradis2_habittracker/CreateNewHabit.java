package com.example.michael.paradis2_habittracker;

import android.provider.CalendarContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class CreateNewHabit extends AppCompatActivity {
    private boolean SnClicked = false;
    private boolean MnClicked = false;
    private boolean TClicked = false;
    private boolean WClicked = false;
    private boolean ThClicked = false;
    private boolean FClicked = false;
    private boolean SClicked = false;
    private boolean[] buttons = {SnClicked, MnClicked, TClicked, WClicked, ThClicked, FClicked, SClicked};
    private int[]  WeekDayButtonsid = {R.id.SundayToggleButton, R.id.MondayToggleButton, R.id.TuesdayToggleButton,
            R.id.WednesdayToggleButton, R.id.ThursdayToggleButton, R.id.FridayToggleButton, R.id.SaturdayToggleButton};

    public int[] getWeekDayButtonsid() {
        return WeekDayButtonsid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Date habitdate = new Date();
        super.onCreate(savedInstanceState);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        setContentView(R.layout.activity_create_new_habit);
        // http://stackoverflow.com/questions/2008558/displaying-a-default-date-in-a-edittext-widget
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        DateText.setText(formats.format(new Date()) );
        int dayOfWeek = new DayOfWeek().getDayofWeek();
        ((ToggleButton)findViewById(WeekDayButtonsid[dayOfWeek])).toggle();




    }
    public void showDatePickerDialog(View v){
        Date habitdate = new Date();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "habitDatePicker");


    }
    public void cancelButtonPressed(View v){
        this.finish();

    }
    public void confirmButtonPressed(View v){
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        //Calendar habitDate = Calendar.getInstance(DateText.getText().toString());
        //habitdate.setTime(DateText.getText().toString());

        String[] date_Array = DateText.getText().toString().split("-");
        int year = Integer.valueOf(date_Array[0]+1900);
        int month = Integer.valueOf(date_Array[1]);
        int day = Integer.valueOf(date_Array[2]);
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date habitdate = c.getTime();


        String habitname = ((EditText)findViewById(R.id.NewHabitName)).getText().toString();
        Habit newhabit = new Habit(habitname,habitdate);
        String FileName = newhabit.getHabbitname() + Integer.toString(( new Random()).nextInt());



    }
    //TODO Save into masater.sav and create the habit.sav
}
