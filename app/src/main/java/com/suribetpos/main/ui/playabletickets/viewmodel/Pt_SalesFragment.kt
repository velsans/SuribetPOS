package com.suribetpos.main.ui.playabletickets.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.suribetpos.R
import com.suribetpos.main.ui.playabletickets.fragments.Pt_Sales_GenrateFragment
import com.suribetpos.main.ui.playabletickets.fragments.Pt_Sales_TransactionFragment

class Pt_SalesFragment : Fragment() {

    companion object {
        fun newInstance() = Pt_SalesFragment()
    }

    private val titles = arrayOf("TICKETS", "TRANSACTION")
    private lateinit var viewModel: PtSalesViewModel
    var viewPager: ViewPager2? = null

    private var pagerAdapter: FragmentStateAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.pt_sales_fragment, container, false)
        viewModel = ViewModelProvider(this).get(PtSalesViewModel::class.java)

        viewPager = view.findViewById(R.id.pt_sales_viewpager)
        pagerAdapter = MyPagerAdapter(activity, titles.size)
        viewPager!!.adapter = pagerAdapter
        val tabLayout = view.findViewById(R.id.pt_sales_tabs) as TabLayout
        TabLayoutMediator(
            tabLayout, viewPager!!
        ) { tab: TabLayout.Tab, position: Int -> tab.text = titles[position] }.attach()

        // or view.pt_sales_viewpager
        /*pagerAdapter = MyPagerAdapter(activity, titles.size)
        pt_sales_viewpager!!.adapter = pagerAdapter
        TabLayoutMediator(
            pt_sales_tabs, pt_sales_viewpager!!
        ) { tab: TabLayout.Tab, position: Int -> tab.text = titles[position] }.attach()*/

        return view
    }

    private class MyPagerAdapter(fa: FragmentActivity?, TABS: Int) : FragmentStateAdapter(fa!!) {
        val TABSs = TABS
        override fun createFragment(pos: Int): Fragment {
            return when (pos) {
                0 -> Pt_Sales_GenrateFragment.newInstance()
                1 -> Pt_Sales_TransactionFragment.newInstance()
                else -> Pt_Sales_GenrateFragment.newInstance()
            }
        }

        override fun getItemCount(): Int {
            return TABSs
        }
    }
}