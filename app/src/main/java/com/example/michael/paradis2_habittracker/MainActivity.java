package com.example.michael.paradis2_habittracker;
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
// File saving from LonelyTwitter lab
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
 /*
    private final String MasterFile = "MASTERFILE.SAV"; // Will hold the file names for every habbit
    private final String MondayFile = "MONDAY.SAV";
    private final String TuesdayFile = "TUESDAY.SAV";
    private final String WednesdayFile = "WEDNESDAY.SAV";
    private final String ThursdayFile = "THURSDAY.SAV";
    private final String FridayFile = "FRIDAY.SAV";
    private final String SaturdayFile = "SATURDAY.SAV";
    private final String SundayFile = "SUNDAY.SAV";
*/
    private static String FILENAME = "Data.sav";
    private HabitList masterHabitList = new HabitList();
    private HabitList todaysHabits = new HabitList();
    private ArrayAdapter<HabitList> todaysHabitListAdapter;
    private HabitListController hlc = new HabitListController();
    private HabitList hl = HabitListController.getHabitlist();
    private ArrayList<Habit> habits =  CurrentDayHabitListController.getHabitlist().getHabits();
    private ArrayAdapter<Habit> habitAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        listView = (ListView) findViewById(R.id.CurrentHabitsList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HabitModificationController.setModifyHabit(CurrentDayHabitListController.getHabitlist().getHabit(position));
                viewIndividualHabit();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setMessage("Delete " + habits.get(position).getHabitName()+"?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = CurrentDayHabitListController.getHabitlist().getHabit(finalPosition);
                        CurrentDayHabitListController.getHabitlist().removeHabit(habit);
                        HabitListController.getHabitlist().removeHabit(habit);
                        saveHabits();
                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                adb.show();


                return false;
            }
        });
        loadHabits();
    }
    @Override
    protected void onStart() {
        super.onStart();
        //loadHabits();

        //listView = (ListView) findViewById(R.id.CurrentHabitsList);

        CurrentDayHabitListController.getHabitlist().addListener(new Listener() {
            @Override
            public void update() {
                //habits.clear();
                //ArrayList<Habit> habitsNew = HabitListController.getHabitlist().getHabits();
                //habits.clear();
                //habits.addAll(HabitListController.getHabitlist().getHabits());
                //habits.addAll(HabitListController.getHabitlist().getHabits());
                //habits.addAll(habitsNew);
                habitAdapter.notifyDataSetChanged();
            }
        });
        //listView.setAdapter(habitAdapter);
        //Habit test = new Habit("Habit1");
        //test.setDayOfWeek(0,true);
        //habits.add(test);

        habitAdapter.notifyDataSetChanged();
    }



    @Override
    protected void onResume(){
        super.onResume();
        //loadHabits();
        CurrentDayHabitListController.getHabitlist().addListener(new Listener() {
            @Override
            public void update() {
                //habits.clear();
                //ArrayList<Habit> habitsNew = HabitListController.getHabitlist().getHabits();
                //habits.clear();
                //habits.addAll(HabitListController.getHabitlist().getHabits());
                //habits.addAll(habitsNew);
                habitAdapter.notifyDataSetChanged();
            }
        });
        habits = CurrentDayHabitListController.getHabitlist().getHabits();

        habitAdapter.notifyDataSetChanged();

        Toast.makeText(MainActivity.this, Integer.toString(habits.size()), Toast.LENGTH_SHORT).show();
        //saveHabits();

    }
    // From student picker menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void addHabit(View v){
        Intent intent = new Intent(MainActivity.this, CreateNewHabit.class);
        startActivity(intent);

    }

    public void viewAllHabits(MenuItem menu){
        Intent intent = new Intent(MainActivity.this, ViewAllHabits.class);
        startActivity(intent);
    }
    public void loadHabits(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<HabitList>(){}.getType();
            //HabitListController.setHabitlist(new HabitList());
            HabitList habitList;

            habitList = gson.fromJson(in,listType);
            HabitListController.setHabitlist(habitList);
            HabitListController.getHabitlist().addListener(new Listener() {
                @Override
                public void update() {
                    habitAdapter.notifyDataSetChanged();
                }
            });
            HabitList currentDayHabits = new HabitList();
            int day = new DayOfWeek().getDayOfWeek();
            for (int i = 0; i < habitList.getSize(); ++i){
                if (habitList.getHabit(i).getDayOfWeek(day)){
                    currentDayHabits.addHabit(habitList.getHabit(i));
                    //Toast.makeText(this, "I am remaking currentday", Toast.LENGTH_SHORT).show();
                }
            }
            CurrentDayHabitListController.setHabitlist(currentDayHabits);
            CurrentDayHabitListController.getHabitlist().addListener(new Listener() {
                @Override
                public void update() {
                    habitAdapter.notifyDataSetChanged();

                }
            });
            habits = CurrentDayHabitListController.getHabitlist().getHabits();
            habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habits);
            listView.setAdapter(habitAdapter);
            habitAdapter.notifyDataSetChanged();


        } catch (FileNotFoundException e) {
            /* Create a brand new tweet list if we can't find the file. */
            habits = CurrentDayHabitListController.getHabitlist().getHabits();
            habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habits);
            listView.setAdapter(habitAdapter);
            habitAdapter.notifyDataSetChanged();
        }


    }
    public void saveHabits(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(HabitListController.getHabitlist(), out);
            out.flush();

            fos.close();
            Toast.makeText(MainActivity.this, " I am saving", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }


    public void viewIndividualHabit(){
        Intent intent = new Intent(MainActivity.this, ViewIndividualHabit.class);
        startActivity(intent);
    }
    public HabitList getMasterHabitList() {
        return masterHabitList;
    }

    public HabitList getTodaysHabits() {
        return todaysHabits;
    }

    public static String getFileName(){
        return FILENAME;
    }

}
