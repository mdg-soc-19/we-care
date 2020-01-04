package com.example.wecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;



public class PlanningAid_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static LinearLayout medicalaid_layout;
    private static Animation shakeAnimation;
    private static Button TakeMedAlarmBtn;
    private static Button RestockMedBtn;
    private static Button DocBtn;
    private static Button SymptomBtn;
    private static FragmentManager fragmentManager;

    public PlanningAid_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.planingaid_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        TakeMedAlarmBtn = (Button) view.findViewById(R.id.util);
        RestockMedBtn = (Button) view.findViewById(R.id.cal);



    }

    private void setListeners() {
        TakeMedAlarmBtn.setOnClickListener(this);
        RestockMedBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.util:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Utilities_Fragment(),
                                Utils.Utilities_Fragment).commit();
                break;

            case R.id.cal:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Calendar_Fragment(),
                                Utils.Calendar_Fragment).commit();
                break;






        }

    }

}


