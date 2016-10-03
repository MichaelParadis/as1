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
//  Used the file saving from lonely Twitter lab

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ViewAllHabits extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Habit> allHabitAdapter;
    private ArrayList<Habit> habits = HabitListController.getHabitlist().getHabits();
    private String FILENAME = "Data.sav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_habits);
        listView = (ListView) findViewById(R.id.AllHabitsListView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewAllHabits.this);
                adb.setMessage("Delete " + habits.get(position).getHabitName()+"?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Habit habit = HabitListController.getHabitlist().getHabit(finalPosition);

                        HabitListController.getHabitlist().removeHabit(habit);
                        CurrentDayHabitListController.getHabitlist().removeHabit(habit);
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HabitModificationController.setModifyHabit(HabitListController.getHabitlist().getHabit(position));
                viewIndividualHabit();

            }
        });

    }

    private void viewIndividualHabit() {
        Intent intent = new Intent(ViewAllHabits.this, ViewIndividualHabit.class);
        startActivity(intent);
    }

    @Override
    protected void onStart(){
        super.onStart();
        allHabitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habits);
        HabitListController.getHabitlist().addListener(new Listener() {
            @Override
            public void update() {
                allHabitAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(allHabitAdapter);
    }
    public void saveHabits(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            HabitList newHabitList = HabitListController.getHabitlist();
            gson.toJson(newHabitList, out);
            out.flush();

            fos.close();
            // Toast.makeText(this, "I am saving", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }

}
