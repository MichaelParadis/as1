package com.example.michael.paradis2_habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by michael on 25/09/16.
 */

public class HabitList {
    private ArrayList<Habit> HabitsList;
    private int size;
    protected ArrayList<Listener> listeners = new ArrayList<>();

    public HabitList(ArrayList<Habit> habitsList, int size) {
        HabitsList = habitsList;
        this.size = size;
    }

    public HabitList() {
        HabitsList = new ArrayList<>();
        size = 0;
    }
    public void addHabit(Habit newHabit){
        HabitsList.add(newHabit);
        ++size;
        notifyListeners();
    }
    public void removeHabit(Habit removeHabit){
        HabitsList.remove(removeHabit);
        --size;
        notifyListeners();
    }
    public int getSize(){
        return size;
    }
    public  Habit getHabit(int i){
        return HabitsList.get(i);
    }
    public ArrayList<Habit> getHabits(){
        return HabitsList;
    }
    public void notifyListeners(){
        for (Listener listener: listeners) {
            listener.update();
        }
            
    }

    public void addListener(Listener l){
        listeners.add(l);
    }
    public void removeListener(Listener l){
        listeners.remove(l);
    }
}
