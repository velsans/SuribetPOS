package com.suribetpos.main.ui.commission.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.suribetpos.main.data.model.etopup.CancelCommissionModel
import com.suribetpos.main.data.model.etopup.SaleCommissionModel
import com.suribetpos.main.data.model.etopup.TotalCommissionModel
import com.suribetpos.main.ui.commission.model.EtopupCommissionRequest
import com.suribetpos.main.ui.commission.model.EtopupCommissionResponce
import com.suribetpos.main.ui.commission.repo.EtopUpsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class EtopUpsViewModel(private val etopUpsRepository: EtopUpsRepository) : ViewModel() {
    // etopUp Commissions
    private val _etopupCommission = MutableLiveData<Response<EtopupCommissionResponce>>()

    val etopupCommission: LiveData<Response<EtopupCommissionResponce>>
        get() = _etopupCommission

    //Sales Commission list
    private val _salesCommissionlist = MutableLiveData<List<SaleCommissionModel>>()

    val salesCommission: LiveData<List<SaleCommissionModel>>
        get() = _salesCommissionlist


    //Sales Commission list
    private val _totalCommissionlist = MutableLiveData<List<TotalCommissionModel>>()

    val totalCommission: LiveData<List<TotalCommissionModel>>
        get() = _totalCommissionlist

    fun getRetailerETopUpVoucher(etopupCommissionRequest: EtopupCommissionRequest) {
        viewModelScope.launch {
            try {
                val response =
                    etopUpsRepository.getRetailerETopUpCVoucherCommission(etopupCommissionRequest)
                _etopupCommission.value = response
                if (response.isSuccessful) {
                    /* var _salesCommission =
                         response.body()?.SaleCommission as List<SaleCommissionModel>*/

                    _salesCommissionlist.value =
                        response.body()?.SaleCommission as List<SaleCommissionModel>

                    _totalCommissionlist.value =
                        response.body()?.TotalCommission as List<TotalCommissionModel>

                    /* val totalCommissionList =
                         response.body()?.TotalCommission as List<TotalCommissionModel>
                     val cancelCommissionList =
                         response.body()?.CancelCommission as List<CancelCommissionModel>*/
                }

            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }

    fun getFliteredList(fiterList: List<SaleCommissionModel>) {
        try {
            _salesCommissionlist.value = fiterList
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        }
    }
}