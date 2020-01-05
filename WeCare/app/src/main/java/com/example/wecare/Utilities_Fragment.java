package com.example.wecare;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Utilities_Fragment extends Fragment {
    private static View view;

    private static Animation shakeAnimation;
    private static Button Save1,BackBtn;
    private static EditText Name,Dose;
    private DatePicker d;
    public Context context;
    private static Button TakeMedAlarmBtn;
    private static Button RestockMedBtn;
    private static TextView Symptom;
    private DatabaseReference RootRef,DemoRef13;
    private static FragmentManager fragmentManager;
    public int  NOTIFICATION_REMINDER = 3;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public Utilities_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.utilities_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        Name=(EditText)view.findViewById(R.id.name);
       // Dose=(EditText)view.findViewById(R.id.dose);
        Symptom=(TextView)view.findViewById(R.id.symptom);
        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef13=RootRef.child("UBillName");


        d=(DatePicker)view.findViewById(R.id.D);
        Save1= (Button) view.findViewById(R.id.save1);
        BackBtn= (Button) view.findViewById(R.id.back);


    }

    private void setListeners() {
        d.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar now = Calendar.getInstance();
                        Calendar current = Calendar.getInstance();

                        now.set(now.get(d.getYear()), now.get(d.getMonth()), now.get(d.getDayOfMonth()));
                        if (now.compareTo(current) <= 0) {
                            Toast.makeText(getActivity(), "invalid time", Toast.LENGTH_LONG).show();
                        } else {
                            Intent notifyIntent = new Intent(context, MyReceiver.class);
                            notifyIntent.putExtra("title","title");
                            PendingIntent pendingIntent = PendingIntent.getBroadcast
                                    (context,NOTIFICATION_REMINDER, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                            alarmManager.set(AlarmManager.RTC, now.getTimeInMillis(), pendingIntent);
                        }
                    }
                });
        Save1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String Medname1 = Name.getText().toString();
                        DemoRef13.push().setValue(Medname1);

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



}



