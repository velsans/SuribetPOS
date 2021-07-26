package com.suribetpos.main.data.api

import com.suribetpos.main.data.model.etopup.CardValidationModel
import com.suribetpos.main.ui.commission.model.*
import com.suribetpos.main.ui.topup.model.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by Velmurugan on 2/8/2021.
 */
interface TopupApi {

    @POST(ServiceURL.TopUpController + "/AddUpdatePrintVouchersDetails")
    suspend fun pushCreateVoucher(
        @Body createVoucherModel: CreateVoucherModel
    ): Response<CreateVoucherResponse>

    @POST(ServiceURL.TopUpController + "/VerifySalesVoucherForCancel")
    suspend fun pushVerifyVoucher(
        @Body verifyCancelVoucherRequestModel: VerifyCancelVoucherRequestModel
    ): Response<VerifyCanceVocuherResponceModel>


    @POST(ServiceURL.TopUpController + "/CancelSalesVouchers")
    suspend fun pushCancelVoucher(
        @Body cancelRequestModel: CancelRequestModel
    ): Response<CancelResponseModel>

    @POST(ServiceURL.TopUpController + "/DisplaySalesVoucherCancelHistory")
    suspend fun getCancelledVoucher(@Body tillid: Int): Response<CancelledVoucherResponce>


    // Etopup Api
    @POST(ServiceURL.TopUpController + "/GetRetailerETopCancelSalesVouchersUpCVoucherCommission/")
    suspend fun getRetailerETopUpCVoucherCommission(
        @Body etopupCommissionRequest: EtopupCommissionRequest
    ): Response<EtopupCommissionResponce>


    @POST(ServiceURL.TopUpController + "/GetRetailerTopUpCVoucherCommission_V1/")
    suspend fun getRetailerTopupVoucherCommission(
        @Body topupCommissionRequestModel: TopupCommissionRequestModel
        //@Query("TillID") tillid: Int
    ): Response<TopUpCommissionResponce>

    @POST(ServiceURL.TopUpController + "/GetTopUpVoucherCommissionReport/")
    suspend fun getTVCommissionReportApi(
        @Query("TillID") tillid: Int
    ): Response<TVCommissionReportResponse>


    @POST(ServiceURL.TopUpController + "/GetSalesVoucherDetails")
    suspend fun pushOpenbarcodes(
        @Body openBarcodesRequestModel: OpenBarcodesRequestModel
    ): Response<OpenBarcodesResponceModel>

    //Call<CashoutCommissionInputModel> GetCashoutCommission(@Body CashoutCommissionInputModel inputparameter);
    @POST("GetCustomerTrackDetails")
    fun getCardValidation(@Body inputparameter: CardValidationModel?):
            Response<List<CardValidationModel>>
}