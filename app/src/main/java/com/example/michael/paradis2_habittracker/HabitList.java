package com.example.michael.paradis2_habittracker;

import java.util.ArrayList;

/**
 * Created by michael on 25/09/16.
 */

public class HabitList {
    private ArrayList<Habit> HabitsList;
    private int size;
    public HabitList() {
        HabitsList = new ArrayList<>();
        size = 0;
    }
    public void addHabit(Habit newHabit){
        HabitsList.add(newHabit);
        ++size;
    }
    public void removeHabit(Habit removeHabit){
        HabitsList.remove(removeHabit);
        --size;
    }
    public int getSize(){
        return size;
    }
}
