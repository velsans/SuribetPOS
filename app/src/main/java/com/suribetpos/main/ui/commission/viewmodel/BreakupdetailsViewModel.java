package com.suribetpos.main.ui.commission.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.suribetpos.BR;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.etopup.BalanceAmountModel;
import com.suribetpos.main.data.model.etopup.TillTransactionAmountModel;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.utils.Common;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreakupdetailsViewModel extends BaseObservable {
    private MutableLiveData<List<BalanceAmountModel>> BreakUpListData;
    ApiInterface ClientInfoApi;
    Context context;
    DecimalFormat df = new DecimalFormat("###0.00");

    public BreakupdetailsViewModel(Context Context) {
        ClientInfoApi = ApiClient.getInstance().getUserService();
        BreakUpListData = new MutableLiveData<>();
        this.context = Context;
    }

    @Bindable
    private int networkStatus;

    public int getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(int networkStatus) {
        this.networkStatus = networkStatus;
        notifyPropertyChanged(BR.networkStatus);
    }

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    private String topupTransactionAmt;
    @Bindable
    private String topupSaleCommAmt;
    @Bindable
    private String locationtopupSalesCommissionPercent;

    public String getTopupTransactionAmt() {
        return topupTransactionAmt;
    }

    public void setTopupTransactionAmt(String topupTransactionAmt) {
        this.topupTransactionAmt = topupTransactionAmt;
        notifyPropertyChanged(BR.topupTransactionAmt);
    }

    public String getTopupSaleCommAmt() {
        return topupSaleCommAmt;
    }

    public void setTopupSaleCommAmt(String topupSaleCommAmt) {
        this.topupSaleCommAmt = topupSaleCommAmt;
        notifyPropertyChanged(BR.topupSaleCommAmt);
    }

    public String getLocationtopupSalesCommissionPercent() {
        String Value = "0.0";
        if (isNullOrEmpty(locationtopupSalesCommissionPercent)) {
        } else {
            Value = locationtopupSalesCommissionPercent;
        }
        return " (" + Value + "%)";
    }

    public void setLocationtopupSalesCommissionPercent(String locationtopupSalesCommissionPercent) {
        this.locationtopupSalesCommissionPercent = locationtopupSalesCommissionPercent;
        notifyPropertyChanged(BR.locationtopupSalesCommissionPercent);
    }

    @Bindable
    private String openingBal;
    @Bindable
    private String eTopupTranamt;
    @Bindable
    private String eTopupCommPercent;
    @Bindable
    private String eTopupTrCommamt;
    @Bindable
    private String eCashoutTranamt;
    @Bindable
    private String eCashoutCommPercent;
    @Bindable
    private String eCashoutTrCommamt;
    @Bindable
    private String closingBalance;
    @Bindable
    private String currencyCode;
    @Bindable
    private String FromTodate;
    @Bindable
    private String CollectionAmount;
    @Bindable
    private String cashoutNnComTransactionAmount;


    public String getFromTodate() {
        if (NewHomeActivity.BalanceAmountList.size() > 0) {
            FromTodate = CommonMessage(R.string.From) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate()) + " " + CommonMessage(R.string.To) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getGamingDate());
        }
        return FromTodate;
    }

    public void setFromTodate(String fromTodate) {
        this.FromTodate = fromTodate;
        notifyPropertyChanged(BR.FromTodate);
    }

    public String getCollectionAmount() {
        if (NewHomeActivity.BalanceAmountList.size() > 0) {
            CollectionAmount = Common.CurrencyCode + " " + df.format(NewHomeActivity.BalanceAmountList.get(0).getLastCollectedAmt());
        } else {
            CollectionAmount = "0.00";
        }
        return CollectionAmount;
    }

    public void setCollectionAmount(String collectionAmount) {
        CollectionAmount = collectionAmount;
        notifyPropertyChanged(BR.CollectionAmount);
    }

    public String getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(String openingBal) {
        this.openingBal = openingBal;
        notifyPropertyChanged(BR.openingBal);
    }

    public String getETopupTranamt() {
        return eTopupTranamt;
    }

    public void setETopupTranamt(String eTopupTranamt) {
        this.eTopupTranamt = eTopupTranamt;
        notifyPropertyChanged(BR.eTopupTranamt);
    }

    public String getETopupCommPercent() {
        String Value = "0.0";
        if (isNullOrEmpty(eTopupCommPercent)) {
        } else {
            Value = eTopupCommPercent;
        }
        return " (" + Value + "%)";
    }

    public void setETopupCommPercent(String eTopupCommPercent) {
        this.eTopupCommPercent = eTopupCommPercent;
        notifyPropertyChanged(BR.eTopupCommPercent);
    }

    public String getETopupTrCommamt() {
        return eTopupTrCommamt;
    }

    public void setETopupTrCommamt(String eTopupTrCommamt) {
        this.eTopupTrCommamt = eTopupTrCommamt;
        notifyPropertyChanged(BR.eTopupTrCommamt);
    }

    public String getECashoutTranamt() {
        return eCashoutTranamt;
    }

    public void setECashoutTranamt(String eCashoutTranamt) {
        this.eCashoutTranamt = eCashoutTranamt;
        notifyPropertyChanged(BR.eCashoutTranamt);
    }

    public String getECashoutCommPercent() {
        String Value = "0.0";
        if (isNullOrEmpty(eCashoutCommPercent)) {
        } else {
            Value = eCashoutCommPercent;
        }
        return " (" + Value + "%)";
    }

    public void setECashoutCommPercent(String eCashoutCommPercent) {
        this.eCashoutCommPercent = eCashoutCommPercent;
        notifyPropertyChanged(BR.eCashoutCommPercent);
    }

    public String getECashoutTrCommamt() {
        return eCashoutTrCommamt;
    }

    public void setECashoutTrCommamt(String eCashoutTrCommamt) {
        this.eCashoutTrCommamt = eCashoutTrCommamt;
        notifyPropertyChanged(BR.eCashoutTrCommamt);
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
        notifyPropertyChanged(BR.closingBalance);
    }

    public String getCurrencyCode() {
        return Common.CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        notifyPropertyChanged(BR.currencyCode);
    }

    public String getCashoutNnComTransactionAmount() {
        return cashoutNnComTransactionAmount;
    }

    public void setCashoutNnComTransactionAmount(String cashoutNnComTransactionAmount) {
        this.cashoutNnComTransactionAmount = cashoutNnComTransactionAmount;
        notifyPropertyChanged(BR.cashoutNnComTransactionAmount);
    }

    public void BreakUoDetails() {
        try {
            TillTransactionAmountModel transactionAmountModel = new TillTransactionAmountModel();
            transactionAmountModel.setUserId(Common.UserId);
            transactionAmountModel.setTillId(Common.TillId);
            transactionAmountModel.setClientId(Common.ClientId);
            transactionAmountModel.setMacAddress(Common.MobileMacAddress);
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetTillTransactionAmount(transactionAmountModel).enqueue(new Callback<TillTransactionAmountModel>() {
                @Override
                public void onResponse(Call<TillTransactionAmountModel> call, Response<TillTransactionAmountModel> response) {
                    // try {
                    if (response.body() != null) {
                        if (response.body().getTable() != null) {
                            if (response.body().getTable().get(0).getStatus() == 1) {
                                if (response.body().getTable1().size() > 0) {
                                    BreakUpListData.setValue(response.body().getTable1());
                                    Common.ClientBalance = response.body().getTable1().get(0).getTotalTransactionAmount();
                                    Common.ClinetGamingDate = response.body().getTable1().get(0).getGamingDate();

                                    setOpeningBal(String.valueOf(response.body().getTable1().get(0).getOpeningBal()));

                                    setETopupTranamt(String.valueOf(response.body().getTable1().get(0).getETopupTranamt()));
                                    setETopupCommPercent(String.valueOf(response.body().getTable1().get(0).getETopupCommPercent()));
                                    setETopupTrCommamt(String.valueOf(response.body().getTable1().get(0).getETopupTrCommamt()));
                                    setECashoutTranamt(String.valueOf(response.body().getTable1().get(0).getECashoutTranamt()));
                                    setECashoutCommPercent(String.valueOf(response.body().getTable1().get(0).getECashoutCommPercent()));
                                    setECashoutTrCommamt(String.valueOf(response.body().getTable1().get(0).getECashoutTrCommamt()));

                                    setClosingBalance(String.valueOf(response.body().getTable1().get(0).getClosingBalance()));
                                    setCashoutNnComTransactionAmount(String.valueOf(response.body().getTable1().get(0).getECashoutNonComTransactionAmount()));

                                    setTopupSaleCommAmt(String.valueOf(response.body().getTable1().get(0).getTopupSaleCommAmt()));
                                    setTopupTransactionAmt(String.valueOf(response.body().getTable1().get(0).getTopupTranAmt()));
                                    setLocationtopupSalesCommissionPercent(String.valueOf(response.body().getTable1().get(0).getLocationtopupSalesCommissionPercent()));

                                    setNetworkStatus(1);
                                } else {
                                    setNetworkStatus(0);
                                }
                            } else {
                                setNetworkStatus(0);
                            }
                        } else {
                            setNetworkStatus(0);
                        }
                        setToastMessage(response.body().getTable().get(0).getErrorCode());
                    } else {
                        setNetworkStatus(0);
                        setToastMessage(CommonMessage(R.string.No_Response_from_API));
                    }
                }

                @Override
                public void onFailure(Call<TillTransactionAmountModel> call, Throwable t) {
                    CrashAnalytics.logReportOnly(t.toString());
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }


    public String CommonMessage(int Common_Msg) {
        return context.getResources().getString(Common_Msg);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public String DateFormat(String givendate) {
        String strDate = "";
        DateFormat givenformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat Outputformatter = new SimpleDateFormat("dd-MMMM-yyyy");
        try {
            Date date = givenformat.parse(givendate);
            strDate = Outputformatter.format(date);
        } catch (Exception ex) {
            Log.e("", "" + ex.toString());
        }
        return strDate;
    }

}