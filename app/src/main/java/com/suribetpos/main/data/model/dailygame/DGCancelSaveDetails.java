package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/6/2018.
 */

public class DGCancelSaveDetails {
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

    public int DrawId, Betnumber;
    public double RemainingLimit;

}
