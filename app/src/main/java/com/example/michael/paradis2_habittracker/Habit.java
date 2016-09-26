package com.example.michael.paradis2_habittracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by michael on 25/09/16.
 */

public class Habit implements Serializable{
    private String habitName;
    private Date startDate;
    private ArrayList<Date> completionDates;
    private int completions;
    private int missedCompletions;
    public Habit(String newHabitName, Date newStartDate ){
        habitName = newHabitName;
        completions = 0;
        missedCompletions = 0;
        completionDates = new ArrayList<Date>();
        startDate = newStartDate;
    }
    public Habit(String newHabitName){
        habitName = newHabitName;
        completions = 0;
        missedCompletions = 0;
        completionDates = new ArrayList<Date>();
        startDate = new Date();
    }

    public ArrayList<Date> getCompletionDates() {
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
        Date newestDate = new Date();
        completionDates.add(newestDate);
        ++completions;
    }

    public Habit(String habitName, Date startDate, ArrayList<Date> completionDates, int completions, int missedCompletions) {
        this.habitName = habitName;
        this.startDate = startDate;
        this.completionDates = completionDates;
        this.completions = completions;
        this.missedCompletions = missedCompletions;
    }

    public void removeCompletion(Date dateToRemove){
        if (completionDates.remove(dateToRemove))
            --completions;
    }
}
