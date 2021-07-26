package com.suribetpos.main.ui.commission.model

/**
 * Created by Velmurugan on 2/27/2021.
 */
data class EtopupCommissionRequest(
    var TillId: Int = 0, var ClientId: Int = 0, var UserId: Int = 0,
    var MacAddress: String? = null, var CollectedDate: String? = null
)
