package com.suribetpos.main.ui.commission.model

import java.util.*

/**
 * Created by Velmurugan on 3/2/2021.
 */
data class TopUpCommissionResponce(
    var m_Item1: String?,
    var m_Item2: ArrayList<TopUpPaidCommissionDetails?>? = ArrayList(),
    var m_Item3: ArrayList<TopUpPaidCommissionValues?>? = ArrayList(),
)