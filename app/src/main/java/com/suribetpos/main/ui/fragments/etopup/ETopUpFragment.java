/*package com.suribetpos.main.fragments.etopup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import androidx.fragment.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.suribetpos.R;
import com.suribetpos.main.activity.HomeActivity;
import com.suribetpos.main.activity.NewHomeActivity;
import com.suribetpos.main.fragments.common.HomeFragment;
import com.suribetpos.main.model.common.ProductName_User;
import com.suribetpos.main.utilities.Common;

public class ETopUpFragment extends Fragment {
    private View mRoot;
    private FragmentActivity myContext;
    int PAGE_COUNT;
    Activity activityContext;
    LocalBroadcastManager lbm;
    private String[] tabTitles;
    ETopUpFragmentPageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", ">>>>>>>>>");
        NewHomeActivity.setActionBarTitle("E-Topup"); // here are other names according to each fragment
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", ">>>>>>>>>");
        NewHomeActivity.setActionBarTitle("E-Topup");
    }

    @Override
    public void onAttach(Activity activity) {
        Log.d("onAttach", ">>>>>>>>>");
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_etopup, null);
        //setHasOptionsMenu(true);

        mRoot.setFocusableInTouchMode(true);
        mRoot.requestFocus();
        mRoot.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    Common.NavigationSelectedItem = 10;
                    Fragment fragment = new HomeFragment();
                    if (fragment != null) {
                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.frame_container, fragment).commit();
                    }
                    //((NewHomeActivity) getActivity()).RefreshNAvigationList();
                    return true;
                }
                return false;
            }
        });

        for (int l = 0; l < Common.listProductDetails.size(); l++) {
            ProductName_User product = Common.listProductDetails.get(l);
            String TopUpCancelProcud = product.getProductName();
            if (TopUpCancelProcud.equals(Common.defaultPOSProFlag[0].toString())) {
                Common.topUpcancelFlag = true;
            }
        }
        PAGE_COUNT = 2;
        tabTitles = new String[]{"CREATE", "TODAY'S TRASACTIONS"};
if (Common.topUpcancelFlag == true) {
            PAGE_COUNT = 3;
            tabTitles = new String[]{"CREATE", "CANCEL", "CANCELLED VOUCHER"};
        } else {
            PAGE_COUNT = 2;
            tabTitles = new String[]{"CREATE", "CANCELLED VOUCHER"};
        }



        final ViewPager viewPager = mRoot.findViewById(R.id.etopup_viewpager);


        adapter = new ETopUpFragmentPageAdapter(getChildFragmentManager(), PAGE_COUNT, tabTitles);
        int limit = (adapter.getCount() > 1 ? adapter.getCount() - 1 : 1);
        viewPager.setAdapter(adapter);
        // Stop the refresh tabs
        viewPager.setOffscreenPageLimit(limit);

        final TabLayout tabLayout = mRoot.findViewById(R.id.etopup_tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setTabTextColors(Color.parseColor("#707070"), Color.parseColor("#FFFFFF"));
 LinearLayout layout = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(PAGE_COUNT));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
        layoutParams.weight = 1; // e.g. 0.5f
        layout.setLayoutParams(layoutParams);
        tabLayout.setLayoutParams(layoutParams);

 LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.GRAY);
        drawable.setSize(1, 1);
        linearLayout.setDividerPadding(10);
        linearLayout.setDividerDrawable(drawable);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        HideKeyboard();
                        viewPager.setCurrentItem(tab.getPosition());
                        Common.DailyGameAfterBilling_Flag = false;
                        if (tab.getPosition() == 0) {
                            lbm = LocalBroadcastManager.getInstance(getActivity());
                            Intent i = new Intent("CREATE_REFRESH");
                            lbm.sendBroadcast(i);
                        }

                        if (PAGE_COUNT > 2) {
                            if (tab.getPosition() == 2) {
                                lbm = LocalBroadcastManager.getInstance(getActivity());
                                Intent i = new Intent("CANCELLED_REFRESH");
                                lbm.sendBroadcast(i);
                            }
                        } else {
                            if (tab.getPosition() == 1) {
                                lbm = LocalBroadcastManager.getInstance(getActivity());
                                Intent i = new Intent("CANCELLED_REFRESH");
                                lbm.sendBroadcast(i);
                            }
                        }
                    }
                });

        return mRoot;
    }

    static class ETopUpFragmentPageAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] mtabTitles;

        public ETopUpFragmentPageAdapter(FragmentManager fm, int NumOfTabs, String[] tabTitle) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
            this.mtabTitles = tabTitle;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mtabTitles[position];
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    CreateFragment tab1 = new CreateFragment();
                    return tab1;
                case 1:
                    TodaysVoucherFragment tab2 = new TodaysVoucherFragment();
                    return tab2;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

    public void HideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) activityContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
*/
