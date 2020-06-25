package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 09/12/2016.
 */
public class LGDetails {
    String ID, BetNumber1, BetNumber2, BetNumber3, BetNumber4, BetNumber5, BetNumber6;
    int DrawID, IsPromoBet, IsQuick;

    public String getBetNumber3() {
        return BetNumber3;
    }

    public void setBetNumber3(String betNumber3) {
        BetNumber3 = betNumber3;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBetNumber1() {
        return BetNumber1;
    }

    public void setBetNumber1(String betNumber1) {
        BetNumber1 = betNumber1;
    }

    public String getBetNumber2() {
        return BetNumber2;
    }

    public void setBetNumber2(String betNumber2) {
        BetNumber2 = betNumber2;
    }

    public String getBetNumber4() {
        return BetNumber4;
    }

    public void setBetNumber4(String betNumber4) {
        BetNumber4 = betNumber4;
    }

    public String getBetNumber6() {
        return BetNumber6;
    }

    public void setBetNumber6(String betNumber6) {
        BetNumber6 = betNumber6;
    }

    public String getBetNumber5() {
        return BetNumber5;
    }

    public void setBetNumber5(String betNumber5) {
        BetNumber5 = betNumber5;
    }

    public int getDrawID() {
        return DrawID;
    }

    public void setDrawID(int drawID) {
        DrawID = drawID;
    }

    public int getIsQuick() {
        return IsQuick;
    }

    public void setIsQuick(int isQuick) {
        IsQuick = isQuick;
    }

    public int getIsPromoBet() {
        return IsPromoBet;
    }

    public void setIsPromoBet(int isPromoBet) {
        IsPromoBet = isPromoBet;
    }


}
