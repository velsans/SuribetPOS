package com.suribetpos.main.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

import com.suribetpos.R;
import com.suribetpos.main.data.model.common.BetNumberDetails;
import com.suribetpos.main.data.model.common.Bettypiddetails_user;
import com.suribetpos.main.data.model.common.Changepassword;
import com.suribetpos.main.data.model.common.Clientcurrency;
import com.suribetpos.main.data.model.common.ClientDenominationModel;
import com.suribetpos.main.data.model.common.Clientinformation;
import com.suribetpos.main.data.model.common.Clientproductname;
import com.suribetpos.main.data.model.common.Currenydetails_user;
import com.suribetpos.main.data.model.common.Denomination;
import com.suribetpos.main.data.model.common.LanguageModel;
import com.suribetpos.main.data.model.common.ProductName_User;
import com.suribetpos.main.data.model.common.Salescommission;
import com.suribetpos.main.data.model.common.StatusModel;
import com.suribetpos.main.data.model.common.TillBalanceModel;
import com.suribetpos.main.data.model.common.Userdetails_user;
import com.suribetpos.main.data.model.languages.LanguagesListModel;
import com.suribetpos.main.data.model.topup.Salesvouchers;
import com.suribetpos.main.model.balance.Balance_CurrencyPojo;
import com.suribetpos.main.model.balance.Balance_DenominationPojo;
import com.suribetpos.main.model.balance.Balance_TotalDenomPojo;
import com.suribetpos.main.model.balance.Balance_TotalPojo;
import com.suribetpos.main.model.dailygame.DGAccountTransaction;
import com.suribetpos.main.model.dailygame.DGBetDetailsForCancel;
import com.suribetpos.main.model.dailygame.DGBetDetailsForCancelFirst;
import com.suribetpos.main.model.dailygame.DGCancelSaveDetails;
import com.suribetpos.main.model.dailygame.DGPayoutDetails;
import com.suribetpos.main.model.dailygame.DGPayoutDetailsPrint;
import com.suribetpos.main.model.dailygame.DGPayoutHistory;
import com.suribetpos.main.model.dailygame.DGActiveOpenDrawDetails;
import com.suribetpos.main.model.dailygame.DGbetnumbers;
import com.suribetpos.main.model.dailygame.DGdisplaycurrencyrate;
import com.suribetpos.main.model.dailygame.DGmultiplier;
import com.suribetpos.main.model.dailygame.DGrecentresultlimits;
import com.suribetpos.main.model.dailygame.DGremainingbetlimit;
import com.suribetpos.main.model.dailygame.DGdrawlist;
import com.suribetpos.main.model.dailygame.DGuiparameter;
import com.suribetpos.main.model.dailygame.DGInsertCancelBetDetails;
import com.suribetpos.main.model.lotto.LGAccountTransaction;
import com.suribetpos.main.model.lotto.LGCancelValitaion;
import com.suribetpos.main.model.lotto.LGCancellBetDetails;
import com.suribetpos.main.model.lotto.LGCancelledHistory;
import com.suribetpos.main.model.lotto.LGFreeTicketDetails;
import com.suribetpos.main.model.lotto.LGDetails;
import com.suribetpos.main.model.dailygame.DGReplayTicketDGDetails;
import com.suribetpos.main.model.lotto.LGPayoutHistory;
import com.suribetpos.main.model.lotto.LGPayoutTransactionAll;
import com.suribetpos.main.model.lotto.LGReplayTicketDetails;
import com.suribetpos.main.model.lotto.LGSlipDetails;
import com.suribetpos.main.model.lotto.LGSlipStatusDetails;

public class Common {
    public static String Netconnection_ClassName, UserName, UserPassword, UserLocation, ClintPhoneNumber,
            ClientName, ClientAddress, TillName, SiteURl, CustomerCare, MobileMacAddress, ServiceURLPath = "", LocationCode = "", ClinetGamingDate, ClientLogoURL,
            CurrentDateTime, EnableSBCurrency, status, CountryClientName, CountryCode, CurrencyCode, VoucherFooterText, VoucherTerms,
            IsTillActiveMsg = "Till is Not Active! Contact admin for Support!";

    public static String[] LanguagesStringArray = null;

