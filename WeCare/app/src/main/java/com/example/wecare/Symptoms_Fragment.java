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

public class Symptoms_Fragment extends Fragment implements View.OnClickListener {
    private static View view;

    private static Animation shakeAnimation;
    private static Button TakeMedAlarmBtn;
    private static Button RestockMedBtn;
    private static FragmentManager fragmentManager;

    public Symptoms_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.symptoms_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

    }

    private void setListeners() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takemed:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new TakeMedsAlarm_Fragment(),
                                Utils.TakeMedsAlarm_Fragment).commit();
                break;

            /** switch (v.getId()) {
             case R.id.restockmed:
             fragmentManager
             .beginTransaction()
             .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
             .replace(R.id.frameContainer,
             new RestockMeds_Fragment(),
             Utils.RestockMeds_Fragment).commit();
             break;

             }
             */

        }
    }

}



