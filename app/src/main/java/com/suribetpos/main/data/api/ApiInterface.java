package com.suribetpos.main.data.api;

import com.suribetpos.main.data.model.cashout.CashoutInputPaymentModel;
import com.suribetpos.main.data.model.cashout.RequestInputModel;
import com.suribetpos.main.data.model.commission.CashoutCommissionInputModel;
import com.suribetpos.main.data.model.common.AppUpdateModel;
import com.suribetpos.main.data.model.common.Changepassword;
import com.suribetpos.main.ui.denomination.DenominationResponceModel;
import com.suribetpos.main.data.model.common.ClientInformationModel;
import com.suribetpos.main.data.model.etopup.CancelInputModel;
import com.suribetpos.main.data.model.etopup.CardValidationModel;
import com.suribetpos.main.data.model.etopup.CommissionModel;
import com.suribetpos.main.data.model.languages.LanguageCodeModel;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.cashout.TransactionInputModel;
import com.suribetpos.main.data.model.topup.Securityvalidateuser;
import com.suribetpos.main.data.model.etopup.AddETopupModel;
import com.suribetpos.main.data.model.etopup.TillEtopUpTransactionsModel;
import com.suribetpos.main.data.model.etopup.TillTransactionAmountModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @POST(ServiceURL.ApplicationUpdateMethod + "/GetApplicationUpdateUrl")
    Call<AppUpdateModel> GetApplicationUpdateUrl(@Body AppUpdateModel updateModel);

    @GET(ServiceURL.securityControllorName + "/GetClientIDByPhysicalAdd_v1")
    Call<ClientInformationModel> GetClientInformation(@Query("MacAddress") String macaddress);

    //@POST(ServiceURL.securityControllorName + "/GetSecuritySBValidateUser")
    @POST(ServiceURL.securityControllorName + "/GetSecuritySBValidateUser_v2")
    Call<Securityvalidateuser> GetSecuritySBValidateUser(@Body Securityvalidateuser extDBValues);

    // @POST(ServiceURL.TopUpController + "/GetAllActiveDenomination")
    @POST(ServiceURL.TopUpController + "/GetTopupDenomination") //changed according to client
    Call<DenominationResponceModel> GetAllActiveDenomination(@Body int cliendID);

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
    @POST(ServiceURL.CashoutControllName + "/GetCashoutTransactionDetails_v1")
    //Call<TransactionInputModel> GetCashoutTransaction(@QueryMap Map<String, String> parameters);
    Call<TransactionInputModel> GetCashoutTransaction(@Body TransactionInputModel inputparameters);

    @POST(ServiceURL.CashoutControllName + "/GETWRCodeDetails")
    Call<RequestInputModel> GetWRCodeDetails(@QueryMap Map<String, String> parameters);

    @POST(ServiceURL.CashoutControllName + "/DoWithdrawPayment")
        //Call<CashoutInputPaymentModel> GetWDRCodeDetails(@QueryMap Map<String, Object> parameters);
    Call<CashoutInputPaymentModel> GetWDRCodeDetails(@Body CashoutInputPaymentModel inputparameter);

    @POST(ServiceURL.CashoutControllName + "/GetWDRPaidCommissionDetails")
    Call<CashoutCommissionInputModel> GetCashoutCommission(@Body CashoutCommissionInputModel inputparameter);
    //Call<CashoutCommissionInputModel> GetCashoutCommission(@Body CashoutCommissionInputModel inputparameter);

    // http://vg.suribet.sr/PlayercardAPI/api/Customer/
    //@POST("PlayercardAPI/api/Customer/GetCustomerTrackDetails")
    @POST("GetCustomerTrackDetails")
    Call<List<CardValidationModel>> GetCardValidation(@Body CardValidationModel inputparameter);

}
