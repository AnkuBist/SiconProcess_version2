package com.hgil.siconprocess.activity.fragments.invoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.dbModels.PaymentModel;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
import com.hgil.siconprocess.database.tables.PaymentTable;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class FinalInvoiceFragment extends BaseFragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvOpeningBalance)
    TextView tvOpeningBalance;
    @BindView(R.id.tvTodaySale)
    TextView tvTodaySale;
    @BindView(R.id.tvAmountCollected)
    TextView tvAmountCollected;
    @BindView(R.id.tvOsBalance)
    TextView tvOsBalance;

    @BindView(R.id.btnInvoiceCancel)
    Button btnInvoiceCancel;
    @BindView(R.id.btnSendSms)
    Button btnSendSms;

    public FinalInvoiceFragment() {
        // Required empty public constructor
    }

    public static FinalInvoiceFragment newInstance(String customer_id, String customer_name) {
        FinalInvoiceFragment fragment = new FinalInvoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_final_invoice;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null) {
            tvCustomerName.setText(customer_name);
        }

        // get customer credit outstanding
        CreditOpeningTable creditOpeningTable = new CreditOpeningTable(getContext());
        double creditOs = creditOpeningTable.custCreditAmount(customer_id);
        tvOpeningBalance.setText(strRupee + String.valueOf(creditOs));

        PaymentTable paymentTable = new PaymentTable(getContext());
        PaymentModel paymentModel = paymentTable.getCustomerPaymentInfo(customer_id);
        double todaySale = paymentModel.getSaleAmount();
        double amountCollected = paymentModel.getTotalPaidAmount();
        double osBalance = creditOs + todaySale - amountCollected;
        tvTodaySale.setText(strRupee + String.valueOf(todaySale));
        tvAmountCollected.setText(strRupee + String.valueOf(amountCollected));
        tvOsBalance.setText(strRupee + String.valueOf(osBalance));

        setTitle("Invoice");
        hideSaveButton();

    }

    @OnClick(R.id.btnInvoiceCancel)
    public void onInvoiceCancel(View view) {

    }

    @OnClick(R.id.btnSendSms)
    public void onSendSms(View view) {
    }
}
