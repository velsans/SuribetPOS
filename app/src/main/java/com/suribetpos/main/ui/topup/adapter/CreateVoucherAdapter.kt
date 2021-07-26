package com.suribetpos.main.ui.topup.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.databinding.CreateVoucherItemsBinding
import com.suribetpos.main.data.model.topup.Salesvouchers
import com.suribetpos.main.ui.topup.fragments.CreateVoucherFragment
import com.suribetpos.main.utils.Common
import es.dmoral.toasty.Toasty


/**
 * Created by Velmurugan on 2/9/2021.
 */
class CreateVoucherAdapter(
    private var salesVoucher: ArrayList<Salesvouchers>,
    private val onClickListener: CreateVoucherClickListener
) :
    RecyclerView.Adapter<CreateVoucherAdapter.CreateVoucherViewHolder>() {
    inner class CreateVoucherViewHolder(val createVoucherItemsBinding: CreateVoucherItemsBinding) :
        RecyclerView.ViewHolder(createVoucherItemsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CreateVoucherViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.create_voucher_items,
                parent, false
            )
        )


    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(
        holder: CreateVoucherViewHolder,
        position: Int
    ) {
        holder.createVoucherItemsBinding.salesVoucher = salesVoucher[position]
        val salesItem = salesVoucher[position]
        /*holder.createVoucherItemsBinding.root.setOnClickListener {
            onClickListener.CreateVoucherRecyclerClick(
                holder.createVoucherItemsBinding.root, salesVoucher[position]
            )
        }*/
        holder.createVoucherItemsBinding.currencyCodeTXT.text = Common.CurrencyCode
        holder.createVoucherItemsBinding.removeVoucher.setOnClickListener {
            onClickListener.CreateVoucherRecyclerClick(
                holder.createVoucherItemsBinding.removeVoucher, salesVoucher, position
            )
        }
        holder.createVoucherItemsBinding.addQuantityIMG.setOnClickListener {
            var quantityCount = CreateVoucherFragment.salesDenomVouchers.sum()
            if (quantityCount > CreateVoucherFragment.printlimit) {
                onClickListener.errorMessage(
                    holder.createVoucherItemsBinding.addQuantityIMG,
                    "Recharge voucher limit is ${CreateVoucherFragment.printlimit}"
                )
            } else {
                var quantity = holder.createVoucherItemsBinding.quantityEDT.text.toString()
                if (quantity.isEmpty() || quantity.isBlank()) {
                    quantity = "0"
                }
                val plusquantity = quantity.toInt() + 1
               // val total: String = String.format("%d", (plusquantity * salesVoucher[position].Denomination.toInt())    )
                val total: String =   CreateVoucherFragment.df.format(plusquantity * salesVoucher[position].Denomination.toDouble()).toString()
                salesVoucher[position].Quantity = plusquantity
                salesVoucher[position].Total = total

                holder.createVoucherItemsBinding.quantityEDT.setText(plusquantity.toString())
                holder.createVoucherItemsBinding.totalDenomination.text = total
                onClickListener.TotalCountAmount(
                    holder.createVoucherItemsBinding.addQuantityIMG,
                    salesVoucher
                )
            }
        }
        holder.createVoucherItemsBinding.minusQuantityIMG.setOnClickListener {
            var quantity = holder.createVoucherItemsBinding.quantityEDT.text.toString()
            if (quantity.isEmpty() || quantity.isBlank()) {
                quantity = "0"
            }
            var minusquantity: Int = quantity.toInt() - 1
            if (minusquantity < 0) {
                minusquantity = 0
            }
            //val total: String = String.format("%d", (minusquantity * salesVoucher[position].Denomination.toInt()))
            val total: String =  CreateVoucherFragment.df.format(minusquantity * salesVoucher[position].Denomination.toDouble()).toString()
            salesVoucher[position].Quantity = minusquantity
            salesVoucher[position].Total = total
            holder.createVoucherItemsBinding.quantityEDT.setText(minusquantity.toString())
            holder.createVoucherItemsBinding.totalDenomination.text = total
            onClickListener.TotalCountAmount(
                holder.createVoucherItemsBinding.minusQuantityIMG,
                salesVoucher
            )
        }

        holder.createVoucherItemsBinding.quantityEDT.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {


            }

            override fun afterTextChanged(editable: Editable) {
                var quantity = editable.toString()
                if (quantity.isEmpty() || quantity.isBlank()) {
                    quantity = "0"
                }

                //val total: String = String.format("%d", (quantity.toInt() * salesVoucher[position].Denomination.toInt()))
                val total: String =
                    CreateVoucherFragment.df.format(quantity.toInt() * salesVoucher[position].Denomination.toDouble()).toString()
                salesVoucher[position].Quantity = quantity.toInt()
                salesVoucher[position].Total = total

                holder.createVoucherItemsBinding.totalDenomination.text = total
                onClickListener.TotalCountAmount(
                    holder.createVoucherItemsBinding.quantityEDT,
                    salesVoucher
                )
                CreateVoucherFragment.salesDenomVouchers =
                    salesVoucher.map { salesvouchers ->
                        salesvouchers.Quantity
                    } as ArrayList<Int>
                var quantityCount = CreateVoucherFragment.salesDenomVouchers.sum()
                if (quantityCount > CreateVoucherFragment.printlimit) {
                    salesVoucher[position].Quantity = 0
                    salesVoucher[position].Total = "0"

                    holder.createVoucherItemsBinding.totalDenomination.text = total
                    onClickListener.TotalCountAmount(
                        holder.createVoucherItemsBinding.quantityEDT,
                        salesVoucher
                    )
                    onClickListener.errorMessage(
                        holder.createVoucherItemsBinding.addQuantityIMG,
                        "Recharge voucher limit is ${CreateVoucherFragment.printlimit}"
                    )
                    try {
                        holder.createVoucherItemsBinding.quantityEDT.setText("0")
                    } catch (ex: Exception) {
                        Log.e("Exception", ex.toString())
                    }

                }

            }
        })
        onClickListener.TotalCountAmount(holder.createVoucherItemsBinding.root, salesVoucher)
    }

    override fun getItemCount(): Int = salesVoucher.size

    companion object {
        lateinit var salesVoucher: ArrayList<Salesvouchers>
    }

    init {
        this.salesVoucher = salesVoucher
    }
}