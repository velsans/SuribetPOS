package com.suribetpos.main.data.model.common;

import java.util.Date;
import java.util.List;

public class UserLoginInformation {
	
	private int Id;
	private int UserID;
	private String UserName;
	private Date MainBankGamingDate;
	private Date TillGamingDate;
	private boolean IsTillActive;
	private boolean IsTillGDActive;
	private boolean IsMainBank;
	private String TillName;
	private String BackColor;
	private int MaxBetsPerSlip;
	private Double MaximumPayout;
	private boolean IsExtraSlipPrintOutNeeded; 
	private int MaxPayoutCurrencyID;
	private String WCFEndPointAddress;
	private Date MaximumTillOperatingTime; 
	private int MinimumBet;
	private int MinimumBetCurrID;
	private int SlipValidity;
	private int UpdateTimerIntervel;
	private String DailyGameWCFEndpointAddress;
	private String LottoGameWCFEndPointAddress;
	private boolean IsConfirmPrintNeeded;
	private long AutoClearInterval;
	private boolean IsPasswordChangeNeeded;
	private boolean OutRightBet;
	private String VirtualGameWCFEndpointAddress;
	private String LiveBetWCFEndPointAddress;
	private int TillID;
	private int LocationID;
	private String LocationCode;
	private String LocationName;
	private int AutoLogOffIdleTimeLimit;
	private int AutoLogOffTimerInterval;
    private int SBGameListRefreshInterval;
    private String GameListWCFEndPointAddress;
    private String ShopTransactionServiceAddress;
    private String LocalCurrency;
    private Date CurrentDateTime;
    private boolean EnableSBCurrency;
    private String LiveBettingListenerServiceURL;
    private String ErrorMessage;
    
    private int EmployeeId;
    private String Password;
    private String CurrentVersion;
    private String IPAddress;
    private List<BetType> BetType;
    private List<ProductName_User> ProductName;
    
    
    
    // Setter start
    
    
    
    
    
    public int setId(int iD)
    {
        return this.Id = iD;	
    }
    
    public int setUserID(int userID)
    {
    	return this.UserID = userID;
    }
    
    public String setUserName(String userName)
    {
    	return this.UserName = userName;
    }
    
    
    public Date setMainBankGamingDate(Date mainBankGamingDate)
    {
    	return this.MainBankGamingDate = mainBankGamingDate;
    }
    
    public Date setTillGamingDate(Date TillGamingDate)
    {
    	return this.TillGamingDate = TillGamingDate;
    }
    
    public boolean setIsTillActive(boolean IsTillActive)
    {
    	return this.IsTillActive = IsTillActive;
    }
    
    public boolean setIsTillGDActive(boolean IsTillGDActive)
    {
    	return this.IsTillGDActive = IsTillGDActive;
    }
    
    public boolean setIsMainBank(boolean IsMainBank)
    {
      return this.IsMainBank = IsMainBank;
    }
    
    public String setTillName(String TillName)
    {
    	return this.TillName = TillName;
    }
    
    public String setBackColor(String BackColor)
    {
    	return this.BackColor = BackColor;
    }
    
    public int setMaxBetsPerSlip(int MaxBetsPerSlip)
    {
    	return this.MaxBetsPerSlip = MaxBetsPerSlip;
    }
    
    public Double setMaximumPayout(Double MaximumPayout)
    {
    	return this.MaximumPayout = MaximumPayout;
    }
    
    public boolean setIsExtraSlipPrintOutNeeded(boolean IsExtraSlipPrintOutNeeded)
    {
    	return this.IsExtraSlipPrintOutNeeded =IsExtraSlipPrintOutNeeded ;
    }
    
    public int setMaxPayoutCurrencyID(int MaxPayoutCurrencyID)
    {
    	return this.MaxPayoutCurrencyID = MaxPayoutCurrencyID;
    }
    
    public String setWCFEndPointAddress(String WCFEndPointAddress)
    {
      return this.WCFEndPointAddress = WCFEndPointAddress;
    }
    
    public Date setMaximumTillOperatingTime(Date MaximumTillOperatingTime)
    {
    	return this.MaximumTillOperatingTime  = MaximumTillOperatingTime;
    }
    
    public int setMinimumBet(int MinimumBet)
    {
    	return this.MinimumBet = MinimumBet;
    }
    
    public int setMinimumBetCurrID(int MinimumBetCurrID)
    {
    	return this.MinimumBetCurrID = MinimumBetCurrID;
    }
    
    
    public int setSlipValidity(int SlipValidity)
    {
    	return this.SlipValidity = SlipValidity;
    }
    
