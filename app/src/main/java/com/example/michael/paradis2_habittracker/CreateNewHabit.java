package com.example.michael.paradis2_habittracker;
/*
        Android App that keeps track of your daily habits.
        Copyright (C) 2016  Michael Paradis

        This program is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        This program is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with this program.  If not, see <http://www.gnu.org/licenses/>.
        */
// File saving from LonelyTwitter lab
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class CreateNewHabit extends AppCompatActivity {
    private String FILENAME = "Data.sav";
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
        int dayOfWeek = new DayOfWeek().getDayOfWeek();
        ((ToggleButton)findViewById(WeekDayButtonsid[dayOfWeek])).toggle();




    }
    public void showDatePickerDialog(View v){

        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "habitDatePicker");


    }
    public void cancelButtonPressed(View v){
        this.finish();

    }
    public void confirmButtonPressed(View v) {
        EditText DateText = (EditText) findViewById(R.id.NewHabitDate);
        //Calendar habitDate = Calendar.getInstance(DateText.getText().toString());
        //habitdate.setTime(DateText.getText().toString());

        String[] date_Array = DateText.getText().toString().split("-");
        int year = Integer.valueOf(date_Array[0]);
        int month = Integer.valueOf(date_Array[1]) - 1;
        int day = Integer.valueOf(date_Array[2]);
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);
        Date habitdate = c.getTime();


        String habitname = ((EditText) findViewById(R.id.NewHabitName)).getText().toString();
        Habit newhabit = new Habit(habitname, habitdate);
        HabitListController hlc = new HabitListController();
        CurrentDayHabitListController cdhlc = new CurrentDayHabitListController();
        int dayIndex = 0;
        for (int dayOfWeek : WeekDayButtonsid) {

            ToggleButton weekDayToggleButton = (ToggleButton) findViewById(dayOfWeek);
            newhabit.setDayOfWeek(dayIndex, weekDayToggleButton.isChecked());
            ++dayIndex;
        }
        int day1 = new DayOfWeek().getDayOfWeek();
        if (newhabit.getDayOfWeek(day1)) {
            cdhlc.addHabit(newhabit);


        }
        hlc.addHabit(newhabit);
        saveHabits();
        this.finish();
    }


    public void saveHabits(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            HabitList newHabitList = HabitListController.getHabitlist();
            gson.toJson(newHabitList, out);
            out.flush();

            fos.close();
            Toast.makeText(this, "I am saving", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }




    //TODO Save into masater.sav and create the habit.sav
}
