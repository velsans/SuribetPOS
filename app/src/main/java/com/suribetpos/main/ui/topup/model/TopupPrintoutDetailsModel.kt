package com.suribetpos.main.ui.topup.model

data class TopupPrintoutDetailsModel(
    var Barcode: String? = null,
    var CurrencyAmount: String? = null,
    var CurrencyCode: String? = null,
    var CurrencyWithCode: String? = null,
    var CustomVoucherSalesMessage: String? = null,
    var CustomerCare: String? = null,
    var LocationAddress: String? = null,
    var LocationName: String? = null,
    var LocationPhone: String? = null,
    var PIN: String? = null,
    var PrintingDateTime: String? = null,
    var SiteUrl: String? = null,
    var TillCode: String? = null,
    var UserName: String? = null,
    var ValidTill: String? = null,
    var VoucherId: String? = null,
)