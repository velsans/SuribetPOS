package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 12/29/2017.
 */

public class DGBetSavedDetails {
    public int getDrawId() {
        return DrawId;
    }

    public void setDrawId(int drawId) {
        DrawId = drawId;
    }

    public int getBetnumber() {
        return Betnumber;
    }

    public void setBetnumber(int betnumber) {
        Betnumber = betnumber;
    }

    public double getRemainingLimit() {
        return RemainingLimit;
    }

    public void setRemainingLimit(double remainingLimit) {
        RemainingLimit = remainingLimit;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public int DrawId, Betnumber;
    public double RemainingLimit;
    public String BarCode;
}
