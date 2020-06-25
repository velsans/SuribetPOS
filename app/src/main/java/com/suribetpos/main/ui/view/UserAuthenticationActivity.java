package com.suribetpos.main.ui.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.GsonBuilder;
import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.api.ApiResponseInterface;
import com.suribetpos.main.data.api.RetrofitDAO;
import com.suribetpos.main.data.fcm.AutoUpdateHelper;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.fcm.DownloadService;
import com.suribetpos.main.data.model.common.ClientInformationModel;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.topup.Securityvalidateuser;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.LocaleManager;
import com.suribetpos.main.utils.SessionManager;
import com.suribetpos.main.utils.SuribetException;

import java.io.File;
import java.math.BigInteger;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAuthenticationActivity extends BaseActivity implements ApiResponseInterface, AutoUpdateHelper.OnUpdateCheckListener {
    AlertDialog alertDialog = null;
    EditText txtUserName, txtUserPassword;
    FrameLayout btnLogin;
    LinearLayout logo_layout, Language_optionLay;
    TextView registered_pleaseTxT, resetPassWordTxT, VersionCodeTxT;
    static BigInteger UserPassword;
    ImageView txtHeader;
    private boolean isInternetPresent = false, isAfterRegistration = false;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    Toast mToast;
    ApiInterface ClientInfoApi = null;
    static Spinner Language_spin;
    public static boolean LanguageFlagLocal = false;
    public static String SelectedLanguage = "", SelectedCountryCode = "", SelectedLanguageCode = "";
    public static int LanguageID = 0;
    ApiResponseInterface apiInte;
    RetrofitDAO apiDAOInter;
    private FirebaseAnalytics fbAnalytics;
    public static HashMap<String, HashMap<String, String>> LanguageErrorCodeMap;

    public void showAToast(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(UserAuthenticationActivity.this, message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AutoUpdateHelper.with(this).OnUpdateCheck(this).check();
        registerReceiver(receiver, new IntentFilter(DownloadService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    public void Multiple_Languages() {
        try {
            SelectedLanguage = session.RetreiveLanguage();
            SelectedCountryCode = session.RetreiveCountryCode();
            SelectedLanguageCode = session.RetreiveLanguageCode();
            LanguageID = session.RetriveLanguageID();
            if (Common.MacApiStatusDetails.get(0).getStatus() == 1) {
                Language_optionLay.setVisibility(View.VISIBLE);
                apiDAOInter.LanguageList();
            } else {
                Language_optionLay.setVisibility(View.GONE);
            }
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Session Manager*/
        setContentView(R.layout.activity_user_authentication);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setTitle(CommonMessage(R.string.LoginHD));
        fbAnalytics = FirebaseAnalytics.getInstance(this);
        session = new SessionManager(getApplicationContext());
        apiInte = UserAuthenticationActivity.this;
        apiDAOInter = new RetrofitDAO(UserAuthenticationActivity.this, apiInte);
        Intialization();
        Multiple_Languages();
        try {
            /*Get Base URl*/
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            VersionCodeTxT.setText(CommonMessage(R.string.VersionCode) + ": " + pInfo.versionCode + " , " + CommonMessage(R.string.VersionName) + ": " + pInfo.versionName);
            //ClientInformatonAPI();
        } catch (PackageManager.NameNotFoundException ex) {
            CrashAnalytics.CrashReport(ex);
            ex.printStackTrace();
        }
        txtUserPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String input;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Login();
                }
                return false; // pass on to other listeners.
            }
        });
        btnLogin.setOnClickListener(v -> Login());
        resetPassWordTxT.setOnClickListener(v -> {
            Intent resetPassword = new Intent(UserAuthenticationActivity.this, ChangePasswordActivity.class);
            startActivity(resetPassword);
        });
        Language_spin.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LanguageFlagLocal = true;
                return false;
            }
        });

        Language_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (LanguageFlagLocal == true) {
                        LanguageFlagLocal = false;
                        Common.LanguageIDPOS = position;
                        LanguagesListModel LoadMOd = Common.Languages.get(position);
                        SelectedLanguage = LoadMOd.getLanguage();
                        if (SelectedLanguage.equals("Chinese")) {
                            SelectedLanguageCode = "zh";
                            SelectedCountryCode = "CN";
                        } else if (SelectedLanguage.equals("Dutch")) {
                            SelectedLanguageCode = "";
                            SelectedCountryCode = "CH";
                        } else if (SelectedLanguage.equals("English")) {
                            SelectedLanguageCode = "en";
                            SelectedCountryCode = "US";
                        }
                        LanguageID = LoadMOd.getLangID();
                        session.editLanguageSession(LanguageID, SelectedLanguage, SelectedCountryCode, SelectedLanguageCode);
                        //apiDAOInter.LanguageValues(LanguageID);
                        Common.LanguageMap = LanguageErrorCodeMap.get(SelectedLanguage);
                        LocaleManager.setNewLocale(UserAuthenticationActivity.this, SelectedLanguageCode, SelectedCountryCode);
                        recreate();
                    }
                } catch (Exception ex) {
                    CrashAnalytics.CrashReport(ex);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void LanguageSelection() {
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
        //LocaleManager.setAppLocale(this, SelectedLanguageCode, SelectedCountryCode);
    }

    public void HideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void Login() {
        HideKeyboard();
        if (UserCredentialValidation(txtUserName.getText().toString(), txtUserPassword.getText().toString())) {
            Common.UserId = Integer.parseInt(txtUserName.getText().toString());
            UserPassword = new BigInteger(txtUserPassword.getText().toString());
            Common.UserPassword = String.valueOf(UserPassword);
            //CrashAnalytics.logReportOnly("User Login some message before a crash happen");
            if (!CheckisInternetPresent()) {
                AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
            } else {
                //CrashAnalytics.FireBaseAnalytics("Login/Registration", UserAuthenticationActivity.this);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.METHOD, "login");
                fbAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
                try {
                    Common.ApiStatusDetails.clear();
                    Common.listUserDetails.clear();
                    Common.listbetTypIdDetails.clear();
                    Common.listProductDetails.clear();
                    Common.listCurrencyDetails.clear();

                    Securityvalidateuser preSecurityValidateUser = new Securityvalidateuser();
                    preSecurityValidateUser.setUserID(Common.UserId);
                    preSecurityValidateUser.setUserPassword(Common.UserPassword);
                    preSecurityValidateUser.setCurrentVersion("1.0");
                    preSecurityValidateUser.setMacAddress(Common.MobileMacAddress);
                    preSecurityValidateUser.setIPAddress("");//(Common.MobileIPAddress);
                    preSecurityValidateUser.setLocationID(Common.LocationId);
                    preSecurityValidateUser.setTillID(Common.TillId);
                    preSecurityValidateUser.setLangID(LanguageID);
                    ShowProgressBar(true);
                    String stringInput = new GsonBuilder().create().toJson(preSecurityValidateUser);
                    ClientInfoApi = ApiClient.getApiInterface();
                    ClientInfoApi.GetSecuritySBValidateUser(preSecurityValidateUser).enqueue(new Callback<Securityvalidateuser>() {
                        @Override
                        public void onResponse(Call<Securityvalidateuser> call, Response<Securityvalidateuser> response) {
                            if (SuribetException.APIException(response.code()) == true) {
                                if (response.isSuccessful()) {
                                    Common.ApiStatusDetails = response.body().getM_Item1();
                                    if (Common.ApiStatusDetails.get(0).getStatus() == 1) {
                                        Common.listUserDetails.addAll(response.body().getM_Item2());
                                        Common.listbetTypIdDetails.addAll(response.body().getM_Item3());
                                        Common.listProductDetails.addAll(response.body().getM_Item4());
                                        Common.listCurrencyDetails.addAll(response.body().getM_Item5());
                                        UserAutheticationUpdate();
                                    } else {
                                        AlertDialogBox(CommonMessage(R.string.Registration), Common.ApiStatusDetails.get(0).getMessage(), false);
                                    }
                                } else {
                                    AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                            }
                            ShowProgressBar(false);
                        }

                        @Override
                        public void onFailure(Call<Securityvalidateuser> call, Throwable t) {
                            CrashAnalytics.logReportOnly(t.toString());
                            ShowProgressBar(false);
                            AlertDialogBox(CommonMessage(R.string.ClientInfoHead), t.toString(), false);
                        }
                    });
                } catch (Exception ex) {
                    //CrashAnalytics.FireBaseAnalytics(ex.toString(), UserAuthenticationActivity.this);
                    CrashAnalytics.CrashReport(ex);
                    AlertDialogBox(CommonMessage(R.string.ClientInfoHead), ex.toString(), false);
                }
            }
        }
    }

    public void UserAutheticationUpdate() {
        if (Common.MacApiStatusDetails.get(0).getStatus() == 0) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.METHOD, "sign_up");
                fbAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);
                isAfterRegistration = true;
                ClientInformatonAPI();
                NewHomeActivity.LanguageFlagLocal = true;
            } catch (Exception ex) {
                CrashAnalytics.CrashReport(ex);
                AlertDialogBox(CommonMessage(R.string.Registration), ex.toString(), false);
            }
        } else {
            AuthenticationDetails();
        }

    }

    public void AuthenticationDetails() {
        Common.POSDefault_ClientProducts.clear();
        Common.POSDefault_ClientProducts_img.clear();
        Common.Filter_ClientProdName.clear();
        Common.Filter_ClientProdImg.clear();
        /*SharedPreferences*/
        session.createLoginSession(txtUserName.getText().toString(), txtUserPassword.getText().toString(), SelectedLanguage, LanguageID, SelectedCountryCode, SelectedLanguageCode);
        /*SharedPreferences*/
        userDetailsHand.post(userDetailsRunna);
        for (int i = 0; i < Common.defaultPOSPro.length; i++) {
            Common.POSDefault_ClientProducts.add(Common.defaultPOSPro[i]);
            Common.POSDefault_ClientProducts_img.add(BitmapFactory.decodeResource(getResources(), Common.defaultPOSPro_img[i]));
        }
        for (int j = 0; j < Common.POSDefault_ClientProducts.size(); j++) {
            for (int k = 0; k < Common.listProductDetails.size(); k++) {
                if (Common.POSDefault_ClientProducts.get(j).toString().equals(Common.listProductDetails.get(k).getProductName().toString())) {
                    Common.Filter_ClientProdName.add(Common.POSDefault_ClientProducts.get(j).toString());
                    Common.Filter_ClientProdImg.add(Common.POSDefault_ClientProducts_img.get(j));
                }
            }
        }
        /*Is Password Change Needed*/
        if (Common.listUserDetails.size() > 0) {
            int passwordchanged = Common.listUserDetails.get(0).getIsPasswordChangeNeeded();
            if (passwordchanged == 0) {
                Common.IsPassWordChangeNeeded = false;
            } else {
                Common.IsPassWordChangeNeeded = true;
            }
        }
        if (Common.Filter_ClientProdName.size() > 0) {
            Intent i = new Intent(getBaseContext(), NewHomeActivity.class);
            startActivity(i);
        } else {
            AlertDialogBox(CommonMessage(R.string.LoginHD), CommonMessage(R.string.No_Products_for_this_User_Please_Contact_Admin), false);
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public boolean CheckisInternetPresent() {
        try {
            isInternetPresent = ConnectionFinder.isInternetOn(getBaseContext());
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
        }
        if (!isInternetPresent) {
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
            return false;
        } else {
            return true;
        }
    }

    public void Intialization() {
        logo_layout = findViewById(R.id.logo_layout);
        registered_pleaseTxT = findViewById(R.id.registered_pleasetxt);
        registered_pleaseTxT.setVisibility(View.GONE);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserPassword = findViewById(R.id.txtPassword);
        txtHeader = findViewById(R.id.txtHeader);
        //progressbarlayout = findViewById(R.id.progressbar_layout);
        //progressbarlayout.setVisibility(View.GONE);
        clientLogohan.post(clientLogorun);
        txtUserName.requestFocus();
        txtUserPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
        btnLogin = findViewById(R.id.btnLogin);
        resetPassWordTxT = findViewById(R.id.forgetpassword_txt);
        VersionCodeTxT = findViewById(R.id.versionCode_txt);
        Language_spin = (Spinner) findViewById(R.id.language_spinner);
        Language_optionLay = findViewById(R.id.Language_optionLay);
    }

    Handler clientLogohan = new Handler();
    Runnable clientLogorun = new Runnable() {
        @Override
        public void run() {
            if (Common.IsLogoAvailable) {
                logo_layout.setVisibility(View.VISIBLE);
                txtHeader.setImageBitmap(Common.ClientLogo);
                registered_pleaseTxT.setVisibility(View.GONE);
            } else {
                logo_layout.setVisibility(View.GONE);
                registered_pleaseTxT.setVisibility(View.VISIBLE);
            }
        }
    };

    public void ClientInformatonAPI() {
        try {
            Common.MacApiStatusDetails.clear();
            Common.ClientinformtionDetails.clear();
            Common.ClientProductDetails.clear();
            Common.ClientCurrency.clear();
            ClientInfoApi = ApiClient.getApiInterface();
            ClientInfoApi.GetClientInformation(Common.MobileMacAddress).enqueue(new Callback<ClientInformationModel>() {
                @Override
                public void onResponse(Call<ClientInformationModel> call, Response<ClientInformationModel> response) {
                    try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.isSuccessful()) {
                                Common.MacApiStatusDetails = response.body().getM_Item1();
                                //if (isNullOrEmpty(Common.ClientErrorMsg)) {
                                if (Common.MacApiStatusDetails.get(0).getStatus() == 1) {
                                    Common.ClientinformtionDetails.addAll(response.body().getM_Item2());
                                    Common.ClientProductDetails.addAll(response.body().getM_Item3());
                                    Common.ClientCurrency.addAll(response.body().getM_Item4());

                                    Common.ClientId = Common.ClientinformtionDetails.get(0).getClientID();
                                    Common.ClientName = Common.ClientinformtionDetails.get(0).getClientName().toString();
                                    Common.TillId = Common.ClientinformtionDetails.get(0).getTillId();

                                    Common.LocationId = Common.ClientinformtionDetails.get(0).getLocationId();
                                    Common.LocationTypeId = Common.ClientinformtionDetails.get(0).getLocationTypeId();
                                    if (isNullOrEmpty(Common.ClientinformtionDetails.get(0).getClientLogo())) {
                                        Common.IsLogoAvailable = false;
                                    } else {
                                        Common.IsLogoAvailable = true;
                                        Common.ClientLogoURL = Common.ClientinformtionDetails.get(0).getClientLogo();
                                        Common.ClientLogo = StringToBitMap(Common.ClientinformtionDetails.get(0).getClientLogo());
                                    }
                                    Common.CurrencyID = Common.ClientCurrency.get(0).getCurrencyId();
                                    Common.CurrencyCode = Common.ClientCurrency.get(0).getCurrencyCode();
                                }
                                clientLogohan.post(clientLogorun);
                                if (isAfterRegistration == true) {
                                    apiDAOInter.LanguageList();
                                }
                            } else {
                                AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                        }
                    } catch (Exception ex) {
                        CrashAnalytics.CrashReport(ex);
                        AlertDialogBox(CommonMessage(R.string.ClientInfoHead), ex.getMessage(), false);
                    }
                }

                @Override
                public void onFailure(Call<ClientInformationModel> call, Throwable t) {
                    CrashAnalytics.logReportOnly(t.toString());
                    AlertDialogBox(CommonMessage(R.string.ClientInfoHead), t.getMessage(), false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessage(R.string.ClientInfoHead), ex.getMessage(), false);
        }
    }

    public boolean UserCredentialValidation(String UserName, String UserPassword) {
        if (UserName.toString().equals("")) {
            AlertDialogBox(CommonMessage(R.string.Registration), CommonMessage(R.string.User_name_is_required), false);
            txtUserName.requestFocus();
            return false;
        } else if (UserPassword.toString().equals("")) {
            AlertDialogBox(CommonMessage(R.string.Registration), CommonMessage(R.string.password_is_required), false);
            txtUserPassword.requestFocus();
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler userDetailsHand = new Handler();
    Runnable userDetailsRunna = new Runnable() {
        @Override
        public void run() {
            try {
                Common.UserLocation = Common.listUserDetails.get(0).getLocationName();
                Common.UserName = Common.listUserDetails.get(0).getUserName();
                Common.LocationCode = Common.listUserDetails.get(0).getLocationCode();
                Common.MinimumBetCurrID = Common.listUserDetails.get(0).getMinimumBetCurrID();
                Common.SlipValidity = Common.listUserDetails.get(0).getSlipValidity();
                Common.EnableSBCurrency = String.valueOf(Common.listUserDetails.get(0).getEnableSBCurrency());
                Common.CurrentDateTime = Common.listUserDetails.get(0).getCurrentDateTime();
                Common.ClientName = Common.listUserDetails.get(0).getClientName();
                Common.ClientAddress = Common.listUserDetails.get(0).getAddress();
                Common.CountryClientName = Common.listUserDetails.get(0).getClientName();
                Common.ClientId = Common.listUserDetails.get(0).getClientID();
                Common.TillId = Common.listUserDetails.get(0).getTillID();
                Common.ClintPhoneNumber = Common.listUserDetails.get(0).getPhone();
                Common.TillName = Common.listUserDetails.get(0).getTillName();
                //Common.CountryID = Integer.valueOf (Common.listUserDetails.get (0).getCountryCode());
                Common.CountryCode = String.valueOf(Common.listUserDetails.get(0).getCountryCode());
            } catch (Exception ex) {
                CrashAnalytics.CrashReport(ex);
                showAToast(ex.toString());
            }
        }
    };

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(UserAuthenticationActivity.this, Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            Common.Clientlogobyte = Base64.decode(encodedString, Base64.NO_PADDING);
            Bitmap bitmap = BitmapFactory.decodeByteArray(Common.Clientlogobyte, 0, Common.Clientlogobyte.length);
            return bitmap;
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            return null;
        }
    }

    @Override
    public void IsResponce(int responce) {
        try {
            if (Common.Languages.size() > 0) {
                UserAuthenticationActivity.LanguageErrorCodeMap = new HashMap<>();
                for (int i = 0; i < Common.Languages.size(); i++) {
                    if (Common.Languages.get(i).getLangID() == LanguageID) {
                        Common.LanguageIDPOS = i;
                    }
                    apiDAOInter.AllLanguageValues(Common.Languages.get(i).getLanguage(), Common.Languages.get(i).getLangID());
                }
            }
            if (isAfterRegistration == true) {
                isAfterRegistration = false;
                AuthenticationDetails();
            }
            LanguageSelection();
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
        }
    }

    @Override
    public void onUpdateCheckListener(String urlAPP) {
        try {
            AutoUpdateshowAlertDialog(UserAuthenticationActivity.this, CommonMessage(R.string.NewVersion), CommonMessage(R.string.pleaseUpdate), true);
        } catch (Exception ex) {

        }
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ShowProgressBar(false);
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String string = bundle.getString(DownloadService.FILEPATH);
                int resultCode = bundle.getInt(DownloadService.RESULT);
                if (resultCode == RESULT_OK) {
                    Toast.makeText(context, "Download Completed", Toast.LENGTH_LONG).show();
                    OpenNewVersion(string);
                } else {
                    Toast.makeText(context, "Download failed",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    void OpenNewVersion(String location) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(location)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void AutoUpdateshowAlertDialog(final Activity context, String title, String message,
                                          Boolean status) {
        if (alertDialog != null && alertDialog.isShowing()) {
            return;
        }
        alertDialog = new AlertDialog.Builder(context, R.style.MyAlertDialogTheme).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        //alertDialog.setCancelable(false);
        if (status != null)
            alertDialog.setIcon((status) ? R.mipmap.success : R.mipmap.fail);
        // Setting OK Button
        alertDialog.setButton(CommonMessage(R.string.update), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ShowProgressBar(true);
                //Toast.makeText(context, AutoUpdateHelper.appUrl, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DownloadService.class);
                intent.putExtra(DownloadService.FILENAME, "SuribetMobilePOS.apk");
                intent.putExtra(DownloadService.URL, AutoUpdateHelper.appUrl);
                context.startService(intent);
                Toast.makeText(context, "Downloading...", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        alertDialog.setButton2(CommonMessage(R.string.Cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, AutoUpdateHelper.appUrl, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }
}
