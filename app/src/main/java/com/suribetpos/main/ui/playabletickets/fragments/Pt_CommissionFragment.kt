package com.suribetpos.main.ui.playabletickets.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.viewmodel.PtCommissionViewModel
import com.suribetpos.main.ui.playabletickets.viewmodel.PtSalesViewModel

class Pt_CommissionFragment : Fragment() {

    companion object {
        fun newInstance() = Pt_CommissionFragment()
    }

    private lateinit var viewModel: PtCommissionViewModel
    private val titles = arrayOf("SOLD", "PAID")

    var viewPager: ViewPager2? = null
    private var pagerAdapter: FragmentStateAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pt_commission_fragment, container, false)
        viewModel = ViewModelProvider(this).get(PtCommissionViewModel::class.java)
        viewPager = view.findViewById(R.id.pt_commission_viewpager)
        pagerAdapter = MyPagerAdapter(activity, titles.size)
        viewPager!!.adapter = pagerAdapter
        val tabLayout = view.findViewById(R.id.pt_commission_tabs) as TabLayout
        TabLayoutMediator(
            tabLayout, viewPager!!
        ) { tab: TabLayout.Tab, position: Int -> tab.text = titles[position] }.attach()
        return view
    }

    private class MyPagerAdapter(fa: FragmentActivity?, TABS: Int) : FragmentStateAdapter(fa!!) {
        val TABS = TABS
        override fun createFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> {
                    Pt_CommSoldFragment.newInstance()
                }
                1 -> {
                    Pt_CommPaidFragment.newInstance()
                }
                else -> Pt_CommSoldFragment.newInstance()
            }
        }

        override fun getItemCount(): Int {

            return TABS
        }
    }
}