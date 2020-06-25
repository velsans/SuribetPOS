package com.suribetpos.main.data.model.common;

public class Shop {

    public int getItemId() {
        return ItemId;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public String getStockQty() {
        return StockQty;
    }

    public String getSales_Units() {
        return Sales_Units;
    }
    public String getSales_Gross() {
        return Sales_Gross;
    }
    public String getSales_Discount() {
        return Sales_Discount;
    }
    public String getSales_Net() {
        return Sales_Net;
    }
    public String getReturns_Units() {
        return Returns_Units;
    }
    public String getReturns_Gross() {
        return Returns_Gross;
    }
    public String getReturns_Net() {
        return Returns_Net;
    }
    public String getTotal_Units() {
        return Total_Units;
    }
    public String getTotal_Gross() {
        return Total_Gross;
    }
    public String getTotal_Net() {
        return Total_Net;
    }

    public void setItemId(int itemId) {
        this.ItemId = itemId;
    }

    public void setCompanyCode(String companyCode) {
        this.CompanyCode = companyCode;
    }

    public void setLocationCode(String locationCode) {
        this.LocationCode = locationCode;
    }

    public void setStockQty(String stockQty) {
        this.StockQty = stockQty;
    }
    public void setSales_Units(String sales_Units) {
        this.Sales_Units = sales_Units;
    }
    public void setSales_Gross(String sales_Gross) {
        this.Sales_Gross = sales_Gross;
    }
    public void setSales_Discount(String sales_Discount) {
        this.Sales_Discount = sales_Discount;
    }
    public void setSales_Net(String sales_Net) {
        this.Sales_Net = sales_Net;
    }
    public void setReturns_Units(String returns_Units) {
        this.Returns_Units = returns_Units;
    }
    public void setReturns_Gross(String returns_Gross) {
        this.Returns_Gross = returns_Gross;
    }
    public void setReturns_Net(String returns_Net) {
        this.Returns_Net = returns_Net;
    }
    public void setTotal_Units(String total_Units) {
        this.Total_Units = total_Units;
    }
    public void setTotal_Gross(String total_Gross) {
        this.Total_Gross = total_Gross;
    }
    public void setTotal_Net(String total_Net) {
        this.Total_Net = total_Net;
    }

    public int ItemId;
    private String CompanyCode;
    private String LocationCode;
    private String StockQty;

    private String Sales_Units;
    private String Sales_Gross;
    private String Sales_Discount;
    private String Sales_Net;
    private String Returns_Units;
    private String Returns_Gross;
    private String Returns_Net;
    private String Total_Units;
    private String Total_Gross;
    private String Total_Net;

    public static final String ITEMID = "itemid";
    public static final String COMCODE = "CompanyCode";
    public static final String CC = "cc";
    public static final String LOCCODE = "LocationCode";
    public static final String STOCK = "StockQty";

    public static final String SALES_Units = "Sales_Units";
    public static final String SALES_Gross = "Sales_Gross";
    public static final String SALES_Discount = "Sales_Discount";
    public static final String SALES_Net = "Sales_Net";
    public static final String RETURNS_Units = "Returns_Units";
    public static final String RETURNS_Gross = "Returns_Gross";
    public static final String RETURNS_Net = "Returns_Net";
    public static final String TOTAL_Units = "Total_Units";
    public static final String TOTAL_Gross = "Total_Gross";
    public static final String TOTAL_Net = "Total_Net";
}
