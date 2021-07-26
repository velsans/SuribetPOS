package com.suribetpos.main.ui.cashout;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.suribetpos.R;
import com.suribetpos.databinding.CreatecashoutFragmentBinding;
import com.suribetpos.main.ui.cashout.viewmodel.CashoutCreateViewModel;
import com.suribetpos.main.ui.etopup.ETopUpActivity;
import com.suribetpos.main.utils.Common;

import es.dmoral.toasty.Toasty;


public class CashoutCreateFragment extends Fragment implements LifecycleOwner {

    View create_rootView;
    CreatecashoutFragmentBinding createcashoutFragmentBinding;
    private static final int SCANNIN_GREQUEST_CODE = 3;
    CashoutCreateViewModel cashoutCreateViewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        createcashoutFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.createcashout_fragment, container, false);
        cashoutCreateViewModel = new CashoutCreateViewModel(getContext());
        createcashoutFragmentBinding.setLifecycleOwner(this);
        createcashoutFragmentBinding.setViewModel(cashoutCreateViewModel);
        createcashoutFragmentBinding.executePendingBindings();
        create_rootView = createcashoutFragmentBinding.getRoot();

        CashoutCreateHandler handler = new CashoutCreateHandler();
        handler.setBinding(createcashoutFragmentBinding, getContext());
        createcashoutFragmentBinding.setHandler(handler);
        Initialization();
        return create_rootView;
    }

    private void Initialization() {
        create_rootView.findViewById(R.id.btnScanAccountNumber).setOnClickListener(scannerhandler);
    }

/*
    @BindingAdapter({"toastMessage", "networkStatus"})
    public void runMe(View view, String ErrorMessage, int StatusCode) {
        Log.e("ErrorCode", ">>>>>>>>>>>>>>" + ErrorMessage);
        if (ErrorMessage != null) {
            if (StatusCode == 1) {
                Log.e("StatusCode", ">>>>>>>>>>" + StatusCode);
                Toasty.success(view.getContext(), ErrorMessage, Toasty.LENGTH_SHORT, true).show();
            } else {
                Log.e("StatusCode", ">>>>>>>>>>" + StatusCode);
                Toasty.error(view.getContext(), ErrorMessage, Toasty.LENGTH_SHORT, true).show();
            }
        }
    }
*/

    public String CommonMessage(int Common_Msg) {
        return getActivity().getResources().getString(Common_Msg);
    }

    View.OnClickListener scannerhandler = v -> {
        try {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CaptureActivity.class);
            startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
        } catch (ActivityNotFoundException anfe) {
            Log.e("onCreate", "Scanner Not Found", anfe);
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    //RequstNR.setText(data.getStringExtra("ScannedBarcode"));
                    cashoutCreateViewModel.setWDRCode(data.getStringExtra("ScannedBarcode"));
                }
                break;
        }
    }
}

