package com.example.wecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class TakeMedsAlarm_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static Button Add;
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
        Add = (Button) view.findViewById(R.id.add);


    }

    private void setListeners() {
        Add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:

                break;



            }
        }
    }



















