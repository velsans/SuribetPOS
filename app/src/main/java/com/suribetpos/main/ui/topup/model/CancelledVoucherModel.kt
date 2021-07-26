package com.suribetpos.main.ui.topup.model

data class CancelledVoucherModel(
    var Barcode: String? = null,
    var TransactionDateTime: String? = null,
    var FirstName: String? = null,
    var CurrencyCode: String? = null,
    var CurrencyAmount: String? = null,
    var Comments: String? = null,
    var VoucherID: String? = null,
    var TillID: String? = null
)