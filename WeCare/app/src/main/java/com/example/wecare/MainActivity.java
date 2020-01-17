package com.example.wecare;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        Fragment Home= fragmentManager.findFragmentByTag(Utils.Home_Fragment);
        if(Home!=null)
        {    moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
                replaceMedicalAid_Fragment();
        // Find the tag of signup and forgot password fragment
    }


    public interface OnBackPressedListener{

        boolean onBackPressed();
    }
}
