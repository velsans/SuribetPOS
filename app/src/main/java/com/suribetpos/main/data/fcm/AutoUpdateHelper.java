package com.suribetpos.main.data.fcm;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class AutoUpdateHelper {
    public static String KEY_UPDATE_ENABLE = "isInstalled";
    public static String KEY_UPDATE_VERSIONCODE = "VersionCode";
    public static String KEY_UPDATE_URL = "AppUrl";
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
        FirebaseRemoteConfig remoteconfig = FirebaseRemoteConfig.getInstance();
        if (remoteconfig.getBoolean(KEY_UPDATE_ENABLE)) {
            double currentVersion = remoteconfig.getDouble(KEY_UPDATE_VERSIONCODE);
            double appVersion = ScanValueCheckDouble(getAppVersion(context));
            double appVersionCode = getAppVersionCode(context);
            appUrl = remoteconfig.getString(KEY_UPDATE_URL);
            if (currentVersion > appVersion && onUpdateCheckListener != null) {
                //if (!TextUtils.equals(currentVersion, appVersion) && onUpdateCheckListener != null) {
                onUpdateCheckListener.onUpdateCheckListener(appUrl);
            }
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
