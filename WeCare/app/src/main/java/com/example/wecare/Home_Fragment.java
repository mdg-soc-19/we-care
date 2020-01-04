package com.example.wecare;

import androidx.annotation.NonNull;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.view.View.OnClickListener;

import android.view.ViewGroup;

import java.security.PublicKey;


public class Home_Fragment extends Fragment implements OnClickListener {

    private static View view;

    private static Button MedAidBtn;
    private static Button PlanAidBtn;
    // private static Button HelpHandBtn;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public Home_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    private void initViews() {

        fragmentManager = getActivity().getSupportFragmentManager();
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        MedAidBtn = (Button) view.findViewById(R.id.medaid);
        PlanAidBtn = (Button) view.findViewById(R.id.planaid);
    }

    private void setListeners() {
        MedAidBtn.setOnClickListener(this);
        PlanAidBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.medaid:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new MedicalAid_Fragment(),
                                Utils.MedicalAid_Fragment).commit();
                break;

            case R.id.planaid:
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new PlanningAid_Fragment(),
                                Utils.PlanningAid_Fragment).commit();

                break;

        }

    }


}
