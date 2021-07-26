package com.suribetpos.main.ui.playabletickets.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.model.Pt_Transactions

/**
 * Created by Velmurugan on 1/30/2021.
 */
internal class TransactionAdapter(private val transactionList: ArrayList<Pt_Transactions>):RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {


    internal inner class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.textView9)
        //var year: TextView = view.findViewById(R.id.year)
        //var genre: TextView = view.findViewById(R.id.genre)
    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pt_sales_transaction_row, parent, false)
        return TransactionHolder(itemView)
    }
    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val movie = transactionList[position]
        holder.title.text = movie.barcode
        //holder.genre.text = movie.barcode()
        //holder.year.text = movie.TransactionID()
    }
    override fun getItemCount(): Int {
        return transactionList.size
    }
}