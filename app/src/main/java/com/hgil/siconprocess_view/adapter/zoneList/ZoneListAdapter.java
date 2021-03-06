package com.hgil.siconprocess_view.adapter.zoneList;

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
import com.hgil.siconprocess_view.activity.fragments.baseLevel.depotList.DepotListFragment;
import com.hgil.siconprocess_view.base.SiconApp;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class ZoneListAdapter extends RecyclerView.Adapter<ZoneListAdapter.ViewHolder> {
    public ArrayList<String> mDataset;
    private Context mContext;

    public ZoneListAdapter(Context mContext, ArrayList<String> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public ZoneListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_depot, null, false);
        ZoneListAdapter.ViewHolder vh = new ZoneListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ZoneListAdapter.ViewHolder holder, int position) {
        final String zoneName = mDataset.get(position);
        holder.tvZoneName.setText(zoneName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiconApp.getInstance().setZoneName(zoneName);
                DepotListFragment fragment = DepotListFragment.newInstance(zoneName);
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
        public TextView tvZoneName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}