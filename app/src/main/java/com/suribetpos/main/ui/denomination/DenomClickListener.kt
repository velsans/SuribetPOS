package com.suribetpos.main.ui.denomination

import android.view.View
import com.suribetpos.main.ui.denomination.DenominationModel

/**
 * Created by Velmurugan on 2/9/2021.
 */
interface DenomClickListener {

    fun DenomRecyclerClick(view:View,clientdenom: DenominationModel)
}