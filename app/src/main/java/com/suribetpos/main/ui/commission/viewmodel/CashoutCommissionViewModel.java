package com.suribetpos.main.ui.commission.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.commission.CashoutCommissionDetails;
import com.suribetpos.main.data.model.commission.CashoutCommissionInputModel;
import com.suribetpos.main.data.model.commission.CashoutCommissonReportDetails;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashoutCommissionViewModel extends ViewModel {
    private MutableLiveData<List<CashoutCommissionDetails>> commissionListData;
    private MutableLiveData<List<CashoutCommissonReportDetails>> commissionReportData;
    ApiInterface ClientInfoApi;

    public CashoutCommissionViewModel() {
        ClientInfoApi = ApiClient.getInstance().getUserService();
        commissionListData = new MutableLiveData<>();
        commissionReportData = new MutableLiveData<>();
    }

    public MutableLiveData<List<CashoutCommissonReportDetails>> getCommissionReport() {
        commissionReportData = new MutableLiveData<>();
        return commissionReportData;
    }

    public MutableLiveData<List<CashoutCommissionDetails>> getCommission() {
        ClientInfoApi = ApiClient.getApiInterface();
        CashoutCommissionInputModel inputparameter = new CashoutCommissionInputModel();
        inputparameter.setTillId(Common.TillId);
        if (NewHomeActivity.BalanceAmountList.size() > 0) {
            inputparameter.setCollectedDate(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate());
        } else {
            inputparameter.setCollectedDate("");
        }
        ClientInfoApi.GetCashoutCommission(inputparameter).enqueue(new Callback<CashoutCommissionInputModel>() {
            @Override
            public void onResponse(Call<CashoutCommissionInputModel> call, Response<CashoutCommissionInputModel> response) {
                if (response.body() != null) {
                    if (response.body().getTable() != null) {
                        commissionListData.setValue(response.body().getTable());
                    }
                    if (response.body().getTable1() != null) {
                        commissionReportData.setValue(response.body().getTable1());
                    }
                }
            }

            @Override
            public void onFailure(Call<CashoutCommissionInputModel> call, Throwable t) {
                Log.e("getMessage", ">>>>>>>" + t.getMessage());
            }
        });
        return commissionListData;
    }
}