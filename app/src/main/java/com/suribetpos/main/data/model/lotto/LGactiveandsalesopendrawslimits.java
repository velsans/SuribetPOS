package com.suribetpos.main.model.lotto;

import java.util.ArrayList;

/**
 * Created by Velmurugan on 08/12/2016.
 */

public class LGactiveandsalesopendrawslimits {


    public int getCountryID() {
        return CountryID;
    }

    public void setCountryID(int countryID) {
        CountryID = countryID;
    }

    int CountryID, TillID;

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ArrayList<LGactiveandsalesopendraws> getObjlottogetactiveandsalesopendraws() {
        return objlottogetactiveandsalesopendraws;
    }

    public void setObjlottogetactiveandsalesopendraws(ArrayList<LGactiveandsalesopendraws> objlottogetactiveandsalesopendraws) {
        this.objlottogetactiveandsalesopendraws = objlottogetactiveandsalesopendraws;
    }

    public String MacAddress;
    public String ErrorMessage;
    ArrayList<LGactiveandsalesopendraws> objlottogetactiveandsalesopendraws;
    ArrayList<LGactiveandsalesopendrawsfreetickets> objlottogetactiveandsalesopendrawsfreetickets;


    public ArrayList<LGactiveandsalesopendrawsfreetickets> getObjlottogetactiveandsalesopendrawsfreetickets() {
        return objlottogetactiveandsalesopendrawsfreetickets;
    }

    public void setObjlottogetactiveandsalesopendrawsfreetickets(ArrayList<LGactiveandsalesopendrawsfreetickets> objlottogetactiveandsalesopendrawsfreetickets) {
        this.objlottogetactiveandsalesopendrawsfreetickets = objlottogetactiveandsalesopendrawsfreetickets;
    }


}
