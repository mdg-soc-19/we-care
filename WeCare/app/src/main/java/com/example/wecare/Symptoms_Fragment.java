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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Symptoms_Fragment extends Fragment implements MainActivity.OnBackPressedListener{
    private static View view;
private static String value,g=null;
    private static Animation shakeAnimation;
    private TextView Symptom;
    public EditText Name;
    public Button BackBtn,Save2,Save;
    private DatabaseReference RootRef,DemoRef12,DemoRef22;
    private static FragmentManager fragmentManager;
    public Query query;
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


        RootRef = FirebaseDatabase.getInstance().getReference();
        DemoRef12=RootRef.child("SName");

        Save2=(Button)view.findViewById(R.id.save2);
        Save=(Button)view.findViewById(R.id.s);

        Symptom=(TextView)view.findViewById(R.id.symptom);

        BackBtn=(Button)view.findViewById(R.id.back);

    }



    private void setListeners() {
        Save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        {
                            String cname = Name.getText().toString();
                            DemoRef12.push().setValue(cname);
                        }
                    }
                });

        Save2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        {
                            query = FirebaseDatabase.getInstance()
                                    .getReference()
                                    .child("SName");
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    HashMap<String, Object> dataMap = (HashMap<String, Object>) dataSnapshot.getValue();
                                    for (String key : dataMap.keySet()) {

                                        // Object data = dataMap.get(key);
                                        g=String.valueOf(dataMap.get(key));

                                        // value = dataSnapshot.getValue().toString();
                                        Symptom.setText(g);
                                    }
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
    @Override
    public boolean onBackPressed() {
        Toast.makeText(getActivity(),"You'll be directed to HomePage",Toast.LENGTH_LONG).show();
        return false;

    }

}



