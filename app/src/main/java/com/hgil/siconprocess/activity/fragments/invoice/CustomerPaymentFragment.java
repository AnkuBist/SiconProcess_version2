package com.hgil.siconprocess.activity.fragments.invoice;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.dbModels.ChequeDetailsModel;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindString;
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

        tvCustomerTotal.setText(strRupee + "0.00");

        // get customer credit outstanding
        CreditOpeningTable creditOpeningTable = new CreditOpeningTable(getContext());
        double creditOs = creditOpeningTable.custCreditAmount(customer_id);
        tvCreditOs.setText(strRupee + creditOs);

        //get customer sale amount.....rejection is also substracted from this
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(getContext());
        CustomerRejectionTable rejectionTable = new CustomerRejectionTable(getContext());
        double invoiceTotal = invoiceOutTable.custInvoiceTotalAmount(customer_id);
        double rejectionTotal = rejectionTable.custRejTotalAmount(customer_id);
        double saleTotal = invoiceTotal - rejectionTotal;
        tvSaleAmount.setText(strRupee + saleTotal);

        // calculate payable amount
        double payable_amount = saleTotal + creditOs;
        tvTotalOs.setText(strRupee + payable_amount);

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

        etCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    tvCustomerTotal.setText(strRupee + Utility.roundTwoDecimals(Double.parseDouble(etCash.getText().toString())));
                } else if (s.length() == 0) {
                    tvCustomerTotal.setText(strRupee + "0.00");
                }
            }
        });

        //etCheque.setEnabled(false);
        /*etCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChequeDetailsActivity.class);
                startActivityForResult(intent, CHEQUE_DETAILS);

            }
        });*/
        etCheque.setOnTouchListener(new View.OnTouchListener()

                                    {
                                        @Override
                                        public boolean onTouch(View v, MotionEvent event) {
           /*     if(MotionEvent.ACTION_UP == event.getAction())
                    Toast.makeText(getApplicationContext(), "onTouch: Up", Toast.LENGTH_SHORT).show();
                return false;*/
                                            Intent intent = new Intent(getContext(), ChequeDetailsActivity.class);
                                            startActivityForResult(intent, CHEQUE_DETAILS);
                                            return false;
                                            // return false;
                                        }
                                    }

        );
    }

    public static final int CHEQUE_DETAILS = 100;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();

            if (requestCode == CHEQUE_DETAILS) {
                ChequeDetailsModel chequeDetail = (ChequeDetailsModel) bundle.getSerializable("cheque_detail");
                chequeDetail.setCustomer_name(customer_name);
                chequeDetail.setCustomer_id(customer_id);
                tvCustomerTotal.setText(strRupee + Utility.roundTwoDecimals(chequeDetail.getChequeAmount()));
            }
        }
    }
}
