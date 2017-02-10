package com.hgil.siconprocess.adapter.routeMap;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.HomeInvoiceActivity;
import com.hgil.siconprocess.activity.NavBaseActivity;
import com.hgil.siconprocess.activity.fragments.invoice.makeSaleInvoice.CustomerInvoiceFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class RouteMapRAdapter extends RecyclerView.Adapter<RouteMapRAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RouteCustomerModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RouteMapRAdapter(Context mContext, ArrayList<RouteCustomerModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RouteMapRAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route_map, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final RouteCustomerModel routeCustomerModel = mDataset.get(position);
        holder.tvCustomerName.setText(routeCustomerModel.getCustomerName());
        holder.tvStatus.setText("Status:" + routeCustomerModel.getStatus());
        holder.tvTotalSaleAmt.setText("Total Sale Amt:" + routeCustomerModel.getSaleAmount());
        holder.customer_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch activity with updated nav bar
                Intent intent = new Intent(mContext, HomeInvoiceActivity.class);
                intent.putExtra("customer_id", routeCustomerModel.getCustomerId());
                intent.putExtra("customer_name", routeCustomerModel.getCustomerName());
                mContext.startActivity(intent);

                /*CustomerInvoiceFragment fragment = CustomerInvoiceFragment.newInstance(routeCustomerModel.getCustomerId(), routeCustomerModel.getCustomerName());
                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = ((NavBaseActivity) mContext).getSupportFragmentManager();
               *//* boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragClassName, 0);
                if (!fragmentPopped) {
                    fragmentManager.beginTransaction().replace(R.id.homeFrame, fragment);
                }*//*
                fragmentManager.beginTransaction().replace(R.id., fragment).addToBackStack(fragClassName).commit();*/
            }
        });

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
        @BindView(R.id.tvCustomerName)
        public TextView tvCustomerName;
        @BindView(R.id.tvStatus)
        public TextView tvStatus;
        @BindView(R.id.tvTotalSaleAmt)
        public TextView tvTotalSaleAmt;
        @BindView(R.id.customer_item)
        public LinearLayout customer_item;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
           /* txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);*/
        }
    }

   /* public void add(int position, String item) {
        mDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(String item) {
        int position = mDataset.indexOf(item);
        mDataset.remove(position);
        notifyItemRemoved(position);
    }*/
}