    /*Timer for Background Process*/
    public static double ClientBalance;
    public static int UserId, TillId, CountryID, CurrencyID, LocationId, LocationTypeId, ClientId,
            TopUpCurrencyId, DrawId, DailyGameMinBet, DailyGameMinBetCurrencyCode, SlipValidity, DailyGameMaximumBet, DailyGameMaxBetCurrencyCode,
            DailyGameDefaultCurrencyId, DailyGameDrawId, IsTillActive, IsTillGDActive, MinimumBetCurrID, Exceed, LottoFreeTicketLimit, RefreshTimer = 10000, SelectedTabPosition = 0,
            LanguageIDPOS = 0, SessionLogoutTime = 2000000;
    public static SimpleDateFormat DateTimeFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
    public static DecimalFormat decimalFormat = new DecimalFormat("###,###.###");

    public static Date IsCurrentDateTime, IsMaximumTillOperatingTime;

    public static boolean AuthorizationFlag = false, timerTaskFlag = false, IsConnected = false, IsMacRegidtered = false, IsNetworkConnection = false, IsRetailer, IsLogoAvailable, topUpemptyFlag = false, topUpcancelFlag = false,
            DailyGameAfterBilling_Flag = true, TopUpVoucherAfterBilling_Flag = true, LottoAfterBilling_Flag = true, hasConnected = false, IsPassWordChangeNeeded = false, isfreeTicketFlag = true,
            AlertDialogVisibleFlag = true, DGTimerRefreshFlag = false, BettingFulFillFalg = true;

    public static String[] defaultPOSPro = {"E-Topup", "TopupVoucher", "LiveBetting", "SportsBetting", "Dailygame", "Lotto", "Commissions", "Transaction", "OnlinePayout", "Balance", "Reset Password"},
            defaultPOSProFlag = {"TopupCancel"};

    public static int[] defaultPOSPro_img = {R.mipmap.hm_etop, R.mipmap.hm_topup, R.mipmap.hm_livebet,
            R.mipmap.hm_sportbet, R.mipmap.hm_dailygame, R.mipmap.hm_lotto,
            R.mipmap.hm_commission, R.mipmap.hm_transection, R.mipmap.hm_cashout, R.mipmap.hm_balance, R.mipmap.hm_password};

    public static Bitmap ClientLogo, DGProdLogo, LGProdLogo, VoucherLogo;
    public static byte[] Clientlogobyte, DGProdLogoByte;
    public static Double DailyGameMultiplier;

    public static Denomination[] objDenominationDisplay;
    public static BetNumberDetails[] objBetDetails;
    public static Salescommission[] listSalesCommission;

    public static ArrayList<ProductName_User> UserAuthorizedProductName;
    public static ArrayList<StatusModel> ApiStatusDetails = new ArrayList<>();
    public static ArrayList<StatusModel> MacApiStatusDetails = new ArrayList<>();
    public static ArrayList<Clientinformation> ClientinformtionDetails = new ArrayList<>();
    public static ArrayList<Clientproductname> ClientProductDetails = new ArrayList<>();
    public static ArrayList<Clientproductname> ClientFlterProductDetails = new ArrayList<>();
    public static ArrayList<Clientcurrency> ClientCurrency = new ArrayList<>();
    public static ArrayList<ClientDenominationModel> ClientDenomination = new ArrayList<>();

