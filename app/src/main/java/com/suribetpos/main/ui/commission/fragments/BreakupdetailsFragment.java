package com.suribetpos.main.ui.commission.fragments;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suribetpos.R;
import com.suribetpos.databinding.BreakupdetailsFragmentBinding;
import com.suribetpos.main.ui.commission.viewmodel.BreakupdetailsViewModel;
import com.suribetpos.main.utils.Common;

import es.dmoral.toasty.Toasty;

public class BreakupdetailsFragment extends Fragment implements LifecycleOwner {

    private BreakupdetailsViewModel mViewModel;
    View breakup_rootView;
    BreakupdetailsFragmentBinding breakupdetailsFragmentBinding;
    BreakupdetailsReceiver breakupdetailsReceiver;

    private class BreakupdetailsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(breakupdetailsReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        breakupdetailsReceiver = new BreakupdetailsReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(breakupdetailsReceiver,
                new IntentFilter("breakup_refresh"));
    }

    public void refresh() {
        mViewModel.BreakUoDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        breakupdetailsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.breakupdetails_fragment, container, false);
        mViewModel = new BreakupdetailsViewModel(getContext());
        breakupdetailsFragmentBinding.setLifecycleOwner(this);
        breakupdetailsFragmentBinding.setViewModel(mViewModel);
        breakupdetailsFragmentBinding.executePendingBindings();
        breakup_rootView = breakupdetailsFragmentBinding.getRoot();
        refresh();
        return breakup_rootView;
    }

    @BindingAdapter({"toastMessage", "networkStatus"})
    public static void runMe(View view, String ErrorCode, int StatusCode) {
        String Message;
        if (ErrorCode != null) {
            if (Common.LanguageMap.containsKey(ErrorCode) == true) {
                Message = Common.LanguageMap.get(ErrorCode);
                if (StatusCode == 1) {
                    Toasty.success(view.getContext(), Message, Toasty.LENGTH_SHORT, true).show();
                } else {
                    Toasty.error(view.getContext(), Message, Toasty.LENGTH_SHORT, true).show();
                }
            } else {
                Toasty.error(view.getContext(), ErrorCode, Toasty.LENGTH_SHORT, true).show();
            }

        }
    }
}