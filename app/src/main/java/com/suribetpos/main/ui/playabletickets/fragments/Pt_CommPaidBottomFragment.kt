package com.suribetpos.main.ui.playabletickets.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.viewmodel.PtCommPaidBottomViewModel

class Pt_CommPaidBottomFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = Pt_CommPaidBottomFragment()
    }

    private lateinit var viewModel: PtCommPaidBottomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pt__comm_paid_bottom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PtCommPaidBottomViewModel::class.java)
        // TODO: Use the ViewModel
    }

}