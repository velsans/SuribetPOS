package com.suribetpos.main.ui.commission.model

/**
 * Created by DEV 27 on 16/02/2017.
 */
data class TopUpPaidCommissionDetails (
    var SalesCommission:Double = 0.0,
    var TotalSold :Double = 0.0,
    var LocationSoldCommission :Double = 0.0,
    var LocationBalance :Double = 0.0,
    var CancelAmount :Double = 0.0,
    var CancelCount:Double = 0.0
)