package com.suribetpos.main.utils;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by DEV 27 on 21/06/2017.
 */

public class DialogUtilities {
    public static ProgressBar showProgressDialog(Context context, String text) {
        ProgressBar m_Dialog = new ProgressBar (context);
        //m_Dialog.setMessage (text);
        //m_Dialog.setProgressStyle (ProgressDialog.STYLE_SPINNER);
        //m_Dialog.setCancelable (false);
        //m_Dialog.show ();
        return m_Dialog;
    }
}
