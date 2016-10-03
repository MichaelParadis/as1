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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ModifyHabit extends AppCompatActivity {
//TODO IF anything breaks when it comes to dayofweek buttons create new ids for the modifying buttons.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_habit);
    }
}