    public int setUpdateTimerIntervel(int UpdateTimerIntervel)
    {
    	return this.UpdateTimerIntervel = UpdateTimerIntervel;
    }
    
    public String setDailyGameWCFEndpointAddress(String DailyGameWCFEndpointAddress)
    {
    	return this.DailyGameWCFEndpointAddress = DailyGameWCFEndpointAddress;
    }
    
    public String setLottoGameWCFEndPointAddress(String LottoGameWCFEndPointAddress)
    {
    	return this.LottoGameWCFEndPointAddress =LottoGameWCFEndPointAddress;
    }
    
    public boolean setIsConfirmPrintNeeded(boolean IsConfirmPrintNeeded)
    {
    	return this.IsConfirmPrintNeeded = IsConfirmPrintNeeded;
    }
    
    public long setAutoClearInterval(long AutoClearInterval)
    {
    	return this.AutoClearInterval =AutoClearInterval;
    }
    
    public boolean setIsPasswordChangeNeeded(boolean IsPasswordChangeNeeded)
    {
    	return this.IsPasswordChangeNeeded = IsPasswordChangeNeeded;
    }
    
    public boolean setOutRightBet(boolean OutRightBet)
    {
    	return this.OutRightBet = OutRightBet;
    }
    
    public String setVirtualGameWCFEndpointAddress(String VirtualGameWCFEndpointAddress)
    {
    	return this.VirtualGameWCFEndpointAddress =VirtualGameWCFEndpointAddress;
    }
    
    public String setLiveBetWCFEndPointAddress(String LiveBetWCFEndPointAddress)
    {
    	return this.LiveBetWCFEndPointAddress = LiveBetWCFEndPointAddress;
    }
    
    public int setTillID(int TillID)
    {
    	return this.TillID = TillID;
    }
    
    public String setLocationCode(String LocationCode)
    {
    	return this.LocationCode = LocationCode;
    }
    
    public String setLocationName(String LocationName)
    {
    	return this.LocationName = LocationName;
    }
    
    public int setAutoLogOffIdleTimeLimit(int AutoLogOffIdleTimeLimit)
    {
    	return this.AutoLogOffIdleTimeLimit = AutoLogOffIdleTimeLimit;
    }
    
    public int setAutoLogOffTimerInterval(int AutoLogOffTimerInterval)
    {
    	return this.AutoLogOffTimerInterval = AutoLogOffTimerInterval;
    }
    
    public int setSBGameListRefreshInterval(int SBGameListRefreshInterval)
    {
    	return this.SBGameListRefreshInterval = SBGameListRefreshInterval;
    }
    
    public String setGameListWCFEndPointAddress(String GameListWCFEndPointAddress)
    {
    	return this.GameListWCFEndPointAddress = GameListWCFEndPointAddress;
    }
    
    public String setShopTransactionServiceAddress(String ShopTransactionServiceAddress)
    {
    	return this.ShopTransactionServiceAddress = ShopTransactionServiceAddress;
    }
    
    public String setLocalCurrency(String LocalCurrency)
    {
    	return this.LocalCurrency = LocalCurrency;
    }
    
    public Date setCurrentDateTime(Date CurrentDateTime)
    {
    	return this.CurrentDateTime = CurrentDateTime;
    }
    
    public boolean setEnableSBCurrency(boolean EnableSBCurrency)
    {
    	return this.EnableSBCurrency = EnableSBCurrency;
    }
    
    public String setLiveBettingListenerServiceURL(String LiveBettingListenerServiceURL)
    {
    	return this.LiveBettingListenerServiceURL =LiveBettingListenerServiceURL;
    }
    
    public String setErrorMessage(String ErrorMessage)
    {
    	return this.ErrorMessage = ErrorMessage;
    }
    
    public int setEmployeeId(int EmployeeId)
    {
    	return this.EmployeeId = EmployeeId;
    }
    
    public String setPassword(String Password)
    {
    	return this.Password = Password;
    }
    
    public String setCurrentVersion(String CurrentVersion)
    {
    	return this.CurrentVersion = CurrentVersion;
    }
    
    public String setIPAddress(String IPAddress)
    {
    	return this.IPAddress =IPAddress;
    }
    
    public List<BetType> setBetType(List<BetType> BetType)
    {
    	return this.BetType = BetType;
    }
    
    public List<ProductName_User> setProductName(List<ProductName_User> ProductName)
    {
    	return this.ProductName = ProductName;
    }
    
    // Setter end
    
    
    // Getter Start
    
    public int getId()
    {
        return Id;	
    }
    
    public int getUserID()
    {
    	return UserID;
    }
    
