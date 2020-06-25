package com.suribetpos.main.data.model.common;

import java.net.URL;
import java.net.URLConnection;

import android.content.Context;

import com.suribetpos.main.utils.Common;
import com.suribetpos.main.data.fcm.CrashAnalytics;


public class ServiceURL {

    public final static String LocalNetwork = "http://demo.ysecit.in:8014/SuribetMobilePOSAPI";
    public final static String ServiceURLPathLocalDGNetwork = "http://demo.ysecit.in:8082/DailygamePOSAPI/api/";
    public final static String ServiceURLPathLocalottoGNetwork = "http://demo.ysecit.in:8082/LottoGameAPI/api";

    //private final static String ServiceURLPathLocalNetwork = "http://192.168.248.240:812/";
    private final static String ServiceURLPathWebNetwork = "http://demo.ysecit.in:82/";
    private final static String ServiceURLPathLiveNetwork = "http://dx.suribet.sr/poswebapi/";
    //public final static String SecurityModel = "SuribetMobilePOSApi/";
    public final static String DailyGameModel = "DailyGameAdminAPI/api/";
    public final static String BASE_URL = LocalNetwork;
    private static Context _context;
    private final static int TIME_OUT = 5000; // Millisecond
    public static boolean CONNECTION_AVAILABILITY = true;
    public final static String[] ServiceURL = new String[]{BASE_URL, ServiceURLPathWebNetwork, ServiceURLPathLiveNetwork};

    public ServiceURL(Context context) {
        this._context = context;
    }

    public static boolean IsConnectingToInternet(String ipAddress) {
        try {
            URL myUrl = new URL(ipAddress);
            URLConnection connection = myUrl.openConnection();
            connection.setConnectTimeout(TIME_OUT);
            connection.connect();
            Common.IsNetworkConnection = true;
            // InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            // inputStream.close();
            return true;
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            Common.IsNetworkConnection = false;
            return false;
        }
    }

    public static void ConnectionCheckAvailability() {
        try {
            for (int i = 0; i < ServiceURL.length; i++) {
                if (IsConnectingToInternet(ServiceURL[i])) {
                    Common.ServiceURLPath = ServiceURL[i];
                    CONNECTION_AVAILABILITY = true;
                    return;
                }
            }
            CONNECTION_AVAILABILITY = false;
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

    public static String getServiceURL(String WebAPIControllerName) {
        Common.ServiceURLPath = BASE_URL;
        WebAPIControllerName = WebAPIControllerName + "/";
        return Common.ServiceURLPath + WebAPIControllerName;
    }


}
