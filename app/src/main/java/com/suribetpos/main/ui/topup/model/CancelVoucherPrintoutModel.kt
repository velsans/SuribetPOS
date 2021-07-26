package com.suribetpos.main.ui.topup.model

data class CancelVoucherPrintoutModel(
    var SalesVoucher: String? = null,
    var CurrencyCode: String? = null,
    var CurrencyAmount: String? = null,
    var LocationName: String? = null,
    var LocationAddress: String? = null,
    var TillCode: String? = null,
    var LocationPhone: String? = null,
    var UserName: String? = null,
    var PrintingDateTime: String? = null,
)