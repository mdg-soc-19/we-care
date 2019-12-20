package com.example.wecare;

import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import androidx.fragment.app.DialogFragment;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.DateFormat;
import java.util.Calendar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TakeMedsAlarm_Fragment extends Fragment  {

    private static View view;
    private static Button Save;
    private static EditText Name,Dose;
    private static Button T;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    private DatabaseReference RootRef;

    public TakeMedsAlarm_Fragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.takemedsalarm_layout, container, false);
        initViews();
        setListeners();

        return view;
    }


    private void initViews() {

        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        RootRef = FirebaseDatabase.getInstance().getReference();

        Name=(EditText)view.findViewById(R.id.name);
        Dose=(EditText)view.findViewById(R.id.dose);
        T=(Button) view.findViewById(R.id.t);
        Save= (Button) view.findViewById(R.id.save);
    }

    private void setListeners() {

        T.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                Calendar c = Calendar.getInstance();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                startAlarm(c);
            }


        });



        Save.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String MedName = Name.getText().toString();
                RootRef.push().setValue(MedName);
                String MedDose = Dose.getText().toString();
                RootRef.push().setValue(MedDose);
            }
        });
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }




    }





























