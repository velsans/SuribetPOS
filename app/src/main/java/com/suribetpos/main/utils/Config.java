package com.suribetpos.main.utils;


import android.app.Activity;
import android.app.ProgressDialog;

class ProgressBar {
    private static ProgressDialog progress;

    public static void setProgressBar(Activity activity) {
        progress = new ProgressDialog(activity);
        progress.setMessage("Downloading Music");
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
    }
}