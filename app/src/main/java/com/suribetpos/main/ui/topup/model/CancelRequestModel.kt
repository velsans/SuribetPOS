package com.suribetpos.main.ui.topup.model

/**
 * Created by Velmurugan on 2/17/2021.
 */
data class CancelRequestModel(
    var TillId: Int = 0,
    var UserId: Int = 0,
    var MacAddress: String? = null,
    var ErrorMessage: String? = null,
    var VoucherBarcode: String? = null,
    var CancelSaleVoucher: ArrayList<CancelSaleVoucher>,
)
