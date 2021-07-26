package com.suribetpos.main.ui.cashout.viewmodel;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.suribetpos.BR;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.cashout.CashoutInputPaymentModel;
import com.suribetpos.main.data.model.cashout.CashoutPaymentDetailsModel;
import com.suribetpos.main.data.model.cashout.RequestInputModel;
import com.suribetpos.main.ui.cashout.CashoutActivity;
import com.suribetpos.main.utils.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CashoutCreateViewModel extends BaseObservable {
    private MutableLiveData<List<RequestNrModel>> cashoutcreateLiveData;
    private MutableLiveData<List<CashoutPaymentDetailsModel>> cashoutpaymentDetailsLiveData;

    Context context;
    ApiInterface ClientInfoApi;
    //RequestNrModel requestNrModel;
    List<RequestNrModel> requestNrList = new ArrayList<>();
    public String WDRCode, AccountID, CurrencyCode, Amount, IDNumber, IDName;
    public Boolean IDVisible = false;

    public CashoutCreateViewModel(Context Context) {
        ClientInfoApi = ApiClient.getInstance().getUserService();
        cashoutcreateLiveData = new MutableLiveData<>();
        cashoutpaymentDetailsLiveData = new MutableLiveData<>();
        //authenticationState=new MutableLiveData<>();
        //Responce=new MutableLiveData<>();
        this.context = Context;
    }

    /*Input feilds*/
    @Bindable
    public String getWDRCode() {
        return WDRCode;
    }

    public void setWDRCode(String WDRCode) {
        this.WDRCode = WDRCode;
        notifyPropertyChanged(BR.wDRCode);
    }

    @Bindable
    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
        notifyPropertyChanged(BR.iDNumber);
    }

    @Bindable
    public String getIDName() {
        return IDName;
    }

    public void setIDName(String IDName) {
        this.IDName = IDName;
        notifyPropertyChanged(BR.iDName);
    }

    @Bindable
    public Boolean getIDVisible() {
        return IDVisible;
    }

    public void setIDVisible(Boolean IDVisible) {
        this.IDVisible = IDVisible;
        notifyPropertyChanged(BR.iDVisible);
    }

    /*Output feilds*/
    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

