package com.example.wecare;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyNewIntentservice","line 21");

        Notification.Builder builder = new Notification.Builder(getApplication());
        builder.setContentTitle("ReStockMeds Alarm");
        builder.setContentText("MedName");
        builder.setSmallIcon(R.drawable.ic_android);
        builder.setAutoCancel(true);
        Log.i("MyNewIntents","line 29");
        Intent notifyIntent = new Intent(getApplicationContext(), RestockMeds_Fragment.class);
        Log.i("MyNewIntents","line 32");
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 2, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);

    }
}
