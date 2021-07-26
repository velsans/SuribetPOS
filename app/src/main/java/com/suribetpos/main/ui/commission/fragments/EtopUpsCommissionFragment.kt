package com.suribetpos.main.ui.commission.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.suribetpos.R
import com.suribetpos.main.data.model.etopup.SaleCommissionModel
import com.suribetpos.main.ui.commission.adapter.SalesCommissionAdapter
import com.suribetpos.main.ui.commission.model.EtopupCommissionRequest
import com.suribetpos.main.ui.commission.repo.EtopUpsRepository
import com.suribetpos.main.ui.commission.viewmodel.EtopUpsViewModel
import com.suribetpos.main.ui.commission.viewmodel.EtopUpsViewModelFactory
import com.suribetpos.main.ui.view.NewHomeActivity
import com.suribetpos.main.utils.Common
import kotlinx.android.synthetic.main.etop_ups_commission_fragment.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class EtopUpsCommissionFragment : Fragment() {

    companion object {
        fun newInstance() = EtopUpsCommissionFragment()
    }

    private lateinit var viewModel: EtopUpsViewModel
    var saleCommissionlist = listOf<SaleCommissionModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.etop_ups_commission_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val etopUpsRepository = EtopUpsRepository()
        val etopUpsViewModelFactory = EtopUpsViewModelFactory(etopUpsRepository)
        viewModel =
            ViewModelProvider(this, etopUpsViewModelFactory).get(EtopUpsViewModel::class.java)
        fromdateTodate.text =
            CommonMessage(R.string.From) + " " + dateFormat(NewHomeActivity.BalanceAmountList[0].collectedDate) + " " + CommonMessage(
                R.string.To
            ) + " " + dateFormat(
                NewHomeActivity.BalanceAmountList[0].gamingDate
            )
        val etopupRequetpojo = EtopupCommissionRequest(
            Common.TillId,
            Common.ClientId,
            Common.UserId,
            Common.MobileMacAddress,
            NewHomeActivity.BalanceAmountList[0].collectedDate
        )
        viewModel.getRetailerETopUpVoucher(etopupRequetpojo)
        viewModel.salesCommission.observe(viewLifecycleOwner, { salesCommission ->
            if (salesCommission.isNotEmpty()) {
                saleCommissionlist = salesCommission
                etopup_recyclerView2.also {
                    it.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                    it.setHasFixedSize(true)
                    it.adapter = salesCommission?.let { it1 ->
                        SalesCommissionAdapter(
                            it1,
                        )
                    }
                }
            }
        })

        viewModel.totalCommission.observe(viewLifecycleOwner, { totalCommission ->
            if (totalCommission.isNotEmpty()) {
                etv_txtSoldTotal.text = totalCommission.get(0).TotalSold.toString()
                etv_salesComission.text = CommonMessage(R.string.Total_sales_Commission).plus(
                    " (" + totalCommission.get(0).SalesCommission.toString() + "%)"
                )
                etv_txtSalesComission.text =
                    (totalCommission.get(0).LocationSoldCommission.toString())
                etv_txtBalReturn.setText(totalCommission.get(0).LocationBalance.toString())

            } else {
                etv_txtSoldTotal.text = ""
                etv_salesComission.text = CommonMessage(R.string.Total_sales_Commission)
                etv_txtSalesComission.text = ""
                etv_txtBalReturn.text = ""
            }
        })

        /* viewModel.etopupCommission.observe(viewLifecycleOwner, { etopupCommission ->
             if (etopupCommission.isSuccessful) {
                 val saleCommissionList =
                     etopupCommission.body()?.SaleCommission as List<SaleCommissionModel>
                 val totalCommissionList =
                     etopupCommission.body()?.TotalCommission as List<TotalCommissionModel>
                 val cancelCommissionList =
                     etopupCommission.body()?.CancelCommission as List<CancelCommissionModel>

                 if (saleCommissionList.isNotEmpty()) {
                     cancelled_recyclerView.also {
                         it.layoutManager =
                             LinearLayoutManager(
                                 requireContext(),
                                 LinearLayoutManager.VERTICAL,
                                 false
                             )
                         it.setHasFixedSize(true)
                         it.adapter = etopupCommission?.let { it1 ->
                             SalesCommissionAdapter(
                                 it1 as List<SaleCommissionModel>,
                             )
                         }
                     }
                 }
                 if (totalCommissionList.isNotEmpty()) {
                     txtSoldTotal.text = totalCommissionList.get(0).TotalSold.toString()
                     tvSalesComission.text = CommonMessage(R.string.Total_sales_Commission).plus(
                         " (" + totalCommissionList.get(0).SalesCommission.toString() + "%)"
                     )
                     txtSalesComission.text =
                         (totalCommissionList.get(0).LocationSoldCommission.toString())
                     txtBalReturn.setText(totalCommissionList.get(0).LocationBalance.toString())
                 } else {
                     txtSoldTotal.text = ""
                     tvSalesComission.text = CommonMessage(R.string.Total_sales_Commission)
                     txtSalesComission.text = ""
                     txtBalReturn.text = ""
                 }
             }

         })*/
    }

    fun CommonMessage(Common_Msg: Int): String? {
        return requireActivity().resources.getString(Common_Msg)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        date_searchView.isSubmitButtonEnabled
        date_searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                /*  Toast.makeText(requireContext(), "No Match found--" + query, Toast.LENGTH_LONG)
                      .show()
                  if (query.isNotEmpty()) {
                      getFilteredList(saleCommission, query)
                  }*/
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isNotEmpty()) {
                    getFilteredList(newText)
                }
                return false
            }
        })
        // SearchView Close button
        val searchCloseButtonId: Int = date_searchView.getContext().getResources()
            .getIdentifier("android:id/search_close_btn", null, null)
        val closeButton: ImageView =
            this.date_searchView.findViewById(searchCloseButtonId) as ImageView
        closeButton.setOnClickListener {
            date_searchView.setQuery("", false)
            // Manage this event.
            val etopupRequetpojo = EtopupCommissionRequest(
                Common.TillId,
                Common.ClientId,
                Common.UserId,
                Common.MobileMacAddress,
                NewHomeActivity.BalanceAmountList[0].collectedDate
            )
            viewModel.getRetailerETopUpVoucher(etopupRequetpojo)
        }
    }

    fun getFilteredList(filteredString: String?) {
        if (filteredString != null) {
            if (saleCommissionlist.isNotEmpty()) {
                viewModel.getFliteredList(saleCommissionlist.filter { saleCommissionlist ->
                    saleCommissionlist.Time?.startsWith(filteredString) == true
                })
            }
        }

    }

    fun dateFormat(givendate: String?): String? {
        var strDate = ""
        //2019-06-11T00:00:00
        val givenformat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val outputformatter = SimpleDateFormat("dd-MMMM-yyyy")
        try {
            val date = givenformat.parse(givendate)
            strDate = outputformatter.format(date)
        } catch (ex: Exception) {
            Log.e("", "" + ex.toString())
        }
        return strDate
    }

}