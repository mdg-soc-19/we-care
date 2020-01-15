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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";
    private DatabaseReference RootRef,DemoRef;
    private static String M=null,m=null;
    private static Med med=null;
    public Query query;



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
        Log.i("NotifFrag","line 63");

        query = FirebaseDatabase.getInstance()
                .getReference()
                .child("MedName");

        query.addChildEventListener(new ChildEventListener() {
            @Override
            @NonNull
            public void onChildAdded(DataSnapshot dataSnapshot,String s) {
                Log.i("NotifFrag","line 72");
                m = dataSnapshot.getValue(String.class);
                Log.i("NotifFrag","line 75");


               // getChannelNotification().setContentText(MedName1)
                //
            }


            public void onChildChanged(DataSnapshot dataSnapshot,String s){}
            public void onChildRemoved(DataSnapshot dataSnapshot){}
            public void onChildMoved(DataSnapshot dataSnapshot,String s){}
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        if(m!=null){

            med.setMedName(m);
            Log.i("NotifFrag", "line 77");
        }
        else
            Log.i("NotifFrag","line 81");

       if(M!=null){ M=med.getMedName();}
        Log.i("NotifFrag","line 93");

        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("TakeMedAlarm")
                .setContentText(M)
                .setSmallIcon(R.drawable.ic_android);

        //           MedName1= "zu";
        // Toast.makeText(getApplicationContext(),"zu1",Toast.LENGTH_LONG).show();


    }



}
