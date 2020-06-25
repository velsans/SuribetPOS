package com.suribetpos.main.model.commission;

/**
 * Created by DEV 27 on 20/02/2017.
 */

public class WRDCommissionReportDetails {
    public int getTotalSlipCount() {
        return TotalSlipCount;
    }

    public void setTotalSlipCount(int totalSlipCount) {
        TotalSlipCount = totalSlipCount;
    }

    public double getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        AmountPaid = amountPaid;
    }

    public double getCommissionPercentage() {
        return CommissionPercentage;
    }

    public void setCommissionPercentage(double commissionPercentage) {
        CommissionPercentage = commissionPercentage;
    }

    public double getCommissionAmount() {
        return CommissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        CommissionAmount = commissionAmount;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
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

    public String getLocationAddress() {
        return LocationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        LocationAddress = locationAddress;
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

    int TotalSlipCount;
    double AmountPaid,CommissionPercentage,CommissionAmount,Balance;
    String LocationCode,PrintDateTime,LocationAddress,LocationFullName,LocationDescription,LocationPhoneNumber;
}
