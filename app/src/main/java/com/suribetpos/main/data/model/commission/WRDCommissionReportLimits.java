package com.suribetpos.main.data.model.commission;

import java.util.ArrayList;

/**
 * Created by DEV 27 on 20/02/2017.
 */

public class WRDCommissionReportLimits {
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

    public ArrayList<WRDCommissionReportDetails> getObjwdrpaidcommissionreportdetails() {
        return objwdrpaidcommissionreportdetails;
    }

    public void setObjwdrpaidcommissionreportdetails(ArrayList<WRDCommissionReportDetails> objwdrpaidcommissionreportdetails) {
        this.objwdrpaidcommissionreportdetails = objwdrpaidcommissionreportdetails;
    }

    int TillId;
    String MacAddress,ErrorMessage;
    public ArrayList<WRDCommissionReportDetails> objwdrpaidcommissionreportdetails;
}
