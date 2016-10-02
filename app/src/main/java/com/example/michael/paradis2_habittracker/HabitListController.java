package com.example.michael.paradis2_habittracker;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by michael on 01/10/16.
 * Based on studentpicker
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
    public void addHabit(Habit habit){
        habitlist.addHabit(habit);
    }
    public void removeHabit(Habit habit){
        habitlist.removeHabit(habit);
    }

}
