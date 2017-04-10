package com.hgil.siconprocess.adapter.invoice.invoiceSale;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.invoice.InvoiceModel;

import java.util.ArrayList;

import butterknife.ButterKnife;


/**
 * Created by mohan.giri on 25-01-2017.
 */

public class CustomerInvoiceAdapter extends RecyclerView.Adapter<CustomerInvoiceAdapter.ViewHolder> {
    public ArrayList<InvoiceModel> mDataset;
    private Context mContext;

    public CustomerInvoiceAdapter(Context mContext, ArrayList<InvoiceModel> myDataset) {
        this.mContext = mContext;
        this.mDataset = myDataset;
    }

    @Override
    public CustomerInvoiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View v = LayoutInflater.from(mContext).inflate(R.layout.item_customer_invoice, null, false);
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_display_next_day_order, null, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //private int stockAvail, tempStock;

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final InvoiceModel itemInvoice = mDataset.get(position);
        holder.invoiceItemView.updateInvoiceItem(holder, itemInvoice, position);

        holder.setIsRecyclable(false);

        /*String itemName = itemInvoice.getItemName();
        final float price = itemInvoice.getItemRate();
        float demandQty = itemInvoice.getDemandTargetQty();
        float orderAmount = itemInvoice.getOrderAmount();

        // get product stock
        final float stockAvail = itemInvoice.getStockAvail();
        float tempStock = itemInvoice.getTempStock();

        holder.tvItemName.setText(itemName);
        holder.tvStock.setText("Stock : " + tempStock);
        holder.tvRate.setText("Rate : " + price);
        holder.tvTarget.setText("TGT : ");
        holder.tvTarget.setVisibility(View.GONE);
        holder.etQty.setText(String.valueOf((int) demandQty));
        holder.etSample.setText(String.valueOf(itemInvoice.getFixedSample()));
        holder.etAmount.setText(String.valueOf(orderAmount));

        holder.setIsRecyclable(false);

        // now calculate the total price of the entered quantity
        holder.etQty.addTextChangedListener(new TextWatcher() {
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
                    int enteredQty = Integer.valueOf(holder.etQty.getText().toString());
                    if (stockAvail >= enteredQty) {
                        int updateStock = (int) stockAvail - enteredQty;
                        float orderAmount = enteredQty * price;
                        itemInvoice.setDemandTargetQty(enteredQty);
                        itemInvoice.setTempStock(updateStock);
                        itemInvoice.setOrderAmount(orderAmount);
                        holder.tvStock.setText("Stock : " + itemInvoice.getTempStock());
                        holder.etAmount.setText(String.valueOf(itemInvoice.getOrderAmount()));
                    } else {
                        Toast.makeText(mContext, "Can't enter quantity more than available quantity", Toast.LENGTH_SHORT).show();

                        itemInvoice.setDemandTargetQty(0);
                        itemInvoice.setTempStock((int) stockAvail);
                        itemInvoice.setOrderAmount(0);

                        holder.etQty.setText("0");
                        holder.etAmount.setText("0.0");
                    }
                } else if (s.length() == 0) {
                    holder.etAmount.setText("0.0");

                    itemInvoice.setDemandTargetQty(0);
                    itemInvoice.setTempStock((int) stockAvail);
                    itemInvoice.setOrderAmount(0);
                }
                //notify data
                //  holder.notifyItemUpdate(position);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CustomerInvoiceItem invoiceItemView;

        /*private Context mContext;
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
*/
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

            invoiceItemView = new CustomerInvoiceItem(mContext, v);
        }

        /*public void updateInvoiceItem(InvoiceModel invoiceItem, int position) {
            invoiceItemView.updateInvoiceItem(invoiceItem, position);
        }*/

       /* public void notifyItemUpdate(int position) {
            notifyItemChanged(position);
        }*/
    }


}