package com.suribetpos.main.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.suribetpos.R;


public class Internet_Connection extends Activity {

    ImageView _Try_Again, _Exit, _Settings;
    int internet = 0;
    public ConnectionDetector cd;
    public static boolean isInternetPresent;
    private static final String TAG = Internet_Connection.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infliatorfailiuelayout);
        _Try_Again = (ImageView) findViewById(R.id.try_again_button1);
        _Settings = (ImageView) findViewById(R.id.settings_internet_button1);
        _Exit = (ImageView) findViewById(R.id.exit_application_button1);

        _Try_Again.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Internetconnection();
            }
        });
        _Settings.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                internet = 1;
                Intent _internetintent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(_internetintent);
                // finish();
            }
        });
        _Exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    @Override
    protected void onResume() {
        // Log.d(TAG, "MY onResume is called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // Log.e(TAG, "onPause");
        super.onPause();
    }

    public void Internetconnection() {
        cd = new ConnectionDetector(getApplicationContext());
        isInternetPresent = cd.isConnectingToInternet();

        if (isInternetPresent) {
            try {
                Class backActivtyClass = Class.forName(Common.Netconnection_ClassName);
                Intent intentprevious = new Intent(Internet_Connection.this, backActivtyClass);
                intentprevious.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentprevious);

            } catch (ClassNotFoundException ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
            }
        } else {
            Connection_error();
        }
    }

    public void Connection_error() {

        Intent intent = new Intent(Internet_Connection.this, Internet_Connection.class);
        startActivity(intent);
        finish();

    }
}
