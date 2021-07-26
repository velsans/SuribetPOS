package com.suribetpos.main.ui.cashout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.suribetpos.R;
import com.suribetpos.databinding.CashtransactionRowBinding;
import com.suribetpos.main.data.model.cashout.TransactionDetails;

import java.util.ArrayList;
import java.util.List;

public class CashoutTransactionAdapter extends RecyclerView.Adapter<CashoutTransactionAdapter.TransactionViewHolder> implements Filterable {

    Context mCtx;
    List<TransactionDetails> TransactionList;
    List<TransactionDetails> TransactionFilterList;
    private View.OnClickListener mOnItemClickListener;
    ValueFilter valueFilter;

    public CashoutTransactionAdapter(Context mCtx, List<TransactionDetails> transactionList) {
        this.mCtx = mCtx;
        this.TransactionList = transactionList;
        this.TransactionFilterList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cashtransaction_row, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        TransactionDetails cashouttransactiopojo = TransactionFilterList.get(position);
        //Glide.with(mCtx).load(hero.getImageurl()).into(holder.imageView);
        holder.bind(cashouttransactiopojo);

    }

    public void setTransactionDetials(List<TransactionDetails> transactionListsa) {
        this.TransactionFilterList = transactionListsa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (TransactionFilterList == null) {
            return 0;
        }
        return TransactionFilterList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        private CashtransactionRowBinding binding;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(this);
            /*Onclick Item listener*/
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public void bind(TransactionDetails item) {
            binding.setTransactiondetails(item);
        }


    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String charString = charSequence.toString();
            if (charString.isEmpty()) {
                TransactionFilterList = TransactionList;
            } else {
                List<TransactionDetails> filteredList = new ArrayList<>();
                for (TransactionDetails row : TransactionFilterList) {
                    if (row.getCASHOUTCODE().toLowerCase().contains(charString.toLowerCase()) || row.getCASHOUTCODE().contains(charSequence)) {
                        filteredList.add(row);
                    }
                }
                TransactionFilterList = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = TransactionFilterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            TransactionFilterList = (ArrayList<TransactionDetails>) results.values;
            notifyDataSetChanged();
        }
    }
}
