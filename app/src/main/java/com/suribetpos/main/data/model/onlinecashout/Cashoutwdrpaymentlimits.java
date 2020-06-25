package com.suribetpos.main.model.onlinecashout;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 09/02/2017.
 */

public class Cashoutwdrpaymentlimits {
    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public String getUWRCode() {
        return UWRCode;
    }

    public void setUWRCode(String UWRCode) {
        this.UWRCode = UWRCode;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public ArrayList<Cashoutwdrpaymentdetails> getObjCashOutWDRpaymentDetails() {
        return objCashOutWDRpaymentDetails;
    }

    public void setObjCashOutWDRpaymentDetails(ArrayList<Cashoutwdrpaymentdetails> objCashOutWDRpaymentDetails) {
        this.objCashOutWDRpaymentDetails = objCashOutWDRpaymentDetails;
    }

    String UWRCode,
            MacAddress,
            ErrorMessage;
    ArrayList<Cashoutwdrpaymentdetails> objCashOutWDRpaymentDetails;
}
