package com.suribetpos.main.data.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.suribetpos.R;
import com.suribetpos.main.data.fcm.CrashAnalytics;
import com.suribetpos.main.data.model.common.ClientInformationModel;
import com.suribetpos.main.data.model.languages.LanguageCodeModel;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.topup.Securityvalidateuser;
import com.suribetpos.main.ui.view.UserAuthenticationActivity;
import com.suribetpos.main.utils.AlertDialogManager;
import com.suribetpos.main.utils.Common;
import com.suribetpos.main.utils.SuribetException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitDAO {
    static ApiInterface ClientInfoApi;
    static AlertDialogManager alert = new AlertDialogManager();
    static Context conTEXT;
    static Exception exceptions = null;
    static ApiResponseInterface apiInterFACE;
    private HashMap<String, String> LanguageMap;

    public RetrofitDAO(Context context, ApiResponseInterface APIInterface) {
        this.conTEXT = context;
        this.ClientInfoApi = ApiClient.getInstance().getUserService();
        this.apiInterFACE = APIInterface;
        //ClientInfoApi = ApiClient.getApiInterface();
    }

    public RetrofitDAO(UserAuthenticationActivity userAuthenticationActivity) {
        super();
    }

    public void MacAdressVerfication() {
        try {
            //ShowProgressBar(true);
            Common.MacApiStatusDetails.clear();
            Common.ClientinformtionDetails.clear();
            Common.ClientProductDetails.clear();
            Common.ClientCurrency.clear();
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
                        } else {
                            AlertDialogBox(CommonMessages(R.string.ClientInfoHead), response.message(), false);
                        }
                    } else {
                        AlertDialogBox(CommonMessages(R.string.ClientInfoHead), response.message(), false);
                    }
                    //ShowProgressBar(false);
                }

                @Override
                public void onFailure(Call<ClientInformationModel> call, Throwable t) {
                    AlertDialogBox(CommonMessages(R.string.ClientInfoHead), t.getMessage(), false);
                    //ShowProgressBar(false);
                }
            });
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            AlertDialogBox(CommonMessages(R.string.ClientInfoHead), ex.toString(), false);
        }
    }

    public int LoginVerification() {
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
        preSecurityValidateUser.setLangID(UserAuthenticationActivity.LanguageID);
        ClientInfoApi.GetSecuritySBValidateUser(preSecurityValidateUser).enqueue(new Callback<Securityvalidateuser>() {
            @Override
            public void onResponse(Call<Securityvalidateuser> call, Response<Securityvalidateuser> response) {
                if (SuribetException.APIException(response.code()) == true) {
                    if (response.body() != null) {
                        Common.ApiStatusDetails = response.body().getM_Item1();
                        if (Common.ApiStatusDetails.get(0).getStatus() == 1) {
                            Common.listUserDetails.addAll(response.body().getM_Item2());
                            Common.listbetTypIdDetails.addAll(response.body().getM_Item3());
                            Common.listProductDetails.addAll(response.body().getM_Item4());
                            Common.listCurrencyDetails.addAll(response.body().getM_Item5());
                            //UserAutheticationUpdate();
                        } else {
                            AlertDialogBox(CommonMessages(R.string.Registration), Common.ApiStatusDetails.get(0).getMessage(), false);
                        }
                    } else {
                        AlertDialogBox(CommonMessages(R.string.ClientInfoHead), response.message(), false);
                    }
                } else {
                    AlertDialogBox(CommonMessages(R.string.ClientInfoHead), response.message(), false);
                }
                //ShowProgressBar(false);
            }

            @Override
            public void onFailure(Call<Securityvalidateuser> call, Throwable t) {
                CrashAnalytics.logReportOnly(t.toString());
                //ShowProgressBar(false);
                AlertDialogBox(CommonMessages(R.string.ClientInfoHead), t.toString(), false);
            }
        });
        return Common.ApiStatusDetails.get(0).getStatus();
    }

    public void LanguageList() {
        try {
            Common.Languages = new ArrayList<>();
            //ShowProgressBar(true);
            HashMap<String, Integer> parameters = new HashMap<>();
            parameters.put("ClientID", Common.ClientId);// client id hard core for all client (11-sep-2020)
            parameters.put("Plotformid", 3);
            parameters.put("Isdevelopment", 0);
            ClientInfoApi = ApiClient.getApiLanguageInterface();
            ClientInfoApi.getLanguageList(parameters).enqueue(new Callback<List<LanguagesListModel>>() {
                @Override
                public void onResponse(Call<List<LanguagesListModel>> call, Response<List<LanguagesListModel>> response) {
                    if (SuribetException.APIException(response.code()) == true) {
                        if (response.body() != null) {
                            Common.Languages.addAll(response.body());
                            if (Common.Languages.size() > 0) {
                                Common.LanguagesStringArray = new String[Common.Languages.size()];
                                for (int i = 0; i < Common.Languages.size(); i++) {
                                    Common.LanguagesStringArray[i] = Common.Languages.get(i).getLanguage();
                                }
                                apiInterFACE.IsResponce(1);
                            } else {
                                AlertDialogBox(CommonMessages(R.string.LanguageHead), CommonMessages(R.string.APINotResponding), false);
                            }
                        } else {
                            AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                        }
                    } else {
                        AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                    }
                    //ShowProgressBar(false);
                }

                @Override
                public void onFailure(Call<List<LanguagesListModel>> call, Throwable t) {
                    CrashAnalytics.logReportOnly(t.toString());
                    AlertDialogBox(CommonMessages(R.string.LanguageHead), t.toString(), false);
                    apiInterFACE.IsResponce(0);
                    //ShowProgressBar(false);
                }
            });
        } catch (Exception ex) {
            exceptions = ex;
            CrashAnalytics.logReportOnly(ex.toString());
        }
    }

    public void LanguageValues(int LanguageID) {
        Common.LanguageMap.clear();
        HashMap<String, Integer> parameters = new HashMap<>();
        parameters.put("LangID", LanguageID);
        parameters.put("prodid", 74);
        parameters.put("PlatForm", 3);
        ClientInfoApi = ApiClient.getApiLanguageInterface();
        ClientInfoApi.getLanguages(parameters).enqueue(new Callback<LanguageCodeModel>() {
            @Override
            public void onResponse(Call<LanguageCodeModel> call, Response<LanguageCodeModel> response) {
                if (SuribetException.APIException(response.code()) == true) {
                    if (response.body() != null) {
                        Common.LanguageMap = response.body().getM_Item1();
                        apiInterFACE.IsResponce(1);
                    } else {
                        AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                    }
                } else {
                    AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                }
            }

            @Override
            public void onFailure(Call<LanguageCodeModel> call, Throwable t) {
                CrashAnalytics.logReportOnly(t.toString());
                apiInterFACE.IsResponce(0);
            }
        });
    }

    public void AllLanguageValues(String Language, int LanguageID) {
        LanguageMap = new HashMap<>();
        HashMap<String, Integer> parameters = new HashMap<>();
        parameters.put("LangID", LanguageID);
        parameters.put("prodid", 74);
        parameters.put("PlatForm", 3);
        ClientInfoApi = ApiClient.getApiLanguageInterface();
        ClientInfoApi.getLanguages(parameters).enqueue(new Callback<LanguageCodeModel>() {
            @Override
            public void onResponse(Call<LanguageCodeModel> call, Response<LanguageCodeModel> response) {
                if (SuribetException.APIException(response.code()) == true) {
                    if (response.body() != null) {
                        LanguageMap = response.body().getM_Item1();
                        UserAuthenticationActivity.LanguageErrorCodeMap.put(Language, LanguageMap);
                        Common.LanguageMap = UserAuthenticationActivity.LanguageErrorCodeMap.get(UserAuthenticationActivity.SelectedLanguage);
                    } else {
                        AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                    }
                } else {
                    AlertDialogBox(CommonMessages(R.string.LanguageHead), response.message(), false);
                }
            }

            @Override
            public void onFailure(Call<LanguageCodeModel> call, Throwable t) {
                CrashAnalytics.logReportOnly(t.toString());
            }
        });
    }

    /*Common Functions*/
    public static String CommonMessages(int Common_Msg) {
        return conTEXT.getResources().getString(Common_Msg);
    }


    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty() || str.equals("null");
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            Common.Clientlogobyte = Base64.decode(encodedString, Base64.NO_PADDING);
            Bitmap bitmap = BitmapFactory.decodeByteArray(Common.Clientlogobyte, 0, Common.Clientlogobyte.length);
            return bitmap;
        } catch (Exception ex) {
            CrashAnalytics.CrashReport(ex);
            return null;
        }
    }

    public static void AlertDialogBox(String Title, String Message, boolean YesNo) {
        if (Common.AlertDialogVisibleFlag == true) {
            if (AlertDialogManager.alertDialog != null) {
                AlertDialogManager.alertDialog.dismiss();
                AlertDialogManager.alertDialog = null;
            }
            Common.AlertDialogVisibleFlag = false;
            alert.showAlertDialog(conTEXT, Title, Message, YesNo);
        } else {
            //do something here... if already showing

        }
    }

}
