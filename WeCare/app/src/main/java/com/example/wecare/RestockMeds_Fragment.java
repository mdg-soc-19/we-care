package com.example.wecare;


import android.app.Activity;
import android.app.AlarmManager;

import android.app.PendingIntent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.Calendar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestockMeds_Fragment extends Fragment implements MainActivity.OnBackPressedListener {
    private static View view;

    private static Animation shakeAnimation;
    private static Button Save1,BackBtn;
    private static EditText Name,Dose;
    private DatePicker d;
    public Context context;

    private static Button RestockMedBtn;
    private static TextView Symptom;
    private DatabaseReference RootRef,DemoRef11;
    private static FragmentManager fragmentManager;
    public int  NOTIFICATION_REMINDER = 3;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public RestockMeds_Fragment() {

    }
/**
    @Override
    public void onAttach(Context context){
        this.context = context;
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restockmedsalarm_layout, container, false);

        initViews();
        setListeners();
        return view;
    }


    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();


        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        Name=(EditText)view.findViewById(R.id.name);

        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef11=RootRef.child("RMedName");



        d=(DatePicker)view.findViewById(R.id.D);
        Save1= (Button) view.findViewById(R.id.save1);
        BackBtn= (Button) view.findViewById(R.id.back);


    }

    private void setListeners() {
        Log.i("RestockMedsFrag","line 82");
        Save1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("RestockMedsFrag","line 86");

                        Calendar now = Calendar.getInstance();
                        Calendar current = Calendar.getInstance();
                        now.set(d.getYear(),d.getMonth(), d.getDayOfMonth());
                        if (now.compareTo(current) <= 0) {
                            Toast.makeText(getActivity(), "invalid time", Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("RestockMedsFrag","line 93");
                            Intent notifyIntent = new Intent(getActivity(), MyReceiver.class);
                            notifyIntent.putExtra("title","title");
                            PendingIntent pendingIntent = PendingIntent.getBroadcast
                                    (getActivity(),NOTIFICATION_REMINDER, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                            alarmManager.set(AlarmManager.RTC, now.getTimeInMillis(), pendingIntent);
                        }

                        String Medname1 = Name.getText().toString();
                        DemoRef11.push().setValue(Medname1);
                    }
                    });


        BackBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new MainActivity().replaceMedicalAid_Fragment();


                    }
                });


                    }

    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(),"You'll be directed to HomePage",Toast.LENGTH_LONG).show();
        return false;

    }



}



