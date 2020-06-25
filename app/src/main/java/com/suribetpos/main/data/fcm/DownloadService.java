package com.suribetpos.main.data.fcm;

import android.app.IntentService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

public class DownloadService extends IntentService {

    private int result = Activity.RESULT_CANCELED;
    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "service receiver";

    public DownloadService() {
        super("DownloadService");
    }

    // Will be called asynchronously by OS.
    @Override
    protected void onHandleIntent(Intent intent) {
        String OutPATH = "";
        File outputFile = null;
        try {
            String urlPath = intent.getStringExtra(URL);
            String fileName = intent.getStringExtra(FILENAME);
            OutPATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            outputFile = new File(OutPATH, fileName);
            if (outputFile.exists()) {
                outputFile.delete();
            }
            // Download File from url
            URL u = new URL(urlPath + fileName);
            URLConnection conn = u.openConnection();
            int contentLength = conn.getContentLength();

            DataInputStream stream = new DataInputStream(u.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            DataOutputStream fos = new DataOutputStream(new FileOutputStream(outputFile));
            fos.write(buffer);
            fos.flush();
            fos.close();
            result = Activity.RESULT_OK;
        } catch (MalformedURLException e) {
            result = Activity.RESULT_CANCELED;
        } catch (IOException ex) {
            CrashAnalytics.CrashReport(ex);
            result = Activity.RESULT_CANCELED;
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            result = Activity.RESULT_CANCELED;
        }
        /*Broadcast Receiver*/
        publishResults(outputFile.toString(), result);
    }

    private void publishResults(String outputPath, int result) {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}