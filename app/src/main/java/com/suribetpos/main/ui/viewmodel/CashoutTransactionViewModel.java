package com.suribetpos.main.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.onlinecashout.Cashouttransactiondetails;
import com.suribetpos.main.utils.Common;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
public class CashoutTransactionViewModel extends ViewModel {

    private MutableLiveData<List<Cashouttransactiondetails>> transactionListData;
    ApiInterface ClientInfoApi;


    public CashoutTransactionViewModel() {
        ClientInfoApi = ApiClient.getInstance().getUserService();
        transactionListData = new MutableLiveData<>();
        LoadTransaction();
    }

    public MutableLiveData<List<Cashouttransactiondetails>> getTransaction() {
        if (transactionListData == null) {
            transactionListData = new MutableLiveData<List<Cashouttransactiondetails>>();
        }
        return transactionListData;
    }

    public void LoadTransaction() {
        ClientInfoApi = ApiClient.getApiInterface();
        Cashouttransactiondetails cashtransactionPOJO = new Cashouttransactiondetails();
        cashtransactionPOJO.setTillId(Common.TillId);
        cashtransactionPOJO.setMacAddress(Common.MobileMacAddress);
        ClientInfoApi.GetCashoutTransaction(cashtransactionPOJO).enqueue(new Callback<List<Cashouttransactiondetails>>() {
            @Override
            public void onResponse(Call<List<Cashouttransactiondetails>> call, Response<List<Cashouttransactiondetails>> response) {
                transactionListData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Cashouttransactiondetails>> call, Throwable t) {
                Log.e("getMessage", ">>>>>>>" + t.getMessage());
            }
        });
    }
}
