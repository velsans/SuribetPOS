package com.suribetpos.main.ui.cashout;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.suribetpos.R;
import com.suribetpos.databinding.TransactionFragmentBinding;
import com.suribetpos.main.data.model.cashout.TransactionDetails;
import com.suribetpos.main.ui.cashout.viewmodel.TransactionViewModel;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.ui.view.SplashScreenActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.BarcodeGeneration;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.CreatePDF;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import recieptservice.com.recieptservice.PrinterInterface;

public class TransactionFragment extends Fragment implements LifecycleOwner, View.OnClickListener {
    View todays_rootview;
    RecyclerView TodaysTransList;
    TextView todays_trans_empty, cashoutTodaysTransCount, cashoutTodaysTransAmount, LocationName_txt, TillName_txt, CashierName_txt, PaymentDateTime_txt,
            Currency_txt, Amount_txt, Terms_message_txt, barcode_txt, bottom_receipt_txt, FromDateTodate, LastCollectedAmount, IDNumber_txt, IDName_txt;
    ImageView barcode_image, closeBill, pdf_prinout, cashoutvoucher_logo;
    LinearLayout pdfviewerLAYOUT, BaseLayout, id_numberLAY, id_nameLAY, toplayoutPrint, footerlayoutPrint;
    SwipeRefreshLayout refreshLay;
    CashoutTransactionAdapter todaysTransadapter;
    TransactionViewModel viewModel;
    LinearLayoutManager horizontalLayoutManager;
    TransactionFragmentBinding transactionBinding;
    List<TransactionDetails> userArrayLists = new ArrayList<>();
    private Bitmap bitmap;
    DecimalFormat df = new DecimalFormat("###0.00");
    CashoutTransactionReceiver cashout_transactionReceiver;
    String directory_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/CashOut/", VoucherID = "", FooterContentDisplay;
    Intent intent = new Intent();
    AlertDialogManager alert = new AlertDialogManager();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private class CashoutTransactionReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(cashout_transactionReceiver);
    }

    @Override
    public void onResume() {
        super.onResume();
        cashout_transactionReceiver = new CashoutTransactionReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(cashout_transactionReceiver,
                new IntentFilter("cashout_transaction"));
    }


    public void refresh() {
        viewModel = ViewModelProviders.of(getActivity()).get(TransactionViewModel.class);
        viewModel.getTransaction().observe(getActivity(), userListUpdateObserver);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        transactionBinding = DataBindingUtil.inflate(inflater, R.layout.transaction_fragment, container, false);
//viewModel = new TransactionViewModel(getContext());
        transactionBinding.setLifecycleOwner(this);
        todays_rootview = transactionBinding.getRoot();
        TodaysTransList = transactionBinding.transactionRecylcerView;
        refresh();
        intialization();

        refreshLay.setOnRefreshListener(() -> {
            refreshLay.setRefreshing(true);
            (new Handler()).postDelayed(() -> {
                refreshLay.setRefreshing(false);
                refresh();
            }, 1000);
        });
        transactionBinding.search.setActivated(true);
        transactionBinding.search.setQueryHint(CommonMessage(R.string.Type_your_keyword_here));
        transactionBinding.search.onActionViewExpanded();
        transactionBinding.search.setIconified(false);
        transactionBinding.search.clearFocus();

        transactionBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                todaysTransadapter.getFilter().filter(newText);
                return false;
            }
        });

        todays_rootview.setFocusableInTouchMode(true);
        todays_rootview.requestFocus();
        todays_rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    HomeActivty();
                    return true;
                }
                return false;
            }
        });

        return todays_rootview;
    }

    public void HomeActivty() {
        Intent _gwwIntent = new Intent(requireActivity(), NewHomeActivity.class);
        startActivity(_gwwIntent);
    }

    Observer<List<TransactionDetails>> userListUpdateObserver = new Observer<List<TransactionDetails>>() {
        @Override
        public void onChanged(List<TransactionDetails> userArrayList) {
            userArrayLists = userArrayList;
            todaysTransadapter = new CashoutTransactionAdapter(getActivity(), userArrayList);
            horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            TodaysTransList.setLayoutManager(horizontalLayoutManager);
            todaysTransadapter.notifyDataSetChanged();
            TodaysTransList.setAdapter(todaysTransadapter);

            if (userArrayList.size() > 0) {
                cashoutTodaysTransCount.setText(String.valueOf(userArrayList.size()));
                cashoutTodaysTransAmount.setText(String.valueOf(TotalAmountVolume(userArrayList)));
                todays_trans_empty.setVisibility(View.GONE);
                /*Onclick Item listener*/
                todaysTransadapter.setOnItemClickListener(onItemClickListener);
            } else {
                todays_trans_empty.setVisibility(View.VISIBLE);
            }
            if (NewHomeActivity.BalanceAmountList.size() > 0) {
                FromDateTodate.setText(CommonMessage(R.string.From) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate()) + " " + CommonMessage(R.string.To) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getGamingDate()));
                LastCollectedAmount.setText(Common.CurrencyCode + " " + df.format(NewHomeActivity.BalanceAmountList.get(0).getLastCollectedAmt()));
            }
        }
    };

    public double TotalAmountVolume(List<TransactionDetails> TotalScannedList) {
        double TotVolume = 0.00;
        for (TransactionDetails inventoreceScanModel : TotalScannedList) {
            TotVolume = TotVolume + inventoreceScanModel.getVALUE();
        }
        return TotVolume;
    }

    private void intialization() {
//TodaysTransList = todays_rootview.findViewById(R.id.cashout_todays_list);
        todays_trans_empty = todays_rootview.findViewById(R.id.cashout_todays_empty);
        cashoutTodaysTransCount = todays_rootview.findViewById(R.id.cashout_todays_count);
        cashoutTodaysTransAmount = todays_rootview.findViewById(R.id.cashout_todays_amount);
        refreshLay = todays_rootview.findViewById(R.id.activity_main_swipe_refresh_layout);
        refreshLay.setColorScheme(R.color.red, R.color.green, R.color.sample_blue);

        /*billing details*/
        pdfviewerLAYOUT = todays_rootview.findViewById(R.id.pdfviewerLAYOUT);
        pdfviewerLAYOUT.setVisibility(View.GONE);
        BaseLayout = todays_rootview.findViewById(R.id.BaseLayout);
        LocationName_txt = todays_rootview.findViewById(R.id.LocationName_txt);
        TillName_txt = todays_rootview.findViewById(R.id.TillName_txt);
        CashierName_txt = todays_rootview.findViewById(R.id.CashierName_txt);
        PaymentDateTime_txt = todays_rootview.findViewById(R.id.PaymentDateTime_txt);
        Currency_txt = todays_rootview.findViewById(R.id.Currency_txt);
        Amount_txt = todays_rootview.findViewById(R.id.Amount_txt);
        Terms_message_txt = todays_rootview.findViewById(R.id.invalid_message_txt);
        barcode_txt = todays_rootview.findViewById(R.id.barcode_txt);
        bottom_receipt_txt = todays_rootview.findViewById(R.id.bottom_receipt_txt);
        barcode_image = todays_rootview.findViewById(R.id.barcode_image);
        closeBill = todays_rootview.findViewById(R.id.bill_close);
        closeBill.setOnClickListener(this);
        pdf_prinout = todays_rootview.findViewById(R.id.pdf_prinout);
        pdf_prinout.setOnClickListener(this);
        cashoutvoucher_logo = todays_rootview.findViewById(R.id.cashoutvoucher_logo);
        FromDateTodate = todays_rootview.findViewById(R.id.fromdateTodate);
        LastCollectedAmount = todays_rootview.findViewById(R.id.LastCollectedAmount);
        IDNumber_txt = todays_rootview.findViewById(R.id.IDNumber_txt);
        IDName_txt = todays_rootview.findViewById(R.id.IDName_txt);
        id_numberLAY = todays_rootview.findViewById(R.id.id_numberLAY);
        id_nameLAY = todays_rootview.findViewById(R.id.id_nameLAY);

        toplayoutPrint = todays_rootview.findViewById(R.id.cashout_topprint);
        footerlayoutPrint = todays_rootview.findViewById(R.id.cashout_footerprint);
    }

    /*Onclick Item listener*/
    private final View.OnClickListener onItemClickListener = view -> {
        try {
            pdfviewerLAYOUT.setVisibility(View.VISIBLE);
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            TransactionDetails thisItem = userArrayLists.get(position);
            if (Common.VoucherLogo != null) {
                cashoutvoucher_logo.setVisibility(View.VISIBLE);
                cashoutvoucher_logo.setImageBitmap(Common.VoucherLogo);
            } else {
                cashoutvoucher_logo.setVisibility(View.GONE);
            }
            LocationName_txt.setText(Common.UserLocation);
            TillName_txt.setText(Common.TillName);
            CashierName_txt.setText(Common.ClientName);
            PaymentDateTime_txt.setText(thisItem.getTIME());
            Currency_txt.setText(Common.CurrencyCode);
            Amount_txt.setText(String.valueOf(thisItem.getVALUE()));
            Terms_message_txt.setText(Common.VoucherTerms);
            barcode_txt.setText(thisItem.getCASHOUTCODE());
            IDNumber_txt.setText(thisItem.getIdNumber());
            IDName_txt.setText(thisItem.getIdName());
            if (isNullOrEmpty(thisItem.getIdNumber())) {
                id_numberLAY.setVisibility(View.GONE);
                id_nameLAY.setVisibility(View.GONE);
            } else {
                id_numberLAY.setVisibility(View.VISIBLE);
                id_nameLAY.setVisibility(View.VISIBLE);
            }
/*Glide.with(this)
.load(imageByteArray)
.asBitmap()
.placeholder(R.drawable.ic_broken)
.into(rowImageView);*/
            barcode_image.setImageBitmap(CreatePDF.CreateBarcodeImage(thisItem.getCASHOUTCODE()));
            FooterContentDisplay = Common.VoucherFooterText.replace('@', '\n');
//bottom_receipt_txt.setText(FooterContentDisplay);
            if (SplashScreenActivity.isPrinterFlag == true) {
                intent.setClassName(
                        "recieptservice.com.recieptservice",
                        "recieptservice.com.recieptservice.service.PrinterService"
                );
                getActivity().bindService(intent, new ServiceConnection() {

                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        PrinterInterface mAidl = PrinterInterface.Stub.asInterface(
                                service
                        );
                        try {
                            //cashout printout
                            mAidl.setAlignment(1);
                            mAidl.printText(CommonMessage(R.string.onlinepayout));
                            mAidl.nextLine(2);
                            mAidl.setAlignment(0);
                            mAidl.printBitmap(topLayout(380, 250));
                            mAidl.nextLine(2);
                            mAidl.printBitmap(footerLayout(380, 200));
                            mAidl.nextLine(4);
                        } catch (Exception ex) {

                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        AlertDialogBox(
                                CommonMessage(R.string.TSC_Conn),
                                name.toString(),
                                false
                        );
                    }
                }, AppCompatActivity.BIND_AUTO_CREATE);
            }
        } catch (Exception ignored) {

        }
    };

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bill_close:
                pdfviewerLAYOUT.setVisibility(View.GONE);
                break;
            case R.id.pdf_prinout:
                bitmap = loadBitmapFromView(BaseLayout, BaseLayout.getWidth(), BaseLayout.getHeight());
                CreatePDF.createPdfinDevice(getActivity(), bitmap, directory_path, VoucherID);
                break;
        }
    }

    /*Pdf Viewer*/
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
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

    private Bitmap topLayout(int width, int height) {
        Bitmap b = null;
        try {
            toplayoutPrint.setDrawingCacheEnabled(true);
            toplayoutPrint.buildDrawingCache();
            b = toplayoutPrint.getDrawingCache();
            b = Bitmap.createScaledBitmap(b, width, height, false);
        } catch (Exception ex) {
        }
        return b;
    }

    private Bitmap footerLayout(int width, int height) {
        Bitmap b = null;
        try {
            footerlayoutPrint.setDrawingCacheEnabled(true);
            footerlayoutPrint.buildDrawingCache();
            b = footerlayoutPrint.getDrawingCache();
            b = Bitmap.createScaledBitmap(b, width, height, false);
        } catch (Exception ex) {
        }
        return b;
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(getActivity(), Title, Message, YesNo);
        } else {
//do something here... if already showing
        }
    }
}