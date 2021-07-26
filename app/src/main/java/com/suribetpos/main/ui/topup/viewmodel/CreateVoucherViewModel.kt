package com.suribetpos.main.ui.topup.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suribetpos.main.data.model.etopup.CardValidationModel
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.denomination.DenominationModel
import com.suribetpos.main.ui.topup.model.CreateVoucherModel
import com.suribetpos.main.ui.topup.model.CreateVoucherResponse
import com.suribetpos.main.ui.topup.repo.CreateVoucherRepository
import com.suribetpos.main.utils.Common
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class CreateVoucherViewModel(private val createVoucherRepository: CreateVoucherRepository) :
    ViewModel() {
    // Denomination Details
    private val _denomination = MutableLiveData<List<DenominationModel>>()

    // Create Voucher
    private val _vouchers = MutableLiveData<List<Salesvouchers>>()


    val denomination: LiveData<List<DenominationModel>>
        get() = _denomination

    fun getDenomination() {
        // call repository function here
        _denomination.value = Common.ClientDenomination
    }


    val salesvouchers: LiveData<List<Salesvouchers>>
        get() = _vouchers

    fun addSalesVouchers(salesVoucher_Frag: ArrayList<Salesvouchers>) {
        _vouchers.value = salesVoucher_Frag
    }

    //Pay Voucher
    private val _createVoucher = MutableLiveData<Response<CreateVoucherResponse>>()

    val createVoucher: LiveData<Response<CreateVoucherResponse>>
        get() = _createVoucher

    fun pushCreateVoucher(createVoucherModel: CreateVoucherModel) {
        viewModelScope.launch {
            try {
                val response = createVoucherRepository.pushCreateVoucher(createVoucherModel)
                _createVoucher.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }
    //card validation
    private val _cardavalidation = MutableLiveData<Response<List<CardValidationModel>>>()

    val cardavalidation: LiveData<Response<List<CardValidationModel>>>
        get() = _cardavalidation

    fun pushCardValidation(cardValidationModel: CardValidationModel) {
        viewModelScope.launch {
            try {
                val response = createVoucherRepository.pushCardValidation(cardValidationModel)
                _cardavalidation.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }
}