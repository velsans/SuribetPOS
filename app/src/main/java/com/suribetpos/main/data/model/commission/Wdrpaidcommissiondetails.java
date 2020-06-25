package com.suribetpos.main.data.model.commission;

/**
 * Created by DEV 27 on 16/02/2017.
 */

public class Wdrpaidcommissiondetails {
    public double getPaidCommission() {
        return PaidCommission;
    }

    public void setPaidCommission(double paidCommission) {
        PaidCommission = paidCommission;
    }

    public double getTotalPaid() {
        return TotalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        TotalPaid = totalPaid;
    }

    public double getLocationPaidCommission() {
        return LocationPaidCommission;
    }

    public void setLocationPaidCommission(double locationPaidCommission) {
        LocationPaidCommission = locationPaidCommission;
    }

    public double getLocationBalance() {
        return LocationBalance;
    }

    public void setLocationBalance(double locationBalance) {
        LocationBalance = locationBalance;
    }

    public int getPaidCount() {
        return PaidCount;
    }

    public void setPaidCount(int paidCount) {
        PaidCount = paidCount;
    }

    double PaidCommission,
            TotalPaid,
            LocationPaidCommission,
            LocationBalance;
    int PaidCount;
}
