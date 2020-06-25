package com.suribetpos.main.model.lotto;

/**
 * Created by Velmurugan on 2/5/2018.
 */

public class LGCommissionReportPrint {
    int TotalSlip,TotalSlipCount, FreeSlipCount, TotalCancelCount,TillID;
    double AmountReceived, CommissionPercentage, Commission, Balance, FreeSlipTotalAmount, TotalCancelAmount, LocationTotalPayout, LocationPayoutCommission, PayoutCommissionPercentage, TotalPayoutCount;
    String LocationCode, PrintDateTime, LocationAddress, LocationFullName, LocationDescription, LocationPhoneNumber;

    public int getTotalSlip() {
        return TotalSlip;
    }

    public void setTotalSlip(int totalSlip) {
        TotalSlip = totalSlip;
    }

    public int getTotalSlipCount() {
        return TotalSlipCount;
    }

    public void setTotalSlipCount(int totalSlipCount) {
        TotalSlipCount = totalSlipCount;
    }

    public int getFreeSlipCount() {
        return FreeSlipCount;
    }

    public void setFreeSlipCount(int freeSlipCount) {
        FreeSlipCount = freeSlipCount;
    }

    public int getTotalCancelCount() {
        return TotalCancelCount;
    }

    public void setTotalCancelCount(int totalCancelCount) {
        TotalCancelCount = totalCancelCount;
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

    public double getFreeSlipTotalAmount() {
        return FreeSlipTotalAmount;
    }

    public void setFreeSlipTotalAmount(double freeSlipTotalAmount) {
        FreeSlipTotalAmount = freeSlipTotalAmount;
    }

    public double getTotalCancelAmount() {
        return TotalCancelAmount;
    }

    public void setTotalCancelAmount(double totalCancelAmount) {
        TotalCancelAmount = totalCancelAmount;
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

    public double getTotalPayoutCount() {
        return TotalPayoutCount;
    }

    public void setTotalPayoutCount(double totalPayoutCount) {
        TotalPayoutCount = totalPayoutCount;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getPrintDateTime() {
        return PrintDateTime;
    }

    public void setPrintDateTime(String printDateTime) {
        PrintDateTime = printDateTime;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    public String getLocationFullName() {
        return LocationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        LocationFullName = locationFullName;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        LocationDescription = locationDescription;
    }

    public String getLocationPhoneNumber() {
        return LocationPhoneNumber;
    }

    public void setLocationPhoneNumber(String locationPhoneNumber) {
        LocationPhoneNumber = locationPhoneNumber;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }
}