/*    @Bindable
    private int networkStatus;

    public int getNetworkStatus() {
        return networkStatus;
    }

    public void setNetworkStatus(int networkStatus) {
        this.networkStatus = networkStatus;
        notifyPropertyChanged(BR.networkStatus);
    }*/

    @Bindable
    public String getAccountID() {
        return AccountID;
    }

    public void setAccountID(String accountID) {
        AccountID = accountID;
        notifyPropertyChanged(BR.accountID);
    }

    @Bindable
    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        CurrencyCode = currencyCode;
        notifyPropertyChanged(BR.currencyCode);
    }

    @Bindable
    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
        notifyPropertyChanged(BR.amount);
    }

    /*Onclick*/
    public void onRequestWDRDetails() {
        if (isInputWRDValid()) {
            if (isInputDataValid()) {
                GetWDRCodeDetails();
            } else {
                setToastMessage(CommonMessage(R.string.Request_Nr_lenght));
                Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
            }
        } else {
            setToastMessage(CommonMessage(R.string.please_enter_request_number));
            Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
        }
    }

    public void onRequestWDRPay() {
        if (isInputAccountValid()) {
            if (getIDVisible() == true) {
                if (isInputIDNumber()) {
                } else {
                    setToastMessage(CommonMessage(R.string.please_enter_Id_number));
                    Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                    return;
                }
                if (isInputIDName()) {
                } else {
                    setToastMessage(CommonMessage(R.string.please_enter_Id_Name));
                    Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                    return;
                }
            }
            GetRequestWDRPay();
        } else {
            setToastMessage(CommonMessage(R.string.please_enter_request_number));
            Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
        }

    }

    public void onClearRequestTexts() {
        setWDRCode("");
        setAccountID("");
        setCurrencyCode("");
        setAmount("");
        setIDName("");
        setIDNumber("");
    }


    /*Validation*/
    public boolean isInputDataValid() {
        int minimumLength = 12;
        return !TextUtils.isEmpty(getWDRCode()) && getWDRCode().length() == minimumLength;
    }

    public boolean isInputWRDValid() {
        return !TextUtils.isEmpty(getWDRCode());
    }

    public boolean isInputAccountValid() {
        return !TextUtils.isEmpty(getAccountID());
    }

    public boolean isInputIDNumber() {
        return !TextUtils.isEmpty(getIDNumber());
    }

    public boolean isInputIDName() {
        return !TextUtils.isEmpty(getIDName());
    }


    public MutableLiveData<List<RequestNrModel>> getTransaction() {
        //if (cashoutcreateLiveData == null) {
        cashoutcreateLiveData = new MutableLiveData<>();
        //}
        return cashoutcreateLiveData;
    }

    public void GetWDRCodeDetails() {
        ClientInfoApi = ApiClient.getApiInterface();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("UWRcode", getWDRCode());
        ClientInfoApi.GetWRCodeDetails(parameters).enqueue(new Callback<RequestInputModel>() {
            @Override
            public void onResponse(Call<RequestInputModel> call, Response<RequestInputModel> response) {
                if (response.body() != null) {
                    if (response.body().getM_Item1() != null) {
                        if (response.body().getM_Item1().get(0).getStatus() == 1) {
                            requestNrList = response.body().getM_Item2();
                            cashoutcreateLiveData.setValue(requestNrList);
                            setAccountID(String.valueOf(requestNrList.get(0).getAccountID()));
                            setCurrencyCode(String.valueOf(requestNrList.get(0).getCurrencyCode()));
                            setAmount(String.valueOf(requestNrList.get(0).getAmount()));
                            if (Common.EnableCashoutValidation == true) {
                                if (requestNrList.get(0).getAmount() >= Common.MinCashoutAmount) {
                                    setIDVisible(true);
                                } else {
                                    setIDVisible(false);
                                }
                            } else {
                                setIDVisible(false);
                            }
                            setToastMessage(Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode()));
                            Toasty.success(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                        } else {
                            setIDVisible(false);
                            onClearRequestTexts();
                            setToastMessage(Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode()));
                            Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                        }
                    } else {
                        setIDVisible(false);
                        setToastMessage(Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode()));
                        Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                    }
                } else {
                    setIDVisible(false);
                    onClearRequestTexts();
                    setToastMessage(CommonMessage(R.string.No_Response_from_API));
                    Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<RequestInputModel> call, Throwable t) {
                onClearRequestTexts();
                setToastMessage(t.getMessage());
                Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
            }
        });
    }

    public void GetRequestWDRPay() {
        CashoutInputPaymentModel inputparameter = new CashoutInputPaymentModel();
        inputparameter.setUWRCode(getWDRCode());
        inputparameter.setAccountId(getAccountID());
        inputparameter.setUserTypeID(requestNrList.get(0).getUserTypeID());
        inputparameter.setTillID(Common.TillId);
        inputparameter.setCurrencyId(requestNrList.get(0).getCurrencyID());
        inputparameter.setUserId(Common.UserId);
        inputparameter.setMacAddress(Common.MobileMacAddress);
        inputparameter.setIdNumber(getIDNumber());
        inputparameter.setIdName(getIDName());

        ClientInfoApi.GetWDRCodeDetails(inputparameter).enqueue(new Callback<CashoutInputPaymentModel>() {
            @Override
            public void onResponse(Call<CashoutInputPaymentModel> call, Response<CashoutInputPaymentModel> response) {
                if (response.body() != null) {
                    if (response.body().getM_Item1() != null) {
                        if (response.body().getM_Item1().get(0).getStatus() == 1) {
                            cashoutpaymentDetailsLiveData.setValue(response.body().getM_Item2());
                            onClearRequestTexts();
                            if (getIDVisible() == true) {
                                setIDVisible(false);
                            }
                            if (Common.LanguageMap.containsKey(response.body().getM_Item1().get(0).getErrorCode()) == true) {
                                setToastMessage(Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode()));
                            } else {
                                setToastMessage(response.body().getM_Item1().get(0).getMessage());
                            }
                            ((CashoutActivity) context).RefreshBalance();
                            Toasty.success(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                        } else {
                            if (response.body().getM_Item1().get(0).getErrorCode().equals("COD10026")) {
                                String ErrorCodeMsg = Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode());
                                String AmountStr = ErrorCodeMsg.replaceAll("10000", response.body().getM_Item1().get(0).getTillLimit() + " ");
                                String CurrencyStr = AmountStr.replaceAll("SRD", Common.CurrencyCode + " ");
                                String ClientName = CurrencyStr.replaceAll("Suribet", Common.ClientName);
                                setToastMessage(ClientName);
                            } else {
                                if (Common.LanguageMap.containsKey(response.body().getM_Item1().get(0).getErrorCode()) == true) {
                                    setToastMessage(Common.LanguageMap.get(response.body().getM_Item1().get(0).getErrorCode()));
                                } else {
                                    setToastMessage(response.body().getM_Item1().get(0).getMessage());
                                }
                            }
                            Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                        }
                    }
                } else {
                    setToastMessage(CommonMessage(R.string.No_Response_from_API));
                    Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
                }
            }

            @Override
            public void onFailure(Call<CashoutInputPaymentModel> call, Throwable t) {
                setToastMessage(t.getMessage());
                Toasty.error(context, getToastMessage(), Toasty.LENGTH_SHORT, true).show();
            }
        });
    }


    public String CommonMessage(int Common_Msg) {
        return context.getResources().getString(Common_Msg);
    }

}
