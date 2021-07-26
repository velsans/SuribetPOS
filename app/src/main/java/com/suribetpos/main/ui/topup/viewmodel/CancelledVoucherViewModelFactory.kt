package com.suribetpos.main.ui.topup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.main.ui.topup.repo.CancelledRepository

/**
 * Created by Velmurugan on 2/15/2021.
 */
class CancelledVoucherViewModelFactory(private val repository: CancelledRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CancelledVoucherViewModel(repository) as T
    }

}