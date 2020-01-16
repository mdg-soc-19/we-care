package com.example.wecare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class SignUp_Fragment extends Fragment implements OnClickListener {

    private static View view;
    private static EditText fullName, Email, mobileNumber, location,
            Password, confirmPassword;
    private static TextView login;
    private static Button signUpButton;


    private FirebaseAuth firebaseAuth;
    public SignUp_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        view = inflater.inflate(R.layout.sign_up_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize all views
    private void initViews() {
        fullName = (EditText) view.findViewById(R.id.fullName);
        Email = (EditText) view.findViewById(R.id.userEmailId);
        mobileNumber = (EditText) view.findViewById(R.id.mobileNumber);
        location = (EditText) view.findViewById(R.id.location);
        Password = (EditText) view.findViewById(R.id.password);
        confirmPassword = (EditText) view.findViewById(R.id.confirmPassword);
        signUpButton = (Button) view.findViewById(R.id.signUpBtn);
        login = (TextView) view.findViewById(R.id.already_user);



    }

    // Set Listeners
    private void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUpBtn:

                // Call checkValidation method
                checkValidation();

                break;

            case R.id.already_user:

                // Replace login fragment
                new MainActivity().replaceLoginFragment();
                break;
        }

    }


        // Check Validation Method

    private void checkValidation(){

        // Get all edittext texts
        String getFullName = fullName.getText().toString();
        String getEmailId = Email.getText().toString();
        String getMobileNumber = mobileNumber.getText().toString();
        String getLocation = location.getText().toString();
        String getPassword = Password.getText().toString();
        String getConfirmPassword = confirmPassword.getText().toString();

        // Pattern match for email id
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        // Check if all strings are null or not
        if (getFullName.equals("") || getFullName.length() == 0
                || getEmailId.equals("") || getEmailId.length() == 0
                || getMobileNumber.equals("") || getMobileNumber.length() == 0
                || getLocation.equals("") || getLocation.length() == 0
                || getPassword.equals("") || getPassword.length() == 0
                || getConfirmPassword.equals("")
                || getConfirmPassword.length() == 0)

            new CustomToast().Show_Toast(getActivity(), view,
                    "All fields are required.");

            // Check if email id valid
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "Your Email Id is Invalid.");

            // Confirm
        else if (!getConfirmPassword.equals(getPassword))
            new CustomToast().Show_Toast(getActivity(), view,
                    "Both password doesn't match.");

            //  Terms and Conditions checkbox

            // Else do signup
        else
            //getting email and password from edit texts
            registerUser();


    }


    private void registerUser() {
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //checking if success
                if (task.isSuccessful()) {
                    //display some message here
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Toast.makeText(getActivity(), "Successfully registered", Toast.LENGTH_LONG).show();

                } else {
                    //display some message here
                    Toast.makeText(getActivity(), "Registration Error", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}



