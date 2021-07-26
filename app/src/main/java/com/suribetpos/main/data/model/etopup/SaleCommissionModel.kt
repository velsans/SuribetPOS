package com.suribetpos.main.data.model.etopup

data class SaleCommissionModel(
    var VoucherID: Int = 0,
    var Barcode: String? = null,
    var Time: String? = null,
    var Denom: Double = 0.0
)