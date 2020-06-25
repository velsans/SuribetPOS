package com.suribetpos.main.data.model.topup;

/**
 * Created by DEV 27 on 20/02/2017.
 */

public class TopUpVoucherCommissionReportDetails {
    public int getTotalSlipCount() {
        return TotalSlipCount;
    }

    public void setTotalSlipCount(int totalSlipCount) {
        TotalSlipCount = totalSlipCount;
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

    public double getTotalCancelAmount() {
        return TotalCancelAmount;
    }

    public void setTotalCancelAmount(double totalCancelAmount) {
        TotalCancelAmount = totalCancelAmount;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
    }

    public String getPrintDateTime() {
        return PrintDateTime;
    }

    public void setPrintDateTime(String printDateTime) {
        PrintDateTime = printDateTime;
    }

    public String getLocationFullName() {
        return LocationFullName;
    }

    public void setLocationFullName(String locationFullName) {
        LocationFullName = locationFullName;
    }

    public String getLocationPhoneNumber() {
        return LocationPhoneNumber;
    }

    public void setLocationPhoneNumber(String locationPhoneNumber) {
        LocationPhoneNumber = locationPhoneNumber;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        LocationDescription = locationDescription;
    }

    int TotalSlipCount, TotalCancelCount;
    double AmountReceived;
    double CommissionPercentage;
    double Commission;
    double TotalCancelAmount;

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    double Balance;
    String LocationCode, PrintDateTime, LocationAddress, LocationFullName, LocationDescription, LocationPhoneNumber;
}
