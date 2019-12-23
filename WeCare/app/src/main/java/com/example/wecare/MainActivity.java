package com.example.wecare;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import androidx.appcompat.app.ActionBar;
import android.app.ActivityManager;
import androidx.appcompat.app.ActionBar;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
   // private Stack<BackPressListener> backPressListeners = new Stack<BackPressListener>();
    private static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new Login_Fragment()
                            ,Utils.Login_Fragment).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });



    }

    // Replace Login Fragment with animation
    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Login_Fragment(),
                        Utils.Login_Fragment).commit();
    }

    protected void replaceMedicalAid_Fragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new Home_Fragment(),
                        Utils.Home_Fragment).commit();
    }


    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment

        Fragment SignUp_Fragment = fragmentManager.findFragmentByTag(Utils.SignUp_Fragment);
        Fragment ForgotPassword_Fragment = fragmentManager.findFragmentByTag(Utils.ForgotPassword_Fragment);
        Fragment RestockMeds1_Fragment = fragmentManager.findFragmentByTag(Utils.RestockMeds1_Fragment);
        Fragment RestockMeds_Fragment = fragmentManager.findFragmentByTag(Utils.RestockMeds_Fragment);
        Fragment TakeMedsAlarm_Fragment = fragmentManager.findFragmentByTag(Utils.TakeMedsAlarm_Fragment);
        Fragment TakeMedsAlarm1_Fragment = fragmentManager.findFragmentByTag(Utils.TakeMedsAlarm1_Fragment);
        Fragment Symptoms_Fragment = fragmentManager.findFragmentByTag(Utils.Symptoms_Fragment);
        Fragment Symptoms1_Fragment = fragmentManager.findFragmentByTag(Utils.Symptoms1_Fragment);

        if (RestockMeds1_Fragment != null)
            replaceMedicalAid_Fragment();
        else if (RestockMeds_Fragment != null)
            replaceMedicalAid_Fragment();
        else
            super.onBackPressed();

        if (TakeMedsAlarm_Fragment != null)
            replaceMedicalAid_Fragment();
        else if (TakeMedsAlarm1_Fragment != null)
            replaceMedicalAid_Fragment();
        else
            super.onBackPressed();

        if (Symptoms1_Fragment != null)
            replaceMedicalAid_Fragment();
        else if (Symptoms_Fragment != null)
            replaceMedicalAid_Fragment();
        else
            super.onBackPressed();

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (SignUp_Fragment != null)
            replaceLoginFragment();
        else if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}
