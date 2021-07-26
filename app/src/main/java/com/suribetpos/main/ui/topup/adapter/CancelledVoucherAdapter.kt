package com.suribetpos.main.ui.topup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.CancelledVoucherItemsBinding
import com.suribetpos.main.ui.commission.adapter.SalesCommissionAdapter
import com.suribetpos.main.ui.topup.model.CancelledVoucherModel

/**
 * Created by Velmurugan on 2/15/2021.
 */
class CancelledVoucherAdapter(
    private var cancelledVoucher: List<CancelledVoucherModel>
) :
    RecyclerView.Adapter<CancelledVoucherAdapter.MyViewHolder>() {
    inner class MyViewHolder(val cancelledVoucherItemsBinding: CancelledVoucherItemsBinding) :
        RecyclerView.ViewHolder(cancelledVoucherItemsBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.cancelled_voucher_items, parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cancelledVoucherItemsBinding.cancelledVoucher = cancelledVoucher[position]
    }

    override fun getItemCount(): Int = cancelledVoucher.size

}