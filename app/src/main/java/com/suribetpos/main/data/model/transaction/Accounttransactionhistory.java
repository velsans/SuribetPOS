package com.suribetpos.main.model.transaction;

public class Accounttransactionhistory {

    private String Acc;

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String acc) {
        Acc = acc;
    }

    private String Type;
    private String Paid;
    private String CURR;
    private String CASHIER;
    private String TIME;
    private String Note;
    double Received, PAIDLocal, RECLocal, VALUE;
    int ID;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPaid() {
        return Paid;
    }

    public void setPaid(String paid) {
        Paid = paid;
    }

    public String getCURR() {
        return CURR;
    }

    public void setCURR(String CURR) {
        this.CURR = CURR;
    }

    public String getCASHIER() {
        return CASHIER;
    }

    public void setCASHIER(String CASHIER) {
        this.CASHIER = CASHIER;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public double getReceived() {
        return Received;
    }

    public void setReceived(double received) {
        Received = received;
    }

    public double getPAIDLocal() {
        return PAIDLocal;
    }

    public void setPAIDLocal(double PAIDLocal) {
        this.PAIDLocal = PAIDLocal;
    }

    public double getRECLocal() {
        return RECLocal;
    }

    public void setRECLocal(double RECLocal) {
        this.RECLocal = RECLocal;
    }

    public double getVALUE() {
        return VALUE;
    }

    public void setVALUE(double VALUE) {
        this.VALUE = VALUE;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
