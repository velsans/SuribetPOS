package com.suribetpos.main.ui.view;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.suribetpos.R;

import java.util.Timer;
import java.util.TimerTask;

public class ConnectionErrorActivity extends Activity{
	int SPLASH_TIME_OUT = 5000;
   @Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_internetconnectionerror);
    setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    
    
    Timer RunSplash = new Timer();
	TimerTask ShowSplash = new TimerTask() {
		@Override
		public void run() {
			android.os.Process.killProcess(android.os.Process.myPid());
			finish();
			
		}
	};
	RunSplash.schedule(ShowSplash, SPLASH_TIME_OUT);
}
}
