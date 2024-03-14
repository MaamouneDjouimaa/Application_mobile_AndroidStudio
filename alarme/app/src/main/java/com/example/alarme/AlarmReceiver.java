package com.example.alarme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int hours = intent.getIntExtra("hours", 0);
        int minutes = intent.getIntExtra("minutes", 0);

        String toastMessage = context.getString(R.string.toast_time_up);
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

        vibrateDevice(context);
        showNotification(context);
    }

    private void vibrateDevice(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(4000);
        }
    }

    private void showNotification(Context context) {
        String title = context.getString(R.string.alarm_title);
        String message = context.getString(R.string.alarm_message);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "alarm_channel")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());
    }
}
