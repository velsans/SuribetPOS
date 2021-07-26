package com.suribetpos.main.ui.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.google.gson.GsonBuilder;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.model.common.Changepassword;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    ApiInterface ClientInfoApi = null;
    EditText txtNewPassoword, txtConfirmPassword, txtOldPassword;
    Button btnChangePassowrd;
    ImageView btnCloseIMG;
    AlertDialogManager alert = new AlertDialogManager();
    private boolean isInternetPresent = false;
    SessionManager session;
    String customMsg = "", NewPassword = "", OldPassword = "";
    List<StatusModel> StatusList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_changepassowrd);
        ClientInfoApi = ApiClient.getInstance().getUserService();
        session = new SessionManager(getApplicationContext());
        session.checkLogin();
        //Get User data from session
        HashMap<String, String> user = session.getUserDetails();
        initilizationViews();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            HomeActivty();
        }
        return true;
    }

    public void HomeActivty() {
        Intent _gwwIntent = new Intent(this, NewHomeActivity.class);
        startActivity(_gwwIntent);
        finish();
    }

    private void getUserPasswordAPI() {
        StatusList.clear();
        Changepassword pChangePassword = new Changepassword();
        pChangePassword.setUserId(Common.UserId);
        pChangePassword.setNewPassword(NewPassword);
        pChangePassword.setOldPassword(OldPassword);
        pChangePassword.setMacAddress(Common.MobileMacAddress);
        String stringInput = new GsonBuilder().create().toJson(pChangePassword);
        ShowProgressBar(true);

        ClientInfoApi = ApiClient.getApiInterface();
        ClientInfoApi.getChangedPassword(pChangePassword).enqueue(new Callback<Changepassword>() {
            @Override
            public void onResponse(Call<Changepassword> call, Response<Changepassword> response) {
                if (response.body() != null) {
                    StatusList = response.body().getTable();
                    if (StatusList.get(0).getStatus() == 1) {
                        customMsg = CommonMessage(R.string.PassWordResetHead) + "\n" + Common.LanguageMap.get(StatusList.get(0).getErrorCode()) + "--" + NewPassword + "\n" + CommonMessage(R.string.login_newpassword);
                        MessageAlertSuccessFullyChange(customMsg);
                    } else {
                        AlertDialogBox(CommonMessage(R.string.PassWordResetHead), Common.LanguageMap.get(StatusList.get(0).getErrorCode()), false);
                    }
                } else {
                    AlertDialogBox(CommonMessage(R.string.PassWordResetHead), CommonMessage(R.string.APINotResponding), false);
                }
                ShowProgressBar(false);
            }

            @Override
            public void onFailure(Call<Changepassword> call, Throwable t) {
                ShowProgressBar(false);
                AlertDialogBox(CommonMessage(R.string.PassWordResetHead), t.getMessage(), false);
            }
        });
    }

    private void initilizationViews() {
        txtNewPassoword = findViewById(R.id.txtNewPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnChangePassowrd = findViewById(R.id.btnChangePassword);
        btnChangePassowrd.setOnClickListener(this);
        btnCloseIMG = findViewById(R.id.btnClose);
        btnCloseIMG.setOnClickListener(this);
        txtOldPassword = findViewById(R.id.txtOldPassword);

        txtNewPassoword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Character at 0 index is: " + s + "-" + count);
                if (count > 0) {
                    char ch1 = s.charAt(0);
                    String sam = String.valueOf(ch1);
                    //boolean res = sam.equals("0");
                    if (sam.equals("0") == true) {
                        Toasty.error(ChangePasswordActivity.this, CommonMessage(R.string.FirstLetter), Toasty.LENGTH_SHORT, true).show();
                        txtNewPassoword.setText("");
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public boolean CheckisInternetPresent() {
        try {
            isInternetPresent = ConnectionFinder.isInternetOn(getApplicationContext());
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Internet_connection), ex.toString(), false);
        }
        if (!isInternetPresent) {
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
            return false;
        } else {
            return true;
        }
    }

    private boolean ValidationChangePassowrd(String Old_password, String NewPassowrd, String ConfirmPassowrd) {
        if (Old_password.equals("")) {
            AlertDialogBox(CommonMessage(R.string.PassWord_reset_validation), CommonMessage(R.string.Old_password_is_required), false);
            txtOldPassword.requestFocus();
            return false;
        }
        if (NewPassowrd.equals("")) {
            AlertDialogBox(CommonMessage(R.string.PassWord_reset_validation), CommonMessage(R.string.New_password_is_required), false);
            txtNewPassoword.requestFocus();
            return false;
        }
        if (ConfirmPassowrd.equals("")) {
            AlertDialogBox(CommonMessage(R.string.PassWord_reset_validation), CommonMessage(R.string.Confirm_password_is_required), false);
            txtConfirmPassword.requestFocus();
            return false;
        }
        if (!NewPassowrd.equals(ConfirmPassowrd)) {
            AlertDialogBox(CommonMessage(R.string.PassWord_reset_validation), CommonMessage(R.string.New_password_and_confirm_password_should_be_same), false);
            txtNewPassoword.requestFocus();
            txtNewPassoword.setText("");
            txtConfirmPassword.setText("");
            return false;
        }
        return true;
    }

    private void MessageAlertSuccessFullyChange(String ErrorMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(ErrorMessage);
        builder.setCancelable(true);
        builder.setPositiveButton(CommonMessage(R.string.Okay),
                (dialog, id) -> {
                    dialog.cancel();
                    Intent intent = new Intent(getApplicationContext(), UserAuthenticationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Common.UserId = 0;
                    Common.UserName = "";
                    Common.UserPassword = "";
                    session.logoutUser();
                    startActivity(intent);
                    finish();
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(ChangePasswordActivity.this, Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClose:
                HomeActivty();
                break;
            case R.id.btnChangePassword:
                if (ValidationChangePassowrd(txtOldPassword.getText().toString(), txtNewPassoword.getText().toString(), txtConfirmPassword.getText().toString())) {
                    NewPassword = txtNewPassoword.getText().toString();
                    OldPassword = txtOldPassword.getText().toString();
                    if (!CheckisInternetPresent()) {
                        AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
                    } else {
                        getUserPasswordAPI();
                    }
                }
                break;
        }
    }
}
