package com.hgil.siconprocess_view.adapter.remarkSummary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class OutletRemarkListAdapter extends RecyclerView.Adapter<OutletRemarkListAdapter.ViewHolder> {
    public ArrayList<OutletRemarkModel> mDataset;
    private Context mContext;

    public OutletRemarkListAdapter(Context mContext, ArrayList<OutletRemarkModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public OutletRemarkListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_outlet_remark, null, false);
        OutletRemarkListAdapter.ViewHolder vh = new OutletRemarkListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final OutletRemarkListAdapter.ViewHolder holder, int position) {
        final OutletRemarkModel outletRemarkModel = mDataset.get(position);
        holder.tvOutletName.setText(outletRemarkModel.getOutlet_name());
        holder.tvOutletRemark.setText(outletRemarkModel.getRemark());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO--expand and collapse the outlet remark onClick

                /*String route_id = outletRemarkModel.getRoute_id();

                RemarkRouteOutletListFragment fragment = RemarkRouteOutletListFragment.newInstance();
                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = ((RouteListActivity) mContext).getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
                        .replace(R.id.base_frame, fragment)
                        .addToBackStack(fragClassName)
                        .commit();*/

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
        @BindView(R.id.tvOutletName)
        public TextView tvOutletName;
        @BindView(R.id.tvOutletRemark)
        public TextView tvOutletRemark;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}