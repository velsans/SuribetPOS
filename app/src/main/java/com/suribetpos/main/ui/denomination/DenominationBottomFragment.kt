package com.suribetpos.main.ui.denomination

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.suribetpos.R
import com.suribetpos.main.ui.topup.repo.CreateVoucherRepository
import com.suribetpos.main.ui.topup.viewmodel.CreateVoucherViewModel
import com.suribetpos.main.ui.topup.viewmodel.CreateVoucherViewModelFactory
import com.suribetpos.main.utils.Common
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.denomination_bottom_fragment.*
import java.lang.ClassCastException


open class DenominationBottomFragment : BottomSheetDialogFragment(), DenomClickListener {

    lateinit var mOnInputSelected: OnInputSelected

    private lateinit var viewModel: CreateVoucherViewModel
    var salesDenomination = listOf<Double>()
    var _denomination =ArrayList<DenominationModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.denomination_bottom_fragment, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val createVoucherRepository = CreateVoucherRepository()
        val createVoucherViewModelFactory = CreateVoucherViewModelFactory(createVoucherRepository)
        viewModel = ViewModelProvider(
            this,
            createVoucherViewModelFactory
        ).get(CreateVoucherViewModel::class.java)
        viewModel.getDenomination()
        viewModel.denomination.observe(viewLifecycleOwner, { denomination ->
            _denomination = denomination as ArrayList<DenominationModel>
            salesDenomination = denomination.map { denomination ->
                denomination.Denomination.toDouble()
            }.distinct()
            denom_recyclerView.also {
                it.layoutManager = GridLayoutManager(requireContext(), 4)
                it.setHasFixedSize(true)
                it.adapter = DenominationAdapter(denomination, this)
                if (denomination.size > 0) {
                    noValueFound.visibility = View.GONE
                } else {
                    noValueFound.visibility = View.VISIBLE
                }
            }
        })
        denom_manual.setOnClickListener {
            if(denom_manual_EDT.text.isNotEmpty()) {
                val clientdenom = DenominationRequestModel(
                    Common.CurrencyCode,
                    Common.CurrencyID,
                    denom_manual_EDT.text.toString().toDouble(),
                    0
                )
                mOnInputSelected!!.sendInput(clientdenom)
            }else {
                activity?.let { it1 -> Toasty.error(it1, "please enter denomation") }
            }
            /* if (denom_manual_EDT.text.isNotEmpty()) {
                     if (salesDenomination.isEmpty()) {
                         val clientdenom = DenominationRequestModel(
                             Common.CurrencyCode,
                             Common.CurrencyID,
                             denom_manual_EDT.text.toString().toDouble(),
                             0
                         )
                         mOnInputSelected!!.sendInput(clientdenom)
                     } else {
                         if (salesDenomination.contains(denom_manual_EDT.text.toString().toDouble())) {
                             for (denom in _denomination) {
                                 if (denom.Denomination == denom_manual_EDT.text.toString()
                                         .toInt()
                                 ) {
                                     val clientdenom = DenominationRequestModel(
                                         Common.CurrencyCode,
                                         Common.CurrencyID,
                                         denom_manual_EDT.text.toString().toDouble(),
                                         denom.DenominationID
                                     )
                                     mOnInputSelected!!.sendInput(clientdenom)
                                 }
                             }
                         } else {
                             val clientdenom = DenominationRequestModel(
                                 Common.CurrencyCode,
                                 Common.CurrencyID,
                                 denom_manual_EDT.text.toString().toDouble(),
                                 0
                             )
                             mOnInputSelected!!.sendInput(clientdenom)
                         }
                     }
                 } else {
                     activity?.let { it1 -> Toasty.error(it1, "please enter denomation") }
                 }*/

        }
    }

    override fun DenomRecyclerClick(view: View, clientdenoms: DenominationModel) {
        val clientdenom = DenominationRequestModel(
            Common.CurrencyCode,
            Common.CurrencyID,
            clientdenoms.Denomination.toDouble(),
            0
        )
        mOnInputSelected!!.sendInput(clientdenom)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputSelected = targetFragment as OnInputSelected
        } catch (e: ClassCastException) {
            Log.e("onAttach", e.message.toString())

        }
    }

    interface OnInputSelected {
        fun sendInput(clientdenom: DenominationRequestModel)
    }

}