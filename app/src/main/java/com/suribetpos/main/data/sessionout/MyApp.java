package com.suribetpos.main.data.sessionout;

import android.app.Application;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.suribetpos.main.utils.Common;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MyApp extends Application {

    Timer timer;
    LogOutListener logOutListener;

    public void startUserSession() {
        cancelTimer();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logOutListener.onSessionLogout();
            }
        }, Common.SessionLogoutTime); // twenty MINUTE FOR  LOG OUT // now 15 mints

    }

    public void cancelTimer() {
        if (timer != null) timer.cancel();
    }

    public void registerSessionListener(LogOutListener logOutListener) {

        this.logOutListener = logOutListener;
    }

    public void onUserIntracted() {
        startUserSession();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        Map<String, Object> defaultvalues = new HashMap<>();
        //defaultvalues.put(AutoUpdateHelper.KEY_UPDATE_ENABLE, true);
        //defaultvalues.put(AutoUpdateHelper.KEY_UPDATE_VERSIONCODE, "1.5");
        //defaultvalues.put(AutoUpdateHelper.KEY_UPDATE_URL, "http://demo.ysecit.in:8014/EtopUp/V1.1.5/SuribetMobilePOS.apk");

        remoteConfig.setDefaultsAsync(defaultvalues);
        remoteConfig.fetch(5).addOnCompleteListener(task -> remoteConfig.activate());
    }
    /*    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    public void setLocale(String localeName) {
        Locale locale2 = new Locale(localeName);
        Locale.setDefault(locale2);
        Configuration config2 = new Configuration();
        config2.locale = locale2;
        this.getResources().updateConfiguration(config2, null);
    }*/
}
