package com.suribetpos.main.ui.topup.fragments

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.suribetpos.R
import com.suribetpos.main.data.model.common.ProductName_User
import com.suribetpos.main.ui.topup.adapter.CancelledVoucherAdapter
import com.suribetpos.main.ui.topup.model.CancelledVoucherModel
import com.suribetpos.main.ui.topup.repo.CancelledRepository
import com.suribetpos.main.ui.topup.viewmodel.CancelledVoucherViewModel
import com.suribetpos.main.ui.topup.viewmodel.CancelledVoucherViewModelFactory
import com.suribetpos.main.ui.view.NewHomeActivity
import com.suribetpos.main.utils.BarcodeGeneration
import com.suribetpos.main.utils.Common
import kotlinx.android.synthetic.main.cancelled_voucher_fragment.*
import kotlinx.android.synthetic.main.create_voucher_fragment.*


class CancelledVoucherFragment : Fragment() {
    private var saveClickCounter = 0
    var cancelledTotal = listOf<Double>()

    companion object {
        private lateinit var viewModel: CancelledVoucherViewModel
        private lateinit var factory: CancelledVoucherViewModelFactory

        fun newInstance() = CancelledVoucherFragment()
        fun cancelledList() {
            try {
                viewModel.getCancelledVoucher(Common.TillId)
            }catch (e: Exception){

            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cancelled_voucher_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Intialize viewmodel in fragment
        val repository = CancelledRepository()
        factory = CancelledVoucherViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CancelledVoucherViewModel::class.java)
        progressBar_cancelled.visibility = View.VISIBLE
        cancelledList()
        viewModel.cancelledVoucher.observe(viewLifecycleOwner, { cancelledVoucher ->
            progressBar_cancelled.visibility = View.GONE
            if (cancelledVoucher.isSuccessful) {
                val cancelledVoucherStatus = cancelledVoucher.body()?.m_Item1 as String
                if (cancelledVoucherStatus.isNullOrEmpty()) {
                    val cancelledVoucherList =
                        cancelledVoucher.body()?.m_Item2 as ArrayList<CancelledVoucherModel>
                    if (cancelledVoucherList.isNotEmpty()) {
                        cancelled_recyclerView.also {
                            it.layoutManager =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            it.setHasFixedSize(true)
                            it.adapter = cancelledVoucherList?.let { it1 ->
                                CancelledVoucherAdapter(
                                    it1 as List<CancelledVoucherModel>,
                                )
                            }
                        }

                        novalue_cancelledvoucher.visibility = View.GONE
                        cancelCountTXT.text = cancelledVoucherList.size.toString()
                        cancelledTotal = cancelledVoucherList.map { cancelledVoucherList ->
                            cancelledVoucherList.CurrencyAmount?.toDouble()!!
                        }
                        val sum = cancelledTotal.sum()
                        cancelAmountTXT.text = sum.toString()
                    } else {
                        novalue_cancelledvoucher.visibility = View.VISIBLE
                        cancelCountTXT.text = ""
                        cancelAmountTXT.text = ""
                    }
                } else {
                    Toast.makeText(requireContext(), cancelledVoucherStatus, Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Log.e("Responce-else", cancelledVoucher.errorBody().toString())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*TopUp Voucher Cancel Hide and Show if User Having Permission*/
        canceltopup_IMG.visibility = View.GONE
        for (l in 0 until Common.listProductDetails.size) {
            val product: ProductName_User = Common.listProductDetails.get(l)
            if (product.ProductName == Common.defaultPOSProFlag[0].toString()) {
                canceltopup_IMG.visibility = View.VISIBLE
            }
        }

        canceltopup_IMG.setOnClickListener { v ->
            if (saveClickCounter++ == 0) {
                val dialog = CancelVoucherFragment()
                dialog.setTargetFragment(this, 1)
                fragmentManager?.let { dialog.show(it, "MyCustomDialog") }
                Handler().postDelayed({
                    saveClickCounter = 0
                }, 1000)
            }

        }
        generateBarCode()
    }

    private fun generateBarCode() {
        var bitmap: Bitmap? = null
        val iv = ImageView(activity)

        try {
            bitmap = BarcodeGeneration.encodeAsBitmap(
                requireContext(),
                "116TV4710382351",
                BarcodeFormat.CODE_128,
                300,
                100
            )
            iv.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}