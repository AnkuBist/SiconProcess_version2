package com.hgil.siconprocess.adapter.vanStock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 06-02-2017.
 */

public class VanStockAdapter extends RecyclerView.Adapter<VanStockAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<VanStockModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public VanStockAdapter(Context mContext, ArrayList<VanStockModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VanStockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_van_stock, parent, false);
        VanStockAdapter.ViewHolder vh = new VanStockAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(VanStockAdapter.ViewHolder holder, int position) {
        final VanStockModel vanStockModel = mDataset.get(position);
        holder.tvItemName.setText(vanStockModel.getItem_name());
        holder.tvLoadQty.setText(String.valueOf(vanStockModel.getLoadQty()));
        holder.tvGrossSale.setText(String.valueOf(vanStockModel.getGross_sale()));
        holder.tvSample.setText(String.valueOf(vanStockModel.getSample()));
        holder.tvMarketRejection.setText(String.valueOf(vanStockModel.getMarket_rejection()));
        holder.tvFreshRejection.setText(String.valueOf(vanStockModel.getFresh_rejection()));
        holder.tvLeftover.setText(String.valueOf(vanStockModel.getLeft_over()));

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemName)
        TextView tvItemName;
        @BindView(R.id.tvLoadQty)
        public TextView tvLoadQty;
        @BindView(R.id.tvGrossSale)
        public TextView tvGrossSale;
        @BindView(R.id.tvSample)
        public TextView tvSample;
        @BindView(R.id.tvMarketRejection)
        public TextView tvMarketRejection;
        @BindView(R.id.tvFreshRejection)
        public TextView tvFreshRejection;
        @BindView(R.id.tvLeftover)
        public TextView tvLeftover;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}