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

/*
            Copyright 2016 Michael Paradis, Android Documentation

            Licensed under the Apache License, Version 2.0 (the "License");
            you may not use this file except in compliance with the License.
            You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

            Unless required by applicable law or agreed to in writing, software
            distributed under the License is distributed on an "AS IS" BASIS,
            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
            See the License for the specific language governing permissions and
            limitations under the License.
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
