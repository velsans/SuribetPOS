package com.suribetpos.main.ui.topup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.OpenbarcodesitemBinding
import com.suribetpos.main.ui.topup.model.OpenBarcodesModel
import com.suribetpos.main.ui.topup.model.OpenBarcodesResponceModel

/**
 * Created by Velmurugan on 5/31/2021.
 */
class OpenBarcodesAdapter(
    private var openbarcodeList: List<OpenBarcodesModel>,
    private val onClickListener: OpenBarcodesClickListener
) :
    RecyclerView.Adapter<OpenBarcodesAdapter.MyViewHolder>() {
    inner class MyViewHolder(val openbarcodesitemBinding: OpenbarcodesitemBinding) :
        RecyclerView.ViewHolder(openbarcodesitemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.openbarcodesitem, parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.openbarcodesitemBinding.openbarcodeResponceModel =
            openbarcodeList[position]

        holder.openbarcodesitemBinding.openbarcodes.setOnClickListener {
            onClickListener.openBarcodesRecyclerClick(
                holder.openbarcodesitemBinding.openbarcodes, openbarcodeList, position
            )
        }
    }

    override fun getItemCount(): Int = openbarcodeList.size

}