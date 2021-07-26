package com.suribetpos.main.ui.topup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suribetpos.main.ui.topup.model.CancelledVoucherResponce
import com.suribetpos.main.ui.topup.repo.CancelledRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CancelledVoucherViewModel(private val repository: CancelledRepository) : ViewModel() {

    // Denomination Details
    private val _cancelledVoucher = MutableLiveData<Response<CancelledVoucherResponce>>()

    val cancelledVoucher: LiveData<Response<CancelledVoucherResponce>>
        get() = _cancelledVoucher

    fun getCancelledVoucher(tillID:Int) {
        viewModelScope.launch {
            val response = repository.getCanceldVoucher(tillID)
            // call repository function here
            _cancelledVoucher.value = response
        }
    }
}