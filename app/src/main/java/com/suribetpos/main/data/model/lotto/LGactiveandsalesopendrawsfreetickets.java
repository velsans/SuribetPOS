package com.suribetpos.main.model.lotto;

/**
 * Created by DEV 27 on 21/12/2016.
 */
public class LGactiveandsalesopendrawsfreetickets {
    public String getDrawID() {
        return DrawID;
    }

    public void setDrawID(String drawID) {
        DrawID = drawID;
    }

    public String getBuyTicketsNos() {
        return BuyTicketsNos;
    }

    public void setBuyTicketsNos(String buyTicketsNos) {
        BuyTicketsNos = buyTicketsNos;
    }

    public String getGetTicketsNos() {
        return GetTicketsNos;
    }

    public void setGetTicketsNos(String getTicketsNos) {
        GetTicketsNos = getTicketsNos;
    }

    String DrawID,BuyTicketsNos,GetTicketsNos;
}
