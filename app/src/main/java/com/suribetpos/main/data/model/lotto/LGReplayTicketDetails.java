package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 1/21/2018.
 */

public class LGReplayTicketDetails {

    int BetNumber1;
    int BetNumber2;
    int BetNumber3;
    int BetNumber4;
    int BetNumber5;

    public int getBetNumber1() {
        return BetNumber1;
    }

    public void setBetNumber1(int betNumber1) {
        BetNumber1 = betNumber1;
    }

    public int getBetNumber2() {
        return BetNumber2;
    }

    public void setBetNumber2(int betNumber2) {
        BetNumber2 = betNumber2;
    }

    public int getBetNumber3() {
        return BetNumber3;
    }

    public void setBetNumber3(int betNumber3) {
        BetNumber3 = betNumber3;
    }

    public int getBetNumber4() {
        return BetNumber4;
    }

    public void setBetNumber4(int betNumber4) {
        BetNumber4 = betNumber4;
    }

    public int getBetNumber5() {
        return BetNumber5;
    }

    public void setBetNumber5(int betNumber5) {
        BetNumber5 = betNumber5;
    }

    public int getBetNumber6() {
        return BetNumber6;
    }

    public void setBetNumber6(int betNumber6) {
        BetNumber6 = betNumber6;
    }

    int BetNumber6,TillID;
    String BarCode;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }
}
