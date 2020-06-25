/*
package com.suribetpos.main.utils.commonclasses;

import android.os.AsyncTask;
import android.util.Log;


import com.suribetpos.main.data.model.common.TillBalanceModel;
import com.suribetpos.main.interfaces.TillBalanceInterface;

import com.google.gson.Gson;
import com.suribetpos.main.utils.Common;

import org.json.JSONObject;

*/
/**
 * Created by DEV 27 on 02/06/2017.
 *//*


public class TillBalance extends AsyncTask<Object, Object, Object> {

    private TillBalanceInterface listener = null;

    public TillBalance(TillBalanceInterface asyncResponse) {
        listener = asyncResponse;//Assigning call back interfacethrough constructor
    }

    @Override
    protected Object doInBackground(Object[] params) {
        String ControllerName = "Till/GetTillTransactionAmount";
        String TillBalanceURL = "";//ServiceURL.getServiceURL (ControllerName);
        try {
            String TillBalance = new Communicator ().POST_ObectClass (TillBalanceURL, Common.TillId);
            if (TillBalance != null) {
                JSONObject jsonObj = new JSONObject (TillBalance);
                Common.TillBalnaceErrMsg = jsonObj.getString ("m_Item1");
                if (Common.TillBalnaceErrMsg.equals ("")) {
                    TillBalanceModel tillBalance_infoPOJO = new Gson ().fromJson (jsonObj.getString ("m_Item2"), TillBalanceModel.class);
                   // Common.TillBalanceArrayList.add (TillBalance_infoPOJO);
                    Log.e ("Ondoing-ClinetBalance", ">>>>>" + Common.tillBalanceArrayList.size ());
                }
            } else {
                Common.TillBalnaceErrMsg = "";
            }
        } catch (Exception ex) {
            Common.TillBalnaceErrMsg = e.toString ();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d ("TillBalance", "onPreExecute");
        Common.tillBalanceArrayList.clear ();
        super.onPreExecute ();
    }

    @Override
    protected void onPostExecute(Object result) {
        Log.d ("TillBalance", "onPreExecute");
        listener.onTillBalanceDetails (result);
        //super.onPostExecute (o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.d ("TillBalance", "onProgressUpdate");
        super.onProgressUpdate (values);
    }

    @Override
    protected void onCancelled(Object o) {
        Log.d ("TillBalance", "onCancelled");
        super.onCancelled (o);
    }

    @Override
    protected void onCancelled() {
        Log.d ("ClientInformation", "onCancelled");
        super.onCancelled ();
    }

}


*/
