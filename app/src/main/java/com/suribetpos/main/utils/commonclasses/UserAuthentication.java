/*
package com.suribetpos.main.utils.commonclasses;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.suribetpos.main.data.api.ServiceURL;
import com.suribetpos.main.data.model.common.Bettypiddetails_user;
import com.suribetpos.main.data.model.common.Currenydetails_user;
import com.suribetpos.main.data.model.common.ProductName_User;
import com.suribetpos.main.data.model.common.Userdetails_user;
import com.suribetpos.main.data.model.topup.Securityvalidateuser;
import com.suribetpos.main.interfaces.UserAuithenticationDetailsInterface;

import com.google.gson.Gson;
import com.suribetpos.main.utils.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

*/
/**
 * Created by DEV 27 on 19/05/2017.
 *//*


public class UserAuthentication extends AsyncTask<Object, Object, Object> {

    private UserAuithenticationDetailsInterface listener = null;
    JSONObject jsonObj;

    public UserAuthentication(UserAuithenticationDetailsInterface asyncResponse) {
        listener = asyncResponse;//Assigning call back interfacethrough constructor
    }

    @Override
    protected Object doInBackground(Object[] params) {
        Common.listUserDetails = new ArrayList<>();
        Common.listbetTypIdDetails = new ArrayList<>();
        Common.listCurrencyDetails = new ArrayList<>();
        Common.listProductDetails = new ArrayList<>();

        String ControllerName = ServiceURL.BASE_URL;DoWithdrawPayment
        Uri.Builder DGUrlPLD = new Uri.Builder();
        DGUrlPLD.appendPath("security")// Controllor
                .appendPath("GetSecuritySBValidateUser");// Method name
        String LoginURL = ControllerName + DGUrlPLD.build().toString();
        Securityvalidateuser preSecurityValidateUser = new Securityvalidateuser();

        preSecurityValidateUser.setUserID(Common.UserId);
        preSecurityValidateUser.setUserPassword(Common.UserPassword);
        preSecurityValidateUser.setCurrentVersion("1.0");
        preSecurityValidateUser.setMacAddress(Common.MobileMacAddress);
        preSecurityValidateUser.setIPAddress("");//(Common.MobileIPAddress);
        preSecurityValidateUser.setLocationID(Common.LocationId);
        preSecurityValidateUser.setTillID(Common.TillId);
        try {
            String UserDetailsInfo = new Communicator().POST_ObectClass(LoginURL, preSecurityValidateUser);
            if (UserDetailsInfo != null) {
                try {
                    jsonObj = new JSONObject(UserDetailsInfo);
                    Common.UserAuthenticErrorMsg = jsonObj.getString("m_Item1");
                    if (Common.UserAuthenticErrorMsg.equals("")) {
                        String UserDetails_InfoValues = jsonObj.getString("m_Item2");
                        Log.e("UserDetails_InfoValues", ">>>>>>>>>" + UserDetails_InfoValues);
                        JSONArray UserDetails_Info_Array = new JSONArray(UserDetails_InfoValues);
                        for (int userInfo_Index = 0; userInfo_Index < UserDetails_Info_Array.length(); userInfo_Index++) {
                            Userdetails_user user_infoPOJO = new Gson().fromJson(UserDetails_Info_Array.getString(userInfo_Index), Userdetails_user.class);
                            Common.listUserDetails.add(user_infoPOJO);
                        }

                        String BetTypeID_Info = jsonObj.getString("m_Item3");
                        JSONArray BetTypeID_Array = new JSONArray(BetTypeID_Info);
                        for (int betID_index = 0; betID_index < BetTypeID_Array.length(); betID_index++) {
                            Bettypiddetails_user betTypeID_detailsPOJO = new Gson().fromJson(BetTypeID_Array.getString(betID_index), Bettypiddetails_user.class);
                            Common.listbetTypIdDetails.add(betTypeID_detailsPOJO);
                        }

                        String ProductDetails_Info = jsonObj.getString("m_Item4");
                        JSONArray ProductDetails_Array = new JSONArray(ProductDetails_Info);
                        for (int prodDe_index = 0; prodDe_index < ProductDetails_Array.length(); prodDe_index++) {
                            ProductName_User ProductDetailsPOJO = new Gson().fromJson(ProductDetails_Array.getString(prodDe_index), ProductName_User.class);
                            Common.listProductDetails.add(ProductDetailsPOJO);
                        }
                        String CurrencyDetails_Info = jsonObj.getString("m_Item5");
                        JSONArray CurrencyDetails_Array = new JSONArray(CurrencyDetails_Info);
                        for (int curyde_index = 0; curyde_index < CurrencyDetails_Array.length(); curyde_index++) {
                            Currenydetails_user CurrencyDetailsPOJO = new Gson().fromJson(CurrencyDetails_Array.getString(curyde_index), Currenydetails_user.class);
                            Common.listCurrencyDetails.add(CurrencyDetailsPOJO);
                        }
                    }
                    Common.IsConnected = true;
                } catch (Exception ex) {
                }
            } else {
                Common.UserAuthenticErrorMsg = Common.ContactAdminMsg;
                Common.IsConnected = false;
            }
        } catch (NullPointerException ex) {
            Common.IsConnected = false;
            Common.UserAuthenticErrorMsg = e.toString();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        Log.d("ClientInformation", "onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result) {
        Log.d("ClientInformation", "onPreExecute");
        listener.onUserAuthendicInformationDetails(result);
        //super.onPostExecute (o);
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        Log.d("ClientInformation", "onProgressUpdate");
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Object o) {
        Log.d("ClientInformation", "onCancelled");
        super.onCancelled(o);
    }

    @Override
    protected void onCancelled() {
        Log.d("ClientInformation", "onCancelled");
        super.onCancelled();
    }


}

*/
