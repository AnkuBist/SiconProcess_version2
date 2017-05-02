package com.hgil.siconprocess_view.adapter.routeList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.RouteListActivity;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.NavRouteBaseActivity;
import com.hgil.siconprocess_view.base.SiconApp;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class RouteListAdapter extends RecyclerView.Adapter<RouteListAdapter.ViewHolder> {
    public ArrayList<RouteListModel> mDataset;
    private Context mContext;

    public RouteListAdapter(Context mContext, ArrayList<RouteListModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public RouteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_depot_route, null, false);
        RouteListAdapter.ViewHolder vh = new RouteListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RouteListAdapter.ViewHolder holder, int position) {
        final RouteListModel routeListModel = mDataset.get(position);

        holder.tvRouteName.setText(routeListModel.getRoute_name());

        String itemInfo = "<font color='" + mContext.getResources().getColor(R.color.colorRLTextBlack) + "'>" + "LO/TL: "
                + "</font>" +"<B>" + routeListModel.getRoute_leftover() + "/" + routeListModel.getRoute_total_loading() +"</B>" ;
        String saleInfo = "<font color='" + mContext.getResources().getColor(R.color.colorRLTextBlack) + "'>" + "PC/TC: "
                + "</font>" + "<B>" +routeListModel.getRoute_productive_calls() + "/" + routeListModel.getRoute_target_calls()+"</B>";
        String rejPrct = "<font color='" + mContext.getResources().getColor(R.color.colorRLTextBlack) + "'>" + "R%: "
                + "</font>" + "<B>" + routeListModel.getRoute_rej_prct()+"</B>";
        holder.tvItemInfo.setText(Html.fromHtml(itemInfo));
        holder.tvSaleInfo.setText(Html.fromHtml(saleInfo));
        holder.tvRejPrct.setText(Html.fromHtml(rejPrct));

        if (routeListModel.getRouteCloseStatus() == 1)
            holder.tvRouteStatus.setVisibility(View.VISIBLE);
        else
            holder.tvRouteStatus.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NavRouteBaseActivity.class);
                String route_id = routeListModel.getRoute_id();

                // first assign value to application
                RouteView routeView = new RouteView(mContext);
                RouteModel routeModel = routeView.getRouteById(route_id);

                SiconApp.getInstance().setRouteModel(routeModel);
                SiconApp.getInstance().setRouteId(route_id);
                SiconApp.getInstance().setRouteName(routeModel.getRouteName());
                SiconApp.getInstance().setCashierName(routeModel.getCashierName());

                mContext.startActivity(intent);
                ((RouteListActivity) mContext).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvRouteName)
        public TextView tvRouteName;
        @BindView(R.id.tvItemInfo)
        public TextView tvItemInfo;
        @BindView(R.id.tvSaleInfo)
        public TextView tvSaleInfo;
        @BindView(R.id.tvRejPrct)
        public TextView tvRejPrct;
        @BindView(R.id.tvRouteStatus)
        public TextView tvRouteStatus;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}