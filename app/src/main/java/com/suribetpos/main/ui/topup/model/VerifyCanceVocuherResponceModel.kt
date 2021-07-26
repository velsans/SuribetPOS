package com.suribetpos.main.ui.topup.model

/**
 * Created by DEV 27 on 03/02/2017.
 */
data class VerifyCanceVocuherResponceModel(
    var Barcode: String? = null,
    var CurrencyCode: String? = null,
    var Amount: Double? = null,
    var CurrencyAmount: Double? = null,
    var VoucherID: Int = 0,
    var VoucherBarcode: String? = null,
    var ErrorMessage: String? = null,
    var MacAddress: String? = null,
    var UserId: Int = 0,
    var TillId: Int = 0
)