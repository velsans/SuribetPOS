package com.suribetpos.main.ui.playabletickets.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.viewmodel.PtSalesGenrateViewModel
import kotlinx.android.synthetic.main.pt_sales_generation_fragment.*


class Pt_Sales_GenrateFragment : Fragment() {

    companion object {
        fun newInstance() = Pt_Sales_GenrateFragment()
    }

    private lateinit var viewModel: PtSalesGenrateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pt_sales_generation_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pt_genIMG.setOnClickListener { v ->
            //Toast.makeText(activity, "Bottom Sheet", Toast.LENGTH_SHORT).show();
            /*val addPhotoBottomDialogFragment: DenominationBottomFragment =
                DenominationBottomFragment.newInstance()
            activity?.let {
                addPhotoBottomDialogFragment.show(
                    it.getSupportFragmentManager(),
                    "_photo_dialog_fragment"
                )
            }*/
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PtSalesGenrateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}