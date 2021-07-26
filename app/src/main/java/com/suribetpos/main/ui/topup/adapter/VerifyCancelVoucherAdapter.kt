package com.suribetpos.main.ui.topup.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.CancelledVoucherItemsBinding
import com.suribetpos.databinding.VerifycanelvoucheritemBinding
import com.suribetpos.main.ui.topup.model.VerifyCanceVocuherResponceModel

/**
 * Created by Velmurugan on 2/15/2021.
 */
class VerifyCancelVoucherAdapter(
    private var verifyCanceVocuherResponcList: List<VerifyCanceVocuherResponceModel>,
    private val onClickListener: CancelVoucherClickListener
) :
    RecyclerView.Adapter<VerifyCancelVoucherAdapter.MyViewHolder>() {
    inner class MyViewHolder(val verifycanelvoucheritemBinding: VerifycanelvoucheritemBinding) :
        RecyclerView.ViewHolder(verifycanelvoucheritemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.verifycanelvoucheritem, parent, false
            )
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.verifycanelvoucheritemBinding.verifyCanceVocuherResponceModel =
            verifyCanceVocuherResponcList[position]

        holder.verifycanelvoucheritemBinding.cancelVerifybtn.setOnClickListener {
            onClickListener.verifyVoucherRecyclerClick(
                holder.verifycanelvoucheritemBinding.cancelVerifybtn, verifyCanceVocuherResponcList, position
            )
        }
    }

    override fun getItemCount(): Int = verifyCanceVocuherResponcList.size

}