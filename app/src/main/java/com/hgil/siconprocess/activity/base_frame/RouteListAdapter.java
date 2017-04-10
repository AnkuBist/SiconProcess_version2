package com.hgil.siconprocess.activity.base_frame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.NavBaseActivity;
import com.hgil.siconprocess.base.SiconApp;
import com.hgil.siconprocess.database.masterTables.RouteView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

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
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_route, null, false);
        RouteListAdapter.ViewHolder vh = new RouteListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final RouteListAdapter.ViewHolder holder, int position) {
        final RouteListModel routeListModel = mDataset.get(position);
        holder.tvRouteName.setText(routeListModel.getRoute_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NavBaseActivity.class);

                String route_id = routeListModel.getRoute_id();

                // first assign value to application
                RouteView routeView = new RouteView(mContext);
                RouteModel routeModel = routeView.getRouteById(route_id);

                SiconApp.getInstance().setRouteModel(routeModel);
                SiconApp.getInstance().setRouteId(route_id);
                SiconApp.getInstance().setRouteName(routeModel.getRouteName());

                //intent.putExtra("route_id", routeListModel.getRoute_id());
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

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}