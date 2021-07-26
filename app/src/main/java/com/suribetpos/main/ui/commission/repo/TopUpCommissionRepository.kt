package com.suribetpos.main.ui.commission.repo

import com.suribetpos.main.data.api.RetrofitInstance
import com.suribetpos.main.ui.commission.model.TVCommissionReportResponse
import com.suribetpos.main.ui.commission.model.TopUpCommissionResponce
import com.suribetpos.main.ui.commission.model.TopupCommissionRequestModel
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by Velmurugan on 3/2/2021.
 */
class TopUpCommissionRepository {
    suspend fun getRetailserTopupCommission(topupCommissionRequestModel: TopupCommissionRequestModel): Response<TopUpCommissionResponce> {
        return RetrofitInstance.api.getRetailerTopupVoucherCommission(topupCommissionRequestModel)
    }
    suspend fun getTVCommissionReportRepo(tillid: Int): Response<TVCommissionReportResponse> {
        return RetrofitInstance.api.getTVCommissionReportApi(tillid)
    }
}
