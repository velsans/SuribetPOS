package com.suribetpos.main.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleManager {

    public static Context setNewLocale(Context mContext, String languageCode, String CountryCode) {
        //setLanguagePref(mContext, language);
        return updateResources(mContext, languageCode, CountryCode);
    }

    private static Context updateResources(Context context, String languageCode, String CountryCode) {
        Locale locale = new Locale(languageCode, CountryCode);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale);
            context = context.createConfigurationContext(config);
        } else {
            config.locale = locale;
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }

    public static void setAppLocale(Context context, String languageCode, String CountryCode) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(languageCode.toLowerCase()));
        } else {
            config.locale = new Locale(languageCode.toLowerCase(), CountryCode);
        }
        resources.updateConfiguration(config, dm);
    }
}
