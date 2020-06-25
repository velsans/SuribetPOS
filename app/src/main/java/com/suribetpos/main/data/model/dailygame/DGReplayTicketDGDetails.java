package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 1/21/2018.
 */

public class DGReplayTicketDGDetails {

    int RowNum, BetNumber;
    String BarCode;

    public int getRowNum() {
        return RowNum;
    }

    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }

    public int getBetNumber() {
        return BetNumber;
    }

    public void setBetNumber(int betNumber) {
        BetNumber = betNumber;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}
