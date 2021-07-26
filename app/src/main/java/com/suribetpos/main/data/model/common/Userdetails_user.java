package com.suribetpos.main.data.model.common;

/**
 * Created by DEV 27 on 07/03/2017.
 */

public class Userdetails_user {

    public int UserID, IsTillActive, IsTillGDActive, IsMainBank, MaxBetsPerSlip, MaximumPayout, IsExtraSlipPrintOutNeeded, MaxPayoutCurrencyID, MinimumBet,
            MinimumBetCurrID,SlipValidity, UpdateTimerIntervel,IsConfirmPrintNeeded, AutoClearInterval, IsPasswordChangeNeeded, OutRightBet, TillID, LocationID,
            AutoLogOffIdleTimeLimit, AutoLogOffTimerInterval, SBGameListRefreshInterval, EnableSBCurrency, CountryCode,ClientID, LocationTypeID,MinCashoutValidation;

    public String UserName, MainBankGamingDate, TillGamingDate, TillName, BackColor, WCFEndPointAddress, MaximumTillOperatingTime, DailyGameWCFEndpointAddress,
            LottoGameWCFEndPointAddress, VirtualGameWCFEndpointAddress,LiveBetWCFEndPointAddress, LocationCode, LocationName, GameListWCFEndPointAddress,
            ShopTransactionServiceAddress, LocalCurrency, CurrentDateTime, LiveBettingListenerServiceURL, OtherProductServiceURL,ClientName, Address, Phone,
            GameListVGSportsApiURL, VirtualSportsClientAPIUrl, VirtualDailyGameSignalR, VirtualDailyGameAPI, GPSignalRHost, VGSignalRConn, PlayableTicketsApiURL,
            CustomerTrackerApiURL ;
    public double Playableticketmaxamt, Playableticketminamt, MaximumBetCreditLimit, MaximumPayLimit, MinimumOddValue, VGTaxPer;
    public boolean ShowCustomerDisplay, EnablePrinterValidation, ShowVGTaxCalc,EnableCashoutValidation;

    public int getIsTillActive() {
        return IsTillActive;
    }

    public void setIsTillActive(int isTillActive) {
        IsTillActive = isTillActive;
    }

    public int getIsTillGDActive() {
        return IsTillGDActive;
    }

    public void setIsTillGDActive(int isTillGDActive) {
        IsTillGDActive = isTillGDActive;
    }

    public int getIsMainBank() {
        return IsMainBank;
    }

    public void setIsMainBank(int isMainBank) {
        IsMainBank = isMainBank;
    }

    public int getMaxBetsPerSlip() {
        return MaxBetsPerSlip;
    }

    public void setMaxBetsPerSlip(int maxBetsPerSlip) {
        MaxBetsPerSlip = maxBetsPerSlip;
    }

    public int getMaximumPayout() {
        return MaximumPayout;
    }

    public void setMaximumPayout(int maximumPayout) {
        MaximumPayout = maximumPayout;
    }

    public int getIsExtraSlipPrintOutNeeded() {
        return IsExtraSlipPrintOutNeeded;
    }

    public void setIsExtraSlipPrintOutNeeded(int isExtraSlipPrintOutNeeded) {
        IsExtraSlipPrintOutNeeded = isExtraSlipPrintOutNeeded;
    }

    public int getMaxPayoutCurrencyID() {
        return MaxPayoutCurrencyID;
    }

    public void setMaxPayoutCurrencyID(int maxPayoutCurrencyID) {
        MaxPayoutCurrencyID = maxPayoutCurrencyID;
    }

    public int getMinimumBet() {
        return MinimumBet;
    }

    public void setMinimumBet(int minimumBet) {
        MinimumBet = minimumBet;
    }

    public int getMinimumBetCurrID() {
        return MinimumBetCurrID;
    }

    public void setMinimumBetCurrID(int minimumBetCurrID) {
        MinimumBetCurrID = minimumBetCurrID;
    }

    public int getSlipValidity() {
        return SlipValidity;
    }

    public void setSlipValidity(int slipValidity) {
        SlipValidity = slipValidity;
    }

    public int getUpdateTimerIntervel() {
        return UpdateTimerIntervel;
    }

    public void setUpdateTimerIntervel(int updateTimerIntervel) {
        UpdateTimerIntervel = updateTimerIntervel;
    }

    public int getIsConfirmPrintNeeded() {
        return IsConfirmPrintNeeded;
    }

    public void setIsConfirmPrintNeeded(int isConfirmPrintNeeded) {
        IsConfirmPrintNeeded = isConfirmPrintNeeded;
    }

    public int getAutoClearInterval() {
        return AutoClearInterval;
    }

    public void setAutoClearInterval(int autoClearInterval) {
        AutoClearInterval = autoClearInterval;
    }

    public int getIsPasswordChangeNeeded() {
        return IsPasswordChangeNeeded;
    }

    public void setIsPasswordChangeNeeded(int isPasswordChangeNeeded) {
        IsPasswordChangeNeeded = isPasswordChangeNeeded;
    }

