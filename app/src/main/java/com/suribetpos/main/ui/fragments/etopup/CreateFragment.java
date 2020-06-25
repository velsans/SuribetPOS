package com.suribetpos.main.ui.fragments.etopup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.GsonBuilder;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.topup.Salesvouchers;
import com.suribetpos.main.data.model.etopup.AddETopupModel;
import com.suribetpos.main.ui.view.DenominationActivity;
import com.suribetpos.main.ui.view.ETopUpActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.utils.SuribetException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DEV 27 on 06/06/2017.
 */

public class CreateFragment extends Fragment implements View.OnClickListener {

    Button btnSaveAccount, btnCancel, btnSaveAccountPrint;
    ImageView btnQuickAmount;
    EditText txtAccountNumberEDT, txtConfirmAmountEDT, txtAmountEDT, txtAlterMobileNotEDT, txtAlterMobileNoEDT, txtReceivedAmountEDT;
    String txtAccountNumberStr, txtAlterMobileNoStr, txtMobileNoStr;
    FrameLayout _blutoothlay;
    Button btnScan, btnTestPrint;
    boolean IsBracodeScanner = false;
    private final static int SCANNIN_GREQUEST_CODE = 1, DENOMINATION_GREQUEST_CODE = 2;
    private boolean isInternetPresent = false;
    ConnectivityManager connectivityManager;
    AlertDialogManager alert = new AlertDialogManager();
    View rootView;
    public ArrayList<Salesvouchers> listSalesVouchers = new ArrayList<Salesvouchers>();
    InputMethodManager inputManager;
    TextView tvNConnecting, mobiletxt, account_mobiletxt, txtReturnAmount;
    List<StatusModel> StatusList = new ArrayList<StatusModel>();
    Toast mToast;
    Activity activityContext;
    Double ReceivedAmount = 0.00, ReturnAmount = 0.00, txtAmount = 0.00, txtConAmount = 0.00;
    /*Denomination add*/
    String strDenominationValue = "", strDenominationId = "", strCurrencyId = "";
    int Quantity = 1;
    EtopUpCreateReceiver etopup_todays_r;
    ApiInterface ClientInfoApi = null;

    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /*static {
        CMD_INIT_PRT = new byte[]{FontDefine.ESC, (byte) 64};
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private class EtopUpCreateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //CreateFragment.this.refresh();
        }
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(etopup_todays_r);
    }

    @Override
    public void onResume() {
        super.onResume();
        etopup_todays_r = new EtopUpCreateReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(etopup_todays_r,
                new IntentFilter("ETOPUP_REFRESH"));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        rootView = inflater.inflate(R.layout.fragment_etopup_create, container, false);
        ClientInfoApi = ApiClient.getInstance().getUserService();
        activityContext = getActivity();
        initialization();
        scanEnable();
        txtAmountEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (count > 0) {
                        if (s.toString().equals("0")) {
                            showAToast(CommonMessage(R.string.AddAmount));
                        } else {
                            for (int i = 0; i < Common.ClientDenomination.size(); i++) {
                                if (Common.ClientDenomination.get(i).getDenomination() == (Integer.parseInt(s.toString()))) {
                                    strDenominationValue = String.valueOf(Common.ClientDenomination.get(i).getDenomination());
                                    strDenominationValue = strDenominationValue.substring(0, strDenominationValue.length() - 3);
                                    strDenominationId = String.valueOf(Common.ClientDenomination.get(i).getDenominationID());
                                    strCurrencyId = String.valueOf(Common.ClientDenomination.get(i).getCurrencyID());
                                } else {
                                    strDenominationValue = s.toString();
                                    strDenominationId = s.toString();
                                    strCurrencyId = String.valueOf(Common.ClientCurrency.get(0).getCurrencyId());
                                }
                            }
                            if (listSalesVouchers.size() == 1) {
                                listSalesVouchers.set(0, new Salesvouchers(1, Integer.parseInt(strCurrencyId), Integer.parseInt(strDenominationId), strDenominationValue, Quantity, strDenominationValue));
                            } else {
                                listSalesVouchers.add(0, new Salesvouchers(1, Integer.parseInt(strCurrencyId), Integer.parseInt(strDenominationId), strDenominationValue, Quantity, strDenominationValue));
                            }
                        }
                    }
                    txtConfirmAmountEDT.setText(s.toString());
                    txtReceivedAmountEDT.setText("");
                    txtReturnAmount.setText("");
                    styleMenuButton(0.00);
                } catch (Exception ex) {
                    CrashAnalytics.CrashReport(ex);
                    ex.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) { //9842429800--

            }
        });
        txtReceivedAmountEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtAmountEDT.getText().toString().length() > 0 && !txtAmountEDT.getText().toString().equals("0")) {
                    Double PaidAmount = 0.0, ReturnAmount = 0.00, selectedAmt = 0.00;
                    try {
                        if (isNullOrEmpty(s.toString())) {
                            txtReturnAmount.setText(String.valueOf(Common.decimalFormat.format(ReturnAmount)));
                        } else {
                            PaidAmount = Double.valueOf(s.toString());
                            if (isNullOrEmpty(txtAmountEDT.getText().toString())) {
                                selectedAmt = 0.00;
                            } else {
                                selectedAmt = Double.parseDouble(txtAmountEDT.getText().toString());
                            }
                            ReturnAmount = PaidAmount - selectedAmt;
                            txtReturnAmount.setText(String.valueOf(Common.decimalFormat.format(ReturnAmount)));
                        }
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                    }
                    styleMenuButton(ReturnAmount);
                } else {
                    // showAToast(CommonMessage(R.string.AddTotalAmount));
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return rootView;
    }

    private void styleMenuButton(Double ReturnAmt) {
        if (ReturnAmt > 0.00) {
            txtReturnAmount.setBackgroundColor(getActivity().getResources().getColor(R.color.green));
        } else {
            if (ReturnAmt == 0.00) {
                txtReturnAmount.setBackgroundColor(getActivity().getResources().getColor(R.color.color_black));
            } else {
                txtReturnAmount.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
            }
        }
    }

    public void initialization() {

        btnSaveAccountPrint = rootView.findViewById(R.id.btnSaveAccountPrint);
        btnSaveAccountPrint.setOnClickListener(this);

        btnQuickAmount = rootView.findViewById(R.id.btnQuickAmount);
        btnQuickAmount.setOnClickListener(this);

        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

        txtAccountNumberEDT = rootView.findViewById(R.id.txtAccountNumber);
        txtAccountNumberEDT.requestFocus();
        txtAccountNumberEDT.setOnClickListener(this);

        txtAmountEDT = rootView.findViewById(R.id.txtAmount);
        txtConfirmAmountEDT = rootView.findViewById(R.id.txtConfirmAmount);
        //txtAmount.setFocusable (false);
        //txtConfirmAmountEDT.setFocusable (false);
        txtAlterMobileNoEDT = rootView.findViewById(R.id.txtMobileNumber);
        txtAlterMobileNotEDT = rootView.findViewById(R.id.txtMobileNo);

        btnSaveAccount = rootView.findViewById(R.id.btnSaveAccount);
        btnSaveAccount.setOnClickListener(this);

        btnScan = rootView.findViewById(R.id.button_scanDri);
        btnTestPrint = rootView.findViewById(R.id.button_testPrint);

        tvNConnecting = rootView.findViewById(R.id.textView_bt_Connect);
        _blutoothlay = rootView.findViewById(R.id.blurtooth_printer);

        mobiletxt = rootView.findViewById(R.id.mobiletxtetopup);
        account_mobiletxt = rootView.findViewById(R.id.account_mobiletxt);
        txtReceivedAmountEDT = rootView.findViewById(R.id.txtReceivedAmount);
        txtReturnAmount = rootView.findViewById(R.id.txtReturnAmount);
    }

    private void scanEnable() {
        Intent intent = new Intent("com.android.scanservice.scan.on", null);
        this.getActivity().sendOrderedBroadcast(intent, null);
    }

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException ex) {
            app_installed = false;
        }
        return app_installed;
    }

    private boolean ValidationElectronicsTopUpVoucher(String AccountNumberVal, Double RechargeAmountVal, Double RechargeConfirmAmountVal, String MobileNumberVal, Double PaidAmountVal) {
       /* if (AccountNumberVal.equals("")) {
            AlertDialogBox(CommonMessage(R.string.EtopUpHead), "Account number is required!", false);
            txtAccountNumberEDT.requestFocus();
            txtAccountNumberEDT.setText("");
            return false;
        } else*/
        if (MobileNumberVal.equals("")) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.mobile_number_is_required), false);
            txtAlterMobileNoEDT.setText("");
            return false;
        } else if (RechargeAmountVal == 0.00) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.Recharge_amount_is_required), false);//txtAmount.requestFocus ();
            txtAmountEDT.setText("");
            return false;
        }
        /*validation for received amnt  */
        if (ReturnAmount < 0.00) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.Paid_amount_should_be_greater_or_equal_to_Total_amount), false);
            txtReceivedAmountEDT.setText("");
            txtReceivedAmountEDT.setError(CommonMessage(R.string.Paid_amount_should_be_greater_or_equal_to_Total_amount));
            return false;
        }
        if (PaidAmountVal == 0.00) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.Paid_amount_should_not_be_empty_and_0), false);
            txtReceivedAmountEDT.setText("");
            txtReceivedAmountEDT.setError(CommonMessage(R.string.Paid_amount_should_not_be_empty_and_0));
            return false;
        }
        if (txtAmount > PaidAmountVal) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.Paid_amount_should_not_be_less_then_Total_amount), false);
            txtReceivedAmountEDT.setText("");
            txtReceivedAmountEDT.setError(CommonMessage(R.string.Paid_amount_should_not_be_less_then_Total_amount));
            return false;
        }
        return true;
    }

    private void ConfirmationAlert(String ErrorMessage) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
        builder1.setMessage(ErrorMessage);
        builder1.setCancelable(true);
        builder1.setPositiveButton(CommonMessage(R.string.Okay),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        YesOnClickListner(dialog, id);
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(CommonMessage(R.string.Cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    private void YesOnClickListner(DialogInterface dialog, int id) {
        if (!CheckisInternetPresent()) {
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
        } else {
            //new GetElectronicTopUpVoucher().execute();
            GetElectronicTopUpVoucherAPI();
        }
    }

    public boolean CheckisInternetPresent() {
        try {
            isInternetPresent = ConnectionFinder.isInternetOn(getContext());
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            showAToast(ex.toString());
        }
        if (!isInternetPresent) {
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestCode", ">>>>>>" + requestCode);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    if (txtAccountNumberEDT.getText().toString() == null) {
                        txtAccountNumberEDT.setText(data.getStringExtra("ScannedBarcode"));
                    }
                }
                break;
            case DENOMINATION_GREQUEST_CODE:
                String strDenominationValue, strDenominationId, strCurrencyId;
                int Quantity = 1;
                Double ReceivedAmounDou = 0.0;
                try {
                    if (resultCode == Activity.RESULT_OK) {
                        listSalesVouchers.clear();
                        strDenominationValue = data.getStringExtra("DenominationValue");
                        strDenominationValue = strDenominationValue.substring(0, strDenominationValue.length() - 3);
                        strDenominationId = data.getStringExtra("DenominationId");
                        strCurrencyId = data.getStringExtra("CurrencyId");

                        listSalesVouchers.add(new Salesvouchers(1, Integer.parseInt(strCurrencyId), Integer.parseInt(strDenominationId), strDenominationValue, Quantity, strDenominationValue));

                        txtAmountEDT.setText(strDenominationValue);
                        txtConfirmAmountEDT.setText(strDenominationValue);
                        txtConfirmAmountEDT.setTag(strCurrencyId);
                        if (isNullOrEmpty(txtReceivedAmountEDT.getText().toString())) {
                            ReturnAmount = 0.00 - Double.valueOf(strDenominationValue);
                        } else {
                            ReturnAmount = Double.valueOf(txtReceivedAmountEDT.getText().toString()) - Double.valueOf(strDenominationValue);
                        }
                        txtReturnAmount.setText(String.valueOf(Common.decimalFormat.format(ReturnAmount)));
                        styleMenuButton(ReturnAmount);
                    } else if (resultCode == Activity.RESULT_CANCELED) {
                        if (txtAccountNumberEDT.getText().equals("")) {
                            txtAccountNumberEDT.requestFocus();
                        }
                    }
                } catch (Exception ex) {
                    CrashAnalytics.CrashReport(ex);
                    showAToast(ex.toString());
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSaveAccount:
                try {
                    HideKeyboard();
                    try {
                        //ReturnAmount = Double.valueOf(txtReturnAmount.getText().toString());
                        ReturnAmount = ScanValueCheckDouble(txtReturnAmount.getText().toString());
                        txtAccountNumberStr = txtAccountNumberEDT.getText().toString();
                        txtMobileNoStr = txtAlterMobileNoEDT.getText().toString();
                        txtAlterMobileNoStr = txtAlterMobileNotEDT.getText().toString();
                       /* if (isNullOrEmpty(txtAmountEDT.getText().toString())) {
                            txtAmount = 0.00;
                        } else {
                            txtAmount = Double.parseDouble(txtAmountEDT.getText().toString());
                        }
                        if (isNullOrEmpty(txtConfirmAmountEDT.getText().toString())) {
                            txtConAmount = 0.00;
                        } else {
                            txtConAmount = Double.parseDouble(txtConfirmAmountEDT.getText().toString());
                        }
                        if (isNullOrEmpty(txtReceivedAmountEDT.getText().toString())) {
                            ReceivedAmount = 0.00;
                        } else {
                            ReceivedAmount = Double.parseDouble(txtReceivedAmountEDT.getText().toString());
                        }*/
                        txtAmount = ScanValueCheckDouble(txtAmountEDT.getText().toString());
                        txtConAmount = ScanValueCheckDouble(txtConfirmAmountEDT.getText().toString());
                        ReceivedAmount = ScanValueCheckDouble(txtReceivedAmountEDT.getText().toString());
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.Create_Voucher), ex.toString(), false);
                    }
                    if (ValidationElectronicsTopUpVoucher(txtAccountNumberStr, txtAmount, txtConAmount, txtMobileNoStr, ReceivedAmount)) {
                        ConfirmationAlert(CommonMessage(R.string.Are_you_sure_you_want_to_make_recharge));
                    }
                } catch (Exception ex) {
                    CrashAnalytics.CrashReport(ex);
                    AlertDialogBox(CommonMessage(R.string.Create_Voucher), ex.toString(), false);
                }
                break;
            case R.id.btnCancel:
                ClearVlaues();
                break;
            case R.id.btnQuickAmount:
                IsBracodeScanner = false;
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), DenominationActivity.class);
                startActivityForResult(intent2, DENOMINATION_GREQUEST_CODE);
                break;
        }
    }

    public void ClearVlaues() {
        txtAccountNumberEDT.requestFocus();
        txtAccountNumberEDT.setText("");
        txtAmountEDT.setText("");
        txtConfirmAmountEDT.setText("");
        txtAlterMobileNoEDT.setText("");
        txtAlterMobileNotEDT.setText("");
        txtReceivedAmountEDT.setText("");
        txtReturnAmount.setText("");
        styleMenuButton(0.00);
        HideKeyboard();
    }

    private void GetElectronicTopUpVoucherAPI() {
        try {
            StatusList.clear();
            AddETopupModel addEtopupModel = new AddETopupModel();
            addEtopupModel.setUserId(Common.UserId);
            addEtopupModel.setTillId(Common.TillId);
            addEtopupModel.setTotalPaid(txtAmount);
            addEtopupModel.setClientId(Common.ClientId);
            addEtopupModel.setMobileNumber(txtMobileNoStr);
            addEtopupModel.setAccountId(txtAccountNumberStr);
            addEtopupModel.setMacAddress(Common.MobileMacAddress);
            addEtopupModel.setAlternativeMobileNumber(txtAlterMobileNoStr);
            addEtopupModel.setClientName(Common.ClientName);

            String stringInput = new GsonBuilder().create().toJson(addEtopupModel);
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetAddETopup(addEtopupModel).enqueue(new Callback<AddETopupModel>() {
                @Override
                public void onResponse(Call<AddETopupModel> call, Response<AddETopupModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.isSuccessful()) {
                                StatusList.addAll(response.body().getTable());
                                if (StatusList.size() > 0) {
                                    if (StatusList.get(0).getStatus() == 1) {
                                        ClearVlaues();
                                        AlertDialogBox(CommonMessage(R.string.Create_Voucher), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), true);
                                        ((ETopUpActivity) getActivity()).RefreshBalance();
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
                public void onFailure(Call<AddETopupModel> call, Throwable t) {
                    AlertDialogBox(CommonMessage(R.string.Create_Voucher), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), ex.getMessage(), false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(getActivity(), Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    public int ScanValueCheckInteger(String Str) {
        int Value = 000;
        if (isNullOrEmpty(Str)) {
        } else {
            Value = Integer.parseInt(Str);
        }
        return Value;
    }

    public double ScanValueCheckDouble(String Str) {
        Double Value = 0.0;
        if (isNullOrEmpty(Str)) {
        } else {
            Value = Double.parseDouble(Str);
        }
        return Value;
    }

    public boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public void HideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

