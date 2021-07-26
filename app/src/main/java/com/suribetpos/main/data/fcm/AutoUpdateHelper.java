package com.suribetpos.main.data.fcm;

import android.content.Context;
import android.content.pm.PackageManager;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.suribetpos.main.data.api.ServiceURL;

public class AutoUpdateHelper {
    public static String LIVE_UPDATE_ENABLE = "isInstalled";
    public static String LIVE_UPDATE_VERSIONCODE = "VersionCode";
    public static String LIVE_UPDATE_URL = "AppUrl";

    public static String LOCAL_UPDATE_ENABLE = "Local_Installed";
    public static String LOCAL_UPDATE_VERSIONCODE = "Local_VC";
    public static String LOCAL_UPDATE_URL = "Local_Url";

    public static String appUrl;

    public interface OnUpdateCheckListener {
        void onUpdateCheckListener(String urlAPP);
    }

    public static Builder with(Context context) {
        return new Builder(context);
    }

    private OnUpdateCheckListener onUpdateCheckListener;
    private Context context;

    public AutoUpdateHelper(Context context, OnUpdateCheckListener onUpdateCheckListener) {
        this.onUpdateCheckListener = onUpdateCheckListener;
        this.context = context;
    }

    public void check() {
        double currentVersion = 0.0, appVersion = 0.0;
        FirebaseRemoteConfig remoteconfig = FirebaseRemoteConfig.getInstance();
        if (ServiceURL.Local_live_Flag == true) {
            if (remoteconfig.getBoolean(LIVE_UPDATE_ENABLE)) {
                currentVersion = remoteconfig.getDouble(LIVE_UPDATE_VERSIONCODE);
                appVersion = ScanValueCheckDouble(getAppVersion(context));
                appUrl = remoteconfig.getString(LIVE_UPDATE_URL);
            }
        } else {
            if (remoteconfig.getBoolean(LOCAL_UPDATE_ENABLE)) {
                currentVersion = remoteconfig.getDouble(LOCAL_UPDATE_VERSIONCODE);
                appVersion = ScanValueCheckDouble(getAppVersion(context));
                appUrl = remoteconfig.getString(LOCAL_UPDATE_URL);
            }
        }
        double appVersionCode = getAppVersionCode(context);
        if (currentVersion > appVersion && onUpdateCheckListener != null) {
            //if (!TextUtils.equals(currentVersion, appVersion) && onUpdateCheckListener != null) {
            onUpdateCheckListener.onUpdateCheckListener(appUrl);
        }

    }

    public double ScanValueCheckDouble(String Str) {
        Double Value = 0.0;
        if (isNullOrEmpty(Str)) {
        } else {
            Value = Double.parseDouble(Str);
        }
        return Value;
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    private String getAppVersion(Context context) {
        String result = "";
        try {
            result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            result = result.replaceAll("[a-zA-Z] |-", "");

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    private double getAppVersionCode(Context context) {
        double VersionCode = 0.0;
        try {
            VersionCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return VersionCode;
    }

    public static class Builder {
        private Context context;
        private OnUpdateCheckListener onUpdateCheckListener;

        public Builder(Context conText) {
            this.context = conText;
        }

        public Builder OnUpdateCheck(OnUpdateCheckListener onUpdateCheckListener) {
            this.onUpdateCheckListener = onUpdateCheckListener;
            return this;
        }

        public AutoUpdateHelper build() {
            return new AutoUpdateHelper(context, onUpdateCheckListener);
        }

        public AutoUpdateHelper check() {
            AutoUpdateHelper autoUpdate = build();
            autoUpdate.check();
            return autoUpdate;
        }
    }


}
