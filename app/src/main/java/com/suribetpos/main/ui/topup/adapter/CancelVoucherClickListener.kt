package com.suribetpos.main.ui.topup.adapter

import android.view.View
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.topup.model.VerifyCanceVocuherResponceModel
import java.text.FieldPosition

/**
 * Created by Velmurugan on 2/9/2021.
 */
interface CancelVoucherClickListener {
    fun verifyVoucherRecyclerClick(view: View, salesvouchers: List<VerifyCanceVocuherResponceModel>, position: Int)
}