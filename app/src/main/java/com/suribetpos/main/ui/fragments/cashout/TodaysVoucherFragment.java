package com.suribetpos.main.ui.fragments.cashout;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.GsonBuilder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.etopup.CancelInputModel;
import com.suribetpos.main.data.model.etopup.TillEtopUpTransactionsModel;
import com.suribetpos.main.data.model.etopup.TodaysTransectionModel;
import com.suribetpos.main.data.model.onlinecashout.Cashouttransactiondetails;
import com.suribetpos.main.model.etopup.CancelOutpuyModel;
import com.suribetpos.main.model.etopup.CancelSaleVoucherModel;
import com.suribetpos.main.ui.adapter.CashoutTransactionAdapter;
import com.suribetpos.main.ui.view.CashoutActivity;
import com.suribetpos.main.ui.view.ETopUpActivity;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.ui.viewmodel.CashoutTransactionViewModel;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.SuribetException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Created by DEV 27 on 06/06/2017.
 */

public class TodaysVoucherFragment extends Fragment implements View.OnClickListener, LifecycleOwner {
    View todays_rootview;
    RecyclerView TodaysTransList;
    TextView todays_trans_empty, cashoutTodaysTransCount, cashoutTodaysTransAmount;
    SwipeRefreshLayout refreshLay;
    ArrayList<TodaysTransectionModel> ListofTodaysSaleVouchers = new ArrayList<>();
    CashoutTransactionAdapter adapter;
    CashoutTransactionViewModel viewModel;


    public void refresh() {
        todays_ListHan.post(todays_ListRun);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        todays_rootview = inflater.inflate(R.layout.fragment_cashout_todaysvoucher, container, false);
        intialization();
        refresh();
        refreshLay.setOnRefreshListener(() -> {
            refreshLay.setRefreshing(true);
            (new Handler()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    refreshLay.setRefreshing(false);
                    refresh();
                }
            }, 3000);
        });
        return todays_rootview;
    }

    Handler todays_ListHan = new Handler();
    Runnable todays_ListRun = new Runnable() {
        @Override
        public void run() {
            viewModel = ViewModelProviders.of(getActivity()).get(CashoutTransactionViewModel.class);
            viewModel.getTransaction().observe(getActivity(), userListUpdateObserver);
        }
    };
    Observer<List<Cashouttransactiondetails>> userListUpdateObserver = new Observer<List<Cashouttransactiondetails>>() {
        @Override
        public void onChanged(List<Cashouttransactiondetails> userArrayList) {
            adapter = new CashoutTransactionAdapter(getActivity(), userArrayList);
            TodaysTransList.setLayoutManager(new LinearLayoutManager(getActivity()));
            TodaysTransList.setAdapter(adapter);
        }
    };

    private void intialization() {
        TodaysTransList = todays_rootview.findViewById(R.id.cashout_todays_list);
        todays_trans_empty = todays_rootview.findViewById(R.id.cashout_todays_empty);
        cashoutTodaysTransCount = todays_rootview.findViewById(R.id.cashout_todays_count);
        cashoutTodaysTransAmount = todays_rootview.findViewById(R.id.cashout_todays_amount);
        refreshLay = todays_rootview.findViewById(R.id.activity_main_swipe_refresh_layout);
        refreshLay.setColorScheme(R.color.red, R.color.green, R.color.sample_blue);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.printer_listIcon:
                //showAToast("printer_listIcon betting!");
                break;
        }
    }

}

