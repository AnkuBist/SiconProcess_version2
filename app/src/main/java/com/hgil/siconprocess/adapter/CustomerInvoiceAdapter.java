package com.hgil.siconprocess.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.NavBaseActivity;
import com.hgil.siconprocess.activity.navFragments.CustomerInvoiceFragment;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class CustomerInvoiceAdapter extends RecyclerView.Adapter<CustomerInvoiceAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<InvoiceDetailModel> mDataset;

    public CustomerInvoiceAdapter(Context mContext, ArrayList<InvoiceDetailModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public CustomerInvoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_invoice, parent, false);
        CustomerInvoiceAdapter.ViewHolder vh = new CustomerInvoiceAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CustomerInvoiceAdapter.ViewHolder holder, int position) {
        final InvoiceDetailModel invoiceDetailModel = mDataset.get(position);
        holder.updateInvoiceItem(invoiceDetailModel);
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

        public void updateInvoiceItem(InvoiceDetailModel invoiceItem) {
            invoiceItemView.updateInvoiceItem(invoiceItem);
        }
    }
}