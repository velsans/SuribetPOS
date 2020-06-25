package com.suribetpos.main.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suribetpos.R;
import com.suribetpos.main.data.model.onlinecashout.Cashouttransactiondetails;

import java.util.List;

public class CashoutTransactionAdapter extends RecyclerView.Adapter<CashoutTransactionAdapter.TransactionViewHolder> {

    Context mCtx;
    List<Cashouttransactiondetails> TransactionList;

    public CashoutTransactionAdapter(Context mCtx, List<Cashouttransactiondetails> transactionList) {
        this.mCtx = mCtx;
        this.TransactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cashouttrans_listview, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Cashouttransactiondetails cashouttransactiopojo = TransactionList.get(position);
        //Glide.with(mCtx).load(hero.getImageurl()).into(holder.imageView);
        holder.txtUser.setText(cashouttransactiopojo.getUSER());
    }

    @Override
    public int getItemCount() {
        if (TransactionList == null) {
            return 0;
        }
        return TransactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView txtComments, txtPaid, txtUser, txtTime;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            txtComments = itemView.findViewById(R.id.cashouttrans_comments);
            txtPaid = itemView.findViewById(R.id.cashouttrans_paid);
            txtUser = itemView.findViewById(R.id.cashouttrans_user);
            txtTime = itemView.findViewById(R.id.cashouttrans_time);
        }
    }
}
