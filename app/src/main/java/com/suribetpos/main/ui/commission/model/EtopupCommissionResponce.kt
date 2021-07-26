package com.suribetpos.main.ui.commission.model

import com.suribetpos.main.data.model.etopup.CancelCommissionModel
import com.suribetpos.main.data.model.etopup.SaleCommissionModel
import com.suribetpos.main.data.model.etopup.TotalCommissionModel
import java.util.*

/**
 * Created by Velmurugan on 2/27/2021.
 */
data class EtopupCommissionResponce(
    var SaleCommission: ArrayList<SaleCommissionModel?>? = ArrayList(),
    var TotalCommission: ArrayList<TotalCommissionModel?>? = ArrayList(),
    var CancelCommission: ArrayList<CancelCommissionModel?>? = ArrayList()
)
