package com.hgil.siconprocess_view.adapter.planner.unused;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.RouteListActivity;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.planner.PlannerFragment;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.NavRouteBaseActivity;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.base.SiconApp;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class PlannerRouteListAdapter extends RecyclerView.Adapter<PlannerRouteListAdapter.ViewHolder> {
    public ArrayList<RouteListModel> mDataset;
    private Context mContext;

    public PlannerRouteListAdapter(Context mContext, ArrayList<RouteListModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public PlannerRouteListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_route, null, false);
        PlannerRouteListAdapter.ViewHolder vh = new PlannerRouteListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PlannerRouteListAdapter.ViewHolder holder, int position) {
        final RouteListModel routeListModel = mDataset.get(position);
        holder.tvRouteName.setText(routeListModel.getRoute_name());

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

                //intent.putExtra("route_id", routeListModel.getRoute_id());
                PlannerFragment fragment = PlannerFragment.newInstance();
                //((RouteListActivity) mContext).
                //((PlannerListFragment) mContext)

                //mContext.getApplicationContext().launchRouteFragment(fragment);

                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = ((RouteListActivity) mContext).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
                        .replace(R.id.base_frame, fragment)
                        .addToBackStack(fragClassName)
                        .commit();

               /* mContext.startActivity(intent);
                ((RouteListActivity) mContext).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);*/

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