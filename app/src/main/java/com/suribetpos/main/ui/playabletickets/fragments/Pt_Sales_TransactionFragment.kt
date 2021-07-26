package com.suribetpos.main.ui.playabletickets.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.fragments.adapter.TransactionAdapter
import com.suribetpos.main.ui.playabletickets.model.Pt_TransactionDetails
import com.suribetpos.main.ui.playabletickets.model.Pt_Transactions
import com.suribetpos.main.ui.playabletickets.viewmodel.PtSalesTransactionViewModel
import kotlinx.android.synthetic.main.pt_sales_transaction_fragment.*


class Pt_Sales_TransactionFragment : Fragment() {
    private val TAG = "MainActivity"
    var sectionList: ArrayList<Pt_Transactions> = ArrayList()

    companion object {
        fun newInstance() = Pt_Sales_TransactionFragment()
    }

    private lateinit var viewModel: PtSalesTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pt_sales_transaction_fragment, container, false)
        initData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PtSalesTransactionViewModel::class.java)
        // TODO: Use the ViewModel

        val mainRecyclerAdapter = TransactionAdapter(sectionList)
        PT_transactionRV.setAdapter(mainRecyclerAdapter)
        PT_transactionRV.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }

    private fun initData() {
        val sectionOneItems: MutableList<Pt_TransactionDetails> = ArrayList()
        for (i in 1..3) {
            sectionOneItems.add(Pt_TransactionDetails(1000.00, 10, 1))
            sectionOneItems.add(Pt_TransactionDetails(50.00, 10, 2))
            sectionOneItems.add(Pt_TransactionDetails(10.00, 10, 3))
            sectionList.add(Pt_Transactions(101, "123456789", 1200.00, sectionOneItems))
            Log.d(TAG, "initData: $sectionList")
        }
    }
}