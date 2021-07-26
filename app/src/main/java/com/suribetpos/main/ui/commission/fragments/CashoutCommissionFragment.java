package com.suribetpos.main.ui.commission.fragments;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suribetpos.R;
import com.suribetpos.databinding.CashOutCommissionFragmentBinding;
import com.suribetpos.main.data.model.commission.CashoutCommissionDetails;
import com.suribetpos.main.data.model.commission.CashoutCommissonReportDetails;
import com.suribetpos.main.ui.cashout.CashoutCommissionAdapter;
import com.suribetpos.main.ui.commission.viewmodel.CashoutCommissionViewModel;
import com.suribetpos.main.ui.view.NewHomeActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CashoutCommissionFragment extends Fragment implements LifecycleOwner, View.OnClickListener {
    View commissionRoot;
    CashOutCommissionFragmentBinding commissionDataBinding;
    private CashoutCommissionViewModel mViewModel;
    CashoutCommissionAdapter commissionadapter;
    LinearLayoutManager horizontalLayoutManager;
    RecyclerView CashoutCommiosnList;
    TextView commission_empty, txtPayoutTotalpaid, txtTotalPayoutCommission, txtBalReturn, txtTotalPayoutCount,
            txtPayoutComissionPercentage, FromDateTodate,TxtTotalPaidNonCommission;
    List<CashoutCommissionDetails> commissionArrayLists = new ArrayList<>();
    CashoutCommissionReceiver cashout_commissionReceiver;

    private class CashoutCommissionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(cashout_commissionReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        cashout_commissionReceiver = new CashoutCommissionReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(cashout_commissionReceiver,
                new IntentFilter("cashout_refresh"));
    }

    public void refresh() {
        mViewModel = ViewModelProviders.of(this).get(CashoutCommissionViewModel.class);
        mViewModel.getCommission().observe(getActivity(), userListUpdateObserver);
        mViewModel.getCommissionReport().observe(getActivity(), userListReportUpdateObserver);
    }

    public static CashoutCommissionFragment newInstance() {
        return new CashoutCommissionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        commissionDataBinding = DataBindingUtil.inflate(inflater, R.layout.cash_out_commission_fragment, container, false);
        commissionDataBinding.setLifecycleOwner(this);
        commissionRoot = commissionDataBinding.getRoot();
        CashoutCommiosnList = commissionDataBinding.cashoutCommissionRecylcerView;
        refresh();
        initilization();

        commissionDataBinding.search.setActivated(true);
        commissionDataBinding.search.setQueryHint(CommonMessage(R.string.Type_your_keyword_here));
        commissionDataBinding.search.onActionViewExpanded();
        commissionDataBinding.search.setIconified(false);
        commissionDataBinding.search.clearFocus();

        commissionDataBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                commissionadapter.getFilter().filter(newText);
                return false;
            }
        });

        return commissionRoot;
    }

    private void initilization() {
        commission_empty = commissionRoot.findViewById(R.id.payout_cancelled_empty);
        txtPayoutTotalpaid = commissionRoot.findViewById(R.id.txtPayout_Paid);
        txtTotalPayoutCommission = commissionRoot.findViewById(R.id.txtPayoutCommSales);
        txtBalReturn = commissionRoot.findViewById(R.id.txtPayoutBalReturn);
        txtTotalPayoutCount = commissionRoot.findViewById(R.id.txtTotalPayoutCount);
        txtPayoutComissionPercentage = commissionRoot.findViewById(R.id.txtPayoutComissionPercentage);
        FromDateTodate = commissionRoot.findViewById(R.id.fromdateTodate);
        TxtTotalPaidNonCommission = commissionRoot.findViewById(R.id.txtTotalPaidNonCommission);
        CashoutCommiosnList= commissionRoot.findViewById(R.id.cashoutCommissionRecylcerView);
    }

    Observer<List<CashoutCommissonReportDetails>> userListReportUpdateObserver = new Observer<List<CashoutCommissonReportDetails>>() {
        @Override
        public void onChanged(List<CashoutCommissonReportDetails> commissionReportArrayList) {
            if (commissionReportArrayList.size() > 0) {
                txtTotalPayoutCount.setText(String.valueOf(commissionReportArrayList.get(0).getPaidCount()));
                txtPayoutTotalpaid.setText(String.valueOf(commissionReportArrayList.get(0).getTotalPaid()));
                txtTotalPayoutCommission.setText(String.valueOf(commissionReportArrayList.get(0).getLocationPaidCommission()));
                txtBalReturn.setText(String.valueOf(commissionReportArrayList.get(0).getLocationBalance()));
                txtPayoutComissionPercentage.setText(CommonMessage(R.string.Commssion_sales) + " (" + String.valueOf(commissionReportArrayList.get(0).getPaidCommission() + "%)"));
                TxtTotalPaidNonCommission.setText(String.valueOf(commissionReportArrayList.get(0).getTotalPaidNonCommission()));
            }
        }
    };

    Observer<List<CashoutCommissionDetails>> userListUpdateObserver = new Observer<List<CashoutCommissionDetails>>() {
        @Override
        public void onChanged(List<CashoutCommissionDetails> userArrayList) {
            commissionArrayLists = userArrayList;
            commissionadapter = new CashoutCommissionAdapter(getActivity(), userArrayList);
            horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            CashoutCommiosnList.setLayoutManager(horizontalLayoutManager);
            commissionadapter.notifyDataSetChanged();
            CashoutCommiosnList.setAdapter(commissionadapter);
            if (NewHomeActivity.BalanceAmountList.size() > 0) {
                FromDateTodate.setText(CommonMessage(R.string.From) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate()) + " " + CommonMessage(R.string.To) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getGamingDate()));
            } else {
                FromDateTodate.setText(CommonMessage(R.string.No_Response_from_API));
            }
            if (userArrayList.size() > 0) {
                commission_empty.setVisibility(View.GONE);
                /*Onclick Item listener*/
                commissionadapter.setOnItemClickListener(onItemClickListener);
            } else {
                commission_empty.setVisibility(View.VISIBLE);
            }
        }
    };
    /*Onclick Item listener*/
    private View.OnClickListener onItemClickListener = view -> {
        try {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
        } catch (Exception ex) {
        }
    };

    @Override
    public void onClick(View v) {

    }

    public String CommonMessage(int Common_Msg) {
        return getActivity().getResources().getString(Common_Msg);
    }

    public String DateFormat(String givendate) {
        String strDate = "";
        DateFormat givenformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat Outputformatter = new SimpleDateFormat("dd-MMMM-yyyy");
        try {
            Date date = givenformat.parse(givendate);
            strDate = Outputformatter.format(date);
        } catch (Exception ex) {
            Log.e("", "" + ex.toString());
        }
        return strDate;
    }
}