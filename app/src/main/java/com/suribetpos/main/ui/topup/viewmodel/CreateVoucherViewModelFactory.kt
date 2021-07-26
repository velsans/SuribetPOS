package com.suribetpos.main.ui.topup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.main.ui.topup.repo.CreateVoucherRepository

/**
 * Created by Velmurugan on 2/16/2021.
 */
class CreateVoucherViewModelFactory(private val createVoucherRepository: CreateVoucherRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateVoucherViewModel(createVoucherRepository) as T
    }
}