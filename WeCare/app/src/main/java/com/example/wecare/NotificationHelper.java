package com.example.wecare;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private DatabaseReference RootRef,DemoRef1,DemoRef2;
    private  String MedName,MedDose;

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);
    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotification() {

        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef1=RootRef.child("MedName");
        DemoRef2=RootRef.child("MedDose");
        MedName= "zu";

         class Med {

            public String Medname;
            public String Meddose;

            public Med(String MedName, String MedDose) {
                // ...
            }

        }

        RootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             Med med =  dataSnapshot.getValue(Med.class);
             MedName=med.Medname;
             MedDose=med.Meddose;

            }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("UserListActivity", "Error occured");
                    // Do something about the error


                }
                });


        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("TakeMedAlarm")
                .setContentText(MedName)
                .setSmallIcon(R.drawable.ic_android);
    }



}
