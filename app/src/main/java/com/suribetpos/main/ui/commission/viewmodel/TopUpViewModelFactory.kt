package com.suribetpos.main.ui.commission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.main.ui.commission.repo.TopUpCommissionRepository

/**
 * Created by Velmurugan on 3/2/2021.
 */
class TopUpViewModelFactory(private val topUpCommissionRepository: TopUpCommissionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopUpCommissionViewModel(topUpCommissionRepository) as T
    }

}