package com.suribetpos.main.model.dailygame;

public class DGActiveOpenDrawDetails
{
	private String DrawID ;
    private String DrawDate ;
    private String DrawDateInFormat ;
    private String Multiplier ;
	private String WinningAmt ;
    private String CloseTime ;
    private String DayOfTheWeek ;
    private String POSHeaderColor ;
    private String SkinName ;
    private String DrawStatus ;

	public String getWinningAmt() {
		return WinningAmt;
	}
	public void setWinningAmt(String winningAmt) {
		WinningAmt = winningAmt;
	}
	public void setDrawId(String drawId) {
		DrawID = drawId;
	}
	public void setDrawDate(String drawDate) {
		DrawDate = drawDate;
	}
	public void setDrawDateInFormat(String drawDateInFormat) {
		DrawDateInFormat = drawDateInFormat;
	}
	public void setMultipler(String multipler) {
		Multiplier = multipler;
	}
	public void setCloseTime(String closeTime) {
		CloseTime = closeTime;
	}
	public void setDayOfTheWeek(String dayOfTheWeek) {
		DayOfTheWeek = dayOfTheWeek;
	}
	public void setPOSHeaderCoder(String pOSHeaderCoder) {
		POSHeaderColor = pOSHeaderCoder;
	}
	public void setSkinName(String skinName) {
		SkinName = skinName;
	}
	public void setDrawStatus(String drawStatus) {
		DrawStatus = drawStatus;
	}
	public String getDrawId() {
		return DrawID;
	}
	public String getDrawDate() {
		return DrawDate;
	}
	public String getDrawDateInFormat() {
		return DrawDateInFormat;
	}
	public String getMultipler() {
		return Multiplier;
	}
	public String getCloseTime() {
		return CloseTime;
	}
	public String getDayOfTheWeek() {
		return DayOfTheWeek;
	}
	public String getPOSHeaderCoder() {
		return POSHeaderColor;
	}
	public String getSkinName() {
		return SkinName;
	}
	public String getDrawStatus() {
		return DrawStatus;
	}

}
