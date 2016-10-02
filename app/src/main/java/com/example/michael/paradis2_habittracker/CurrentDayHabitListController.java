package com.example.michael.paradis2_habittracker;

/**
 * Created by michael on 02/10/16.
 */

public class CurrentDayHabitListController {
    private static HabitList habitlist = null;
    public static HabitList getHabitlist() {
        if (habitlist == null) {
            habitlist = new HabitList();
            return habitlist;
        }
        return habitlist;
    }
    public void addHabit(Habit habit){
        habitlist.addHabit(habit);
    }
    public void removeHabit(Habit habit){
        habitlist.removeHabit(habit);
    }
}
