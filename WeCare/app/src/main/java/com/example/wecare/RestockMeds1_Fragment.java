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

public class RestockMeds1_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
     private static LinearLayout restockmeds1_layout;
    private static Animation shakeAnimation;
    private static Button AddBtn;
    private static FragmentManager fragmentManager;

    public RestockMeds1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.restockmedsalarm1_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        AddBtn=(Button)view.findViewById(R.id.addr);



    }

    private void setListeners() {

        AddBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addr:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new RestockMeds_Fragment(),
                                Utils.RestockMeds_Fragment).commit();
                break;




        }

    }

}
