package com.suribetpos.main.ui.denomination

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suribetpos.main.utils.Common

class DenominationBottomViewModel : ViewModel() {
    // Denomination Details
    private val _denomination = MutableLiveData<List<DenominationModel>>()

    val denomination: LiveData<List<DenominationModel>>
        get() = _denomination

    fun getDenomination() {
        // call repository function here
        _denomination.value = Common.ClientDenomination
    }
}