    public String getUserName()
    {
    	return UserName;
    }
    
    
    public Date getMainBankGamingDate()
    {
    	return MainBankGamingDate;
    }
    
    public Date getTillGamingDate()
    {
    	return TillGamingDate;
    }
    
    public boolean getIsTillActive()
    {
    	return IsTillActive;
    }
    
    public boolean getIsTillGDActive()
    {
    	return IsTillGDActive;
    }
    
    public boolean getIsMainBank()
    {
      return IsMainBank;
    }
    
    public String getTillName()
    {
    	return TillName;
    }
    
    public String getBackColor()
    {
    	return BackColor;
    }
    
    public int getMaxBetsPerSlip()
    {
    	return MaxBetsPerSlip;
    }
    
    public Double getMaximumPayout()
    {
    	return MaximumPayout;
    }
    
    public boolean getIsExtraSlipPrintOutNeeded()
    {
    	return IsExtraSlipPrintOutNeeded ;
    }
    
    public int getMaxPayoutCurrencyID()
    {
    	return MaxPayoutCurrencyID ;
    }
    
    public String getWCFEndPointAddress()
    {
      return WCFEndPointAddress;
    }
    
    public Date getMaximumTillOperatingTime()
    {
    	return MaximumTillOperatingTime;
    }
    
    public int getMinimumBet()
    {
    	return MinimumBet;
    }
    
    public int getMinimumBetCurrID()
    {
    	return MinimumBetCurrID;
    }
    
    
    public int getSlipValidity()
    {
    	return SlipValidity;
    }
    
    public int getUpdateTimerIntervel()
    {
    	return UpdateTimerIntervel;
    }
    
    public String getDailyGameWCFEndpointAddress()
    {
    	return DailyGameWCFEndpointAddress;
    }
    
    public String getLottoGameWCFEndPointAddress()
    {
    	return LottoGameWCFEndPointAddress;
    }
    
    public boolean getIsConfirmPrintNeeded()
    {
    	return IsConfirmPrintNeeded ;
    }
    
    public long getAutoClearInterval()
    {
    	return AutoClearInterval;
    }
    
    public boolean getIsPasswordChangeNeeded()
    {
    	return IsPasswordChangeNeeded;
    }
    
    public boolean getOutRightBet()
    {
    	return OutRightBet;
    }
    
    public String getVirtualGameWCFEndpointAddress()
    {
    	return VirtualGameWCFEndpointAddress;
    }
    
    public String getLiveBetWCFEndPointAddress()
    {
    	return LiveBetWCFEndPointAddress;
    }
    
    public int getTillID()
    {
    	return TillID;
    }
    
    public String getLocationCode()
    {
    	return LocationCode;
    }
    
    public String getLocationName()
    {
    	return LocationName;
    }
    
    public int getAutoLogOffIdleTimeLimit()
    {
    	return AutoLogOffIdleTimeLimit;
    }
    
    public int getAutoLogOffTimerInterval()
    {
    	return AutoLogOffTimerInterval;
    }
    
    public int getSBGameListRefreshInterval()
    {
    	return SBGameListRefreshInterval;
    }
    
    public String getGameListWCFEndPointAddress()
    {
    	return GameListWCFEndPointAddress;
    }
    
    public String getShopTransactionServiceAddress()
    {
    	return ShopTransactionServiceAddress;
    }
    
    public String getLocalCurrency()
    {
    	return LocalCurrency;
    }
    
    public Date setCurrentDateTime()
    {
    	return CurrentDateTime;
    }
    
    public boolean getEnableSBCurrency()
    {
    	return EnableSBCurrency;
    }
    
    public String getLiveBettingListenerServiceURL()
    {
    	return LiveBettingListenerServiceURL;
    }
    
    public String getErrorMessage()
    {
    	return ErrorMessage;
    }
    
    
    public int getEmployeeId()
    {
    	return EmployeeId;
    }
    
    public String getPassword()
    {
    	return Password;
    }
    
    public String getCurrentVersion()
    {
    	return CurrentVersion;
    }
    
    public String getIPAddress()
    {
    	return IPAddress;
    }
    
    public List<BetType> getBetType()
    {
    	return BetType;
    }
    
    public List<ProductName_User> getProductName()
    {
    	return ProductName;
    }
    
    // Getter End
    
    
    
    public static final String EMPLOYEEID = "employeeid";
	public static final String PASSWORD = "password";
	public static final String CURRENTVERSION = "currentversion";
	public static final String MACADDRESS = "macaddress";
	public static final String IPADDRESS = "ipaddress";
}
