package com.hgil.siconprocess_view.adapter.routeMap.homeAll;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.outletLevel.OutletHomeActivity;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.NavRouteBaseActivity;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class RouteOutletAllAdapter extends RecyclerView.Adapter<RouteOutletAllAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RouteCustomerModel> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public RouteOutletAllAdapter(Context mContext, ArrayList<RouteCustomerModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RouteOutletAllAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_route_outlet_all, parent, false);
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
        holder.tvTotalSaleAmt.setText("G.Sale: " + holder.strRupee + Math.round(routeCustomerModel.getSaleAmount()));

        //text color change on status
        String status = routeCustomerModel.getCustStatus();
        if (status == null || status.matches("Pending")) { //status.matches("") ||
            String colored_status = "Status:" + " <font color='" + mContext.getResources().getColor(R.color.colorTextRed) + "'>" + "P"
                    + "</font>";
            holder.tvStatus.setText(Html.fromHtml(colored_status));
            holder.customer_item.setBackgroundColor(mContext.getResources().getColor(R.color.colorWhite));
        } else if (status.matches("Completed")) {
            String colored_status = "Status:" + " <font color='" + mContext.getResources().getColor(R.color.colorTextGreen) + "'>" + "C"
                    + "</font>";
            holder.tvStatus.setText(Html.fromHtml(colored_status));
            holder.customer_item.setBackgroundColor(mContext.getResources().getColor(R.color.colorBackgroundGreen));
        }

        String saleTime = routeCustomerModel.getSale_time();
        if (saleTime != null && !saleTime.matches("") && !saleTime.matches("00:00")) {
            String smsTime = routeCustomerModel.getSms_time();
            if (smsTime != null && !smsTime.matches("") && !smsTime.matches("00:00"))
                holder.tvSaleTime.setText("S.Time: " + saleTime + "/" + smsTime);
            else holder.tvSaleTime.setText("S.Time: " + saleTime);
            /*sku updates*/
            holder.tvSkuDetail.setText("" + routeCustomerModel.getOutlet_purchased_sku() + "/" + routeCustomerModel.getVan_total_sku());
        } else {
            holder.tvSaleTime.setVisibility(View.GONE);
        }

        /* travel time*/
        String travelTime = routeCustomerModel.getTime_diff();
        if (travelTime != null && !travelTime.matches("") && !travelTime.matches("00:00")) {
            holder.tvTravelTime.setText("T.Time: " + travelTime);
        } else {
            holder.tvTravelTime.setVisibility(View.GONE);
        }

        /*customer rejection percentage*/
        double rejPrct = routeCustomerModel.getRejPrct();
        //if (rejPrct > 0)
            holder.tvRejPrct.setText("Rej: " + Math.round(rejPrct) + "%");
        //else
         //   holder.tvRejPrct.setVisibility(View.GONE);

        /*average customer sale history*/
        long avgShGrossSale = routeCustomerModel.getAvgSHSale();
        // if (avgShNetSale > 0)
        holder.tvASHSale.setText("A.G.Sale: " + holder.strRupee + avgShGrossSale);
        //else
        //   holder.tvASHSale.setText("A.Sale: " + holder.strRupee + "0");

        /*sale history rejection percentage*/
        long shRejPrct = routeCustomerModel.getAvgSaleRejPrct();
        //if (shRejPrct > 0)
        holder.tvASHRejPrct.setText("A.Rej: " + shRejPrct + "%");
        // else
        //holder.tvASHRejPrct.setVisibility(View.GONE);
        // holder.tvASHRejPrct.setText("A.Rej: " + "0" + "%");

        holder.customer_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //launch activity with updated nav bar
                Intent intent = new Intent(mContext, OutletHomeActivity.class);
                intent.putExtra("customer_id", routeCustomerModel.getCustomerId());
                intent.putExtra("customer_name", routeCustomerModel.getCustomerName());
                mContext.startActivity(intent);
                ((NavRouteBaseActivity) mContext).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
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
        @BindView(R.id.tvSkuDetail)
        public TextView tvSkuDetail;
        @BindView(R.id.tvStatus)
        public TextView tvStatus;
        @BindView(R.id.tvTotalSaleAmt)
        public TextView tvTotalSaleAmt;
        @BindView(R.id.tvSaleTime)
        public TextView tvSaleTime;
        @BindView(R.id.tvRejPrct)
        public TextView tvRejPrct;
        @BindView(R.id.tvTravelTime)
        public TextView tvTravelTime;
        @BindView(R.id.tvASHSale)
        public TextView tvASHSale;
        @BindView(R.id.tvASHRejPrct)
        public TextView tvASHRejPrct;
        @BindView(R.id.customer_item)
        public LinearLayout customer_item;

        @BindString(R.string.strRupee)
        protected String strRupee;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }
    }

    /*update adapter*/
    public void updateData(ArrayList<RouteCustomerModel> viewModels) {
        mDataset.clear();
        mDataset.addAll(viewModels);
        notifyDataSetChanged();
    }
}