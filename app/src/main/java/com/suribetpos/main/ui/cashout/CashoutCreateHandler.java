package com.suribetpos.main.ui.cashout;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;

import com.suribetpos.R;
import com.suribetpos.databinding.CreatecashoutFragmentBinding;

public class CashoutCreateHandler {
    CreatecashoutFragmentBinding createcashoutFragmentBinding;
    Context context;
    public void setBinding(CreatecashoutFragmentBinding binding, Context Context) {
        this.createcashoutFragmentBinding = binding;
        this.context = Context;
    }

    public void passwordValidator(Editable editable) {
        if (createcashoutFragmentBinding.cashoutRequstNR == null) return;
        int minimumLength = 12;
        if (!TextUtils.isEmpty(editable.toString()) && editable.length() == minimumLength) {
            createcashoutFragmentBinding.cashoutRequstNR.setError(CommonMessage(R.string.Request_Nr_lenght));
        } else {
            createcashoutFragmentBinding.cashoutRequstNR.setError(null);
        }
    }
    public String CommonMessage(int Common_Msg) {
        return context.getResources().getString(Common_Msg);
    }

}
