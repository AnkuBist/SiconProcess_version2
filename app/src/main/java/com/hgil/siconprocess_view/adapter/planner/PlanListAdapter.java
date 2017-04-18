package com.hgil.siconprocess_view.adapter.planner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.ViewHolder> {
    public ArrayList<PlanModel> mDataset;
    private Context mContext;

    public PlanListAdapter(Context mContext, ArrayList<PlanModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public PlanListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_user_plan, null, false);
        PlanListAdapter.ViewHolder vh = new PlanListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PlanListAdapter.ViewHolder holder, int position) {
        final PlanModel planModel = mDataset.get(position);
        holder.tvPlanDate.setText(planModel.getPlanDate());
        holder.tvUserPlan.setText(planModel.getUserPlan());
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvPlanDate)
        public TextView tvPlanDate;
        @BindView(R.id.tvUserPlan)
        public TextView tvUserPlan;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}