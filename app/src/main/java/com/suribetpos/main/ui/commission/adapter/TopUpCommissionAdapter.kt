package com.suribetpos.main.ui.commission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.SalescommissionTopupItemBinding
import com.suribetpos.main.ui.commission.model.TopUpPaidCommissionValues

/**
 * Created by Velmurugan on 3/2/2021.
 */
class TopUpCommissionAdapter(private var tvpaidCommissionList: List<TopUpPaidCommissionValues>) :
    RecyclerView.Adapter<TopUpCommissionAdapter.TopupCommissionViewHolder>() {
    inner class TopupCommissionViewHolder(val salescommissionTopupItemBinding: SalescommissionTopupItemBinding) :
        RecyclerView.ViewHolder(salescommissionTopupItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopupCommissionViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.salescommission_topup_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: TopupCommissionViewHolder, position: Int) {
        holder.salescommissionTopupItemBinding.tvCommissiondetails = tvpaidCommissionList[position]
    }

    override fun getItemCount(): Int = tvpaidCommissionList.size
}