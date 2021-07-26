package com.suribetpos.main.ui.denomination

data class DenominationRequestModel(
    var CurrencyCode: String,
    val CurrencyID: Int,
    val Denomination: Double,
    val DenominationID: Int
)