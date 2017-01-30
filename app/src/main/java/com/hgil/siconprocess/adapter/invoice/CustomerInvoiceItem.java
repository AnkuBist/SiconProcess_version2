package com.hgil.siconprocess.adapter.invoice;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.tables.DemandTargetTable;
import com.hgil.siconprocess.database.tables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.FixedSampleTable;
import com.hgil.siconprocess.database.tables.ProductView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.DemandTargetModel;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.FixedSampleModel;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.ProductModel;

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

    private int stockAvail;

    public void updateInvoiceItem(InvoiceModel itemInvoice) {

        String itemId = itemInvoice.getItemId();
        String customerId = itemInvoice.getCustomerId();
        final float price = itemInvoice.getItemRate();

        // get product stock
        stockAvail = new DepotInvoiceView(mContext).getLoadingCount(itemId);

        //---------------if invoice exists-------------------//
        ProductView dbItemDetails = new ProductView(mContext);
        ProductModel itemDetail = dbItemDetails.getProductById(itemId);

        // get item sample
        FixedSampleTable dbSample = new FixedSampleTable(mContext);
        FixedSampleModel fixedSampleModel = dbSample.getFixedSampleItem(itemId, customerId);

        // get invoice item target
        DemandTargetTable dbDemandTarget = new DemandTargetTable(mContext);
        DemandTargetModel demandTarget = dbDemandTarget.getDemandTargetByItem(itemId, customerId);

        tvItemName.setText(itemDetail.getItemName());
        tvStock.setText("Stock : " + stockAvail);
        tvRate.setText("Rate : " + itemInvoice.getItemRate());
        tvTarget.setText("TGT : ");
        tvTarget.setVisibility(View.GONE);
        etQty.setText(String.valueOf((int) demandTarget.getTargetQty()));
        etSample.setText(String.valueOf(fixedSampleModel.getSQty()));
        etAmount.setText(String.valueOf(demandTarget.getTargetQty() * itemInvoice.getItemRate()));

        // now calculate the total price of the entered quantity
        etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // qty cant exceed the available stock
                // amount to be calculated on basis of qty and price
                if (s.length() != 0) {
                    int enteredQty = Integer.valueOf(etQty.getText().toString());
                    if (stockAvail >= enteredQty) {
                        tvStock.setText("Stock : " + String.valueOf(stockAvail - enteredQty));
                        etAmount.setText(String.valueOf(enteredQty * price));
                    } else {
                        Toast.makeText(mContext, "Can't enter quantity more than available quantity", Toast.LENGTH_SHORT).show();
                        etQty.setText("0");
                        etAmount.setText("0.0");
                    }
                } else if (s.length() == 0) {
                    etAmount.setText("0.0");
                }
            }
        });

    }


}
