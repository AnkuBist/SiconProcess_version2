package com.hgil.siconprocess.adapter.invoiceRejection;

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
 * Created by mohan.giri on 04-02-2017.
 */

public class InvoiceRejectionAdapter extends RecyclerView.Adapter<InvoiceRejectionAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CRejectionModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public InvoiceRejectionAdapter(Context mContext, ArrayList<CRejectionModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public InvoiceRejectionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rejection, parent, false);
        InvoiceRejectionAdapter.ViewHolder vh = new InvoiceRejectionAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(InvoiceRejectionAdapter.ViewHolder holder, int position) {
        final CRejectionModel cRejectionModel = mDataset.get(position);
        holder.tvItemName.setText(cRejectionModel.getItem_name());

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvItemName)
        public TextView tvItemName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}