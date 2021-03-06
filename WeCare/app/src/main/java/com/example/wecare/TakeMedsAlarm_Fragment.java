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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wecare.R;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TakeMedsAlarm_Fragment extends Fragment implements MainActivity.OnBackPressedListener{

    private static View view;

    private static Button Save,BackBtn;


    private static EditText Name,Dose;
    private static Button T;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    private DatabaseReference RootRef,DemoRef;
    private FirebaseUser user;
    private String uid;
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


        user = FirebaseAuth.getInstance().getCurrentUser();
        RootRef = FirebaseDatabase.getInstance().getReference();
        uid= user.getUid();
        DemoRef = RootRef.child(uid).child("Medname");

        Name=(EditText)view.findViewById(R.id.name);
      //  Dose=(EditText)view.findViewById(R.id.dose);

        T=(Button) view.findViewById(R.id.t);
        BackBtn=(Button) view.findViewById(R.id.back);

        Save= (Button) view.findViewById(R.id.save);
    }

    private void setListeners() {

        T.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();

                timePicker.show(getFragmentManager(), "time picker");

               /** new CustomToast().Show_Toast(getActivity(), view,
                        "Zu.");*/

            }


        });



        Save.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Medname = Name.getText().toString();

               DemoRef.push().setValue(Medname);

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
























