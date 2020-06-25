/*
package com.suribetpos.main.ui.fragments.cashout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.gson.GsonBuilder;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.topup.Salesvouchers;
import com.suribetpos.main.ui.view.DenominationActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.SuribetException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

*/
/**
 * Created by DEV 27 on 06/06/2017.
 *//*


public class CreateFragment extends Fragment implements View.OnClickListener {

    ImageView CloseCashoutIMG, BarCodeIMG, SearchIMG;
    EditText RequestNumberEDT;
    TextView AccountTXT, AmountTXT, CurrencyTXT, PayTXT, CancelTXT;
    String AccountNrStr, RequestNrStr, AmountStr, CurrencyStr;
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
    */
/*Denomination add*//*

    String strDenominationValue = "", strDenominationId = "", strCurrencyId = "";
    int Quantity = 1;
    cashoutCreateReceiver cashout_todays_r;
    ApiInterface ClientInfoApi = null;

    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    */
/*static {
        CMD_INIT_PRT = new byte[]{FontDefine.ESC, (byte) 64};
    }*//*


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private class cashoutCreateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //CreateFragment.this.refresh();
        }
    }

    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(cashout_todays_r);
    }

    @Override
    public void onResume() {
        super.onResume();
        cashout_todays_r = new cashoutCreateReceiver();
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(cashout_todays_r,
                new IntentFilter("cashout_REFRESH"));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        rootView = inflater.inflate(R.layout.fragment_cashout_create, container, false);
        ClientInfoApi = ApiClient.getInstance().getUserService();
        activityContext = getActivity();
        initialization();
        scanEnable();
        RequestNumberEDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


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

    public void initialization() {
        RequestNumberEDT = rootView.findViewById(R.id.txtAccountNumber);
        RequestNumberEDT.requestFocus();
        RequestNumberEDT.setOnClickListener(this);

        CloseCashoutIMG = rootView.findViewById(R.id.OnlinePayoutLayoutClose);
        CloseCashoutIMG.setOnClickListener(this);
        BarCodeIMG = rootView.findViewById(R.id.btnScanAccountNumber);
        BarCodeIMG.setOnClickListener(this);
        SearchIMG = rootView.findViewById(R.id.cashout_search);
        SearchIMG.setOnClickListener(this);
        AccountTXT = rootView.findViewById(R.id.cashout_account);
        AmountTXT = rootView.findViewById(R.id.cashout_amount);
        CurrencyTXT = rootView.findViewById(R.id.cashout_currency);

        PayTXT = rootView.findViewById(R.id.cashout_pay);
        PayTXT.setOnClickListener(this);
        CancelTXT = rootView.findViewById(R.id.cashout_cancel);
        CancelTXT.setOnClickListener(this);
    }

    private void scanEnable() {
        Intent intent = new Intent("com.android.scanservice.scan.on", null);
        this.getActivity().sendOrderedBroadcast(intent, null);
    }

        private boolean ValidationE_CashOut(String AccountNumberVal, Double RechargeAmountVal, Double RechargeConfirmAmountVal, String MobileNumberVal, Double PaidAmountVal) {
       */
/* if (AccountNumberVal.equals("")) {
            AlertDialogBox(CommonMessage(R.string.cashoutHead), "Account number is required!", false);
            txtAccountNumberEDT.requestFocus();
            txtAccountNumberEDT.setText("");
            return false;
        } else*//*

        if (MobileNumberVal.equals("")) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.mobile_number_is_required), false);
            txtAlterMobileNoEDT.setText("");
            return false;
        } else if (RechargeAmountVal == 0.00) {
            AlertDialogBox(CommonMessage(R.string.Create_Voucher), CommonMessage(R.string.Recharge_amount_is_required), false);//txtAmount.requestFocus ();
            txtAmountEDT.setText("");
            return false;
        }
        */
/*validation for received amnt  *//*

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
                       */
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
                        }*//*

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
            AddcashoutModel addcashoutModel = new AddcashoutModel();
            addcashoutModel.setUserId(Common.UserId);
            addcashoutModel.setTillId(Common.TillId);
            addcashoutModel.setTotalPaid(txtAmount);
            addcashoutModel.setClientId(Common.ClientId);
            addcashoutModel.setMobileNumber(txtMobileNoStr);
            addcashoutModel.setAccountId(txtAccountNumberStr);
            addcashoutModel.setMacAddress(Common.MobileMacAddress);
            addcashoutModel.setAlternativeMobileNumber(txtAlterMobileNoStr);
            addcashoutModel.setClientName(Common.ClientName);

            String stringInput = new GsonBuilder().create().toJson(addcashoutModel);
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetAddcashout(addcashoutModel).enqueue(new Callback<AddcashoutModel>() {
                @Override
                public void onResponse(Call<AddcashoutModel> call, Response<AddcashoutModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.isSuccessful()) {
                                StatusList.addAll(response.body().getTable());
                                if (StatusList.size() > 0) {
                                    if (StatusList.get(0).getStatus() == 1) {
                                        ClearVlaues();
                                        AlertDialogBox(CommonMessage(R.string.Create_Voucher), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), true);
                                        ((cashoutActivity) getActivity()).RefreshBalance();
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
                public void onFailure(Call<AddcashoutModel> call, Throwable t) {
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

*/
