package com.suribetpos.main.ui.commission.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suribetpos.main.ui.commission.model.TVCommissionReportResponse
import com.suribetpos.main.ui.commission.model.TopUpCommissionResponce
import com.suribetpos.main.ui.commission.model.TopupCommissionRequestModel
import com.suribetpos.main.ui.commission.repo.TopUpCommissionRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class TopUpCommissionViewModel(private val topUpCommissionRepository: TopUpCommissionRepository) :
    ViewModel() {

    // etopUp Commissions
    private val _topupCommission = MutableLiveData<Response<TopUpCommissionResponce>>()

    val topupCommission: LiveData<Response<TopUpCommissionResponce>>
        get() = _topupCommission

    fun getRetailerTopupVoucherCommission(topupCommissionRequestModel: TopupCommissionRequestModel) {
        viewModelScope.launch {
            try {
                val response = topUpCommissionRepository.getRetailserTopupCommission(topupCommissionRequestModel)
                _topupCommission.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }

    // TV Commission Report
    private val _tvCommissionReport = MutableLiveData<Response<TVCommissionReportResponse>>()

    val tvCommissionReport: LiveData<Response<TVCommissionReportResponse>>
        get() = _tvCommissionReport

    fun getTVCommissionReport(tillid: Int) {
        viewModelScope.launch {
            try {
                val response = topUpCommissionRepository.getTVCommissionReportRepo(tillid)
                _tvCommissionReport.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }

}