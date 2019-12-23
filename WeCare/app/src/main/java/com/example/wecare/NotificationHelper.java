package com.example.wecare;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public String MedName1,MedName,MedDose;
    public Med Med;




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
       // MedName= "zu";


        DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference().child("MedName");
        RootRef.addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange( DataSnapshot dataSnapshot) {

                for(DataSnapshot med : dataSnapshot.getChildren()){
                    Med Med = med.getValue(Med.class);
                    if(Med!=null)
                        MedName1=Med.MedName;

                }

                   /** Med = dataSnapshot.getValue(Med.class);
                    if(Med!=null)
                    MedName=Med.MedName;
                    if(Med!=null)
                    MedDose = Med.MedDose;*/

            }
                @Override
                public void onCancelled(  DatabaseError databaseError) {
                    // Do something about the error
                    Toast.makeText(getApplicationContext(),"Unable to retrieve data",Toast.LENGTH_LONG).show();
            }
                });


        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("TakeMedAlarm")
                .setContentText(MedName1)
                .setSmallIcon(R.drawable.ic_android);
    }



}
