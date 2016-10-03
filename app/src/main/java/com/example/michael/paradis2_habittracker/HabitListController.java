package com.example.michael.paradis2_habittracker;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by michael on 01/10/16.
 * Based on studentpicker
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
public class HabitListController {
    private static HabitList habitlist = null;
    public static HabitList getHabitlist(){
        if (habitlist == null) {
            habitlist = new HabitList();
            return habitlist;
        }
        return habitlist;
    }
    public static HabitList getCurrentDayHabits(){

        int day = new DayOfWeek().getDayOfWeek();
        HabitList returnList = new HabitList();
        for(int i = 0; i < habitlist.getSize(); ++i){
            if (habitlist.getHabit(i).getDayOfWeek(day)){
                returnList.addHabit(habitlist.getHabit(i));
            }
        }
        return returnList;
    }
    public static void setHabitlist(HabitList newHabitList){
        habitlist = newHabitList;
    }
    public void addHabit(Habit habit){
        habitlist.addHabit(habit);
    }
    public void removeHabit(Habit habit){
        habitlist.removeHabit(habit);
    }

}
