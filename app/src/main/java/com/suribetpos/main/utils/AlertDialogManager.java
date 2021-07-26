package com.suribetpos.main.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;

import com.suribetpos.R;

public class AlertDialogManager {
    public static AlertDialog alertDialog = null;
    AlertDialog.Builder builder1 = null;

    public void showAlertDialog(final Context context, String title, String message,
                                Boolean status) {
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }
        alertDialog = new AlertDialog.Builder(context, R.style.MyAlertDialogTheme).create();
        // Setting Dialog Title
        alertDialog.setTitle(title);
        // Setting Dialog Message
        alertDialog.setMessage(message);
        if (status != null)
            // Setting alert dialog collector
            alertDialog.setIcon((status) ? R.mipmap.success : R.mipmap.fail);
        // Setting OK Button
        alertDialog.setButton(CommonMessage(context, R.string.Okay), (dialog, which) -> {
            Common.AlertDialogVisibleFlag = true;
            if (Common.AuthorizationFlag == true) {
               /* Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //session.logoutUser();
                context.startActivity(intent);*/
                Common.AuthorizationFlag = false;
            }
        });
        // Showing Alert Message
        //Common.AlertDialogVisibleFlag = false;
        alertDialog.show();
    }


    public void ExitshowAlertDialog(final Activity context, String title, String message,
                                    Boolean status) {
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }
        alertDialog = new AlertDialog.Builder(context, R.style.MyAlertDialogTheme).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        if (status != null)
            alertDialog.setIcon((status) ? R.mipmap.success : R.mipmap.fail);
        // Setting OK Button
        alertDialog.setButton(CommonMessage(context, R.string.Okay), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                    context.finishAffinity();
                } else if (Build.VERSION.SDK_INT >= 21) {
                    context.finishAndRemoveTask();
                }
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    public String CommonMessage(Context cont, int Common_Msg) {
        return cont.getResources().getString(Common_Msg);
    }

}
