package com.example.wecare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
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
    private static EditText fullName, Email, mobileNumber, location,
            Password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;
    private static CheckBox terms_conditions;

    private FirebaseAuth firebaseAuth;

    public Home_Fragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.home_layout, container, false);

        return view;
    }

    /**private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        Email = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        Password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);
        terms_conditions = (CheckBox) view.findViewById(R.id.terms_conditions);


    }

    //private void setListeners() {
       // signUpButton.setOnClickListener(this);
     //   login.setOnClickListener(this);
   // }
*/
    @Override
    public void onClick(View v) {
        //switch (v.getId()) {
           // case R.id.
               // break;

        }

    }



