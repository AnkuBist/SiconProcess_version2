package com.hgil.siconprocess_view.adapter.routeRemark;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;

import java.util.ArrayList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class RouteRemarkListAdapter extends RecyclerView.Adapter<RouteRemarkListAdapter.ViewHolder> {
    public ArrayList<RouteRemarkModel> mDataset;
    private Context mContext;

    public RouteRemarkListAdapter(Context mContext, ArrayList<RouteRemarkModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public RouteRemarkListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_route_remark, null, false);
        RouteRemarkListAdapter.ViewHolder vh = new RouteRemarkListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RouteRemarkListAdapter.ViewHolder holder, int position) {
        final RouteRemarkModel routeRemarkModel = mDataset.get(position);
        holder.tvRemarkDate.setText(routeRemarkModel.getRemark_date());

        if (routeRemarkModel.getRoute_remark() != null && routeRemarkModel.getRoute_remark().length() > 0)
            holder.tvRouteRemark.setText(holder.strFilledArrow + routeRemarkModel.getRoute_remark());

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvRemarkDate)
        public TextView tvRemarkDate;
        @BindView(R.id.tvRouteRemark)
        public TextView tvRouteRemark;

        @BindString(R.string.strFilledArrow)
        String strFilledArrow;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}