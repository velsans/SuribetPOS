package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/7/2018.
 */

public class DGCommPPReport {
    int TillID;
    double TotalPlaceBet;
    double LocationBetCommission;
    double LocationTotalPayout;
    double LocationPayoutCommission;

    public double getTotalPlaceBet() {
        return TotalPlaceBet;
    }

    public void setTotalPlaceBet(double totalPlaceBet) {
        TotalPlaceBet = totalPlaceBet;
    }

    public double getLocationBetCommission() {
        return LocationBetCommission;
    }

    public void setLocationBetCommission(double locationBetCommission) {
        LocationBetCommission = locationBetCommission;
    }

    public double getLocationTotalPayout() {
        return LocationTotalPayout;
    }

    public void setLocationTotalPayout(double locationTotalPayout) {
        LocationTotalPayout = locationTotalPayout;
    }

    public double getLocationPayoutCommission() {
        return LocationPayoutCommission;
    }

    public void setLocationPayoutCommission(double locationPayoutCommission) {
        LocationPayoutCommission = locationPayoutCommission;
    }

    public double getLocationBalance() {
        return LocationBalance;
    }

    public void setLocationBalance(double locationBalance) {
        LocationBalance = locationBalance;
    }

    double LocationBalance;


    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }


}
