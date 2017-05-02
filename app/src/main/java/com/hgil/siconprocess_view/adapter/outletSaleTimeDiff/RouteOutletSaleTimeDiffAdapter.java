package com.hgil.siconprocess_view.adapter.outletSaleTimeDiff;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class RouteOutletSaleTimeDiffAdapter extends RecyclerView.Adapter<RouteOutletSaleTimeDiffAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RouteCustomerModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RouteOutletSaleTimeDiffAdapter(Context mContext, ArrayList<RouteCustomerModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RouteOutletSaleTimeDiffAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_outlet_sale_time_diff, parent, false);
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
        holder.tvTotalSaleAmt.setText("Gross Sale: " + holder.strRupee + Math.round(routeCustomerModel.getSaleAmount()));

        //text color change on status
        String status = routeCustomerModel.getCustStatus();
        if (status == null || status.matches("Pending")) { //status.matches("") ||
            String colored_status = "Status:" + " <font color='" + mContext.getResources().getColor(R.color.colorTextRed) + "'>" + status
                    + "</font>";
            holder.tvStatus.setText(Html.fromHtml(colored_status));
            holder.customer_item.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        } else if (status.matches("Completed")) {
            String colored_status = "Status:" + " <font color='" + mContext.getResources().getColor(R.color.colorTextGreen) + "'>" + status
                    + "</font>";
            holder.tvStatus.setText(Html.fromHtml(colored_status));
            holder.customer_item.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundGreen));
        }

        String saleTime = routeCustomerModel.getSale_time();
        if (saleTime != null && !saleTime.matches("") && !saleTime.matches("00:00"))
            holder.tvSaleTime.setText("Sale Time: " + saleTime);
        else
            holder.tvSaleTime.setVisibility(View.GONE);

        if (routeCustomerModel.getCash_received() > 0)
            holder.tvAmountReceived.setText("Amount Received : " + holder.strRupee + Math.round(routeCustomerModel.getCash_received()));
        else
            holder.tvAmountReceived.setVisibility(View.GONE);

        String travelTime = routeCustomerModel.getTime_diff();
        if (travelTime != null && !travelTime.matches("") && !travelTime.matches("00:00"))
            holder.tvSaleTimeDiff.setText("Travel Time: " + travelTime);
        else
            holder.tvSaleTimeDiff.setVisibility(View.GONE);

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
        @BindView(R.id.tvSkuDetail)
        public TextView tvSkuDetail;
        @BindView(R.id.tvStatus)
        public TextView tvStatus;
        @BindView(R.id.tvTotalSaleAmt)
        public TextView tvTotalSaleAmt;
        @BindView(R.id.tvAmountReceived)
        public TextView tvAmountReceived;
        @BindView(R.id.tvSaleTime)
        public TextView tvSaleTime;
        @BindView(R.id.tvSaleTimeDiff)
        public TextView tvSaleTimeDiff;
        @BindView(R.id.customer_item)
        public LinearLayout customer_item;

        @BindString(R.string.strRupee)
        protected String strRupee;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }
}