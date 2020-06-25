package com.suribetpos.main.data.model.common;

/**
 * Created by DEV 27 on 07/03/2017.
 */

public class Bettypiddetails_user {
    public int getBetTypeID() {
        return BetTypeID;
    }

    public void setBetTypeID(int betTypeID) {
        BetTypeID = betTypeID;
    }

    public String getPickDescription() {
        return PickDescription;
    }

    public void setPickDescription(String pickDescription) {
        PickDescription = pickDescription;
    }

    public int BetTypeID;
    public String PickDescription;
}
