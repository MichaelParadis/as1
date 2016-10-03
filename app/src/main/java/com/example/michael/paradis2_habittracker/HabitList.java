package com.example.michael.paradis2_habittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by michael on 25/09/16.
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
        if (HabitsList.remove(removeHabit)){
            --size;
            notifyListeners();
        }

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
        if (listeners != null) {
            for (Listener listener : listeners) {
                if (listener != null)
                    listener.update();
            }
        }
            
    }

    public void addListener(Listener l){
        listeners.add(l);
    }
    public void removeListener(Listener l){
        listeners.remove(l);
    }
}
