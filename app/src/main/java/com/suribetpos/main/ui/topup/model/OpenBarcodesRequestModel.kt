package com.suribetpos.main.ui.topup.model

/**
 * Created by DEV 27 on 03/02/2017.
 */
data class OpenBarcodesRequestModel(
    var UserId: Int = 0,
    var TillId: Int = 0,
    var VoucherBarcode: String? = null,
)