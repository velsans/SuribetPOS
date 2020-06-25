/*
package com.suribetpos.main.utils.commonclasses;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.suribetpos.main.data.model.common.ServiceURL;
import com.suribetpos.main.interfaces.DGRetailerCreditCheckInformationInterface;
import com.suribetpos.main.model.dailygame.DGRetailerCreditCheck;
import com.suribetpos.main.utils.Communicator;
import com.suribetpos.main.utils.ConnectionException;
import com.suribetpos.main.utils.JSONHttpClient;
import com.suribetpos.main.utils.Common;

import org.json.JSONArray;
import org.json.JSONObject;


*/
/**
 * Created by Velmurugan on 1/6/2018.
 *//*


public class DGRetailerCreditCheckInformation extends AsyncTask<Object, Object, Object> {

    private DGRetailerCreditCheckInformationInterface listener = null;

    public DGRetailerCreditCheckInformation(DGRetailerCreditCheckInformationInterface asyncResponse) {
        listener = asyncResponse;//Assigning call back interfacethrough constructor
    }

    @Override
    protected Object doInBackground(Object[] params) {

        JSONHttpClient jsonHttpClient = new JSONHttpClient();

        String ControllerName = ServiceURL.ServiceURLPathLocalDGNetwork;
        Uri.Builder DGUrlPLD = new Uri.Builder();
        DGUrlPLD.appendPath("DailygameMOB")// Controllor
                .appendPath("RetailerCreditCheck");// Method name
        DGRetailerCreditCheck retailerCreditPojo = new DGRetailerCreditCheck();
        retailerCreditPojo.setTillId(Common.TillId);
        retailerCreditPojo.setAmount(Common.TotalStakeAmount);
        retailerCreditPojo.setMacaddress(Common.MobileMacAddress);
        retailerCreditPojo.setUserID(Common.UserId);
        retailerCreditPojo.setFormType(Common.FormType);
        String RetailerCreditCheckURL = ControllerName + DGUrlPLD.build().toString();
        try {
            String RetailerCheckobj = new Communicator().POST_Obect(RetailerCreditCheckURL, retailerCreditPojo);
            if (RetailerCheckobj != null) {
                JSONArray RetailerCheckArray = new JSONArray(RetailerCheckobj);
                JSONObject jsonObj = RetailerCheckArray.getJSONObject(0);
                Common.ClientErrorMsg = jsonObj.getString("ErrorMessage");
                Common.Exceed = Integer.parseInt(jsonObj.getString("Exceed"));
                Common.AuthorizedMsg = jsonObj.getString("Authorized");
                Common.IsConnected = true;
            } else {
                Common.ClientErrorMsg = Common.ContactAdminMsg;
                Common.IsConnected = false;
                try {
                    throw new ConnectionException("No connection");
                } catch (ConnectionException ex) {
                    e.printStackTrace();
                }
            }
        } catch (Exception ex) {
            Common.IsConnected = false;
            Common.ClientErrorMsg = e.toString();
            Log.e("Exception", ">>>>>>>>>" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result) {
        listener.onDGRetailerCreditCheckDetails(result);
        //super.onPostExecute (o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

}


*/
