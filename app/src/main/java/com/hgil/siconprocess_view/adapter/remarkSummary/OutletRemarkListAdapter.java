package com.hgil.siconprocess_view.adapter.remarkSummary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;

import java.util.ArrayList;

import butterknife.BindString;
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
        holder.tvRemarkDate.setText(outletRemarkModel.getRemark_date());

        if (outletRemarkModel.getRemark() != null && outletRemarkModel.getRemark().length() > 0)
            holder.tvOutletRemark.setText(holder.strFilledArrow + outletRemarkModel.getRemark());

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvOutletName)
        public TextView tvOutletName;
        @BindView(R.id.tvRemarkDate)
        public TextView tvRemarkDate;
        @BindView(R.id.tvOutletRemark)
        public TextView tvOutletRemark;

        @BindString(R.string.strFilledArrow)
        String strFilledArrow;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}