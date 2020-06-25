package com.suribetpos.main.model.lotto;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 03/01/2017.
 */

public class LGrecentresultlimits {

    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ArrayList<LGrecentresult> getObjLottoRecentResults() {
        return objLottoRecentResults;
    }

    public void setObjLottoRecentResults(ArrayList<LGrecentresult> objLottoRecentResults) {
        this.objLottoRecentResults = objLottoRecentResults;
    }

    int TillID, CountryID;
    public String MacAddress;
    public String ErrorMessage;
    ArrayList<LGrecentresult> objLottoRecentResults;

}
