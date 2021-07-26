package com.suribetpos.main.ui.etopup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.GsonBuilder;
import com.suribetpos.R;

import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.etopup.CancelInputModel;
import com.suribetpos.main.model.etopup.CancelOutpuyModel;
import com.suribetpos.main.model.etopup.CancelSaleVoucherModel;
import com.suribetpos.main.data.model.etopup.TillEtopUpTransactionsModel;
import com.suribetpos.main.data.model.etopup.TodaysTransectionModel;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.utils.AlertDialogManager;

import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.utils.CreatePDF;
import com.suribetpos.main.utils.SuribetException;


import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DEV 27 on 06/06/2017.
 */

public class TodaysVoucherFragment extends Fragment implements View.OnClickListener {

    public static final String ARG_PAGE = "ARG_PAGE";
    private boolean isInternetPresent = false;
    AlertDialogManager alert = new AlertDialogManager();
    Toast mToast;
    View todays_rootview;
    TodaysVoucherAdapter todaysTransadapter;
    DecimalFormat df = new DecimalFormat("###0.00");
    RecyclerView TodaysTransList;
    TextView todays_trans_empty, EtopUpTodaysTransCount, EtopUpTodaysTransAmount;
    SwipeRefreshLayout refreshLay;
    EtopUpTodaysTransReceiver etopup_todays_r;
    ArrayList<TodaysTransectionModel> ListofTodaysSaleVouchers = new ArrayList<>();
    //ArrayList<String> BarcodeList = new ArrayList<>();
    //ArrayList<String> StatusValueList = new ArrayList<>();
    List<StatusModel> StatusList = new ArrayList<StatusModel>();
    LinearLayoutManager horizontalLayoutManager;
    ArrayList<CancelSaleVoucherModel> CancelSaleVoucher = new ArrayList<>();
    ArrayList<CancelOutpuyModel> ListofCancelledSaleVouchers = new ArrayList<>();
    /*Pdf Viewer*/
    private LinearLayout BaseLayout, PDFviewerLAYOUT;
    private ImageView pdf_print, pdf_logo, pdf_barcode, PDF_prinoutClose;
    private Bitmap bitmap;
    private TextView WebsiteTxT, ClientNameTxT, AddressTxT, VoucherTxT, TillTxT, CashierTxT, AmountTxT, PinTxT,
            BarcodeValueTxT, BottomConditionsTxT, BottomRulesTxT, CustomerServiveNoTxT, DateTxT, BarcodeHeadTxT, FromDateTodate, LastCollectedAmount;
    String directory_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/EtopUp/", VoucherID = "", FooterContentDisplay;
    ApiInterface ClientInfoApi = null;
    SearchView searchView;

