package com.example.michael.paradis2_habittracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ToggleButton;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by michael on 25/09/16.
 * Used from https://developer.android.com/guide/topics/ui/controls/pickers.html
 * to allow the setting of the date for the newhabit
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    //EditText input = (EditText) getActivity().findViewById(R.id.NewHabitDate);




    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(),  this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        Calendar c = Calendar.getInstance();
        c.set(year,month,day);
        Date date = c.getTime();
        EditText input = (EditText) getActivity().findViewById(R.id.NewHabitDate);
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        input.setText(formats.format(date));
        int[] weekDayButtons =  ((CreateNewHabit) getActivity()).getWeekDayButtonsid();
        for(int i= 0; i < 7; i++){
            ((ToggleButton)getActivity().findViewById(weekDayButtons[i])).setChecked(false);
        }

        int weekday = new DayOfWeek(c).getDayOfWeek();
        ((ToggleButton)getActivity().findViewById(weekDayButtons[weekday])).toggle();


        //this.dismiss();
    }

}
