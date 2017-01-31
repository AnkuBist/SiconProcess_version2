package com.hgil.siconprocess.adapter.invoice;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.navFragments.CustomerInvoiceFragment;
import com.hgil.siconprocess.utils.Utility;

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

    private int stockAvail, tempStock;

    public void updateInvoiceItem(final CustomerInvoiceAdapter.ViewHolder holder, final InvoiceModel itemInvoice, final int position) {
        String itemName = itemInvoice.getItemName();
        final float price = itemInvoice.getItemRate();
        float demandQty = itemInvoice.getDemandTargetQty();
        final double orderAmount = itemInvoice.getOrderAmount();
        CustomerInvoiceFragment.listItemOrderAmount.set(position, Utility.roundTwoDecimals(orderAmount));

        // get product stock
        stockAvail = itemInvoice.getStockAvail();
        tempStock = itemInvoice.getTempStock();

        tvItemName.setText(itemName);
        tvStock.setText("Stock : " + tempStock);
        tvRate.setText("Rate : " + price);
        tvTarget.setText("TGT : ");
        tvTarget.setVisibility(View.GONE);
        etQty.setText(String.valueOf((int) demandQty));
        etSample.setText(String.valueOf(itemInvoice.getFixedSample()));
        etAmount.setText(String.valueOf(orderAmount));

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
                double oldOrderAmount = orderAmount;
                if (s.length() != 0) {
                    int enteredQty = Integer.valueOf(etQty.getText().toString());
                    if (stockAvail >= enteredQty) {
                        int updateStock = stockAvail - enteredQty;
                        double orderAmount = Utility.roundTwoDecimals(enteredQty * price);
                        itemInvoice.setDemandTargetQty(enteredQty);
                        itemInvoice.setTempStock(updateStock);
                        itemInvoice.setOrderAmount(orderAmount);
                        tvStock.setText("Stock : " + itemInvoice.getTempStock());
                        etAmount.setText(String.valueOf(orderAmount));

                        CustomerInvoiceFragment.listItemOrderAmount.set(position, orderAmount);
                    } else {
                        Toast.makeText(mContext, "Can't enter quantity more than available quantity", Toast.LENGTH_SHORT).show();

                        itemInvoice.setDemandTargetQty(0);
                        itemInvoice.setTempStock(stockAvail);
                        itemInvoice.setOrderAmount(0);
                        tvStock.setText("Stock : " + stockAvail);
                        etQty.setText("0");
                        etAmount.setText("0.0");

                        CustomerInvoiceFragment.listItemOrderAmount.set(position, (double) (0));
                    }
                } else if (s.length() == 0) {
                    etAmount.setText("0.0");
                    tvStock.setText("Stock : " + stockAvail);

                    itemInvoice.setDemandTargetQty(0);
                    itemInvoice.setTempStock(stockAvail);
                    itemInvoice.setOrderAmount(0);

                    CustomerInvoiceFragment.listItemOrderAmount.set(position, (double) (0));
                }

                // calculate the total of invoice
                //  for(int i = 0; i<holder.m)
                //float currentOrderAmount = itemInvoice.getOrderAmount();
                //float amountToUpdateTotal = currentOrderAmount - oldOrderAmount;

                //CustomerInvoiceFragment.grandTotal += Double.parseDouble(etAmount.getText().toString());
                CustomerInvoiceFragment.grandTotal = 0;
                for (int i = 0; i < CustomerInvoiceFragment.listItemOrderAmount.size(); i++) {
                    CustomerInvoiceFragment.grandTotal += CustomerInvoiceFragment.listItemOrderAmount.get(i);
                }
                //
                CustomerInvoiceFragment.tvCustomerTotal.setText(String.valueOf(CustomerInvoiceFragment.grandTotal));

            }
        });
    }


}
