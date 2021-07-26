package com.suribetpos.main.data.model.etopup

data class CancelCommissionModel(
    var VoucherID: Int = 0,
    var Barcode: String? = null,
    var Denom: String? = null,
    var Time: String? = null
)