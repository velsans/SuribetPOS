package com.suribetpos.main.ui.topup.repo

import com.suribetpos.main.data.api.RetrofitInstance
import com.suribetpos.main.ui.topup.model.CancelledVoucherResponce
import retrofit2.Response

/**
 * Created by Velmurugan on 2/15/2021.
 */
class CancelledRepository {

    suspend fun getCanceldVoucher(tillID:Int): Response<CancelledVoucherResponce> {
        return RetrofitInstance.api.getCancelledVoucher(tillID)
    }
}