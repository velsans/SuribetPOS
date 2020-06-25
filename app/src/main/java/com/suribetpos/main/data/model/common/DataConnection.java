package com.suribetpos.main.data.model.common;

import java.net.URL;
import java.net.URLConnection;

import android.net.ConnectivityManager;

import com.suribetpos.main.utils.Common;
import com.suribetpos.main.data.fcm.CrashAnalytics;


public class DataConnection {
	private final static int TIME_OUT = 5000;
    public boolean isConnected(ConnectivityManager connectivityManager) {
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        
        
        
        try{
     		  String ipAddress = Common.ServiceURLPath;
              URL myUrl = new URL(ipAddress);
              URLConnection connection = myUrl.openConnection();
              connection.setConnectTimeout(TIME_OUT);
              connection.connect();
              Common.IsNetworkConnection = true;
             // InputStream inputStream = new BufferedInputStream(connection.getInputStream());
             // inputStream.close();
              return false;
          } 
     	 catch (Exception ex) {
             CrashAnalytics.CrashReport(ex);
         	 Common.IsNetworkConnection = false;
         	return true;
          }

    }
    

    

}