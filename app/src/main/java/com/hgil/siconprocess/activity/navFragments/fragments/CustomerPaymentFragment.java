package com.hgil.siconprocess.activity.navFragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerPaymentFragment extends BaseFragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.tvCreditOs)
    TextView tvCreditOs;

    @BindView(R.id.etCash)
    EditText etCash;
    @BindView(R.id.etCheque)
    EditText etCheque;
    @BindView(R.id.etDD)
    EditText etDD;
    @BindView(R.id.etRTGS)
    EditText etRTGS;
    @BindView(R.id.etNEFT)
    EditText etNEFT;

    @BindView(R.id.tvSaleAmount)
    TextView tvSaleAmount;
    @BindView(R.id.tvTotalOs)
    TextView tvTotalOs;

    @BindView(R.id.tvCustomerTotal)
    TextView tvCustomerTotal;


    public CustomerPaymentFragment() {
        // Required empty public constructor
    }

    public static CustomerPaymentFragment newInstance(String customer_id, String customer_name) {
        CustomerPaymentFragment fragment = new CustomerPaymentFragment();
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
        return R.layout.fragment_customer_payment;
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
        tvCreditOs.setText(getContext().getResources().getString(R.string.strRupee) + creditOs);

        //get customer sale amount.....rejection is also substracted from this
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(getContext());
        CustomerRejectionTable rejectionTable = new CustomerRejectionTable(getContext());
        double invoiceTotal = invoiceOutTable.custInvoiceTotalAmount(customer_id);
        double rejectionTotal = rejectionTable.custRejTotalAmount(customer_id);
        double saleTotal = invoiceTotal - rejectionTotal;

        // calculate payable amount
        double payable_amount = saleTotal + creditOs;

        tvSaleAmount.setText(getContext().getResources().getString(R.string.strRupee) + saleTotal);


        setTitle("Payment");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save some more info


                //move to next fragment
               /* CustomerPaymentFragment rejectionFragment = CustomerPaymentFragment.newInstance(customer_id, customer_name);
                String fragClassName = rejectionFragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, rejectionFragment)
                        .addToBackStack(fragClassName)
                        .commit();*/

            }
        });
    }
}
