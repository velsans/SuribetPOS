package com.suribetpos.main.ui.cashout.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.cashout.TransactionDetails;
import com.suribetpos.main.data.model.cashout.TransactionInputModel;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.utils.Common;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionViewModel extends ViewModel {

    private MutableLiveData<List<TransactionDetails>> transactionListData;
    ApiInterface ClientInfoApi;

    public TransactionViewModel() {
        ClientInfoApi = ApiClient.getInstance().getUserService();
        transactionListData = new MutableLiveData<>();
    }

    public MutableLiveData<List<TransactionDetails>> getTransaction() {
        try {
            ClientInfoApi = ApiClient.getApiInterface();
            TransactionInputModel inputparameters = new TransactionInputModel();
            inputparameters.setTillId(Common.TillId);
            inputparameters.setMacAddress(Common.MobileMacAddress);
            if (NewHomeActivity.BalanceAmountList.size() > 0) {
                inputparameters.setCollectedDate(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate());
            } else {
                inputparameters.setCollectedDate("");
            }
            ClientInfoApi.GetCashoutTransaction(inputparameters).enqueue(new Callback<TransactionInputModel>() {
                @Override
                public void onResponse(Call<TransactionInputModel> call, Response<TransactionInputModel> response) {
                    if (response.body() != null) {
                        if (response.body().getM_Item1() != null) {
                            transactionListData.setValue(response.body().getM_Item1());
                        }
                    }
                }

                @Override
                public void onFailure(Call<TransactionInputModel> call, Throwable t) {
                    Log.e("getMessage", ">>>>>>>" + t.getMessage());
                }
            });
        } catch (Exception ex) {
            Log.e("getMessage", ">>>>>>>" + ex.toString());
        }
        return transactionListData;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

}