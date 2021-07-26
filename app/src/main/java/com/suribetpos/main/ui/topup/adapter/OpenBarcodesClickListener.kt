package com.suribetpos.main.ui.topup.adapter

import android.view.View
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.topup.model.OpenBarcodesModel
import com.suribetpos.main.ui.topup.model.OpenBarcodesResponceModel
import com.suribetpos.main.ui.topup.model.VerifyCanceVocuherResponceModel
import java.text.FieldPosition

/**
 * Created by Velmurugan on 2/9/2021.
 */
interface OpenBarcodesClickListener {
    fun openBarcodesRecyclerClick(view: View, openBarCodes: List<OpenBarcodesModel>, position: Int)
}