    public int getOutRightBet() {
        return OutRightBet;
    }

    public void setOutRightBet(int outRightBet) {
        OutRightBet = outRightBet;
    }

    public int getTillID() {
        return TillID;
    }

    public void setTillID(int tillID) {
        TillID = tillID;
    }

    public int getLocationID() {
        return LocationID;
    }

    public void setLocationID(int locationID) {
        LocationID = locationID;
    }

    public int getAutoLogOffIdleTimeLimit() {
        return AutoLogOffIdleTimeLimit;
    }

    public void setAutoLogOffIdleTimeLimit(int autoLogOffIdleTimeLimit) {
        AutoLogOffIdleTimeLimit = autoLogOffIdleTimeLimit;
    }

    public int getAutoLogOffTimerInterval() {
        return AutoLogOffTimerInterval;
    }

    public void setAutoLogOffTimerInterval(int autoLogOffTimerInterval) {
        AutoLogOffTimerInterval = autoLogOffTimerInterval;
    }

    public int getSBGameListRefreshInterval() {
        return SBGameListRefreshInterval;
    }

    public void setSBGameListRefreshInterval(int SBGameListRefreshInterval) {
        this.SBGameListRefreshInterval = SBGameListRefreshInterval;
    }

    public int getEnableSBCurrency() {
        return EnableSBCurrency;
    }

    public void setEnableSBCurrency(int enableSBCurrency) {
        EnableSBCurrency = enableSBCurrency;
    }

