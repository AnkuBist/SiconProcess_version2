package com.hgil.siconprocess.adapter.invoice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;

import java.util.ArrayList;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class CustomerInvoiceAdapter extends RecyclerView.Adapter<CustomerInvoiceAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<InvoiceModel> mDataset;

    public CustomerInvoiceAdapter(Context mContext, ArrayList<InvoiceModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public CustomerInvoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_invoice, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InvoiceModel invoiceModel = mDataset.get(position);
        holder.updateInvoiceItem(invoiceModel);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CustomerInvoiceItem invoiceItemView;

        public ViewHolder(View v) {
            super(v);
            invoiceItemView = new CustomerInvoiceItem(mContext, v);
        }

        public void updateInvoiceItem(InvoiceModel invoiceItem) {
            invoiceItemView.updateInvoiceItem(invoiceItem);
        }
    }
}