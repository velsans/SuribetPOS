package com.suribetpos.main.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.suribetpos.R;
import com.suribetpos.main.data.sessionout.LogOutListener;
import com.suribetpos.main.data.sessionout.MyApp;
import com.suribetpos.main.utils.LocaleManager;

public abstract class BaseActivity extends AppCompatActivity implements LogOutListener {

    FrameLayout framLAY;
    static ProgressDialog mProgressDialog;
    RelativeLayout constrainLAY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this, R.style.Theme_MyDialog);
        mProgressDialog.setMessage(CommonMessage(R.string.PleaseWait));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressbar_custom));
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.getWindow().setGravity(Gravity.CENTER);
        /*Session Logout*/
        ((MyApp) getApplication()).registerSessionListener(this);
        ((MyApp) getApplication()).startUserSession();
        //resetTitles();
    }

    @Override
    public void setContentView(int layoutResID) {
        constrainLAY = (RelativeLayout) getLayoutInflater().inflate(R.layout.activity_baselayout, null);
        framLAY = constrainLAY.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, framLAY, true);
        super.setContentView(layoutResID);
    }

    public static void ShowProgressBar(boolean visibleFlag) {
        //progressBar.setVisibility(visibleFlag ? View.VISIBLE : View.INVISIBLE);
        if (visibleFlag == true) {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } else {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        ((MyApp) getApplication()).onUserIntracted();
    }

    @Override
    public void onSessionLogout() {
        //finish();
        Intent intent = new Intent(BaseActivity.this, UserAuthenticationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ((MyApp) getApplication()).cancelTimer();
        ((MyApp) getApplication()).startUserSession();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyApp) getApplication()).cancelTimer();
        ((MyApp) getApplication()).startUserSession();
    }
  /*  @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }*/
    protected void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