    public int getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(int countryCode) {
        CountryCode = countryCode;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public int getLocationTypeID() {
        return LocationTypeID;
    }

    public void setLocationTypeID(int locationTypeID) {
        LocationTypeID = locationTypeID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getMainBankGamingDate() {
        return MainBankGamingDate;
    }

    public void setMainBankGamingDate(String mainBankGamingDate) {
        MainBankGamingDate = mainBankGamingDate;
    }

    public String getTillGamingDate() {
        return TillGamingDate;
    }

    public void setTillGamingDate(String tillGamingDate) {
        TillGamingDate = tillGamingDate;
    }

    public String getTillName() {
        return TillName;
    }

    public void setTillName(String tillName) {
        TillName = tillName;
    }

    public String getBackColor() {
        return BackColor;
    }

    public void setBackColor(String backColor) {
        BackColor = backColor;
    }

    public String getWCFEndPointAddress() {
        return WCFEndPointAddress;
    }

    public void setWCFEndPointAddress(String WCFEndPointAddress) {
        this.WCFEndPointAddress = WCFEndPointAddress;
    }

    public String getMaximumTillOperatingTime() {
        return MaximumTillOperatingTime;
    }

    public void setMaximumTillOperatingTime(String maximumTillOperatingTime) {
        MaximumTillOperatingTime = maximumTillOperatingTime;
    }

    public String getDailyGameWCFEndpointAddress() {
        return DailyGameWCFEndpointAddress;
    }

    public void setDailyGameWCFEndpointAddress(String dailyGameWCFEndpointAddress) {
        DailyGameWCFEndpointAddress = dailyGameWCFEndpointAddress;
    }

    public String getLottoGameWCFEndPointAddress() {
        return LottoGameWCFEndPointAddress;
    }

    public void setLottoGameWCFEndPointAddress(String lottoGameWCFEndPointAddress) {
        LottoGameWCFEndPointAddress = lottoGameWCFEndPointAddress;
    }

    public String getVirtualGameWCFEndpointAddress() {
        return VirtualGameWCFEndpointAddress;
    }

    public void setVirtualGameWCFEndpointAddress(String virtualGameWCFEndpointAddress) {
        VirtualGameWCFEndpointAddress = virtualGameWCFEndpointAddress;
    }

    public String getLiveBetWCFEndPointAddress() {
        return LiveBetWCFEndPointAddress;
    }

    public void setLiveBetWCFEndPointAddress(String liveBetWCFEndPointAddress) {
        LiveBetWCFEndPointAddress = liveBetWCFEndPointAddress;
    }

    public String getLocationCode() {
        return LocationCode;
    }

    public void setLocationCode(String locationCode) {
        LocationCode = locationCode;
    }

    public String getLocationName() {
        return LocationName;
    }

    public void setLocationName(String locationName) {
        LocationName = locationName;
    }

    public String getGameListWCFEndPointAddress() {
        return GameListWCFEndPointAddress;
    }

    public void setGameListWCFEndPointAddress(String gameListWCFEndPointAddress) {
        GameListWCFEndPointAddress = gameListWCFEndPointAddress;
    }

    public String getShopTransactionServiceAddress() {
        return ShopTransactionServiceAddress;
    }

    public void setShopTransactionServiceAddress(String shopTransactionServiceAddress) {
        ShopTransactionServiceAddress = shopTransactionServiceAddress;
    }

    public String getLocalCurrency() {
        return LocalCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        LocalCurrency = localCurrency;
    }

    public String getCurrentDateTime() {
        return CurrentDateTime;
    }

    public void setCurrentDateTime(String currentDateTime) {
        CurrentDateTime = currentDateTime;
    }

    public String getLiveBettingListenerServiceURL() {
        return LiveBettingListenerServiceURL;
    }

    public void setLiveBettingListenerServiceURL(String liveBettingListenerServiceURL) {
        LiveBettingListenerServiceURL = liveBettingListenerServiceURL;
    }

    public String getOtherProductServiceURL() {
        return OtherProductServiceURL;
    }

    public void setOtherProductServiceURL(String otherProductServiceURL) {
        OtherProductServiceURL = otherProductServiceURL;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGameListVGSportsApiURL() {
        return GameListVGSportsApiURL;
    }

    public void setGameListVGSportsApiURL(String gameListVGSportsApiURL) {
        GameListVGSportsApiURL = gameListVGSportsApiURL;
    }

    public String getVirtualSportsClientAPIUrl() {
        return VirtualSportsClientAPIUrl;
    }

    public void setVirtualSportsClientAPIUrl(String virtualSportsClientAPIUrl) {
        VirtualSportsClientAPIUrl = virtualSportsClientAPIUrl;
    }

    public String getVirtualDailyGameSignalR() {
        return VirtualDailyGameSignalR;
    }

    public void setVirtualDailyGameSignalR(String virtualDailyGameSignalR) {
        VirtualDailyGameSignalR = virtualDailyGameSignalR;
    }

    public String getVirtualDailyGameAPI() {
        return VirtualDailyGameAPI;
    }

    public void setVirtualDailyGameAPI(String virtualDailyGameAPI) {
        VirtualDailyGameAPI = virtualDailyGameAPI;
    }

    public String getGPSignalRHost() {
        return GPSignalRHost;
    }

    public void setGPSignalRHost(String GPSignalRHost) {
        this.GPSignalRHost = GPSignalRHost;
    }

    public String getVGSignalRConn() {
        return VGSignalRConn;
    }

    public void setVGSignalRConn(String VGSignalRConn) {
        this.VGSignalRConn = VGSignalRConn;
    }

    public String getPlayableTicketsApiURL() {
        return PlayableTicketsApiURL;
    }

    public void setPlayableTicketsApiURL(String playableTicketsApiURL) {
        PlayableTicketsApiURL = playableTicketsApiURL;
    }

    public double getPlayableticketmaxamt() {
        return Playableticketmaxamt;
    }

    public void setPlayableticketmaxamt(double playableticketmaxamt) {
        Playableticketmaxamt = playableticketmaxamt;
    }

    public double getPlayableticketminamt() {
        return Playableticketminamt;
    }

    public void setPlayableticketminamt(double playableticketminamt) {
        Playableticketminamt = playableticketminamt;
    }

    public double getMaximumBetCreditLimit() {
        return MaximumBetCreditLimit;
    }

    public void setMaximumBetCreditLimit(double maximumBetCreditLimit) {
        MaximumBetCreditLimit = maximumBetCreditLimit;
    }

    public double getMaximumPayLimit() {
        return MaximumPayLimit;
    }

    public void setMaximumPayLimit(double maximumPayLimit) {
        MaximumPayLimit = maximumPayLimit;
    }

    public double getMinimumOddValue() {
        return MinimumOddValue;
    }

    public void setMinimumOddValue(double minimumOddValue) {
        MinimumOddValue = minimumOddValue;
    }

    public double getVGTaxPer() {
        return VGTaxPer;
    }

    public void setVGTaxPer(double VGTaxPer) {
        this.VGTaxPer = VGTaxPer;
    }

    public boolean isShowCustomerDisplay() {
        return ShowCustomerDisplay;
    }

    public void setShowCustomerDisplay(boolean showCustomerDisplay) {
        ShowCustomerDisplay = showCustomerDisplay;
    }

    public boolean isEnablePrinterValidation() {
        return EnablePrinterValidation;
    }

    public void setEnablePrinterValidation(boolean enablePrinterValidation) {
        EnablePrinterValidation = enablePrinterValidation;
    }

    public boolean isShowVGTaxCalc() {
        return ShowVGTaxCalc;
    }

    public void setShowVGTaxCalc(boolean showVGTaxCalc) {
        ShowVGTaxCalc = showVGTaxCalc;
    }

    public int getUserID() {

        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getMinCashoutValidation() {
        return MinCashoutValidation;
    }

    public void setMinCashoutValidation(int minCashoutValidation) {
        MinCashoutValidation = minCashoutValidation;
    }

    public String getCustomerTrackerApiURL() {
        return CustomerTrackerApiURL;
    }

    public void setCustomerTrackerApiURL(String customerTrackerApiURL) {
        CustomerTrackerApiURL = customerTrackerApiURL;
    }

    public boolean getEnableCashoutValidation() {
        return EnableCashoutValidation;
    }

    public void setEnableCashoutValidation(boolean enableCashoutValidation) {
        EnableCashoutValidation = enableCashoutValidation;
    }
}