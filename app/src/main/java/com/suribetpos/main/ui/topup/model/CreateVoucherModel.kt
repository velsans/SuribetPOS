package com.suribetpos.main.ui.topup.model

import com.suribetpos.main.data.model.topup.Salesvouchers

data class CreateVoucherModel (
    private val UserId:Int = 0,
    private val TillId :Int= 0,
    private val SalesVouchers: List<Salesvouchers>? = null,
    private val TotalPaid: Double? = null,
    private val TotalReturn: Double? = null,
    private val ErrorMessage: String? = null,
    private val MacAddress: String? = null,
    private val CustomerCardAccountID:String?=null
)