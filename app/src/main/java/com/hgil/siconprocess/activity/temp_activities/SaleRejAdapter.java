package com.hgil.siconprocess.activity.temp_activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 07-04-2017.
 */

public class SaleRejAdapter extends RecyclerView.Adapter<SaleRejAdapter.ViewHolder> {
    public ArrayList<SaleRejModel> mDataset;
    private Context mContext;

    public SaleRejAdapter(Context mContext, ArrayList<SaleRejModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public SaleRejAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_sale_rej_history, null, false);
        SaleRejAdapter.ViewHolder vh = new SaleRejAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final SaleRejAdapter.ViewHolder holder, int position) {
        final SaleRejModel saleRejModel = mDataset.get(position);
        holder.tvDate.setText(saleRejModel.getStr_date());
        holder.tvSalePrct.setText(String.valueOf(saleRejModel.getSale_prct()));
        holder.tvRejPrct.setText(String.valueOf(saleRejModel.getRej_prct()));


    /*    holder.itemView.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tvDate)
        public TextView tvDate;
        @BindView(R.id.tvSalePrct)
        public TextView tvSalePrct;
        @BindView(R.id.tvRejPrct)
        public TextView tvRejPrct;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}