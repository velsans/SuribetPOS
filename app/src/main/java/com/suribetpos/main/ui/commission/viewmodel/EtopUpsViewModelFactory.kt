package com.suribetpos.main.ui.commission.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.main.ui.commission.repo.EtopUpsRepository

/**
 * Created by Velmurugan on 2/27/2021.
 */
class EtopUpsViewModelFactory (private val etopUpsRepository: EtopUpsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EtopUpsViewModel(etopUpsRepository) as T
    }
}