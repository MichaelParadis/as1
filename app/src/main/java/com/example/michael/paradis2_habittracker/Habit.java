package com.example.michael.paradis2_habittracker;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

public class Habit implements Serializable{
    private String habitName;
    private Date startDate;
    private ArrayList<String> completionDates;
    private int completions;
    private int missedCompletions;
    protected ArrayList<Listener> listeners = new ArrayList<>();

    private boolean[] dayOfWeekBooleanArray = {false, false, false, false, false, false,  false};
    public Habit(String newHabitName, Date newStartDate ){
        habitName = newHabitName;
        completions = 0;
        missedCompletions = 0;
        completionDates = new ArrayList<String>();
        startDate = newStartDate;
    }
    public Habit(String newHabitName){
        habitName = newHabitName;
        completions = 0;
        missedCompletions = 0;
        completionDates = new ArrayList<String>();
        startDate = new Date();
    }

    public ArrayList<String> getCompletionDates() {
        return completionDates;
    }

    public int getCompletions() {
        return completions;
    }

    public int getMissedCompletions() {
        return missedCompletions;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getHabitName() {
        return habitName;
    }

    public void addMissedCompletion(){
        ++missedCompletions;
    }

    public void addCompletion(){
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);

        String newestDate = formats.format(new Date());
        completionDates.add(newestDate);
        ++completions;

        notifyListeners();
    }

    private void notifyListeners() {

            for(Listener listener:listeners) {
                if (listener != null) {
                    listener.update();
                }
            }


    }

    public Habit(String habitName, Date startDate, ArrayList<String> completionDates, int completions, int missedCompletions) {
        this.habitName = habitName;
        this.startDate = startDate;
        this.completionDates = completionDates;
        this.completions = completions;
        this.missedCompletions = missedCompletions;
    }

    public void removeCompletion(String dateToRemove){
        if (completionDates.remove(dateToRemove)){
            --completions;
            notifyListeners();
        }

    }
    public void addListener(Listener l){
        listeners.add(l);
    }
    public void setDayOfWeek(int day, boolean value){
        dayOfWeekBooleanArray[day] = value;
    }
    public boolean getDayOfWeek(int day){
        return dayOfWeekBooleanArray[day];
    }
    public String toString(){
        return habitName;
    }

}
