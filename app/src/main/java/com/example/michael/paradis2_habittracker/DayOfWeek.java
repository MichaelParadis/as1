package com.example.michael.paradis2_habittracker;

import java.util.Calendar;

/**
 * Created by michael on 25/09/16.
 */

public class DayOfWeek {
    private Calendar c;
    public DayOfWeek() {
        c = Calendar.getInstance();
    }
    public DayOfWeek(Calendar calendar){
        c = calendar;

    }

    public final static int Sunday = 0;
    public final static int  Monday = 1;
    public final static int Tuesday = 2;
    public final static int Wednesday = 3;
    public final static int Thursday = 4;
    public final static int Friday = 5;
    public final static int Saturday = 6;
    public int getDayOfWeek(){

        int day = c.get(Calendar.DAY_OF_WEEK);
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
    static public int getDayOfWeek(int offset){
        Calendar d = Calendar.getInstance();
        d.set(Calendar.DAY_OF_YEAR, d.get(Calendar.DAY_OF_YEAR)+offset);
        int day = d.get(Calendar.DAY_OF_WEEK);
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
