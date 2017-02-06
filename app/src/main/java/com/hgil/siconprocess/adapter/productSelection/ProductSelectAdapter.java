package com.hgil.siconprocess.adapter.productSelection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hgil.siconprocess.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 04-02-2017.
 */

public class ProductSelectAdapter extends RecyclerView.Adapter<ProductSelectAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ProductSelectModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductSelectAdapter(Context mContext, ArrayList<ProductSelectModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductSelectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_product, parent, false);
        ProductSelectAdapter.ViewHolder vh = new ProductSelectAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductSelectAdapter.ViewHolder holder, int position) {
        final ProductSelectModel pSelectModel = mDataset.get(position);
        holder.tvItemName.setText(pSelectModel.getItem_name());

        if (pSelectModel.isSelected()) {
            holder.cbItem.setChecked(true);
        } else {
            holder.cbItem.setChecked(false);
        }

        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // add item to list
                    pSelectModel.setSelected(true);
                } else {
                    // remove item from list
                    pSelectModel.setSelected(false);
                }
            }
        });
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemName)
        public TextView tvItemName;
        @BindView(R.id.cbItem)
        public CheckBox cbItem;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}