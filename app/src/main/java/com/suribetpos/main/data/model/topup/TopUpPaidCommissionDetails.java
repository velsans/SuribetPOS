package com.suribetpos.main.data.model.topup;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class TopUpPaidCommissionDetails {
    public double getSalesCommission() {
        return SalesCommission;
    }

    public void setSalesCommission(double salesCommission) {
        SalesCommission = salesCommission;
    }

    public double getTotalSold() {
        return TotalSold;
    }

    public void setTotalSold(double totalSold) {
        TotalSold = totalSold;
    }

    public double getLocationSoldCommission() {
        return LocationSoldCommission;
    }

    public void setLocationSoldCommission(double locationSoldCommission) {
        LocationSoldCommission = locationSoldCommission;
    }

    public double getLocationBalance() {
        return LocationBalance;
    }

    public void setLocationBalance(double locationBalance) {
        LocationBalance = locationBalance;
    }

    public double getCancelAmount() {
        return CancelAmount;
    }

    public void setCancelAmount(double cancelAmount) {
        CancelAmount = cancelAmount;
    }

    public int getCancelCount() {
        return CancelCount;
    }

    public void setCancelCount(int cancelCount) {
        CancelCount = cancelCount;
    }

    double SalesCommission,TotalSold,LocationSoldCommission, LocationBalance, CancelAmount;
    int CancelCount;
}
