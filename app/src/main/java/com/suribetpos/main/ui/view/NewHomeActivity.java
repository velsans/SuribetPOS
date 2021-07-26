package com.suribetpos.main.ui.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.ui.commission.ui.CommissionPage;
import com.suribetpos.main.ui.denomination.DenominationResponceModel;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.etopup.BalanceAmountModel;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.etopup.TillTransactionAmountModel;
import com.suribetpos.main.ui.cashout.CashoutActivity;
import com.suribetpos.main.ui.denomination.DenominationResponceModel;
import com.suribetpos.main.ui.etopup.ETopUpActivity;
import com.suribetpos.main.ui.playabletickets.ui.PlayableTicketActivity;
import com.suribetpos.main.ui.topup.ui.TopUpActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.LocaleManager;
import com.suribetpos.main.utils.SessionManager;
import com.suribetpos.main.utils.SuribetException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//

public class NewHomeActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private NewHomeAdapter ClientProduAdapter;
    private boolean isInternetPresent = false;
    AlertDialogManager alert = new AlertDialogManager();
    AlertDialog.Builder IsTillGDActive_alertDialogBuilder;
    AlertDialog IsTillGDActive_alertDialog;
    boolean dialogShown = false, dialogDGShown = false;
    TextView tvCountry, tvTimer, resetPassWordTxT;
    GridLayoutManager mLayoutManager;
    ApiInterface ClientInfoApi = null;
    String ErrorMessage;
    Toast mToast;
    /*Toolbar*/
    ImageView toolbarHome, toolbarSignOUT;
    TextView toolbarTitle, toolbarAmount;
    public static List<StatusModel> StatusList = new ArrayList<StatusModel>();
    public static ArrayList<BalanceAmountModel> BalanceAmountList = new ArrayList<>();
    //-5-2020
    Timer timer;
    TimerTask timerTask;
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();
    Spinner Language_spin;
    static boolean LanguageFlagLocal = false;
    SessionManager session;
    static String SelectedLanguage = "";

    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(NewHomeActivity.this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
        session = new SessionManager(getApplicationContext());
        LanguageSelection();
        //RefreshBalance();
        DoLoginVerifications();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_home_activity);
        ClientInfoApi = ApiClient.getInstance().getUserService();
        initiallization();
        ToolBarView();
        //onResume we start our timer so it can start when the app comes from the background
        startTimer();
        //new GetHomeClientDenomination().execute();
        if (CheckisInternetPresent() == false) {
            AlertDialogBox(CommonMessage(R.string.Denomination), CommonMessage(R.string.Internet_ConnMsg), false);
        } else {
            GetHomeClientDenominationAPI();
        }
        ProductList();
        resetPassWordTxT.setOnClickListener(v -> {
            //if (Common.IsPassWordChangeNeeded == true) {
            Intent resetPassword = new Intent(NewHomeActivity.this, ChangePasswordActivity.class);
            startActivity(resetPassword);
            //}
        });
        Language_spin.setOnTouchListener((v, event) -> {
            LanguageFlagLocal = true;
            return false;
        });

        Language_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (LanguageFlagLocal == true) {
                    LanguageFlagLocal = false;
                    Common.LanguageIDPOS = position;
                    LanguagesListModel LoadMOd = Common.Languages.get(position);
                    SelectedLanguage = LoadMOd.getLanguage();
                    if (SelectedLanguage.equals("Chinese")) {
                        UserAuthenticationActivity.SelectedLanguageCode = "zh";
                        UserAuthenticationActivity.SelectedCountryCode = "CN";
                    } else if (SelectedLanguage.equals("Dutch")) {
                        UserAuthenticationActivity.SelectedLanguageCode = "";
                        UserAuthenticationActivity.SelectedCountryCode = "CH";
                    } else if (SelectedLanguage.equals("English")) {
                        UserAuthenticationActivity.SelectedLanguageCode = "en";
                        UserAuthenticationActivity.SelectedCountryCode = "US";
                    }
                    UserAuthenticationActivity.LanguageID = LoadMOd.getLangID();
                    session.editLanguageSession(UserAuthenticationActivity.LanguageID, SelectedLanguage, UserAuthenticationActivity.SelectedCountryCode, UserAuthenticationActivity.SelectedLanguageCode);
                    Common.LanguageMap = UserAuthenticationActivity.LanguageErrorCodeMap.get(SelectedLanguage);
                    LocaleManager.setNewLocale(NewHomeActivity.this, UserAuthenticationActivity.SelectedLanguageCode, UserAuthenticationActivity.SelectedCountryCode);
                    recreate();
                    //apiDAOInter.LanguageValues(UserAuthenticationActivity.LanguageID);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // bottom timer
    public void startTimer() {
        timer = new Timer();
        initializeTimerTask();
        timer.schedule(timerTask, 0000, 1000);
    }

    protected void onStop() {
        super.onStop();
        stoptimertask();
    }

    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                handler.post(() -> {
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    final String strDate = simpleDateFormat.format(calendar.getTime());
                    tvTimer.setText(strDate);
                });
            }
        };
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Signout(CommonMessage(R.string.Are_you_SignOut));
        }
        return true;
    }

    public void LanguageSelection() {
        try {
            ArrayAdapter<String> sp_adapter = new ArrayAdapter<String>(this, R.layout.spinnertext, Common.LanguagesStringArray) {
                @Override
                public View getDropDownView(int position, View convertView, ViewGroup parent) {
                    View v = null;
                    v = super.getDropDownView(position, null, parent);
                    if (position == Common.LanguageIDPOS) {
                        v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    } else {
                        v.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    }
                    return v;
                }
            };
            sp_adapter.setDropDownViewResource(R.layout.spinner_selector);
            sp_adapter.notifyDataSetChanged();
            Language_spin.setAdapter(sp_adapter);
            Language_spin.setSelection(Common.LanguageIDPOS);
        } catch (Exception ex) {

        }
    }

    public void ProductList() {
        try {
            mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            ClientProduAdapter = new NewHomeAdapter(this, Common.Filter_ClientProdName, Common.Filter_ClientProdImg);
            ClientProduAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(ClientProduAdapter);
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            ex.printStackTrace();
        }
    }

  /*  public void setActionBarTitle(String title) {
        try {
            if (title.isEmpty() || title.equals(null) || title.equals("null")) {
                actionBar.setTitle("Home");
            } else {
                actionBar.setTitle(title);
            }
        } catch (Exception ex) {

        }
    }*/

    public boolean CheckisInternetPresent() {
        try {
            isInternetPresent = ConnectionFinder.isInternetOn(NewHomeActivity.this);
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            Toast.makeText(NewHomeActivity.this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
        if (!isInternetPresent) {
            return false;
        } else {
            return true;
        }
    }

    private void initiallization() {
        tvCountry = findViewById(R.id.tvCountry);
        tvTimer = findViewById(R.id.tvTime);
        tvCountry.setText(Common.UserLocation + " (" + Common.LocationCode + ")");
        //mToolbar = findViewById(R.id.title_bar);
        recyclerView = findViewById(R.id.recycler_view);
        resetPassWordTxT = findViewById(R.id.forgetpassword_txt);
        resetPassWordTxT.setPaintFlags(resetPassWordTxT.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvTimer.setText(Common.CurrentDateTime);
        Language_spin = findViewById(R.id.language_spinner);
    }

    /*Get All Denomination For User*/
    public void GetHomeClientDenominationAPI() {
        // try {
        ShowProgressBar(true);
        Common.ClientDenomination.clear();
        ClientInfoApi = ApiClient.getApiInterface();
        ClientInfoApi.GetAllActiveDenomination(Common.ClientId).enqueue(new Callback<DenominationResponceModel>() {
            @Override
            public void onResponse(Call<DenominationResponceModel> call, Response<DenominationResponceModel> response) {
                if (SuribetException.APIException(response.code()) == true) {
                    if (response.body() != null) {
                        ErrorMessage = response.body().getM_Item1();
                        if (isNullOrEmpty(ErrorMessage)) {
                            Common.ClientDenomination.addAll(response.body().getM_Item2());
                        } else {
                            AlertDialogBox(CommonMessage(R.string.Denomination), ErrorMessage, false);
                        }
                        if (Common.ClientDenomination.size() > 0) {
                            Common.TopUpCurrencyId = Common.ClientDenomination.get(0).getCurrencyID();
                        }
                    } else {
                        AlertDialogBox(CommonMessage(R.string.Denomination), response.message(), false);
                    }
                } else {
                    AlertDialogBox(CommonMessage(R.string.Denomination), response.message(), false);
                }
                ShowProgressBar(false);
            }

            @Override
            public void onFailure(Call<DenominationResponceModel> call, Throwable t) {
                CrashAnalytics.logReportOnly(t.toString());
                AlertDialogBox(CommonMessage(R.string.Denomination), t.getMessage(), false);
                ShowProgressBar(false);
            }
        });
     /*   } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Denomination), ex.getMessage(), false);
            ShowProgressBar(false);
        }*/
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public void DoLoginVerifications() {
        if (Common.LocationTypeId == 10) {
            Common.IsTillActive = Common.listUserDetails.get(0).getIsTillActive();
            Common.IsTillGDActive = Common.listUserDetails.get(0).getIsTillGDActive();
            try {
                if (Common.listUserDetails.get(0).getMaximumTillOperatingTime() != null && !Common.listUserDetails.get(0).getMaximumTillOperatingTime().toString().isEmpty()) {
                    Common.IsCurrentDateTime = Common.DateTimeFormat.parse(Common.listUserDetails.get(0).getCurrentDateTime());
                    Common.IsMaximumTillOperatingTime = Common.DateTimeFormat.parse(Common.listUserDetails.get(0).getMaximumTillOperatingTime());
                    if (Common.IsMaximumTillOperatingTime.after(Common.IsCurrentDateTime)) {
                        MessageAlertTillActive(CommonMessage(R.string.Authentication_Problem), "Till is operating over the Maximum allowed operating time for this Gaming Date!, Bet And Payouts will be disabled until you close the current gaming date and open new gaming date!");
                    }
                }
            } catch (ParseException ex) {
                CrashAnalytics.CrashReport(ex);
                ex.printStackTrace();
            }
            if (Common.IsTillActive == 0) {
                if (dialogShown == true) {
                    return;
                } else {
                    MessageAlertTillActive(CommonMessage(R.string.Authentication_Problem), Common.IsTillActiveMsg);
                }
            }
            if (Common.IsTillGDActive == 0) {
                if (dialogShown == true) {
                    return;
                } else {
                    MessageAlertTillDGActive(CommonMessage(R.string.GamingDate), CommonMessage(R.string.NotActiveGameDate));
                }
            }
        }
        RefreshBalance();
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(NewHomeActivity.this, Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    public void MessageAlertTillActive(String Title, String ErrorMessage) {
        IsTillGDActive_alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
        IsTillGDActive_alertDialog = IsTillGDActive_alertDialogBuilder.create();
        IsTillGDActive_alertDialog.setCancelable(false);
        IsTillGDActive_alertDialog.setTitle(Title);
        IsTillGDActive_alertDialog.setMessage(ErrorMessage);
        IsTillGDActive_alertDialog.setButton(CommonMessage(R.string.Okay), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogDGShown = false;
                dialog.dismiss();
                if (Common.IsTillActive == 0) {
                    Close_Application();
                }
            }
        });
        if (!IsTillGDActive_alertDialog.isShowing()) {
            dialogDGShown = true;
            IsTillGDActive_alertDialog.show();
        }
    }

    public void MessageAlertTillDGActive(String Title, String ErrorMessage) {
        IsTillGDActive_alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
        IsTillGDActive_alertDialog = IsTillGDActive_alertDialogBuilder.create();
        IsTillGDActive_alertDialog.setCancelable(false);
        IsTillGDActive_alertDialog.setTitle(Title);
        IsTillGDActive_alertDialog.setMessage(ErrorMessage);
        IsTillGDActive_alertDialog.setButton(CommonMessage(R.string.Okay), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogShown = false;
                dialog.dismiss();
            }
        });
        if (!IsTillGDActive_alertDialog.isShowing()) {
            dialogShown = true;
            IsTillGDActive_alertDialog.show();
        }
    }

    public void Close_Application() {
        NewHomeActivity.this.finish();
        System.exit(0);
    }

    public void Signout(String ErrorMessage) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(NewHomeActivity.this, R.style.MyAlertDialogTheme);
        builder1.setMessage(ErrorMessage);
        builder1.setCancelable(true);
        builder1.setPositiveButton(CommonMessage(R.string.Cancel),
                (dialog, id) -> dialog.cancel());
        builder1.setNegativeButton(CommonMessage(R.string.SignOut),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        YesOnClickListner();
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void YesOnClickListner() {
        Intent intent = new Intent(NewHomeActivity.this, UserAuthenticationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Common.UserId = 0;
        Common.UserName = "";
        Common.UserPassword = "";
        startActivity(intent);
    }

    public class NewHomeAdapter extends RecyclerView.Adapter<NewHomeAdapter.NewHomeViewholder> {
        private Activity con_text;
        ArrayList<String> productName;
        ArrayList<Bitmap> productImg;

        public NewHomeAdapter(Activity context, ArrayList<String> ProductNames, ArrayList<Bitmap> ProductImgs) {
            this.con_text = context;
            this.productName = ProductNames;
            this.productImg = ProductImgs;
        }

        @Override
        public NewHomeViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clientproductimg_infliator
                    , parent, false);
            return new NewHomeViewholder(view);
        }

        @Override
        public void onBindViewHolder(NewHomeViewholder holder, final int position) {
            //holder.clientProdctname.setText(productName.get(position).toString());
            holder.clientProductIcon.setImageBitmap(productImg.get(position));
            holder.clientProductIcon.setOnClickListener(v -> {
                String productNames = productName.get(position);
                Intent productIntent;
                if (Common.ClientProductDetails.size() > 0) {
                    for (int productFooterCount = 0; productFooterCount < Common.ClientProductDetails.size(); productFooterCount++) {
                        if (Common.ClientProductDetails.get(productFooterCount).getProductName().equals(productNames)) {
                            Common.VoucherFooterText = Common.ClientProductDetails.get(productFooterCount).getFooterContent();
                            Common.VoucherTerms = Common.ClientProductDetails.get(productFooterCount).getTerms();
                            Common.VoucherLogo = StringToBitMap(Common.ClientProductDetails.get(productFooterCount).getProductLogo());
                            break;
                        }
                    }
                }
                /*if (productNames.equals("Dailygame")) {
                    productIntent = new Intent(NewHomeActivity.this, Dail.class);
                    startActivity(productIntent);
                }*/
                if (productNames.equals("E-Topup")) {
                    productIntent = new Intent(NewHomeActivity.this, ETopUpActivity.class);
                    startActivity(productIntent);
                } else if (productNames.equals("Commissions")) {
                    //productIntent = new Intent(NewHomeActivity.this, CommissionsActivity.class);
                    productIntent = new Intent(NewHomeActivity.this, CommissionPage.class);
                    startActivity(productIntent);
                } else if (productNames.equals("OnlinePayout")) {
                    productIntent = new Intent(NewHomeActivity.this, CashoutActivity.class);
                    startActivity(productIntent);
                } else if (productNames.equals("PlayableTickets")) {
                    productIntent = new Intent(NewHomeActivity.this, PlayableTicketActivity.class);
                    startActivity(productIntent);
                } else if (productNames.equals("TopupVoucher")) {
                    productIntent = new Intent(NewHomeActivity.this, TopUpActivity.class);
                    startActivity(productIntent);
                } else {
                    AlertDialogBox(CommonMessage(R.string.Home_Page), CommonMessage(R.string.In_the_future), false);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productName.size();
        }

        public class NewHomeViewholder extends RecyclerView.ViewHolder {
            TextView clientProdctname;
            ImageView clientProductIcon;

            public NewHomeViewholder(View itemView) {
                super(itemView);
                clientProductIcon = (ImageView) itemView.findViewById(R.id.client_productitems);
                //clientProdctname=(TextView)itemView.findViewById(R.id.cliennt_productName);
            }
        }

        public Bitmap StringToBitMap(String encodedString) {
            try {
                Common.Clientlogobyte = Base64.decode(encodedString, Base64.NO_PADDING);
                Bitmap bitmap = BitmapFactory.decodeByteArray(Common.Clientlogobyte, 0, Common.Clientlogobyte.length);
                return bitmap;
            } catch (Exception ex) {
                CrashAnalytics.CrashReport(ex);
                String Error = ex.getMessage();
                return null;
            }
        }
    }

    public void ToolBarView() {
        toolbarHome = findViewById(R.id.toolbar_home);
        toolbarSignOUT = findViewById(R.id.toolbar_switch);
        toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(CommonMessage(R.string.Home_Page));
        toolbarHome.setVisibility(View.GONE);
        toolbarAmount = findViewById(R.id.toolbar_amount);
       /* toolbarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YesOnClickListner();
            }
        });*/
        toolbarSignOUT.setOnClickListener(v -> Signout(CommonMessage(R.string.Are_you_SignOut)));
    }

    public void RefreshBalance() {
        try {
            TillTransactionAmountModel transactionAmountModel = new TillTransactionAmountModel();
            transactionAmountModel.setUserId(Common.UserId);
            transactionAmountModel.setTillId(Common.TillId);
            transactionAmountModel.setClientId(Common.ClientId);
            transactionAmountModel.setMacAddress(Common.MobileMacAddress);
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetTillTransactionAmount(transactionAmountModel).enqueue(new Callback<TillTransactionAmountModel>() {
                @Override
                public void onResponse(Call<TillTransactionAmountModel> call, Response<TillTransactionAmountModel> response) {
                    // try {
                    if (SuribetException.APIException(response.code()) == true) {
                        if (response.body() != null) {
                            StatusList.clear();
                            BalanceAmountList.clear();
                            StatusList.addAll(response.body().getTable());
                            //if (StatusList.size() > 0) {
                            if (StatusList.get(0).getStatus() == 1) {
                                BalanceAmountList.addAll(response.body().getTable1());
                                if (BalanceAmountList.size() > 0) {
                                    Common.ClientBalance = BalanceAmountList.get(0).getTotalTransactionAmount();
                                    Common.ClinetGamingDate = BalanceAmountList.get(0).getGamingDate();
                                    Log.e("ClinetBalance", ">>>>>>>>>>" + Common.ClientBalance);
                                    styleMenuButton();
                                    //tvTimer.setText(Common.CurrentDateTime);
                                }
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.TillBalance), response.message(), false);
                        }
                    } else {
                        AlertDialogBox(CommonMessage(R.string.TillBalance), response.message(), false);
                    }
                }

                @Override
                public void onFailure(Call<TillTransactionAmountModel> call, Throwable t) {
                    CrashAnalytics.logReportOnly(t.toString());
                    ShowProgressBar(false);
                    AlertDialogBox(CommonMessage(R.string.TillBalance), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.TillBalance), ex.toString(), false);
        }
    }

    private void styleMenuButton() {
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

