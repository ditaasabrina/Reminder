package com.amikom.two;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity_alarm extends AppCompatActivity{

    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_alarm);

        Intent alarmIntent = new Intent(MainActivity_alarm.this, AppReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity_alarm.this, ALARM_REQUEST_CODE, alarmIntent, 0);
    }

    public void startAlarmManager(View v) {

        //set waktu sekarang berdasarkan interval
        Calendar cal = Calendar.getInstance();
        //set interval notifikasi 10 detik
        int interval_seconds = 10;
        cal.add(Calendar.SECOND, interval_seconds);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        //set alarm manager dengan memasukkan waktu yang telah dikonversi menjadi milliseconds
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "AlarmManager Start.", Toast.LENGTH_SHORT).show();
    }


    //Stop/Cancel alarm manager
    public void stopAlarmManager(View v) {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);

        //close existing/current notifications
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        int NOTIFICATION_ID = 1;
        notificationManager.cancel(NOTIFICATION_ID);

        Toast.makeText(this, "AlarmManager Stopped by User.", Toast.LENGTH_SHORT).show();
    }


}
