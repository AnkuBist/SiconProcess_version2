package com.hgil.siconprocess_view.adapter.depotList;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.RouteListActivity;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.routeList.RouteListFragment;
import com.hgil.siconprocess_view.base.SiconApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class DepotListAdapter extends RecyclerView.Adapter<DepotListAdapter.ViewHolder> {
    public ArrayList<DepotModel> mDataset;
    private Context mContext;

    public DepotListAdapter(Context mContext, ArrayList<DepotModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public DepotListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_depot, null, false);
        DepotListAdapter.ViewHolder vh = new DepotListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final DepotListAdapter.ViewHolder holder, int position) {
        final DepotModel depotModel = mDataset.get(position);
        holder.tvDepotName.setText(depotModel.getDepot_name());
        holder.tvDepotLO.setText("LO: " + depotModel.getDepot_leftover());
        holder.tvDepotRejPrct.setText("Rej: " + depotModel.getDepot_rej_prct() + "%");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String depot_id = depotModel.getDepot_id();
                String depot_name = depotModel.getDepot_name();
                SiconApp.getInstance().setDepotName(depot_name);
                RouteListFragment fragment = RouteListFragment.newInstance(depot_id, depot_name);
                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = ((RouteListActivity) mContext).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
                        .replace(R.id.base_frame, fragment)
                        .addToBackStack(fragClassName)
                        .commit();
            }
        });

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvDepotName)
        public TextView tvDepotName;
        @BindView(R.id.tvDepotLO)
        public TextView tvDepotLO;
        @BindView(R.id.tvDepotRejPrct)
        public TextView tvDepotRejPrct;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}