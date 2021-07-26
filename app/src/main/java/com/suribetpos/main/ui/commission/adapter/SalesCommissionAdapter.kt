package com.suribetpos.main.ui.commission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.SalescommissionEtopupItemBinding
import com.suribetpos.main.data.model.etopup.SaleCommissionModel

/**
 * Created by Velmurugan on 2/15/2021.
 */
class SalesCommissionAdapter(
    private var saleCommissionList: List<SaleCommissionModel>
) :
    RecyclerView.Adapter<SalesCommissionAdapter.MyViewHolder>() {
    inner class MyViewHolder(val salescommissionEtopupItemBinding: SalescommissionEtopupItemBinding) :
        RecyclerView.ViewHolder(salescommissionEtopupItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.salescommission_etopup_item, parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.salescommissionEtopupItemBinding.saleCommissiondetails = saleCommissionList[position]
    }

    override fun getItemCount(): Int = saleCommissionList.size

}