package com.example.michael.paradis2_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Calendar;

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
    private HabitList masterHabitList = new HabitList();
    private HabitList todaysHabits = new HabitList();
    private ArrayAdapter<HabitList> todaysHabitListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // From student picker menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onStart() {
        super.onStart();
        loadHabits();
    }

    public void addHabit(View v){
        Intent intent = new Intent(MainActivity.this, CreateNewHabit.class);
        startActivity(intent);

    }
    public void viewAllHabits(View v){
        Intent intent = new Intent(MainActivity.this, ViewAllHabits.class);
        startActivity(intent);
    }

    private void loadHabits(){
        

    }

    public HabitList getMasterHabitList() {
        return masterHabitList;
    }

    public HabitList getTodaysHabits() {
        return todaysHabits;
    }

}
