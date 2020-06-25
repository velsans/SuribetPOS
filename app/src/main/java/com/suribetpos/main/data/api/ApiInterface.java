package com.suribetpos.main.data.api;

import com.suribetpos.main.data.model.common.AppUpdateModel;
import com.suribetpos.main.data.model.common.Changepassword;
import com.suribetpos.main.data.model.common.ClientDenominationInputModel;
import com.suribetpos.main.data.model.common.ClientInformationModel;
import com.suribetpos.main.data.model.etopup.CancelInputModel;
import com.suribetpos.main.data.model.etopup.CommissionModel;
import com.suribetpos.main.data.model.languages.LanguageCodeModel;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.onlinecashout.Cashouttransactiondetails;
import com.suribetpos.main.data.model.topup.Securityvalidateuser;
import com.suribetpos.main.data.model.etopup.AddETopupModel;
import com.suribetpos.main.data.model.etopup.TillEtopUpTransactionsModel;
import com.suribetpos.main.data.model.etopup.TillTransactionAmountModel;
import com.suribetpos.main.ui.viewmodel.CashoutTransactionViewModel;

import java.util.List;
import java.util.Map;

import dagger.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {
    //{"Message":"No HTTP resource was found that matches the request URI 'http://demo.ysecit.in:8014/SuribetMobilePOSAPI/api/security/GetClientIDByPhysicalAdd?MacAddress=86:46:31:03:23:01:36:5'."}
    @POST(ServiceURL.ApplicationUpdateMethod + "/GetApplicationUpdateUrl")
    Call<AppUpdateModel> GetApplicationUpdateUrl(@Body AppUpdateModel updateModel);

    @GET(ServiceURL.securityControllorName + "/GetClientIDByPhysicalAdd_v1")
    Call<ClientInformationModel> GetClientInformation(@Query("MacAddress") String macaddress);

    //@POST(ServiceURL.securityControllorName + "/GetSecuritySBValidateUser")
    @POST(ServiceURL.securityControllorName + "/GetSecuritySBValidateUser_v2")
    Call<Securityvalidateuser> GetSecuritySBValidateUser(@Body Securityvalidateuser extDBValues);

    @POST(ServiceURL.TopUpController + "/GetAllActiveDenomination")
    Call<ClientDenominationInputModel> GetAllActiveDenomination(@Body int url);

    @POST(ServiceURL.TopUpController + "/GetTillTransactionAmount")
    Call<TillTransactionAmountModel> GetTillTransactionAmount(@Body TillTransactionAmountModel tillbalance);

    @POST(ServiceURL.TopUpController + "/GetRetailerETopUpCVoucherCommission/")
    Call<CommissionModel> GetRetailerETopUpCVoucherCommission(@Body CommissionModel extDBValues);

    @POST(ServiceURL.TopUpController + "/AddETopup")
    Call<AddETopupModel> GetAddETopup(@Body AddETopupModel extDBValues);

    @POST(ServiceURL.TopUpController + "/GetTillEtopUpTransactions")
    Call<TillEtopUpTransactionsModel> GetTillEtopUpTransactions(@Body TillEtopUpTransactionsModel extDBValues);

    @POST(ServiceURL.TopUpController + "/ETopupCancelVoucher")
    Call<CancelInputModel> GetETopupCancelVoucher(@Body CancelInputModel extDBValues);

    //@POST(ServiceURL.ResetPassControllor + "/ChangePassword")
    @POST(ServiceURL.ResetPassControllor + "/ChangePassword_v1")
    Call<Changepassword> getChangedPassword(@Body Changepassword changepassword);

    /*  @POST(ServiceURL.LanguageMethod + "/LoadLanguages?")
      Call<List<LanguagesListModel>> getLanguageList(@Query("ClientID") int param1,
                                                     @Query("Plotformid") int param2,
                                                     @Query("Isdevelopment") int param3);*/
    @POST(ServiceURL.LanguageMethod + "/LoadLanguages?")
    Call<List<LanguagesListModel>> getLanguageList(@QueryMap Map<String, Integer> parameters);

    /*    @POST(ServiceURL.LanguageMethod + "/getCommonLoadDBEn")
        Call<LanguageCodeModel> getLanguages(@Query("LangID") int param1,
                                             @Query("prodid") int param2,
                                             @Query("PlatForm") int param3);*/
    @POST(ServiceURL.LanguageMethod + "/getCommonLoadDBEn")
    Call<LanguageCodeModel> getLanguages(@QueryMap Map<String, Integer> parameters);

    /*Cashout*/
    @POST(ServiceURL.CashoutControllName + "/GetCashoutTransactionDetails")
    Call<List<Cashouttransactiondetails>> GetCashoutTransaction(@Body Cashouttransactiondetails cashoutDetails);

}
