package com.suribetpos.main.ui.denomination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.DenominationBottomItemBinding

/**
 * Created by Velmurugan on 2/8/2021.
 */
class DenominationAdapter(
    private val denoms: List<DenominationModel>,
    private val clickListener: DenomClickListener
) :
    RecyclerView.Adapter<DenominationAdapter.DenominationViewHolder>() {


    class DenominationViewHolder(val denominationBottomItemBinding: DenominationBottomItemBinding) :
        RecyclerView.ViewHolder(denominationBottomItemBinding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DenominationViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.denomination_bottom_item,
            parent, false
        )
    )


    override fun onBindViewHolder(holder: DenominationViewHolder, position: Int) {
        holder.denominationBottomItemBinding.clientDenom = denoms[position]
        holder.denominationBottomItemBinding.root.setOnClickListener {
            clickListener.DenomRecyclerClick(
                holder.denominationBottomItemBinding.root,
                denoms[position]
            )
        }
    }

    override fun getItemCount() = denoms.size

}