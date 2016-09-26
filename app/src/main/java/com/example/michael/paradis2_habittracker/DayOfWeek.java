package com.example.michael.paradis2_habittracker;

import java.util.Calendar;

/**
 * Created by michael on 25/09/16.
 */

public class DayOfWeek {
    Calendar c;
    public DayOfWeek() {
        c = Calendar.getInstance();
    }
    public DayOfWeek(Calendar calendar){
        c = calendar;

    }

    private final int Sunday = 0;
    private final int Monday = 1;
    private final int Tuesday = 2;
    private final int Wednesday = 3;
    private final int Thursday = 4;
    private final int Friday = 5;
    private final int Saturday = 6;
    public int getDayofWeek(){

        int day = this.c.get(Calendar.DAY_OF_WEEK);
        switch (day){
            case Calendar.SUNDAY: return Sunday;
            case Calendar.MONDAY: return Monday;
            case Calendar.TUESDAY: return Tuesday;
            case Calendar.WEDNESDAY: return Wednesday;
            case Calendar.THURSDAY: return Thursday;
            case Calendar.FRIDAY: return Friday;
            case Calendar.SATURDAY: return Saturday;
        }
        return -1;
    }

}
