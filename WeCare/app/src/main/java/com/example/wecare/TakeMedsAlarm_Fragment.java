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

public class TakeMedsAlarm_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static Button Addbtn3;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;
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
        Addbtn3 = (Button) view.findViewById(R.id.add);


    }

    private void setListeners() {
        Addbtn3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new TakeMedsAlarm_Fragment(),
                                Utils.TakeMedsAlarm_Fragment).commit();

                break;



            }
        }
    }



















