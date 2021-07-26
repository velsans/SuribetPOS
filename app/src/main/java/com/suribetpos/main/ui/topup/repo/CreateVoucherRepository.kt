package com.suribetpos.main.ui.topup.repo

import com.suribetpos.main.data.api.RetrofitInstance
import com.suribetpos.main.data.model.etopup.CardValidationModel
import com.suribetpos.main.ui.topup.model.CreateVoucherModel
import com.suribetpos.main.ui.topup.model.CreateVoucherResponse
import retrofit2.Response

/**
 * Created by Velmurugan on 2/16/2021.
 */
class CreateVoucherRepository {
    suspend fun pushCreateVoucher(createVoucherModel: CreateVoucherModel): Response<CreateVoucherResponse> {
        return RetrofitInstance.api.pushCreateVoucher(createVoucherModel)
    }

    fun pushCardValidation(cardValidationModel: CardValidationModel): Response<List<CardValidationModel>> {
        return RetrofitInstance.card_api.getCardValidation(cardValidationModel)
    }
}