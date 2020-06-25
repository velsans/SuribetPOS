package com.suribetpos.main.model.dailygame;


public class DGmultiplier {
    private String DrawID;
    private String Multiplier;
    private String OpenTime;

    public String getOpenTime() {
        return OpenTime;
    }

    public String getCloseTime() {
        return CloseTime;
    }

    private String CloseTime;
    private String CountryID;

    public String getDrawId() {
        return DrawID;
    }

    public String getMultiplier() {
        return Multiplier;
    }

    public String getCountryId() {
        return CountryID;
    }


    public void setDrawId(String drawId) {
        DrawID = drawId;
    }

    public void setMultiplier(String multiplier) {
        Multiplier = multiplier;
    }

    public void setOpenTime(String OpenTime) {
        OpenTime = OpenTime;
    }

    public void setCloseTime(String CloseTime) {
        CloseTime = CloseTime;
    }

    public void setCountryId(String countryId) {
        CountryID = countryId;
    }


}
