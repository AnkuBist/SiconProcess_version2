package com.hgil.siconprocess.adapter;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class CustomerInvoiceItem {

    private Context mContext;
    @BindView(R.id.tvItemName)
    TextView tvItemName;
    @BindView(R.id.tvStock)
    TextView tvStock;
    @BindView(R.id.tvRate)
    TextView tvRate;
    @BindView(R.id.tvTarget)
    TextView tvTarget;
    @BindView(R.id.etQty)
    EditText etQty;
    @BindView(R.id.etSample)
    EditText etSample;
    @BindView(R.id.etAmount)
    EditText etAmount;


    public CustomerInvoiceItem(Context mContext, View v) {
        this.mContext = mContext;
        ButterKnife.bind(this, v);
    }

    public void updateInvoiceItem(InvoiceDetailModel itemInvoice) {

        //tvItemName.setText(itemInvoice.get);
        //tvStock.setText(itemInvoice.get);
        tvRate.setText("" + itemInvoice.getItemRate());
        //tvTarget.setText(itemInvoice.get);
    }


}
