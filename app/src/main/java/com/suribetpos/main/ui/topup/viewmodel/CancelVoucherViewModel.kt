 package com.suribetpos.main.ui.topup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suribetpos.main.ui.topup.model.*
import com.suribetpos.main.ui.topup.repo.CancelRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class CancelVoucherViewModel(private val repository: CancelRepository) : ViewModel() {

    // Denomination Details
    private val _cancelledVoucher = MutableLiveData<Response<CancelResponseModel>>()

    val cancelVoucher: LiveData<Response<CancelResponseModel>>
        get() = _cancelledVoucher

    fun getCancelVoucher(cancelRequestModel: CancelRequestModel) {
        viewModelScope.launch {
            val response = repository.getCancelVoucher(cancelRequestModel)
            // call repository function here
            _cancelledVoucher.value = response
        }
    }

    // Verify Cancel Voucher
    private val _verifycancelVoucher = MutableLiveData<Response<VerifyCanceVocuherResponceModel>>()

    val verifycancelVoucher: LiveData<Response<VerifyCanceVocuherResponceModel>>
        get() = _verifycancelVoucher

    fun getVerifyVoucher(verifyCancelVoucherRequestModel: VerifyCancelVoucherRequestModel) {
        viewModelScope.launch {
            try {
                val response = repository.verifyCancelVocuher(verifyCancelVoucherRequestModel)
                // call repository function here
                _verifycancelVoucher.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }


        }
    }

    // open barcode
    private val _openbarcode = MutableLiveData<Response<OpenBarcodesResponceModel>>()

    val openbarcode: LiveData<Response<OpenBarcodesResponceModel>>
        get() = _openbarcode

    fun getOpenBarcodes(openBarcodesRequestModel: OpenBarcodesRequestModel) {
        viewModelScope.launch {
            try {
                val response = repository.openbarcodes(openBarcodesRequestModel)
                // call repository function here
                _openbarcode.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }



}