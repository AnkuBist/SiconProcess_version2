package com.hgil.siconprocess_view.adapter.outletSale;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class OutletSaleItem {
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvSaleQty)
    TextView tvSaleQty;
    @BindView(R.id.tvRejQty)
    TextView tvRejQty;
    @BindString(R.string.strRupee)
    String strRupee;
    private Context mContext;

    public OutletSaleItem(Context mContext, View v) {
        this.mContext = mContext;
        ButterKnife.bind(this, v);
    }

    public void updateSaleItem(final OutletSaleAdapter.ViewHolder holder, final TodaySaleModel itemInvoice, final int position) {
        final String itemName = itemInvoice.getItem_name();
        tvItemName.setText(itemName);
        tvSaleQty.setText("" + itemInvoice.getLoading());
        tvRejQty.setText("" + (itemInvoice.getOtherRej() + itemInvoice.getFreshRej()));
    }
}
