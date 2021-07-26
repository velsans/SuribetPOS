package com.suribetpos.main.ui.playabletickets.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.viewmodel.PtCommSoldViewModel
import kotlinx.android.synthetic.main.pt_comm_sold_fragment.*

class Pt_CommSoldFragment : Fragment() {

    companion object {
        fun newInstance() = Pt_CommSoldFragment()
    }

    private lateinit var viewModel: PtCommSoldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pt_comm_sold_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PtCommSoldViewModel::class.java)
        // TODO: Use the ViewModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        soldbottomdetails_IMG.setOnClickListener { v ->
            //Toast.makeText(activity, "Bottom Sheet", Toast.LENGTH_SHORT).show();
            val addPhotoBottomDialogFragment: Pt_CommSoldBottomFragment =
                Pt_CommSoldBottomFragment.newInstance()
            activity?.let {
                addPhotoBottomDialogFragment.show(
                    it.getSupportFragmentManager(),
                    "_photo_dialog_fragment"
                )
            }
        }
    }

}