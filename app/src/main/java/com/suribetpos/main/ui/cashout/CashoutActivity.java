package com.suribetpos.main.ui.cashout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.etopup.BalanceAmountModel;
import com.suribetpos.main.data.model.etopup.TillTransactionAmountModel;
import com.suribetpos.main.ui.view.BaseActivity;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.ui.view.UserAuthenticationActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.SuribetException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CashoutActivity extends BaseActivity {
    AlertDialogManager alert = new AlertDialogManager();
    private TabLayout cashout_tabLayout;
    private ViewPager cashout_viewPager;
    int ViewPageID;
    LocalBroadcastManager lbm;
    ApiInterface ClientInfoApi = null;
    /*Toolbar*/
    ImageView toolbarHome, toolbarSignOUT;
    TextView toolbarTitle, toolbarAmount;
    List<StatusModel> StatusList = new ArrayList<StatusModel>();

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
        setContentView(R.layout.activity_onlinecashout);
        Initialization();
        ToolBarView();
        RefreshBalance();
        setupViewPager(cashout_viewPager);
        cashout_viewPager.setCurrentItem(Common.SelectedTabPosition);
        cashout_tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Common.SelectedTabPosition = tab.getPosition();
                cashout_viewPager.setCurrentItem(tab.getPosition());
                ViewPageID = tab.getPosition();
                if (ViewPageID == 0) {
                    lbm = LocalBroadcastManager.getInstance(CashoutActivity.this);
                    Intent i = new Intent("cashout_create");
                    lbm.sendBroadcast(i);
                } else if (ViewPageID == 1) {
                    lbm = LocalBroadcastManager.getInstance(CashoutActivity.this);
                    Intent i = new Intent("cashout_transaction");
                    lbm.sendBroadcast(i);
                }
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
        }
        return true;
    }

    // Called when the activity is about to start interacting with the user.
    @Override
    protected void onResume() {
        super.onResume();
        //RefreshBalance();
    }

    private void Initialization() {
        // Inflate our UI from its XML layout description.
        cashout_viewPager = findViewById(R.id.cashout_viewpager);
        cashout_tabLayout = findViewById(R.id.cashout_tabs);
        cashout_tabLayout.setupWithViewPager(cashout_viewPager);
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
        adapter.addFrag(new CashoutCreateFragment(), CommonMessage(R.string.Create));
        adapter.addFrag(new TransactionFragment(), CommonMessage(R.string.Transaction));
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


    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public void Signout(String ErrorMessage) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
        builder1.setMessage(ErrorMessage);
        builder1.setCancelable(true);
        builder1.setPositiveButton(CommonMessage(R.string.Cancel),
                (dialog, id) -> dialog.cancel());
        builder1.setNegativeButton(CommonMessage(R.string.Okay),
                (dialog, id) -> {
                    try {
                        YesOnClickListner();
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.onlinepayout), ex.toString(), false);
                    }
                    dialog.cancel();
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public static int booleanToInt(boolean value) {
        // Convert true to 1 and false to 0.
        return value ? 1 : 0;
    }

    public void HomeActivty() {
        Intent _gwwIntent = new Intent(this, NewHomeActivity.class);
        startActivity(_gwwIntent);
    }

    public static <T> boolean IsNullOrEmpty(Collection<T> list) {
        return list == null || list.isEmpty();
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
            if (Common.AlertDialogVisibleFlag == true) {
                Common.AlertDialogVisibleFlag = false;
                alert.showAlertDialog(this, Title, Message, YesNo);
            } else {
                //do something here... if already showing
            }
    }

    public String CommonMessage(int Common_Msg) {
        String CommonMsgs = "";
        try {
            CommonMsgs = this.getResources().getString(Common_Msg);
            if (isNullOrEmpty(CommonMsgs)) {
                CommonMsgs = "";
            }
        } catch (Exception ex) {
            CommonMsgs = ex.toString();
        }
        return CommonMsgs;
    }

    public void ToolBarView() {
        toolbarHome = findViewById(R.id.toolbar_home);
        toolbarSignOUT = findViewById(R.id.toolbar_switch);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(CommonMessage(R.string.onlinepayout));
        toolbarAmount = findViewById(R.id.toolbar_amount);
        toolbarHome.setOnClickListener(v -> {
            RefreshBalance();
        });

        toolbarSignOUT.setOnClickListener(v -> Signout(CommonMessage(R.string.Are_you_SignOut)));
    }

    private void YesOnClickListner() {
        Intent intent = new Intent(CashoutActivity.this, UserAuthenticationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Common.UserId = 0;
        //Common.UserLocation = "";
        Common.UserName = "";
        Common.UserPassword = "";
        //Common.MobileMacAddress = "";
        //session.logoutUser();
        startActivity(intent);
    }

    public void RefreshBalance() {
        try {
            Log.e("ClinetBalance",CommonMessage(R.string.onlinepayout));
            StatusList.clear();
            NewHomeActivity.BalanceAmountList.clear();
            TillTransactionAmountModel transactionAmountModel = new TillTransactionAmountModel();
            transactionAmountModel.setUserId(Common.UserId);
            transactionAmountModel.setTillId(Common.TillId);
            transactionAmountModel.setClientId(Common.ClientId);
            transactionAmountModel.setMacAddress(Common.MobileMacAddress);
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetTillTransactionAmount(transactionAmountModel).enqueue(new Callback<TillTransactionAmountModel>() {
                @Override
                public void onResponse(Call<TillTransactionAmountModel> call, Response<TillTransactionAmountModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.body() !=null) {
                                StatusList.addAll(response.body().getTable());
                                if (StatusList.size() > 0) {
                                    if (StatusList.get(0).getStatus() == 1) {
                                        NewHomeActivity.BalanceAmountList.addAll(response.body().getTable1());
                                        if (NewHomeActivity.BalanceAmountList.size() > 0) {
                                            Common.ClientBalance = NewHomeActivity.BalanceAmountList.get(0).getTotalTransactionAmount();
                                            Common.ClinetGamingDate = NewHomeActivity.BalanceAmountList.get(0).getGamingDate();
                                            Log.e("ClinetBalance", ">>>>>>>>>>" + Common.ClientBalance);
                                            styleMenuButton();
                                            //new NewHomeActivity().tvTimer.setText(Common.ClinetGamingDate);
                                        }
                                    } else {
                                        AlertDialogBox(CommonMessage(R.string.Create_Voucher), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), false);
                                    }
                                } else {
                                    AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.APINotResponding), false);
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.Create_Voucher), response.message(), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.Create_Voucher), response.message(), false);
                        }
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.Create_Voucher), ex.getMessage(), false);
                    }
                }

                @Override
                public void onFailure(Call<TillTransactionAmountModel> call, Throwable t) {
                    AlertDialogBox(CommonMessage(R.string.Create_Voucher), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), ex.toString(), false);
        }
    }

    private void styleMenuButton() {
        // Find the menu item you want to style
        toolbarAmount.setVisibility(View.VISIBLE);
        if (Common.ClientBalance > 0.00) {
            toolbarAmount.setTextColor(getBaseContext().getResources().getColor(R.color.green));
            toolbarAmount.setText(String.valueOf(Common.ClientBalance));
        } else {
            if (Common.ClientBalance == 0.00) {
                toolbarAmount.setTextColor(getBaseContext().getResources().getColor(R.color.theme_yellow_accent));
                toolbarAmount.setText(String.valueOf(Common.ClientBalance));
            } else {
                toolbarAmount.setTextColor(getBaseContext().getResources().getColor(R.color.red));
                toolbarAmount.setText(String.valueOf(Common.ClientBalance));
            }
        }
    }
}
