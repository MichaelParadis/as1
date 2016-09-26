package com.example.michael.paradis2_habittracker;

/**
 * Created by michael on 26/09/16.
 */
import com.example.michael.paradis2_habittracker.Habit;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class HabitTest extends TestCase {
    @Test
    public void TestHabit() {
        String habitName = "Habit";
        Habit habit = new Habit(habitName);
        assertTrue("Habit name not equal", habitName.equals(habit.getHabitName()));
    }
    @Test
    public void TestDifferentDateHabit(){
        String habitName = "Habit";
        Date startDate = new Date();
        Habit habit = new Habit(habitName, startDate);
        assertTrue("Habit name not equal", habitName.equals(habit.getHabitName()));
        assertTrue("StartDate not equal", habit.getStartDate() == startDate);

    }
    @Test
    public void TestGetCompletions(){
        String habitName = "Habit";
        Habit habit = new Habit(habitName);
        assertTrue("Habit not initialized correctly", habit.getCompletions() == 0);
        assertTrue("Habit not initialized correctly2", habit.getCompletionDates() == new ArrayList<Date>());

        Date startDate = new Date();
        Habit habit2 = new Habit(habitName, startDate);
        assertTrue("Habit with set start date not given correct completions", habit2.getCompletions() == 0);
        assertTrue("Habit with set start date not given correct completiondateslist", habit2.getCompletionDates() == new ArrayList<Date>());

    }

}
