package com.example.wecare;


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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Symptoms_Fragment extends Fragment{
    private static View view;

    private static Animation shakeAnimation;
    private TextView Symptom;
    public EditText Name,Description;
    public Button BackBtn,Save2,Symptom1;
    private DatabaseReference RootRef,DemoRef12,DemoRef22;
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
        Name=(EditText)view.findViewById(R.id.sname);
        Description=(EditText)view.findViewById(R.id.sdesc);

        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef12=RootRef.child("SName");
        DemoRef22=RootRef.child("SDesc");
        Save2=(Button)view.findViewById(R.id.save2);
        Symptom=(TextView)view.findViewById(R.id.symptom);
        Symptom1=(Button) view.findViewById(R.id.symptom1);

        BackBtn=(Button)view.findViewById(R.id.back);

    }



    private void setListeners() {
   Save2.setOnClickListener(
           new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            {

                String sname = Name.getText().toString();
                DemoRef12.push().setValue(sname);
                String sdesc = Description.getText().toString();
                DemoRef22.push().setValue(sdesc);
            }


        }
    });

        Symptom1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        {
                            DemoRef12.child("SName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String value = dataSnapshot.getValue(String.class);
                                    Symptom.setText(value);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                        }
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

}



