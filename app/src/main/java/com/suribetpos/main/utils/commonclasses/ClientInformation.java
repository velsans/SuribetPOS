/*
package com.suribetpos.main.utils.commonclasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.suribetpos.main.data.api.ServiceURL;
import com.suribetpos.main.data.model.common.Clientcurrency;
import com.suribetpos.main.data.model.common.Clientinformation;
import com.suribetpos.main.data.model.common.Clientproductname;
import com.suribetpos.main.interfaces.ClientInformationInterface;

import com.suribetpos.main.utils.ConnectionException;
import com.google.gson.Gson;
import com.suribetpos.main.utils.Common;

import org.json.JSONArray;
import org.json.JSONObject;

*/
/**
 * Created by velmurugan on 08/05/2017.
 *//*


public class ClientInformation extends AsyncTask<Object, Object, Object> {

    private ClientInformationInterface listener = null;

    public ClientInformation(ClientInformationInterface asyncResponse) {
        listener = asyncResponse;//Assigning call back interfacethrough constructor
    }

    @Override
    protected Object doInBackground(Object[] params) {

        String ControllerName = ServiceURL.BASE_URL;
        Uri.Builder DGUrlPLD = new Uri.Builder();
        DGUrlPLD.appendPath("security")// Controllor
                .appendPath("GetClientIDByPhysicalAdd")// Method name
                .appendQueryParameter("MacAddress", String.valueOf(Common.MobileMacAddress));// Parameters
        String ClientInformationURL = ControllerName + DGUrlPLD.build().toString();
        Log.e("ClientInformationURL","ClientInformationURL---" +ClientInformationURL);
        //ClientInformationURL="http://demo.ysecit.in:8014/SuribetMobilePOSAPI/security/GetClientIDByPhysicalAdd?MacAddress=86%3A46%3A31%3A03%3A23%3A01%3A36%3A5";
        try {
            String ClientInformations = new Communicator().POST_Obect(ClientInformationURL, null);
            if (ClientInformations != null) {
                JSONObject jsonObj = new JSONObject(ClientInformations);
                Common.ClientErrorMsg = jsonObj.getString("m_Item1");
                if (Common.ClientErrorMsg.equals("")) {
                    //String Client_InfoValues = jsonObj.getString("m_Item2");
                    //Clientinformation client_infoPOJO = new Gson().fromJson(Client_InfoValues, Clientinformation.class);

                    String Client_Info = jsonObj.getString("m_Item2");
                    JSONArray Client_InfoArray = new JSONArray(Client_Info);
                    for (int Client_ProdIindex = 0; Client_ProdIindex < Client_InfoArray.length(); Client_ProdIindex++) {
                        Clientinformation client_POJO = new Gson().fromJson(Client_InfoArray.getString(Client_ProdIindex), Clientinformation.class);
                        Common.ClientinformtionDetails.add(client_POJO);
                    }

                    String Client_ProductInfo = jsonObj.getString("m_Item3");
                    JSONArray Client_ProdArray = new JSONArray(Client_ProductInfo);
                    for (int Client_ProdIindex = 0; Client_ProdIindex < Client_ProdArray.length(); Client_ProdIindex++) {
                        Clientproductname client_productPOJO = new Gson().fromJson(Client_ProdArray.getString(Client_ProdIindex), Clientproductname.class);
                        Common.ClientProductDetails.add(client_productPOJO);
                    }

                    String Client_CurrencyInfo = jsonObj.getString("m_Item4");
                    JSONArray Client_currencyArray = new JSONArray(Client_CurrencyInfo);
                    for (int Client_curIndex = 0; Client_curIndex < Client_currencyArray.length(); Client_curIndex++) {
                        Clientcurrency client_currencyPOJO = new Gson().fromJson(Client_currencyArray.getString(Client_curIndex), Clientcurrency.class);
                        Common.ClientCurrency.add(client_currencyPOJO);
                    }
                    if (Common.ClientinformtionDetails.size()>0) {
                        Common.ClientId = Common.ClientinformtionDetails.get(0).getClientID();
                        Common.ClientName = Common.ClientinformtionDetails.get(0).getClientName().toString();
                        Common.TillId = Common.ClientinformtionDetails.get(0).getTillId();
                        Common.LocationId = Common.ClientinformtionDetails.get(0).getLocationId();
                        Common.LocationTypeId = Common.ClientinformtionDetails.get(0).getLocationTypeId();

                        if (!Common.ClientinformtionDetails.get(0).getClientLogo().equals("") || Common.ClientinformtionDetails.get(0).getClientLogo() != null) {
                            Common.IsLogoAvailable = true;
                            Common.ClientLogoURL = Common.ClientinformtionDetails.get(0).getClientLogo();
                            Common.ClientLogo = StringToBitMap(Common.ClientinformtionDetails.get(0).getClientLogo());
                        } else {
                            Common.IsLogoAvailable = false;
                        }
                    }
                    if (Common.ClientCurrency.size() > 0) {
                        Common.CurrencyID = Common.ClientCurrency.get(0).getCurrencyId();
                        Common.CurrencyCode = Common.ClientCurrency.get(0).getCurrencyCode();
                    }
                }
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
        Log.d("ClientInformation", "onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result) {
        Log.d("ClientInformation", "onPreExecute");
        listener.onClientInformationDetails(result);
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

    public Bitmap StringToBitMap(String encodedString) {
        try {
            Common.Clientlogobyte = Base64.decode(encodedString, Base64.NO_PADDING);
            Bitmap bitmap = BitmapFactory.decodeByteArray(Common.Clientlogobyte, 0, Common.Clientlogobyte.length);
            return bitmap;
        } catch (Exception ex) {
            String Error = e.getMessage();

            return null;
        }
    }

}

*/
