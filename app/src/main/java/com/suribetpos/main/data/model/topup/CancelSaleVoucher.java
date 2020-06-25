package com.suribetpos.main.data.model.topup;

public class CancelSaleVoucher
{
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	int ID;
	int VoucherId;

	public int getVoucherId()
	{
		return VoucherId;
	}
	public void setVoucherId(int voucherId) {
		VoucherId = voucherId;
	}
}
