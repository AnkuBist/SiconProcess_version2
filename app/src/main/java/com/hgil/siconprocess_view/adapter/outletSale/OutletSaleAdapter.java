package com.hgil.siconprocess_view.adapter.outletSale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletSaleModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class OutletSaleAdapter extends RecyclerView.Adapter<OutletSaleAdapter.ViewHolder> {
    public ArrayList<OutletSaleModel> mDataset;
    private Context mContext;

    public OutletSaleAdapter(Context mContext, ArrayList<OutletSaleModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public OutletSaleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_outlet_sale, null, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final OutletSaleModel itemInvoice = mDataset.get(position);
        holder.outletSaleView.updateSaleItem(holder, itemInvoice, position);
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private OutletSaleItem outletSaleView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            outletSaleView = new OutletSaleItem(mContext, v);
        }
    }
}