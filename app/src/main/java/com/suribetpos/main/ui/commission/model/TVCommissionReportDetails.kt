package com.suribetpos.main.ui.commission.model

/**
 * Created by DEV 27 on 20/02/2017.
 */
data class TVCommissionReportDetails(
    var TotalSlipCount: Int = 0,
    var TotalCancelCount: Int = 0,
    var AmountReceived: Double = 0.0,
    var CommissionPercentage: Double = 0.0,
    var Commission: Double = 0.0,
    var TotalCancelAmount: Double = 0.0,
    var Balance: Double = 0.0,
    var LocationCode: String? = null,
    var PrintDateTime: String? = null,
    var LocationAddress: String? = null,
    var LocationFullName: String? = null,
    var LocationDescription: String? = null,
    var LocationPhoneNumber: String? = null
)