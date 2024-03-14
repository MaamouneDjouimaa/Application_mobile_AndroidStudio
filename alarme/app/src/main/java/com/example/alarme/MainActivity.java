package com.example.alarme;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedHour = timePicker.getCurrentHour();
                int selectedMinute = timePicker.getCurrentMinute();

                Calendar currentTime = Calendar.getInstance();
                int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
                int currentMinute = currentTime.get(Calendar.MINUTE);

                int remainingHours;
                int remainingMinutes;

                if (selectedHour > currentHour || (selectedHour == currentHour && selectedMinute > currentMinute)) {
                    remainingHours = selectedHour - currentHour;
                    remainingMinutes = selectedMinute - currentMinute;
                } else {
                    remainingHours = (24 - currentHour) + selectedHour;
                    remainingMinutes = (60 - currentMinute) + selectedMinute;
                }

                String toastMessage = getString(R.string.toast_alarm_trigger, remainingHours, remainingMinutes);
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT).show();

                startAlarm(remainingHours, remainingMinutes);
            }
        });
    }

    private void startAlarm(int hours, int minutes) {
        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        alarmIntent.putExtra("hours", hours);
        alarmIntent.putExtra("minutes", minutes);
        sendBroadcast(alarmIntent);
    }
}
