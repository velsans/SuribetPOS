package com.suribetpos.main.ui.topup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.main.ui.topup.repo.CancelRepository

/**
 * Created by Velmurugan on 2/17/2021.
 */
class CancelVoucherViewModelFactory(private val repository: CancelRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CancelVoucherViewModel(repository) as T
    }
}