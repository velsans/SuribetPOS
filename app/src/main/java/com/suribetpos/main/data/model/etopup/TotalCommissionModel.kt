package com.suribetpos.main.data.model.etopup

data class TotalCommissionModel (
    var SalesCommission:Double = 0.0,
    var TotalSold:Double = 0.0,
    var LocationSoldCommission:Double = 0.0,
    var LocationBalance:Double = 0.0,
    var CancelAmount:Double = 0.0,
    var CancelCount:Int = 0
    )