    public void refresh() {
        Log.i("Top-Refresh", "Cancelled Fragment");
        todays_ListHan.post(todays_ListRun);
    }


    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static TodaysVoucherFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TodaysVoucherFragment fragment = new TodaysVoucherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cancelled_mPage = getArguments ().getInt (ARG_PAGE);
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(etopup_todays_r);
    }

    @Override
    public void onResume() {
        super.onResume();
        etopup_todays_r = new EtopUpTodaysTransReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(etopup_todays_r,
                new IntentFilter("TODAYS_REFRESH"));
    }

    public void onStart() {
        super.onStart();
        Log.e("TodayHistory", "onStart");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TodayHistory", "onActivityCreated");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TodayHistory", "onStop(");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TodayHistory", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TodayHistory", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TodayHistory", "onDestroy");
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        todays_rootview = inflater.inflate(R.layout.fragment_etopup_todaysvoucher, container, false);
        intialization();
        ClientInfoApi = ApiClient.getInstance().getUserService();
        refresh();
        refreshLay.setOnRefreshListener(() -> {
            refreshLay.setRefreshing(true);
            Log.d("Swipe", "Refreshing Number");
            (new Handler()).postDelayed(() -> {
                refreshLay.setRefreshing(false);
                refresh();
            }, 3000);
        });
        pdf_print.setOnClickListener(v -> {
            bitmap = loadBitmapFromView(BaseLayout, BaseLayout.getWidth(), BaseLayout.getHeight());
            CreatePDF.createPdfinDevice(getActivity(), bitmap, directory_path, VoucherID);
        });
        PDF_prinoutClose.setOnClickListener(v -> PDFviewerLAYOUT.setVisibility(View.GONE));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    BarcodeHeadTxT.setVisibility(View.GONE);
                } else {
                    BarcodeHeadTxT.setVisibility(View.VISIBLE);
                }
                //if (ListofTodaysSaleVouchers.contains(BarcodeList)) {
                if (ListofTodaysSaleVouchers.size() > 0) {
                    todaysTransadapter.getFilter().filter(newText);
                    //todaysTransadapter.filter(newText);
                }
                return false;
            }
        });
        return todays_rootview;
    }

    Handler todays_ListHan = new Handler();
    Runnable todays_ListRun = new Runnable() {
        @Override
        public void run() {
            Common.HideKeyboard(getActivity());
            if (!CheckisInternetPresent()) {
                AlertDialogBox(CommonMessage(R.string.CancelVoucher), CommonMessage(R.string.Internet_ConnMsg), false);
            } else {
                //new GetTillEtopUpTransactions().execute();
                PDFviewerLAYOUT.setVisibility(View.GONE);
                GetTillEtopUpTransactionsAPI();
            }
        }
    };


    private void intialization() {
        TodaysTransList = todays_rootview.findViewById(R.id.etopup_todays_list);
        todays_trans_empty = todays_rootview.findViewById(R.id.etopup_todays_empty);
        EtopUpTodaysTransCount = todays_rootview.findViewById(R.id.etopup_todays_count);
        EtopUpTodaysTransAmount = todays_rootview.findViewById(R.id.etopup_todays_amount);
        refreshLay = todays_rootview.findViewById(R.id.activity_main_swipe_refresh_layout);
        refreshLay.setColorScheme(R.color.red, R.color.green, R.color.sample_blue);

        /*Pdf Print*/
        PDFviewerLAYOUT = todays_rootview.findViewById(R.id.pdfviewerLAYOUT);
        PDFviewerLAYOUT.setVisibility(View.GONE);
        pdf_print = todays_rootview.findViewById(R.id.pdf_prinout);
        BaseLayout = todays_rootview.findViewById(R.id.BaseLayout);
        pdf_logo = todays_rootview.findViewById(R.id.createVoucher_logo);

        WebsiteTxT = todays_rootview.findViewById(R.id.createVoucher_website);
        ClientNameTxT = todays_rootview.findViewById(R.id.createVoucher_clientName);
        AddressTxT = todays_rootview.findViewById(R.id.createVoucher_location);
        VoucherTxT = todays_rootview.findViewById(R.id.createVoucher_id);
        TillTxT = todays_rootview.findViewById(R.id.pdf_till);

        CashierTxT = todays_rootview.findViewById(R.id.createVoucher_cashier);
        AmountTxT = todays_rootview.findViewById(R.id.createVoucher_Amount);
        PinTxT = todays_rootview.findViewById(R.id.pdf_PINValue);
        BarcodeValueTxT = todays_rootview.findViewById(R.id.createVoucher_barcode);
        pdf_barcode = todays_rootview.findViewById(R.id.barcode_image);
        BottomConditionsTxT = todays_rootview.findViewById(R.id.pdf_bottomMessage);
        BottomRulesTxT = todays_rootview.findViewById(R.id.createVoucher_bottomRules);
        CustomerServiveNoTxT = todays_rootview.findViewById(R.id.createVoucher_customerServiceNo);
        DateTxT = todays_rootview.findViewById(R.id.pdf_bottomDate);
        PDF_prinoutClose = todays_rootview.findViewById(R.id.pdf_prinoutClose);
        searchView = todays_rootview.findViewById(R.id.barcode_searchView);
        BarcodeHeadTxT = todays_rootview.findViewById(R.id.BarcodeHead);
        FromDateTodate = todays_rootview.findViewById(R.id.fromdateTodate);
        LastCollectedAmount = todays_rootview.findViewById(R.id.LastCollectedAmount);
    }

    public boolean CheckisInternetPresent() {
        isInternetPresent = ConnectionFinder.isInternetOn(getActivity());
        if (!isInternetPresent) {
            AlertDialogBox(CommonMessage(R.string.CancelVoucher), CommonMessage(R.string.Internet_ConnMsg), false);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.printer_listIcon:
                showAToast("printer_listIcon betting!");
                break;
        }
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

    private void GetTillEtopUpTransactionsAPI() {
        try {
            StatusList.clear();
            ListofTodaysSaleVouchers.clear();
            //BarcodeList.clear();
            //StatusValueList.clear();
            TillEtopUpTransactionsModel transactionModel = new TillEtopUpTransactionsModel();
            transactionModel.setUserId(Common.UserId);
            transactionModel.setTillId(Common.TillId);
            transactionModel.setClientId(Common.ClientId);
            transactionModel.setMacAddress(Common.MobileMacAddress);
            if (NewHomeActivity.BalanceAmountList.size() > 0) {
                transactionModel.setCollectedDate(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate());
                FromDateTodate.setText(CommonMessage(R.string.From) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate()) + " " + CommonMessage(R.string.To) + " " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getGamingDate()));
                LastCollectedAmount.setText(Common.CurrencyCode + " " + df.format(NewHomeActivity.BalanceAmountList.get(0).getLastCollectedAmt()));
            } else {
                transactionModel.setCollectedDate("");
            }
            String stringInput = new GsonBuilder().create().toJson(transactionModel);

            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetTillEtopUpTransactions(transactionModel).enqueue(new Callback<TillEtopUpTransactionsModel>() {
                @Override
                public void onResponse(Call<TillEtopUpTransactionsModel> call, Response<TillEtopUpTransactionsModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.body() != null) {
                                StatusList.addAll(response.body().getTable());
                                ListofTodaysSaleVouchers.addAll(response.body().getTable1());
                                if (StatusList.size() > 0) {
                                    if (StatusList.get(0).getStatus() == 1) {
                                        if (ListofTodaysSaleVouchers.size() > 0) {
                                            todays_trans_empty.setVisibility(View.GONE);
                                            todaysTransadapter = new TodaysVoucherAdapter(getActivity(), ListofTodaysSaleVouchers);
                                            horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                            horizontalLayoutManager.setStackFromEnd(false); //i reomved sorting because backend is coming with order.
                                            TodaysTransList.setLayoutManager(horizontalLayoutManager);
                                            TodaysTransList.getRecycledViewPool().clear();
                                            todaysTransadapter.notifyDataSetChanged();
                                            TodaysTransList.setAdapter(todaysTransadapter);
                                            TotalTransaction(ListofTodaysSaleVouchers);
                                            /*for (TodaysTransectionModel todayObje : ListofTodaysSaleVouchers) {
                                                //BarcodeList.add(todayObje.getBarcode());
                                                StatusValueList.add(todayObje.getVoucherStatus());
                                            }*/
                                        } else {
                                            todays_trans_empty.setVisibility(View.VISIBLE);
                                        }
                                        //AlertDialogBox(CommonMessage(R.string.TodaysTrans), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), true);
                                    } else {
                                        AlertDialogBox(CommonMessage(R.string.Transaction_history), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), false);
                                    }
                                } else {
                                    AlertDialogBox(CommonMessage(R.string.Transaction_history), CommonMessage(R.string.APINotResponding), false);
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.Transaction_history), response.message(), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.Transaction_history), response.message(), false);
                        }
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.Transaction_history), ex.getMessage(), false);
                    }
                }

                @Override
                public void onFailure(Call<TillEtopUpTransactionsModel> call, Throwable t) {
                    AlertDialogBox(CommonMessage(R.string.Transaction_history), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Transaction_history), ex.getMessage(), false);
        }
    }

    private void ETopupCancelVoucherAPI() {
        try {
            ListofCancelledSaleVouchers.clear();
            CancelInputModel cancelModel = new CancelInputModel();
            cancelModel.setCancelSaleVoucher(CancelSaleVoucher);
            cancelModel.setUserId(Common.UserId);
            cancelModel.setTillId(Common.TillId);
            cancelModel.setMacAddress(Common.MobileMacAddress);
            String stringInput = new GsonBuilder().create().toJson(cancelModel);

            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetETopupCancelVoucher(cancelModel).enqueue(new Callback<CancelInputModel>() {
                @Override
                public void onResponse(Call<CancelInputModel> call, Response<CancelInputModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.body() != null) {
                                ListofCancelledSaleVouchers.addAll(response.body().getStatus());
                                if (ListofCancelledSaleVouchers.size() > 0) {
                                    if (ListofCancelledSaleVouchers.get(0).getStatusId() == 1) {
                                        AlertDialogBox(CommonMessage(R.string.CancelVoucher), ListofCancelledSaleVouchers.get(0).getErrorMessage(), true);
                                        GetTillEtopUpTransactionsAPI();
                                        ((ETopUpActivity) getActivity()).RefreshBalance();
                                    } else {
                                        AlertDialogBox(CommonMessage(R.string.CancelVoucher), ListofCancelledSaleVouchers.get(0).getErrorMessage(), false);
                                    }
                                } else {
                                    AlertDialogBox(CommonMessage(R.string.CancelVoucher), CommonMessage(R.string.APINotResponding), false);
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.CancelVoucher), response.message(), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.CancelVoucher), response.message(), false);
                        }
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.CancelVoucher), ex.getMessage(), false);
                    }
                }

                @Override
                public void onFailure(Call<CancelInputModel> call, Throwable t) {
                    AlertDialogBox(CommonMessage(R.string.CancelVoucher), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.CancelVoucher), ex.getMessage(), false);
        }
    }

    public void TotalTransaction(ArrayList<TodaysTransectionModel> ListofTodaysSaleVouchers) {
        try {
            if (ListofTodaysSaleVouchers.size() > 0) {
                EtopUpTodaysTransCount.setText(String.valueOf(ListofTodaysSaleVouchers.size()));
                EtopUpTodaysTransAmount.setText(df.format(TotalAmountVolume(ListofTodaysSaleVouchers)));
            } else {
                EtopUpTodaysTransCount.setText("0");
                EtopUpTodaysTransAmount.setText("0");
            }
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

    public double TotalAmountVolume(ArrayList<TodaysTransectionModel> TotalScannedList) {
        double TotVolume = 0.00;
        for (TodaysTransectionModel inventoreceScanModel : TotalScannedList) {
            TotVolume = TotVolume + inventoreceScanModel.getAmount();
        }
        return TotVolume;
    }


    public class TodaysVoucherAdapter extends RecyclerView.Adapter<TodaysVoucherAdapter.GroceryViewHolder> implements Filterable {
        private List<TodaysTransectionModel> Todaysresult;
        private List<TodaysTransectionModel> TodaysresultFiler;
        Context mContext;
        TodaysTransectionModel todaysModel;

        public TodaysVoucherAdapter(Context context, List<TodaysTransectionModel> ScannedResultList) {
            this.Todaysresult = ScannedResultList;
            this.TodaysresultFiler = ScannedResultList;
            this.mContext = context;

        }

        public TodaysTransectionModel getItem(int position) {
            return TodaysresultFiler.get(position);
        }

        @Override
        public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todaysvoucher_infliator, parent, false);
            GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(GroceryViewHolder holder, final int position) {
            final TodaysTransectionModel toadysModel = TodaysresultFiler.get(position);
            String AccontIDSplit = toadysModel.getAccountID();
            holder.txtAccountID.setText(AccontIDSplit);
            //holder.txtVoucherID.setText(toadysModel.getVoucherID());
            holder.txtBarcode.setText(toadysModel.getBarcode());
            holder.txtDate.setText(toadysModel.getVoucherTime());
            holder.txtAmount.setText(String.valueOf(toadysModel.getAmount()));
            if (toadysModel.getVoucherStatus().equals("Reedemed")) {
                holder.CancelStatus.setText(CommonMessage(R.string.Redeemed));
            } else {
                holder.CancelStatus.setText(toadysModel.getVoucherStatus());
            }
            if (toadysModel.getVoucherStatusID() == 10) {
                holder.Cancel.setVisibility(View.INVISIBLE);
                holder.CancelStatus.setVisibility(View.GONE);
            } else {
                holder.Cancel.setVisibility(View.INVISIBLE);
                holder.CancelStatus.setVisibility(View.VISIBLE);
            }
            holder.Cancel.setOnClickListener(v -> {
                try {
                    todaysModel = TodaysresultFiler.get(position);
                    if (toadysModel.getVoucherStatusID() == 10) {
                        CancelSaleVoucher.clear();
                        CancelSaleVoucherModel cancelModel = new CancelSaleVoucherModel();
                        cancelModel.setID(1);
                        cancelModel.setVoucherID(String.valueOf(toadysModel.getVoucherID()));
                        CancelSaleVoucher.add(cancelModel);
                        ETopupCancelVoucherAPI();
                    } else {
                        AlertDialogBox("Cancel Voucher", "The Voucher is " + toadysModel.getVoucherStatus() + " please try others", false);
                    }
                } catch (Exception ex) {
                    CrashAnalytics.CrashReport(ex);
                    ex.printStackTrace();
                }
            });
            holder.PdfDounload.setOnClickListener(v -> {
                todaysModel = TodaysresultFiler.get(position);
                Values(todaysModel);
                PDFviewerLAYOUT.setVisibility(View.VISIBLE);
            });
        }

        @Override
        public int getItemCount() {
            return TodaysresultFiler.size();
        }

        public class GroceryViewHolder extends RecyclerView.ViewHolder {
            TextView txtAccountID, txtVoucherID, txtBarcode, txtDate, txtName, txtAmount, txtStatus, CancelStatus;
            ImageView PdfDounload, Cancel;
            LinearLayout Background;

            public GroceryViewHolder(View convertView) {
                super(convertView);
                txtAccountID = convertView.findViewById(R.id.etopup_todays_accountid);
                //txtVoucherID = convertView.findViewById(R.id.etopup_todays_voucherid);
                txtBarcode = convertView.findViewById(R.id.etopup_todays_barcode);
                txtDate = convertView.findViewById(R.id.etopup_todays_vouchertime);
                txtAmount = convertView.findViewById(R.id.etopup_todays_amount);
                //txtStatus = convertView.findViewById(R.id.etopup_todays_voucherstatus);
                Cancel = convertView.findViewById(R.id.etopup_todays_cancel);
                PdfDounload = convertView.findViewById(R.id.etopup_todays_recipt);
                CancelStatus = convertView.findViewById(R.id.etopup_todays_cancelStatus);
                Background = convertView.findViewById(R.id.etopup_todays_background);
            }
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        TodaysresultFiler = Todaysresult;
                    } else {
                        List<TodaysTransectionModel> filteredList = new ArrayList<>();
                        for (TodaysTransectionModel row : Todaysresult) {
                            if (row.getBarcode().toLowerCase().contains(charString.toLowerCase()) || row.getBarcode().contains(charSequence)) {
                                filteredList.add(row);
                            }
                        }
                        TodaysresultFiler = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = TodaysresultFiler;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    TodaysresultFiler = (ArrayList<TodaysTransectionModel>) filterResults.values;
                    notifyDataSetChanged();
                    TotalTransaction((ArrayList<TodaysTransectionModel>) TodaysresultFiler);
                }
            };
        }
    }

    private class EtopUpTodaysTransReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            refresh();
        }
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(getActivity(), Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    /*Pdf Viewer*/
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    public void Values(TodaysTransectionModel pdfViewModel) {
        try {
            Common.SiteURl = pdfViewModel.getSiteUrl();
            WebsiteTxT.setText(Common.SiteURl);
            pdf_logo.setImageBitmap(Common.VoucherLogo);
            ClientNameTxT.setText(Common.UserName);
            AddressTxT.setText(Common.ClientAddress);
            VoucherID = pdfViewModel.getVoucherID();
            VoucherTxT.setText(VoucherID);
            TillTxT.setText(Common.TillName);
            AmountTxT.setText(Common.CurrencyCode + " " + pdfViewModel.getAmount() + " /-");
            // PinTxT.setText("PIN # 4455-6677-8899");
            BarcodeValueTxT.setText(pdfViewModel.getBarcode());
            pdf_barcode.setImageBitmap(CreatePDF.CreateBarcodeImage(pdfViewModel.getBarcode()));
            /*MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(pdfViewModel.getBarcode(), BarcodeFormat.CODE_128, 600, 200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                pdf_barcode.setImageBitmap(bitmap);
            } catch (WriterException ex) {
                CrashAnalytics.CrashReport(ex);
                ex.printStackTrace();
            }*/
            /*String[] footerContnt = Common.VoucherFooterText.split("@");
            System.out.println(Arrays.asList(footerContnt));*/
            FooterContentDisplay = Common.VoucherFooterText.replace('@', '\n');
            BottomConditionsTxT.setText(FooterContentDisplay);
            //BottomRulesTxT.setText(Common.VoucherTerms);
            BottomRulesTxT.setText(Common.LanguageMap.get("ETSF_" + Common.ClientId));
            Common.CustomerCare = pdfViewModel.getCustomerCare();
            CustomerServiveNoTxT.setText(Common.CustomerCare);
            DateTxT.setText(pdfViewModel.getVoucherTime());
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

  /*  private void createPdf() {
        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        //  Display display = wm.getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        float hight = displaymetrics.heightPixels;
        float width = displaymetrics.widthPixels;
        int convertHighet = (int) hight, convertWidth = (int) width;
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);
        // write the document content

        File file = new File(directory_path);
        if (!file.exists()) {
            Timber.v("File Created : %s", String.valueOf(file.mkdirs()));
        }
        String dest = directory_path + "VoucherId-" + VoucherID + ".pdf";
        File filePath;
        filePath = new File(dest);
        try {
            document.writeTo(new FileOutputStream(filePath));
        } catch (IOException ex) {
            ex.printStackTrace();
            //Toast.makeText(getActivity(), "Something wrong: " + ex.toString(), Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
        Toast.makeText(getActivity(), "PDF Saved Sucessfully" + filePath, Toast.LENGTH_SHORT).show();
    }*/

    public String CommonMessage(int Common_Msg) {
        return getActivity().getResources().getString(Common_Msg);
    }
}

