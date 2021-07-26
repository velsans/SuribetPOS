package com.suribetpos.main.ui.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.suribetpos.R;
import com.suribetpos.main.data.api.ApiClient;
import com.suribetpos.main.data.api.ApiInterface;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.common.AppUpdateModel;
import com.suribetpos.main.data.model.common.ClientInformationModel;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.ConnectionFinder;
import com.suribetpos.main.utils.Internet_Connection;
import com.suribetpos.main.utils.SuribetException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashScreenActivity extends AppCompatActivity {
    private static String IMEINumber;
    AlertDialogManager alert = new AlertDialogManager();
    private static boolean isInternetPresent = false;
    public static boolean isPrinterFlag = false;
    private static final int PERMISSION_REQUEST_CODE = 200;
    ApiInterface ClientInfoApi = null;
    String VersionName = "", PACKAGE_NAME = "";
    int VersionCode;
    AlertDialog.Builder UpdateApk_alertDialogBuilder;
    AlertDialog UpdateApk_alertDialog;
    AppUpdateModel AppUpdateListPojo = new AppUpdateModel();
    ProgressDialog progress_bar;
    LinearLayout progressbarlayout;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission()) {
            Toast.makeText(getApplicationContext(), CommonMessage(R.string.Permission_already_granted), Toast.LENGTH_LONG).show();
            //ApplicationUpdate();
            ClientHandler.post(ClientRunnable);
        } else {
            requestPermission();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String manufacturerModel = android.os.Build.MANUFACTURER;// + " " + android.os.Build.MODEL;
        if (manufacturerModel.equals("QUALCOMM")) {
            isPrinterFlag = true;
        } else {
            isPrinterFlag = false;
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /* check for Internet status*/
        if (!CheckisInternetPresent()) {
            setContentView(R.layout.empty_flashscreen);
            AlertDialogBox(CommonMessage(R.string.Internet_connection), CommonMessage(R.string.Internet_ConnMsg), false);
            return;
        } else {
            // super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
            setContentView(R.layout.activity_splashscreen);
            ClientInfoApi = ApiClient.getInstance().getUserService();
        }
        try {
            PackageInfo pInfo = SplashScreenActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
            PACKAGE_NAME = getApplicationContext().getPackageName();
            VersionName = pInfo.versionName;
            VersionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        progressbarlayout = findViewById(R.id.progressbar_layout);
        progressbarlayout.setVisibility(View.GONE);
    }


    Handler ClientHandler = new Handler();
    Runnable ClientRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                //Get Device IMEI Number for Mac Address field
                getIMEINumber();
                if (isNullOrEmpty(IMEINumber)) {
                    alert.ExitshowAlertDialog(SplashScreenActivity.this, CommonMessage(R.string.Mac_Address), CommonMessage(R.string.MacAddressProbMsg), false);
                    return;
                }
                Common.MobileMacAddress = IMEINumberToMacAddress(IMEINumber);
                //Common.MobileMacAddress = "00:15:5D:D5:38:56";
                //Common.MobileMacAddress = "86:16:34:04:00:89:54:6";//-till id819
                //"68b434cd9e6b2a84";//"BDF52996-0329-48E0-A4D8-1ABCE3F2FB3F";//"86:30:43:b6:9c:77:ce:04";
                //Common.MobileMacAddress ="b2:c7:58:b2:a6:e3:ea:e6";// super bet live
                Common.MacApiStatusDetails.clear();
                Common.ClientinformtionDetails.clear();
                Common.ClientProductDetails.clear();
                Common.ClientCurrency.clear();

                //ShowProgressBar(true);
                progressbarlayout.setVisibility(View.VISIBLE);
                ClientInfoApi = ApiClient.getApiInterface();
                Log.v(CommonMessage(R.string.ClientInfoHead), ">>>>>>>" + ClientInfoApi.toString());
                ClientInfoApi.GetClientInformation(Common.MobileMacAddress).enqueue(new Callback<ClientInformationModel>() {
                    @Override
                    public void onResponse(Call<ClientInformationModel> call, Response<ClientInformationModel> response) {
                        // try {
                        if (SuribetException.APIException(response.code()) == true) {
                            if (response.body() != null) {
                                Common.MacApiStatusDetails = response.body().getM_Item1();
                                //if (isNullOrEmpty(Common.ClientErrorMsg)) {
                                if (Common.MacApiStatusDetails.get(0).getStatus() == 1) {
                                    Common.ClientinformtionDetails.addAll(response.body().getM_Item2());
                                    Common.ClientProductDetails.addAll(response.body().getM_Item3());
                                    Common.ClientCurrency.addAll(response.body().getM_Item4());

                                    Common.ClientId = Common.ClientinformtionDetails.get(0).getClientID();
                                    Common.ClientName = Common.ClientinformtionDetails.get(0).getClientName();
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
                                //ShowProgressBar(false);
                                progressbarlayout.setVisibility(View.GONE);
                                UserAuthenticationActivity.LanguageFlagLocal = true;
                                Intent i = new Intent(getBaseContext(), UserAuthenticationActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessage(R.string.ClientInfoHead), response.message(), false);
                        }
                       /* } catch (Exception ex) {
                            AlertDialogBox(CommonMessage(R.string.ClinntInfoHead), e.getMessage(), false);
                        }*/
                        //ShowProgressBar(false);
                    }

                    @Override
                    public void onFailure(Call<ClientInformationModel> call, Throwable t) {
                        //ShowProgressBar(false);
                        progressbarlayout.setVisibility(View.GONE);
                        AlertDialogBox(CommonMessage(R.string.ClientInfoHead), t.getMessage(), false);
                    }
                });
            } catch (Exception ex) {
                CrashAnalytics.CrashReport(ex);
                AlertDialogBox(CommonMessage(R.string.ClientInfoHead), ex.toString(), false);
            }
        }
    };

    private String IMEINumberToMacAddress(String IMEINumber) {
        String IMEIMacAddress = "";
        int Count = 1;
        for (int lenIMEI = 0; lenIMEI < IMEINumber.length(); lenIMEI++) {
            IMEIMacAddress += IMEINumber.substring(lenIMEI, lenIMEI + 1);
            if (Count % 2 == 0) {
                IMEIMacAddress = IMEIMacAddress + ":";
            }
            Count = Count + 1;
        }
        if (IMEINumber.length() % 2 == 0) {
            IMEIMacAddress = IMEIMacAddress.substring(0, IMEIMacAddress.length() - 1);
        }
        return IMEIMacAddress;
    }

    // Get Mobile IMEI number
    public void getIMEINumber() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (ActivityCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                IMEINumber = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
            } else {

                if (telephonyManager.getDeviceId() != null) {
                    IMEINumber = telephonyManager.getDeviceId();
                } else {
                    IMEINumber = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            }
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            ex.printStackTrace();
        }
    }

    private boolean checkPermission() {
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        return result1 == PackageManager.PERMISSION_GRANTED &&
                result2 == PackageManager.PERMISSION_GRANTED &&
                result3 == PackageManager.PERMISSION_GRANTED &&
                result4 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean readAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean writeAccepted = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean PhoneAccepted = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && readAccepted && writeAccepted && PhoneAccepted) {
                        //Snackbar.make(findViewById(R.id.snackbar_content), CommonMessage(R.string.PermisssionSucess), Snackbar.LENGTH_LONG).show();
                        //ApplicationUpdate();
                        ClientHandler.post(ClientRunnable);
                    } else {
                        //Snackbar.make(findViewById(R.id.snackbar_content), CommonMessage(R.string.PermisssionFailure), Snackbar.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                PermissionAlertBox(CommonMessage(R.string.Permission_Denied_You_cannot_access_device),
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{CAMERA, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, READ_PHONE_STATE},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }
                break;
        }
    }

    private void PermissionAlertBox(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashScreenActivity.this, R.style.MyAlertDialogTheme)
                .setMessage(message)
                .setPositiveButton(CommonMessage(R.string.Okay), okListener)
                .setNegativeButton(CommonMessage(R.string.Cancel), null)
                .create()
                .show();
    }

    public boolean CheckisInternetPresent() {
        try {
            isInternetPresent = ConnectionFinder.isInternetOn(this);
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            Connection_errorActivity();
        }
        if (!isInternetPresent) {
            return false;
        } else {
            return true;
        }
    }

    public void Connection_errorActivity() {
        Intent intent = new Intent(getApplicationContext(), Internet_Connection.class);
        startActivity(intent);
    }

    public void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(SplashScreenActivity.this, Title, Message, YesNo);
        } else {
            //do something here... if already showing
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
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

    public String CommonMessage(int Common_Msg) {
        return this.getResources().getString(Common_Msg);
    }

}

