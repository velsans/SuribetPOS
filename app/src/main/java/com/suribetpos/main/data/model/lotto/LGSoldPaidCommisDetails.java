package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 2/8/2018.
 */

public class LGSoldPaidCommisDetails {
    int TotalSlip;
    int TotalPayoutCount;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    int TillID;
    double AmountReceived,CommissionPercentage,Commission,Balance,LocationTotalPayout,LocationPayoutCommission,PayoutCommissionPercentage;

    public int getTotalSlip() {
        return TotalSlip;
    }

    public void setTotalSlip(int totalSlip) {
        TotalSlip = totalSlip;
    }

    public int getTotalPayoutCount() {
        return TotalPayoutCount;
    }

    public void setTotalPayoutCount(int totalPayoutCount) {
        TotalPayoutCount = totalPayoutCount;
    }

    public double getAmountReceived() {
        return AmountReceived;
    }

    public void setAmountReceived(double amountReceived) {
        AmountReceived = amountReceived;
    }

    public double getCommissionPercentage() {
        return CommissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        CommissionPercentage = commissionPercentage;
    }

    public double getCommission() {
        return Commission;
    }

    public void setCommission(double commission) {
        Commission = commission;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
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

    public double getPayoutCommissionPercentage() {
        return PayoutCommissionPercentage;
    }

    public void setPayoutCommissionPercentage(double payoutCommissionPercentage) {
        PayoutCommissionPercentage = payoutCommissionPercentage;
    }
}
