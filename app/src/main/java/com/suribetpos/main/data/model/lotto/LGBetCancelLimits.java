package com.suribetpos.main.model.lotto;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 07/01/2017.
 */

public class LGBetCancelLimits {
    int UserID,TillID;
    String BarCode,ErrorMessage;
    ArrayList<LGBetCancel> objLottoBetcancel;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ArrayList<LGBetCancel> getObjLottoBetcancel() {
        return objLottoBetcancel;
    }

    public void setObjLottoBetcancel(ArrayList<LGBetCancel> objLottoBetcancel) {
        this.objLottoBetcancel = objLottoBetcancel;
    }
}
