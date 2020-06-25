package com.suribetpos.main.ui.fragments.commissions;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.etopup.CancelCommissionModel;
import com.suribetpos.main.data.model.etopup.CommissionModel;
import com.suribetpos.main.data.model.etopup.SaleCommissionModel;
import com.suribetpos.main.data.model.etopup.TotalCommissionModel;
import com.suribetpos.main.ui.view.NewHomeActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.SuribetException;

import java.text.DateFormat;
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

public class ETopUpCommissionFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    private boolean isInternetPresent = false;
    AlertDialogManager alert = new AlertDialogManager();
    Toast mToast;
    View etopupComm_rootview;
    SalesCommissionAdapter saleCommadapter;
    CancelCommissionAdapter calcellCommadapter;
    SwipeRefreshLayout refreshLay;
    EtopUpTodaysTransReceiver etopup_todays_r;
    LinearLayoutManager horizontalLayoutManager;

    TextView txtSoldTotal, txtTotalSalesCommission, txtBalReturn, txtCanCount, txtCanAmount, tvComission, tvDate, txtnorecordfound, SoldBtn, CancelledBtn,
            cancelTxTEmpty, DateHeadTxT, FromDateTodate;
    LinearLayout linerLay, SoldLayout, CancelledLayout;
    RecyclerView sales_ListView, cancel_LisView;
    SearchView searchView;
    ApiInterface ClientInfoApi = null;
    ArrayList<SaleCommissionModel> SaleCommissionList = new ArrayList<>();
    ArrayList<TotalCommissionModel> TotalCommissionList = new ArrayList<>();
    ArrayList<CancelCommissionModel> CancelCommissionList = new ArrayList<>();

    public void refresh() {
        Log.i("Top-Refresh", "Cancelled Fragment");
        ETopUp_ListHan.post(ETopUp_ListRun);
    }

    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static ETopUpCommissionFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ETopUpCommissionFragment fragment = new ETopUpCommissionFragment();
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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        etopupComm_rootview = inflater.inflate(R.layout.fragment_sales_commision, container, false);
        intialization();
        ClientInfoApi = ApiClient.getInstance().getUserService();
        refresh();
        refreshLay.setOnRefreshListener(() -> {
            refreshLay.setRefreshing(true);
            Log.d("Swipe", "Refreshing Number");
            new Handler().postDelayed(() -> {
                refreshLay.setRefreshing(false);
                refresh();
            }, 3000);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 0) {
                    DateHeadTxT.setVisibility(View.GONE);
                } else {
                    DateHeadTxT.setVisibility(View.VISIBLE);
                }
                if (SaleCommissionList.size() > 0) {
                    saleCommadapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        return etopupComm_rootview;
    }

    public String DateFormat(String givendate) {
        String strDate = "";
        //2019-06-11T00:00:00
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

    Handler ETopUp_ListHan = new Handler();
    Runnable ETopUp_ListRun = new Runnable() {
        @Override
        public void run() {
            FromDateTodate.setText("From " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getCollectedDate()) + " To " + DateFormat(NewHomeActivity.BalanceAmountList.get(0).getGamingDate()));
            if (CheckisInternetPresent() == false) {
                AlertDialogBox(CommonMessage(R.string.CancelVoucher), CommonMessage(R.string.Internet_ConnMsg), false);
            } else {
                SaleCommissionList.clear();
                TotalCommissionList.clear();
                CancelCommissionList.clear();
                CommissionModel commissonMod = new CommissionModel();
                //commissonMod.setTillId(Common.TillId);
                if (!isNullOrEmpty(String.valueOf(Common.TillId))) {
                    commissonMod.setTillId(Common.TillId);
                } else {
                    commissonMod.setTillId(0);
                }
                if (!isNullOrEmpty(String.valueOf(Common.ClientId))) {
                    commissonMod.setClientId(Common.ClientId);
                } else {
                    commissonMod.setClientId(0);
                }
                if (!isNullOrEmpty(String.valueOf(Common.UserId))) {
                    commissonMod.setUserId(Common.UserId);
                } else {
                    commissonMod.setUserId(0);
                }
                //commissonMod.setClientId(Common.ClientId);
                commissonMod.setMacAddress(Common.MobileMacAddress);

                //((BaseActivity) getActivity()).ShowProgressBar(true);
                ClientInfoApi = ApiClient.getApiInterface();
                ClientInfoApi.GetRetailerETopUpCVoucherCommission(commissonMod).enqueue(new Callback<CommissionModel>() {
                    @Override
                    public void onResponse(Call<CommissionModel> call, Response<CommissionModel> response) {
                        try {
                            if (SuribetException.APIException(response.code()) == true) {
                                if (response.isSuccessful()) {
                                    SaleCommissionList.addAll(response.body().getSaleCommission());
                                    TotalCommissionList.addAll(response.body().getTotalCommission());
                                    CancelCommissionList.addAll(response.body().getCancelCommission());
                                    SalesHandler.post(SalesRunnable);
                                } else {
                                    AlertDialogBox(CommonMessage(R.string.EtopUp), response.message(), false);
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.EtopUp), response.message(), false);
                            }
                        } catch (Exception ex) {
                            CrashAnalytics.CrashReport(ex);
                            AlertDialogBox(CommonMessage(R.string.EtopUp), ex.getMessage(), false);
                        }
                    }

                    @Override
                    public void onFailure(Call<CommissionModel> call, Throwable t) {
                        AlertDialogBox(CommonMessage(R.string.EtopUp), t.getMessage(), false);
                    }
                });
            }
        }
    };

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    Handler SalesHandler = new Handler();
    Runnable SalesRunnable = new Runnable() {
        @Override
        public void run() {
            if (SaleCommissionList.size() > 0) {
                txtnorecordfound.setVisibility(View.GONE);
                saleCommadapter = new SalesCommissionAdapter(getActivity(), SaleCommissionList);
                horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                horizontalLayoutManager.setStackFromEnd(false); //i reomved sorting because backend is coming with order.
                sales_ListView.setLayoutManager(horizontalLayoutManager);
                sales_ListView.getRecycledViewPool().clear();
                saleCommadapter.notifyDataSetChanged();
                sales_ListView.setAdapter(saleCommadapter);
            } else {
                txtnorecordfound.setVisibility(View.VISIBLE);
            }
            if (CancelCommissionList.size() > 0) {
                cancelTxTEmpty.setVisibility(View.GONE);
                calcellCommadapter = new CancelCommissionAdapter(getActivity(), CancelCommissionList);
                horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                horizontalLayoutManager.setStackFromEnd(false); // i reomved sorting because backend is coming with order.
                cancel_LisView.setLayoutManager(horizontalLayoutManager);
                cancel_LisView.getRecycledViewPool().clear();
                calcellCommadapter.notifyDataSetChanged();
                cancel_LisView.setAdapter(calcellCommadapter);
            } else {
                cancelTxTEmpty.setVisibility(View.VISIBLE);
            }
            if (TotalCommissionList.size() > 0) {
                txtSoldTotal.setText(String.valueOf(TotalCommissionList.get(0).getTotalSold()));
                tvComission.setText(CommonMessage(R.string.Total_sales_Commission) + " (" + String.valueOf(TotalCommissionList.get(0).getSalesCommission()) + "%)");
                txtTotalSalesCommission.setText(String.valueOf(TotalCommissionList.get(0).getLocationSoldCommission()));
                txtCanCount.setText(String.valueOf(TotalCommissionList.get(0).getCancelCount()));
                txtCanAmount.setText(String.valueOf(TotalCommissionList.get(0).getCancelAmount()));
                txtBalReturn.setText(String.valueOf(TotalCommissionList.get(0).getLocationBalance()));
            }
        }
    };

    private void intialization() {
        refreshLay = etopupComm_rootview.findViewById(R.id.activity_main_swipe_refresh_layout);
        refreshLay.setColorScheme(R.color.red, R.color.green, R.color.sample_blue);

        txtSoldTotal = etopupComm_rootview.findViewById(R.id.txtSoldTotal);
        txtTotalSalesCommission = etopupComm_rootview.findViewById(R.id.txtSalesComission);
        txtBalReturn = etopupComm_rootview.findViewById(R.id.txtBalReturn);
        txtCanCount = etopupComm_rootview.findViewById(R.id.txtCancelCount);
        txtCanAmount = etopupComm_rootview.findViewById(R.id.txtCancelAmount);
        tvComission = etopupComm_rootview.findViewById(R.id.tvSalesComission);
        tvDate = etopupComm_rootview.findViewById(R.id.tvEndDate);

        linerLay = etopupComm_rootview.findViewById(R.id.layoutRoot);
        txtnorecordfound = etopupComm_rootview.findViewById(R.id.sales_cancelled_empty);
        cancelTxTEmpty = etopupComm_rootview.findViewById(R.id.cancel_cancelled_empty);
        sales_ListView = etopupComm_rootview.findViewById(R.id.sales_commissionList);
        cancel_LisView = etopupComm_rootview.findViewById(R.id.cancel_commissionList);
        SoldBtn = etopupComm_rootview.findViewById(R.id.sold_Btn);
        CancelledBtn = etopupComm_rootview.findViewById(R.id.Cancelled_Btn);
        SoldBtn.setOnClickListener(this);
        CancelledBtn.setOnClickListener(this);
        SoldLayout = etopupComm_rootview.findViewById(R.id.soldlistDetails);
        CancelledLayout = etopupComm_rootview.findViewById(R.id.cancelledlistDetails);

        DateHeadTxT = etopupComm_rootview.findViewById(R.id.soldDateHead);
        searchView = etopupComm_rootview.findViewById(R.id.soldDate_searchView);
        FromDateTodate = etopupComm_rootview.findViewById(R.id.fromdateTodate);
        Sold_Details();

    }

    public void Sold_Details() {
        SoldLayout.setVisibility(View.VISIBLE);
        CancelledLayout.setVisibility(View.GONE);
        SoldBtn.setTextColor(getActivity().getResources().getColor(R.color.white));
        CancelledBtn.setTextColor(getActivity().getResources().getColor(R.color.Login_border));
        CancelledBtn.setBackgroundColor(getActivity().getResources().getColor(R.color.material_animations_accentlight));
        SoldBtn.setBackgroundColor(getActivity().getResources().getColor(R.color.material_animations_accent));
    }

    public void Cancelled_Details() {
        SoldLayout.setVisibility(View.GONE);
        CancelledLayout.setVisibility(View.VISIBLE);
        SoldBtn.setBackgroundColor(getActivity().getResources().getColor(R.color.material_animations_accentlight));
        CancelledBtn.setBackgroundColor(getActivity().getResources().getColor(R.color.material_animations_accent));
        SoldBtn.setTextColor(getActivity().getResources().getColor(R.color.Login_border));
        CancelledBtn.setTextColor(getActivity().getResources().getColor(R.color.white));
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
            case R.id.sold_Btn:
                Sold_Details();
                break;
            case R.id.Cancelled_Btn:
                AlertDialogBox(CommonMessage(R.string.CancelVoucher), CommonMessage(R.string.In_the_future), false);
                //Cancelled_Details();
                break;
            case R.id.printer_listIcon:
                showAToast("printer_listIcon betting!");
                /*Intent serverIntent = new Intent(getActivity(), DeviceListActivity.class);
                startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICES);*/
                break;
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    public class SalesCommissionAdapter extends RecyclerView.Adapter<SalesCommissionAdapter.GroceryViewHolder> implements Filterable {
        private List<SaleCommissionModel> SaleCommissionresult;
        private List<SaleCommissionModel> SaleCommissionresultFiler;
        Context mContext;

        public SalesCommissionAdapter(Context context, List<SaleCommissionModel> ScannedResultList) {
            this.SaleCommissionresult = ScannedResultList;
            this.SaleCommissionresultFiler = ScannedResultList;
            this.mContext = context;
        }

        public SaleCommissionModel getItem(int position) {
            return SaleCommissionresultFiler.get(position);
        }

        @Override
        public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.salescommissionvalue_listview, parent, false);
            GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(GroceryViewHolder holder, final int position) {
            final SaleCommissionModel salesModel = SaleCommissionresultFiler.get(position);
            holder.txtsalesBarCode.setText(salesModel.getBarcode());
            holder.txtsalestotalAmount.setText(String.valueOf(salesModel.getDenom()));
            holder.txtSalesTime.setText(salesModel.getTime());
        }

        @Override
        public int getItemCount() {
            return SaleCommissionresultFiler.size();
        }

        public class GroceryViewHolder extends RecyclerView.ViewHolder {
            TextView txtsalestotalAmount, txtsalesBarCode, txtSalesTime, txtpayoutCurrencyCode;

            public GroceryViewHolder(View convertView) {
                super(convertView);
                txtsalestotalAmount = convertView.findViewById(R.id.salescommisssion_amount);
                txtsalesBarCode = convertView.findViewById(R.id.salescommisssion_Barcode);
                txtSalesTime = convertView.findViewById(R.id.salescommisssion_time);
                txtpayoutCurrencyCode = convertView.findViewById(R.id.paytout_CurrencyCode);
            }
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    String charString = charSequence.toString();
                    if (charString.isEmpty()) {
                        SaleCommissionresultFiler = SaleCommissionresult;
                    } else {
                        List<SaleCommissionModel> filteredList = new ArrayList<>();
                        for (SaleCommissionModel row : SaleCommissionresultFiler) {
                            if (row.getTime().toLowerCase().contains(charString.toLowerCase()) || row.getTime().contains(charSequence)) {
                                filteredList.add(row);
                            }
                        }
                        SaleCommissionresultFiler = filteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = SaleCommissionresultFiler;
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    SaleCommissionresultFiler = (ArrayList<SaleCommissionModel>) filterResults.values;
                    notifyDataSetChanged();
                    TotalSalesCommission((ArrayList<SaleCommissionModel>) SaleCommissionresultFiler);
                }
            };
        }
    }

    public class CancelCommissionAdapter extends RecyclerView.Adapter<CancelCommissionAdapter.GroceryViewHolder> {
        private List<CancelCommissionModel> result;
        Context mContext;

        public CancelCommissionAdapter(Context context, List<CancelCommissionModel> ScannedResultList) {
            this.result = ScannedResultList;
            this.mContext = context;
        }

        public CancelCommissionModel getItem(int position) {
            return result.get(position);
        }

        @Override
        public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View groceryProductView = LayoutInflater.from(parent.getContext()).inflate(R.layout.salescommissionvalue_listview, parent, false);
            GroceryViewHolder gvh = new GroceryViewHolder(groceryProductView);
            return gvh;
        }

        @Override
        public void onBindViewHolder(GroceryViewHolder holder, final int position) {
            holder.txtsalesBarCode.setText(result.get(position).getBarcode());
            //holder.txtpayoutCurrencyCode.setText(salescommisonValue_mpojo.get());
            holder.txtsalestotalAmount.setText(result.get(position).getDenom());
            holder.txtSalesTime.setText(result.get(position).getTime());
        }

        @Override
        public int getItemCount() {
            return result.size();
        }

        public class GroceryViewHolder extends RecyclerView.ViewHolder {
            TextView txtsalestotalAmount, txtsalesBarCode, txtSalesTime, txtpayoutCurrencyCode;

            public GroceryViewHolder(View convertView) {
                super(convertView);
                txtsalestotalAmount = convertView.findViewById(R.id.salescommisssion_amount);
                txtsalesBarCode = convertView.findViewById(R.id.salescommisssion_Barcode);
                txtSalesTime = convertView.findViewById(R.id.salescommisssion_time);
                txtpayoutCurrencyCode = convertView.findViewById(R.id.paytout_CurrencyCode);
            }
        }
    }

    public void TotalSalesCommission(ArrayList<SaleCommissionModel> ListofTodaysSaleVouchers) {
        try {
            if (ListofTodaysSaleVouchers.size() > 0) {
                txtSoldTotal.setText(String.valueOf(TotalAmountVolume(ListofTodaysSaleVouchers)));
                tvComission.setText(CommonMessage(R.string.Total_sales_Commission) + " (" + TotalCommissionList.get(0).getSalesCommission() + "%)");
                txtTotalSalesCommission.setText(String.valueOf(TotalCommissionList.get(0).getLocationSoldCommission()));
                txtBalReturn.setText(String.valueOf(TotalCommissionList.get(0).getLocationBalance()));
            } else {
                txtSoldTotal.setText("0");
                tvComission.setText("0");
                txtTotalSalesCommission.setText("0");
                txtBalReturn.setText("0");
            }
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

    public double TotalAmountVolume(ArrayList<SaleCommissionModel> TotalScannedList) {
        double TotVolume = 0.00;
        for (SaleCommissionModel inventoreceScanModel : TotalScannedList) {
            TotVolume = TotVolume + inventoreceScanModel.getDenom();
        }
        return TotVolume;
    }

    private class EtopUpTodaysTransReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ETopUpCommissionFragment.this.refresh();
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
}

