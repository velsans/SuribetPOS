package com.suribetpos.main.interfaces;

import android.content.DialogInterface;

public interface OnAlertDialogClickListener {
	
    public void onPositiveClick(DialogInterface dialog, int id);
    public void onNegativeClick(DialogInterface dialog, int id);
    public void onNeutralClick(DialogInterface dialog, int id);
}