    public static ArrayList<String> POSDefault_ClientProducts = new ArrayList<>();
    public static ArrayList<Bitmap> POSDefault_ClientProducts_img = new ArrayList<>();
    public static ArrayList<String> Filter_ClientProdName = new ArrayList<>();
    public static ArrayList<Bitmap> Filter_ClientProdImg = new ArrayList<>();
    /*DailyGame Fragments*/
    public static String DGFooterContent, DGProductLogo, DGTerms;
    public static String LGFooterContent, LGProductLogo, LGTerms;
    public static ArrayList<DGbetnumbers> objDailyGameBetNumbers = new ArrayList<>();
    public static ArrayList<DGActiveOpenDrawDetails> DailyGameDrawDetails = new ArrayList<>();
    public static ArrayList<Salesvouchers> salesVouchersPayment = new ArrayList<>();
    public static ArrayList<DGdrawlist> DailyGamesBettingDetails = new ArrayList<>();
    public static ArrayList<DGremainingbetlimit> objDailyGameRemainingBetLimit = new ArrayList<>();
    public static ArrayList<DGrecentresultlimits> objDailyGameRecentResultsLimits = new ArrayList<>();
    public static ArrayList<DGmultiplier> objDailyGameMultiplier = new ArrayList<>();
    public static ArrayList<DGuiparameter> objDailyGameUiParameter = new ArrayList<>();
    public static ArrayList<DGdisplaycurrencyrate> objDailyGameCurrencyRate = new ArrayList<>();
    public static ArrayList<DGBetDetailsForCancel> objBetDetailsForCancel = new ArrayList<>();
    public static ArrayList<DGBetDetailsForCancelFirst> objBetDetailsForCancelFirst = new ArrayList<>();
    public static ArrayList<DGInsertCancelBetDetails> objinsertCancelList = new ArrayList<>();
    public static ArrayList<DGCancelSaveDetails> objDGSavedList = new ArrayList<>();
    public static ArrayList<DGAccountTransaction> objAccountTransList = new ArrayList<>();
    public static ArrayList<DGReplayTicketDGDetails> objDGReplayTicketDetails = new ArrayList<>();
    public static ArrayList<DGPayoutHistory> objDGPayoutHistoryDetails = new ArrayList<>();
    public static ArrayList<DGPayoutDetails> objDGPayoutDetails = new ArrayList<>();
    public static ArrayList<DGPayoutDetailsPrint> objDGPayoutTransactionDetails = new ArrayList<>();


    public static ArrayList<LGReplayTicketDetails> objLGReplayTicketDetails = new ArrayList<>();
    public static ArrayList<LGFreeTicketDetails> objLGFreeTicketDetails = new ArrayList<>();
    public static ArrayList<LGPayoutTransactionAll> objLGPayoutTransactionDetails = new ArrayList<>();
    public static ArrayList<LGAccountTransaction> objLGAccountTransactionDetails = new ArrayList<>();
    public static ArrayList<LGSlipDetails> objLGSlipDetails = new ArrayList<>();
    public static ArrayList<LGSlipStatusDetails> objLGSlipStatusDetails = new ArrayList<>();
    public static ArrayList<LGPayoutHistory> objLGPayoutHistoryDetails = new ArrayList<>();
    public static ArrayList<LGCancelValitaion> objLGCancelValitaionDetails = new ArrayList<>();
    public static ArrayList<LGCancellBetDetails> objLGCancelBetDetails = new ArrayList<>();
    public static ArrayList<LGCancelledHistory> objLGCancelledHistoryDetails = new ArrayList<>();


    public static ArrayAdapter<String> mPairedDevicesArrayAdapter;
    public static BluetoothDevice connect_device;
    public static Set<BluetoothDevice> pairedDevices;

    /*Lotto Fragments*/
    public static ArrayList<LGDetails> Total_BetballsList = new ArrayList<LGDetails>();

    /*Sb.ValidateUser*/
    public static ArrayList<Userdetails_user> listUserDetails = new ArrayList<Userdetails_user>();
    public static ArrayList<Bettypiddetails_user> listbetTypIdDetails = new ArrayList<Bettypiddetails_user>();
    public static ArrayList<ProductName_User> listProductDetails = new ArrayList<ProductName_User>();
    public static ArrayList<Currenydetails_user> listCurrencyDetails = new ArrayList<Currenydetails_user>();
    public static ArrayList<TillBalanceModel> tillBalanceArrayList = new ArrayList<>();

    /*Balance Sheet*/
    public static ArrayList<Balance_TotalPojo> Total_balanceListArray = new ArrayList<>();
    public static ArrayList<Balance_CurrencyPojo> Currency_balanceListArray = new ArrayList<>();
    public static ArrayList<Balance_DenominationPojo> Denomination_balanceListArray = new ArrayList<>();
    public static ArrayList<Balance_TotalDenomPojo> TotalDenom_balanceListArray = new ArrayList<>();
    /*Languages*/
    public static ArrayList<LanguagesListModel> Languages ;
    public static HashMap<String, String> LanguageMap = new HashMap();
}
