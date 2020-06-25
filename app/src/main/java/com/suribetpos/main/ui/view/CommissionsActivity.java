package com.suribetpos.main.ui.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.suribetpos.R;
import com.suribetpos.main.ui.fragments.commissions.ETopUpCommissionFragment;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;

import java.util.ArrayList;
import java.util.List;


public class CommissionsActivity extends BaseActivity {

    AlertDialogManager alert = new AlertDialogManager();
    private TabLayout etopup_tabLayout;
    private ViewPager etopup_viewPager;
    int ViewPageID;
    LocalBroadcastManager lbm;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // Called with the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_newcommissions);
        Initialization();
        //setToolBar();
        /*Fragment for etopup Details*/
        setupViewPager(etopup_viewPager);
        etopup_viewPager.setCurrentItem(Common.SelectedTabPosition);

        // mDBInternalHelper = new InternalDataBaseHelperClass(this);
        etopup_tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Common.SelectedTabPosition = tab.getPosition();
                etopup_viewPager.setCurrentItem(tab.getPosition());
                ViewPageID = tab.getPosition();
                if (ViewPageID == 0) {
                    lbm = LocalBroadcastManager.getInstance(CommissionsActivity.this);
                    Intent i = new Intent("EtopUp_Refresh");
                    lbm.sendBroadcast(i);
                } else if (ViewPageID == 1) {
                    lbm = LocalBroadcastManager.getInstance(CommissionsActivity.this);
                    Intent i = new Intent("TODAYS_REFRESH");
                    lbm.sendBroadcast(i);
                } /*else {
                    lbm = LocalBroadcastManager.getInstance(ETopUpActivity.this);
                    Intent i = new Intent("LOGDETAILS_REFRESH");
                    lbm.sendBroadcast(i);
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            HomeActivty();
            //Signout("Are you sure want to close?");
        }
        return true;
    }

    // Called when the activity is about to start interacting with the user.
    @Override
    protected void onResume() {
        super.onResume();
    }

    private void Initialization() {
        // Inflate our UI from its XML layout description.
        etopup_viewPager = findViewById(R.id.etopup_viewpager);
        etopup_tabLayout = findViewById(R.id.etopup_tabs);
        etopup_tabLayout.setupWithViewPager(etopup_viewPager);

        /*exportDetails_swipeLayout = findViewById(R.id.exportdetails_swipe_refresh_layout);
        // Scheme colors for animation
        exportDetails_swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );*/
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new ETopUpCommissionFragment(), CommonMessage(R.string.EtopUp));
        //adapter.addFrag(new TodaysVoucherFragment(), "TRANSACTION");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static int booleanToInt(boolean value) {
        // Convert true to 1 and false to 0.
        return value ? 1 : 0;
    }

    public void HomeActivty() {
        Intent _gwwIntent = new Intent(this, NewHomeActivity.class);
        startActivity(_gwwIntent);
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(this, Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }
}
