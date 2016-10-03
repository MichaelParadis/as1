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

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewIndividualHabit extends AppCompatActivity {
    private int[]  WeekDayButtonsid = {R.id.SundayToggleButton, R.id.MondayToggleButton, R.id.TuesdayToggleButton,
            R.id.WednesdayToggleButton, R.id.ThursdayToggleButton, R.id.FridayToggleButton, R.id.SaturdayToggleButton};
    private ArrayList<String> completionDates = HabitModificationController.getModifyHabit().getCompletionDates();
    private ArrayAdapter<String> dateAdapter;
    private ListView listView;
    private String FILENAME = "Data.sav";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_habit);
        TextView habitnametextview = (TextView) findViewById(R.id.ModifyHabitHabitName);
        Habit habit = HabitModificationController.getModifyHabit();
        habitnametextview.setText(habit.getHabitName());
        listView = (ListView) findViewById(R.id.IndividualActivityCompletionsListView);

        int day = 0;
        for(int button: WeekDayButtonsid){
            ToggleButton currentDayButton = (ToggleButton)findViewById(button);
            if (habit.getDayOfWeek(day)){
                currentDayButton.setChecked(true);
            }
            ++day;
        }
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
        TextView DateText = (TextView) findViewById(R.id.ViewHabitCreationDateText);
        DateText.setText(formats.format(habit.getStartDate()));
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ViewIndividualHabit.this);
                adb.setMessage("Delete this completion?");
                adb.setCancelable(true);
                final int finalPosition = position;

                adb.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String dateToRemove = HabitModificationController.getModifyHabit().getCompletionDates().get(finalPosition);
                        HabitModificationController.getModifyHabit().removeCompletion(dateToRemove);
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



    }
    @Override
    protected void onStart(){
        super.onStart();
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, completionDates);
        HabitModificationController.getModifyHabit().addListener(new Listener() {
            @Override
            public void update() {
                dateAdapter.notifyDataSetChanged();
            }
        });
        listView.setAdapter(dateAdapter);
    }
    public void addCompletion(View v){
        Habit habit = HabitModificationController.getModifyHabit();
        HabitModificationController.getModifyHabit().addListener(new Listener() {
            @Override
            public void update() {
                dateAdapter.notifyDataSetChanged();
            }});
        habit.addCompletion();
        saveHabits();

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
            //Toast.makeText(ViewIndividualHabit.this, " I am saving", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        } catch (IOException e) {
			/* Rethrow. */
            throw new RuntimeException(e);
        }
    }


}
