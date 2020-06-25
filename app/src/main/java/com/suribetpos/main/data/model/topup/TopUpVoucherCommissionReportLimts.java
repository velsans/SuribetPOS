package com.suribetpos.main.data.model.topup;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 20/02/2017.
 */

public class TopUpVoucherCommissionReportLimts {
    public int getTillId() {
        return TillId;
    }

    public void setTillId(int tillId) {
        TillId = tillId;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        MacAddress = macAddress;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ArrayList<TopUpVoucherCommissionReportDetails> getObjtopvouchercommissionreportdetails() {
        return objtopvouchercommissionreportdetails;
    }
    public void setObjtopvouchercommissionreportdetails(ArrayList<TopUpVoucherCommissionReportDetails> objtopvouchercommissionreportdetails) {
        this.objtopvouchercommissionreportdetails = objtopvouchercommissionreportdetails;
    }
    int TillId;
    String MacAddress, ErrorMessage;
    ArrayList<TopUpVoucherCommissionReportDetails> objtopvouchercommissionreportdetails;

}
