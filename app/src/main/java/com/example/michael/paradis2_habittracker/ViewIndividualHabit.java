package com.example.michael.paradis2_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
        habit.addCompletion();

    }
}
