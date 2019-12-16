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

public class MedicalAid_Fragment extends Fragment implements View.OnClickListener {
    private static View view;
    private static LinearLayout medicalaid_layout;
    private static Animation shakeAnimation;
    private static Button TakeMedAlarmBtn;
    private static Button RestockMedBtn;
    private static Button DocBtn;
    private static Button SymptomBtn;
    private static FragmentManager fragmentManager;

    public MedicalAid_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.medicalaid_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {
        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        TakeMedAlarmBtn = (Button) view.findViewById(R.id.takemed);
        RestockMedBtn = (Button) view.findViewById(R.id.restockmed);
        DocBtn=(Button) view.findViewById(R.id.docvisit);
        SymptomBtn=(Button)view.findViewById(R.id.symptom);


    }

    private void setListeners() {
        TakeMedAlarmBtn.setOnClickListener(this);
        RestockMedBtn.setOnClickListener(this);
        DocBtn.setOnClickListener(this);
        SymptomBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.takemed:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new TakeMedsAlarm1_Fragment(),
                                Utils.TakeMedsAlarm1_Fragment).commit();
                break;

            case R.id.restockmed:
                fragmentManager
                            .beginTransaction()
                            .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                            .replace(R.id.frameContainer,
                                    new RestockMeds1_Fragment(),
                                    Utils.RestockMeds1_Fragment).commit();
                    break;

            case R.id.docvisit:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new DocVisits1_Fragment(),
                                Utils.DocVisits1_Fragment).commit();
                break;

            case R.id.symptom:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new Symptoms1_Fragment(),
                                Utils.Symptoms1_Fragment).commit();
                break;



}

    }

}
