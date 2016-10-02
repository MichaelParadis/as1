package com.example.michael.paradis2_habittracker;

/**
 * Created by michael on 02/10/16.
 */

public class HabitModificationController {
    private static Habit habit = null;
    public static void setModifyHabit(Habit newhabit){
        habit = newhabit;
    }
    public static Habit getModifyHabit(){
        return habit;
    }

}
