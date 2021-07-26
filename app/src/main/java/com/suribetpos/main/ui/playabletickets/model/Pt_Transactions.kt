package com.suribetpos.main.ui.playabletickets.model

/**
 * Created by Velmurugan on 1/30/2021.
 */
data class Pt_Transactions (var TransactionID:Int,var barcode:String,var TotalAmount:Double,var TransactionDetails:List<Pt_TransactionDetails>)