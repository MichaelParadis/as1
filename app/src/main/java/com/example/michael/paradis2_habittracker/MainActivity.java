package com.example.michael.paradis2_habittracker;

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
    protected void onStart() {
        super.onStart();
        loadHabits();
        //listView = (ListView) findViewById(R.id.CurrentHabitsList);
        habitAdapter = new ArrayAdapter<Habit>(this, android.R.layout.simple_list_item_1, habits);
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
        listView.setAdapter(habitAdapter);
        //Habit test = new Habit("Habit1");
        //test.setDayOfWeek(0,true);
        //habits.add(test);
        habitAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume(){
        super.onResume();



        habitAdapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, Integer.toString(habits.size()), Toast.LENGTH_SHORT).show();


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

    private void loadHabits(){
        

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

}
