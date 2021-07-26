package com.suribetpos.main.ui.playabletickets.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.suribetpos.R

class Pt_CommSoldBottomFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance() = Pt_CommSoldBottomFragment()
    }

    private lateinit var viewModel: PtCommSoldBottomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pt__comm_sold_bottom_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PtCommSoldBottomViewModel::class.java)
        // TODO: Use the ViewModel
    }


}