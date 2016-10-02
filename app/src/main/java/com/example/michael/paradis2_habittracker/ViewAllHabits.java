package com.example.michael.paradis2_habittracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewAllHabits extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<Habit> allHabitAdapter;
    private ArrayList<Habit> habits = HabitListController.getHabitlist().getHabits();

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


}
