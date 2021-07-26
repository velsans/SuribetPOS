package com.suribetpos.main.ui.commission.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.suribetpos.R
import com.suribetpos.main.ui.commission.adapter.TopUpCommissionAdapter
import com.suribetpos.main.ui.commission.model.TVCommissionReportDetails
import com.suribetpos.main.ui.commission.model.TopUpPaidCommissionDetails
import com.suribetpos.main.ui.commission.model.TopUpPaidCommissionValues
import com.suribetpos.main.ui.commission.model.TopupCommissionRequestModel
import com.suribetpos.main.ui.commission.repo.TopUpCommissionRepository
import com.suribetpos.main.ui.commission.viewmodel.TopUpCommissionViewModel
import com.suribetpos.main.ui.commission.viewmodel.TopUpViewModelFactory
import com.suribetpos.main.ui.view.NewHomeActivity
import com.suribetpos.main.utils.Common
import kotlinx.android.synthetic.main.top_up_commission_fragment.*
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class TopUpCommissionFragment : Fragment() {

    companion object {
        fun newInstance() = TopUpCommissionFragment()
        var df = DecimalFormat("###0.00")
        var collectedate: String = ""
    }

    private lateinit var viewModel: TopUpCommissionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.top_up_commission_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val topUpCommissionRepository = TopUpCommissionRepository()
        val topUpViewModelFactory = TopUpViewModelFactory(topUpCommissionRepository)
        viewModel =
            ViewModelProvider(this, topUpViewModelFactory).get(TopUpCommissionViewModel::class.java)

        if (NewHomeActivity.BalanceAmountList.size > 0) {
            collectedate = NewHomeActivity.BalanceAmountList[0].collectedDate
        }
        var topucommDetails = TopupCommissionRequestModel(Common.TillId, collectedate)


        viewModel.getRetailerTopupVoucherCommission(topucommDetails)
        viewModel.topupCommission.observe(viewLifecycleOwner) { topupCommission ->
            if (topupCommission.isSuccessful) {
                val responce = topupCommission.body()?.m_Item1 as String
                if (responce.isEmpty()) {
                    val totalCommissionList =
                        topupCommission.body()?.m_Item3 as List<TopUpPaidCommissionValues>
                    topup_recyclerView.also {
                        it.layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )
                        it.setHasFixedSize(true)
                        it.adapter = totalCommissionList?.let { it1 ->
                            TopUpCommissionAdapter(
                                it1,
                            )
                        }
                    }
                }
            }
            val saleCommissionList =
                topupCommission.body()?.m_Item2 as List<TopUpPaidCommissionDetails>
            if (saleCommissionList.isNotEmpty()) {
                tv_txtSoldTotal.text = saleCommissionList[0].TotalSold.toString()
                tv_salesComission.text =
                    ("Total sales commission (" + saleCommissionList[0].SalesCommission.toString() + "%)")
                tv_Comission.text = saleCommissionList[0].LocationSoldCommission.toString()
                txtBalReturn.text = saleCommissionList[0].LocationBalance.toString()
            } else {
                tv_txtSoldTotal.text = " "
                tv_salesComission.text = " "
                tv_Comission.text = " "
                txtBalReturn.text = " "
            }

        }
        viewModel.tvCommissionReport.observe(viewLifecycleOwner, { tvCommissionReport ->
            if (tvCommissionReport.isSuccessful) {
                val responce = tvCommissionReport.body()?.m_Item1 as String
                if (responce.isEmpty()) {
                    val tvCommissionReport =
                        tvCommissionReport.body()?.m_Item2 as List<TVCommissionReportDetails>

                }

            }

        })
        if (NewHomeActivity.BalanceAmountList.size > 0) {
            fromdateTodateTopup.setText(
                CommonMessage(R.string.From) + " " + DateFormat(NewHomeActivity.BalanceAmountList[0].collectedDate) + " " + CommonMessage(
                    R.string.To
                ) + " " + DateFormat(
                    NewHomeActivity.BalanceAmountList[0].gamingDate
                ) + "\n" + CommonMessage(R.string.collected_amount) + Common.CurrencyCode + " " + df.format(
                    NewHomeActivity.BalanceAmountList[0].lastCollectedAmt
                )
            )
        }

    }

    fun CommonMessage(Common_Msg: Int): String? {
        return requireActivity().resources.getString(Common_Msg)
    }

    fun DateFormat(givendate: String?): String? {
        var strDate = ""
        val givenformat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val Outputformatter = SimpleDateFormat("dd-MMMM-yyyy")
        val timeformat: DateFormat = SimpleDateFormat("HH:mm:ss")

        try {
            val date = givenformat.parse(givendate)
            strDate = Outputformatter.format(date)
            // strDate=strDate +" "+timeformat.format(date)
        } catch (ex: Exception) {
            Log.e("", "" + ex.toString())
        }
        return strDate
    }

}