package com.suribetpos.main.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.suribetpos.main.data.fcm.CrashAnalytics;

public class ConnectionFinder {

    private static Context _context;

    public ConnectionFinder(Context context) {
        this._context = context;
    }

    public boolean isConnectingToInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    //Added on 09.Apr.2015 - Start
    public static boolean isInternetOn(Context context) {
        _context = context;
        ConnectivityManager connec = null;
        // get Connectivity Manager object to check connection
        //ConnectivityManager connec =  
        //(ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // get Connectivity Manager object to check connection
        try {
            connec = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 0 is Mobile dataconnection
            // 1 for Wifi Connection
            NetworkInfo activeNetwork = connec.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTING ||
                        connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                    //Common.IsLocalConnection = 2;
                    return true;

                } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
                        connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTING) {
                    //Common.IsLocalConnection = 1;
                    return true;
                } else if (connec.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED) {
                    //Common.IsLocalConnection = 0;
                    return false;
                }   // Check for network connections
            } else {
                // not connected to the internet
            }
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
        return false;
    }
}
