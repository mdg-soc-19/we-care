package com.example.wecare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Symptoms1_Fragment extends Fragment implements MainActivity.OnBackPressedListener{
    private static View view;
    // private static LinearLayout medicalaid_layout;
    private static Animation shakeAnimation;
    private Button BackBtn;

    public CalendarView C;
    private static FragmentManager fragmentManager;

    public Symptoms1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.symptoms1_layout, container, false);
        initViews();
        C.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView C, int year, int month, int dayOfMonth) {
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Symptoms_Fragment(),
                                Utils.Symptoms_Fragment).commit();

            }
        });

        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MainActivity().replaceMedicalAid_Fragment();
            }
        });


        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        C=(CalendarView)view.findViewById(R.id.c);
        BackBtn=(Button)view.findViewById(R.id.back);


    }
    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(),"You'll be directed to HomePage",Toast.LENGTH_LONG).show();
        return false;

    }


    }






