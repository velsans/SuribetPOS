package com.suribetpos.main.model.lotto;

/**
 * Created by DEV 27 on 11/04/2017.
 */

public class LGBetnumbersandDrawID {

    String DrawIds_Display, DrawIDs;
    int IDs;
    int IsQuick;
    int IsPromoBet;
    String[] BetNumbers;


    public LGBetnumbersandDrawID(int iD, String DrawIds_Displaystr, String DrawIDsstr,
                                 int IsQuickin, int IsPromoBetin, String[] BetNumbersstr) {
        super ();
        IDs = iD;
        DrawIds_Display = DrawIds_Displaystr;
        DrawIDs = DrawIDsstr;
        IsQuick = IsQuickin;
        IsPromoBet = IsPromoBetin;
        BetNumbers = BetNumbersstr;
    }

    public int getIDs() {
        return IDs;
    }

    public void setIDs(int IDs) {
        this.IDs = IDs;
    }

    public String getDrawIds_Display() {
        return DrawIds_Display;
    }

    public void setDrawIds_Display(String drawIds_Display) {
        DrawIds_Display = drawIds_Display;
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

    public String getDrawIDs() {
        return DrawIDs;
    }

    public void setDrawIDs(String drawIDs) {
        DrawIDs = drawIDs;
    }

    public String[] getBetNumbers() {
        return BetNumbers;
    }

    public void setBetNumbers(String[] betNumbers) {
        BetNumbers = betNumbers;
    }
}
