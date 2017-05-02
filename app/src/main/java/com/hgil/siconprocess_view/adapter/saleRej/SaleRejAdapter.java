package com.hgil.siconprocess_view.adapter.saleRej;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;
import com.hgil.siconprocess_view.utils.Utility;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 07-04-2017.
 */

public class SaleRejAdapter extends RecyclerView.Adapter<SaleRejAdapter.ViewHolder> {
    public ArrayList<SaleHistoryModel> mDataset;
    private Context mContext;

    public SaleRejAdapter(Context mContext, ArrayList<SaleHistoryModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public SaleRejAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_sale_rej_history, null, false);
        SaleRejAdapter.ViewHolder vh = new SaleRejAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final SaleRejAdapter.ViewHolder holder, int position) {
        final SaleHistoryModel saleHistoryModel = mDataset.get(position);
        holder.tvDate.setText(Utility.getDayMonthAbbrev(saleHistoryModel.getStockDate()));
        double grossSale = saleHistoryModel.getGrossSale();
        double netSale = saleHistoryModel.getNetSale();
        double rejPrct = 0.00;
        if (saleHistoryModel.getGrossSale() > 0)
            rejPrct = (((grossSale - saleHistoryModel.getNetSale()) / grossSale) * 100);

        holder.tvSaleAmt.setText(String.valueOf(Math.round(netSale) + "/" + Math.round(rejPrct)));
        holder.tvVanStockSale.setText(String.valueOf(saleHistoryModel.getItemsSold() + "/" + saleHistoryModel.getRoute_van_stock()));

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDate)
        public TextView tvDate;
        @BindView(R.id.tvSaleAmt)
        public TextView tvSaleAmt;
        @BindView(R.id.tvRejPrct)
        public TextView tvRejPrct;
        @BindView(R.id.tvVanStockSale)
        public TextView tvVanStockSale;
        @BindString(R.string.strRupee)
        protected String strRupee;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}