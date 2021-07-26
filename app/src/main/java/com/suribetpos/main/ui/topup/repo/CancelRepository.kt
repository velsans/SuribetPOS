package com.suribetpos.main.ui.topup.repo

import com.suribetpos.main.data.api.RetrofitInstance
import com.suribetpos.main.ui.topup.model.*
import retrofit2.Response

/**
 * Created by Velmurugan on 2/17/2021.
 */
class CancelRepository {
    suspend fun getCancelVoucher(cancelRequestModel: CancelRequestModel): Response<CancelResponseModel> {
        return RetrofitInstance.api.pushCancelVoucher(cancelRequestModel)
    }
    suspend fun verifyCancelVocuher(verifyCancelVoucherRequestModel: VerifyCancelVoucherRequestModel):Response<VerifyCanceVocuherResponceModel>{
        return RetrofitInstance.api.pushVerifyVoucher(verifyCancelVoucherRequestModel)
    }

    suspend fun openbarcodes(openBarcodesRequestModel: OpenBarcodesRequestModel):Response<OpenBarcodesResponceModel>{
        return RetrofitInstance.api.pushOpenbarcodes(openBarcodesRequestModel)
    }

}