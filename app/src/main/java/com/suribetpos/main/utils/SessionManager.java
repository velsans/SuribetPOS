package com.suribetpos.main.utils;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.suribetpos.main.ui.view.UserAuthenticationActivity;


public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    public static final String PREF_NAME = "SuriBetPref";
    public static final String IS_LOGIN = "Registered";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_LANGUID = "LanguageID";
    public static final String KEY_LANGAGE = "Language";
    public static final String KEY_COUNTRYCODE = "CountryCode";
    public static final String KEY_LANGUAGECODE = "LanguageCode";


    // Constructor
    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String password, String LanguageName, int LanguageID, String CountryCode, String LanguageCode) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putInt(KEY_LANGUID, LanguageID);
        editor.putString(KEY_LANGAGE, LanguageName);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_COUNTRYCODE, CountryCode);
        editor.putString(KEY_LANGUAGECODE, LanguageCode);
        editor.commit();
    }

    public void editLanguageSession(int languageID, String language, String CountryCode, String LanguageCode) {
        editor.putInt(KEY_LANGUID, languageID);
        editor.putString(KEY_LANGAGE, language);
        editor.putString(KEY_COUNTRYCODE, CountryCode);
        editor.putString(KEY_LANGUAGECODE, LanguageCode);
        editor.commit();
    }

    public String RetreiveLanguage() {
        String SelectedLanguage = "";
        if (pref.contains(SessionManager.KEY_LANGAGE)) {
            SelectedLanguage = pref.getString(SessionManager.KEY_LANGAGE, "");
        } else {
            SelectedLanguage = "English";
        }

        return SelectedLanguage;
    }

    public String RetreiveCountryCode() {
        String SelectedLanguage = "";
        if (pref.contains(SessionManager.KEY_COUNTRYCODE)) {
            SelectedLanguage = pref.getString(SessionManager.KEY_COUNTRYCODE, "");
        } else {
            SelectedLanguage = "US";
        }

        return SelectedLanguage;
    }

    public String RetreiveLanguageCode() {
        String SelectedLanguage = "";
        if (pref.contains(SessionManager.KEY_LANGUAGECODE)) {
            SelectedLanguage = pref.getString(SessionManager.KEY_LANGUAGECODE, "");
        } else {
            SelectedLanguage = "en";
        }

        return SelectedLanguage;
    }


    public int RetriveLanguageID() {
        int selectedLangID;
        if (pref.contains(SessionManager.KEY_LANGUID)) {
            selectedLangID = pref.getInt(SessionManager.KEY_LANGUID, 0);
        } else {
            selectedLangID = 1;
        }
        return selectedLangID;
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            Intent i = new Intent(_context, UserAuthenticationActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, UserAuthenticationActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
