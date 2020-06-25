package com.suribetpos.main.data.fcm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.suribetpos.main.utils.Common;

public class CrashAnalytics {
    Context _context;

    public CrashAnalytics(Context context) {
        this._context = context;
    }

    public static void CrashReport(Exception exception) {
        FirebaseCrashlytics.getInstance().setUserId(Common.UserName);
        FirebaseCrashlytics.getInstance().setCustomKey("MACADDRESS", Common.MobileMacAddress /* string value */);
        FirebaseCrashlytics.getInstance().log(exception.toString());
        FirebaseCrashlytics.getInstance().recordException(exception);

        /*Analytics*/
  /*      Bundle params = new Bundle();
        params.putString("UserName", Common.UserName);
        params.putString("MACADDRESS", Common.MobileMacAddress);
        params.putString("Exception", exception.toString());
        FirebaseAnalytics.getInstance(_context).logEvent("share_image", params);*/
    }

    public static void logReportOnly(String logs) {
        FirebaseCrashlytics.getInstance().setCustomKey("UserID", Common.UserId /* string value */);
        FirebaseCrashlytics.getInstance().setCustomKey("MACADDRESS", Common.MobileMacAddress /* string value */);
        FirebaseCrashlytics.getInstance().log(logs);
    }

    public static void setUserId() {
        FirebaseCrashlytics.getInstance().setUserId(Common.UserName);
    }

    public static void FireBaseAnalytics(String exeption, Activity context) {
        /*Analytics*/
        Bundle params = new Bundle();
        params.putString("UserName", Common.UserName);
        params.putString("MACADDRESS", Common.MobileMacAddress);
        params.putString("Exception", exeption);
        FirebaseAnalytics.getInstance(context).logEvent(exeption, params);
    }
}
