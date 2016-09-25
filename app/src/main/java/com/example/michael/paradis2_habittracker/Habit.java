package com.example.michael.paradis2_habittracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by michael on 25/09/16.
 */

public class Habit implements Serializable{
    private ArrayList<Date> completiondates;
    private int completions;
    private int missedcompletions;
    private Date startdate;
    private String habbitname;
    public Habit(String newHabbitName, Date newStartDate ){
        habbitname = newHabbitName;
        completions = 0;
        missedcompletions = 0;
        completiondates = new ArrayList<Date>();
        startdate = newStartDate;
    }
    public Habit(String newHabbitName){
        habbitname = newHabbitName;
        completions = 0;
        missedcompletions = 0;
        completiondates = new ArrayList<Date>();
        startdate = new Date();
    }

    public ArrayList<Date> getCompletiondates() {
        return completiondates;
    }

    public int getCompletions() {
        return completions;
    }

    public int getMissedcompletions() {
        return missedcompletions;
    }

    public Date getStartdate() {
        return startdate;
    }

    public String getHabbitname() {
        return habbitname;
    }

    public void addMissedCompletion(){
        ++missedcompletions;
    }

    public void addCompletion(){
        Date newestDate = new Date();
        completiondates.add(newestDate);
        ++completions;
    }

    public Habit(ArrayList<Date> completiondates, int completions, int missedcompletions, Date startdate, String habbitname) {
        this.completiondates = completiondates;
        this.completions = completions;
        this.missedcompletions = missedcompletions;
        this.startdate = startdate;
        this.habbitname = habbitname;
    }

    public void removeCompletion(Date dateToRemove){
        if (completiondates.remove(dateToRemove))
            --completions;
    }
}
