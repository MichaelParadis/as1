package com.example.michael.paradis2_habittracker;

import java.util.Calendar;

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
