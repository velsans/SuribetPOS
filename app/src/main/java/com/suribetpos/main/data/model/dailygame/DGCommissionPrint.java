package com.suribetpos.main.model.dailygame;

/**
 * Created by Velmurugan on 2/7/2018.
 */

public class DGCommissionPrint {

    double TotalBetReceived,TotalBetCount,CommissionBetReceiv,TotalBetpaid,TotalPaidCount,PayoutCommPercent,CommissionBetPaid,TotalCancelTicket,TotalCancelAmount,Balance;
    String LocationCode,LocationAddress,LocationTelephone,LocationDescription,PrintDate;
    int TillID;

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public double getTotalBetReceived() {
        return TotalBetReceived;
    }

    public void setTotalBetReceived(double totalBetReceived) {
        TotalBetReceived = totalBetReceived;
    }

    public double getTotalBetCount() {
        return TotalBetCount;
    }

    public void setTotalBetCount(double totalBetCount) {
        TotalBetCount = totalBetCount;
    }

    public double getCommissionBetReceiv() {
        return CommissionBetReceiv;
    }

    public void setCommissionBetReceiv(double commissionBetReceiv) {
        CommissionBetReceiv = commissionBetReceiv;
    }

    public double getTotalBetpaid() {
        return TotalBetpaid;
    }

    public void setTotalBetpaid(double totalBetpaid) {
        TotalBetpaid = totalBetpaid;
    }

    public double getTotalPaidCount() {
        return TotalPaidCount;
    }

    public void setTotalPaidCount(double totalPaidCount) {
        TotalPaidCount = totalPaidCount;
    }

    public double getPayoutCommPercent() {
        return PayoutCommPercent;
    }

    public void setPayoutCommPercent(double payoutCommPercent) {
        PayoutCommPercent = payoutCommPercent;
    }

    public double getCommissionBetPaid() {
        return CommissionBetPaid;
    }

    public void setCommissionBetPaid(double commissionBetPaid) {
        CommissionBetPaid = commissionBetPaid;
    }

    public double getTotalCancelTicket() {
        return TotalCancelTicket;
    }

    public void setTotalCancelTicket(double totalCancelTicket) {
        TotalCancelTicket = totalCancelTicket;
    }

    public double getTotalCancelAmount() {
        return TotalCancelAmount;
    }

    public void setTotalCancelAmount(double totalCancelAmount) {
        TotalCancelAmount = totalCancelAmount;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
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

    public String getLocationTelephone() {
        return LocationTelephone;
    }

    public void setLocationTelephone(String locationTelephone) {
        LocationTelephone = locationTelephone;
    }

    public String getLocationDescription() {
        return LocationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        LocationDescription = locationDescription;
    }

    public String getPrintDate() {
        return PrintDate;
    }

    public void setPrintDate(String printDate) {
        PrintDate = printDate;
    }
}
