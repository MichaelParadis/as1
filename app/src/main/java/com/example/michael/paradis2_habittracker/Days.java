package com.example.michael.paradis2_habittracker;

/**
 * Created by michael on 25/09/16.
 */

public class Days {
    private String dayOfWeek;
    private HabitList listofHabits;

    public Days(String weekday) {
        dayOfWeek = weekday;
        listofHabits = new HabitList();
    }
    public void addHabbit(Habit habit){
        listofHabits.addHabit(habit);
    }
    public void completeHabbit(Habit habit){
        habit.addCompletion();

    }
    public void removeHabbit(Habit habit){
        listofHabits.removeHabit(habit);
    }
    public String getDayOfWeek(){
        return dayOfWeek;
    }

}
