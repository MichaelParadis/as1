package com.example.michael.paradis2_habittracker;

/**
 * Created by michael on 02/10/16.
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


public class HabitModificationController {
    private static Habit habit = null;
    public static void setModifyHabit(Habit newhabit){
        habit = newhabit;
    }
    public static Habit getModifyHabit(){
        return habit;
    }

}
