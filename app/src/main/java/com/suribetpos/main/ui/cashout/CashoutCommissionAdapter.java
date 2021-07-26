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
import com.suribetpos.databinding.CashcommissionRowBinding;
import com.suribetpos.main.data.model.cashout.TransactionDetails;
import com.suribetpos.main.data.model.commission.CashoutCommissionDetails;

import java.util.ArrayList;
import java.util.List;

public class CashoutCommissionAdapter extends RecyclerView.Adapter<CashoutCommissionAdapter.CommissionViewHolder> implements Filterable {

    Context mCtx;
    List<CashoutCommissionDetails> CommissionList;
    List<CashoutCommissionDetails> CommissionFilterList;
    private View.OnClickListener mOnItemClickListener;
    ValueFilter valueFilter;

    public CashoutCommissionAdapter(Context mCtx, List<CashoutCommissionDetails> CommissionList) {
        this.mCtx = mCtx;
        this.CommissionList = CommissionList;
        this.CommissionFilterList = CommissionList;
    }

    @NonNull
    @Override
    public CommissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cashcommission_row, parent, false);
        return new CommissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommissionViewHolder holder, int position) {
        CashoutCommissionDetails cashouttransactiopojo = CommissionFilterList.get(position);
        //Glide.with(mCtx).load(hero.getImageurl()).into(holder.imageView);
        holder.bind(cashouttransactiopojo);

    }

    public void setCommissionDetials(List<CashoutCommissionDetails> CommissionListsa) {
        this.CommissionFilterList = CommissionListsa;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (CommissionFilterList == null) {
            return 0;
        }
        return CommissionFilterList.size();
    }

    public class CommissionViewHolder extends RecyclerView.ViewHolder {
        private CashcommissionRowBinding binding;

        public CommissionViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setTag(this);
            /*Onclick Item listener*/
            itemView.setOnClickListener(mOnItemClickListener);
        }
        public void bind(CashoutCommissionDetails item) {
            binding.setCommissionDetails(item);
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
                CommissionFilterList = CommissionList;
            } else {
                List<CashoutCommissionDetails> filteredList = new ArrayList<>();
                for (CashoutCommissionDetails row : CommissionFilterList) {
                    if (row.getUWRCode().toLowerCase().contains(charString.toLowerCase()) || row.getUWRCode().contains(charSequence)) {
                        filteredList.add(row);
                    }
                }
                CommissionFilterList = filteredList;
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = CommissionFilterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            CommissionFilterList = (ArrayList<CashoutCommissionDetails>) results.values;
            notifyDataSetChanged();
        }
    }
}
