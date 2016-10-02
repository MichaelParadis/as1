package com.example.michael.paradis2_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ViewIndividualHabit extends AppCompatActivity {
    private int[]  WeekDayButtonsid = {R.id.SundayToggleButton, R.id.MondayToggleButton, R.id.TuesdayToggleButton,
            R.id.WednesdayToggleButton, R.id.ThursdayToggleButton, R.id.FridayToggleButton, R.id.SaturdayToggleButton};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_individual_habit);
        TextView habitnametextview = (TextView) findViewById(R.id.ModifyHabitHabitName);
        Habit habit = HabitModificationController.getModifyHabit();
        habitnametextview.setText(habit.getHabitName());
        int day = 0;
        for(int button: WeekDayButtonsid){
            ToggleButton currentDayButton = (ToggleButton)findViewById(button);
            if (habit.getDayOfWeek(day)){
                currentDayButton.setChecked(true);
            }
            ++day;
        }
        
    }
}
