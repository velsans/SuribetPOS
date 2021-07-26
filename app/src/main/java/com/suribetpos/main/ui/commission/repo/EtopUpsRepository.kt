package com.suribetpos.main.ui.commission.repo

import com.suribetpos.main.data.api.RetrofitInstance
import com.suribetpos.main.data.model.etopup.CommissionModel
import com.suribetpos.main.ui.commission.model.EtopupCommissionRequest
import com.suribetpos.main.ui.commission.model.EtopupCommissionResponce
import retrofit2.Response

/**
 * Created by Velmurugan on 2/27/2021.
 */
class EtopUpsRepository {
    suspend fun getRetailerETopUpCVoucherCommission(etopupCommissionRequest: EtopupCommissionRequest): Response<EtopupCommissionResponce> {
        return RetrofitInstance.api.getRetailerETopUpCVoucherCommission(etopupCommissionRequest)
    }
}
