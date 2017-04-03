package com.hgil.siconprocess.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.dbModels.MarketProductModel;
import com.hgil.siconprocess.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 30-03-2017.
 */

public class MarketProductAdapter extends RecyclerView.Adapter<MarketProductAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<MarketProductModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MarketProductAdapter(Context mContext, ArrayList<MarketProductModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MarketProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_next_day_order, parent, false);
        MarketProductAdapter.ViewHolder vh = new MarketProductAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MarketProductAdapter.ViewHolder holder, int position) {
        final MarketProductModel marketProductModel = mDataset.get(position);
        holder.tvItemName.setText(marketProductModel.getItemName());
        holder.etQuantity.setText(String.valueOf(marketProductModel.getQuantity()));

        holder.etQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                marketProductModel.setQuantity(Utility.getInteger(holder.etQuantity.getText().toString()));
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
        @BindView(R.id.etQuantity)
        public EditText etQuantity;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}