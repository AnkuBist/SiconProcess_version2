package com.hgil.siconprocess_view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 09-02-2017.
 */

public class OutletListAdapter extends RecyclerView.Adapter<OutletListAdapter.ViewHolder> {
    public ArrayList<RouteCustomerModel> mDataset;
    private Context mContext;

    public OutletListAdapter(Context mContext, ArrayList<RouteCustomerModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public OutletListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_outlet, null, false);
        OutletListAdapter.ViewHolder vh = new OutletListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final OutletListAdapter.ViewHolder holder, int position) {
        final RouteCustomerModel routeCustomerModel = mDataset.get(position);
        holder.tvOutletName.setText(routeCustomerModel.getCustomerName());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SelectedOutletInfoActivity.class);
                intent.putExtra("customer_id", routeCustomerModel.getCustomerId());
                mContext.startActivity(intent);
                ((NavBaseActivity) mContext).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

            }
        });*/

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvOutletName)
        public TextView tvOutletName;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}