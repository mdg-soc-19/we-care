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

public class DocVisits1_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    // private static LinearLayout medicalaid_layout;
    private static Animation shakeAnimation;


    private static Button AddBtn1;
    private static FragmentManager fragmentManager;

    public DocVisits1_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.docvisits1_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        AddBtn1=(Button)view.findViewById(R.id.add);


    }

    private void setListeners() {
        AddBtn1.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new DocVisits_Fragment(),
                                Utils.DocVisits_Fragment).commit();
                break;






        }

    }

}
