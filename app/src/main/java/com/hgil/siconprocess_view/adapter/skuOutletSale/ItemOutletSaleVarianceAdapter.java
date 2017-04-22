package com.hgil.siconprocess_view.adapter.skuOutletSale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class ItemOutletSaleVarianceAdapter extends RecyclerView.Adapter<ItemOutletSaleVarianceAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ItemOutletSaleVarianceModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ItemOutletSaleVarianceAdapter(Context mContext, ArrayList<ItemOutletSaleVarianceModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ItemOutletSaleVarianceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                       int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sku_outlet_sale_count, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final ItemOutletSaleVarianceModel itemSaleVarianceModel = mDataset.get(position);
        holder.tvItemName.setText(itemSaleVarianceModel.getItem_name());
        holder.tvSaleCount.setText(String.valueOf(itemSaleVarianceModel.getItem_access_count() + "/" + itemSaleVarianceModel.getTotal_customers()));

        holder.setIsRecyclable(false);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.tvItemName)
        public TextView tvItemName;
        @BindView(R.id.tvSaleCount)
        public TextView tvSaleCount;

        @BindString(R.string.strRupee)
        protected String strRupee;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}