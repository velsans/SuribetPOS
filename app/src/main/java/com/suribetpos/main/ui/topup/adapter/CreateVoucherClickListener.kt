package com.suribetpos.main.ui.topup.adapter

import android.view.View
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.topup.model.VerifyCanceVocuherResponceModel
import java.text.FieldPosition

/**
 * Created by Velmurugan on 2/9/2021.
 */
interface CreateVoucherClickListener {

    fun CreateVoucherRecyclerClick(view: View, salesvouchers: ArrayList<Salesvouchers>, position: Int)
    fun TotalCountAmount(view: View,salesvouchers: ArrayList<Salesvouchers>)
    fun errorMessage(view: View,message:String)
    //fun OnTouchEditText(view: View, focus: Boolean,position:Int)
}