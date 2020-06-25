package com.suribetpos.main.data.repository;

import com.suribetpos.main.data.api.ApiInterface;

import javax.inject.Inject;

public class CashOutTransactionRepo {

    private ApiInterface apiinterface;

    @Inject
    public CashOutTransactionRepo(ApiInterface apiInterface){
        this.apiinterface=apiInterface;
    }
    /*NetWork Call*/

}
