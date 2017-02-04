package com.hgil.siconprocess.activity.navFragments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.invoice.invoiceOut.CustomerInvoiceOutAdapter;
import com.hgil.siconprocess.adapter.invoice.invoiceSale.InvoiceModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceOutFragment extends BaseFragment {
    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
    private static final String INVOICE_LIST = "invoice_list";
    private String customer_id, customer_name;

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerInvoice)
    RecyclerView rvCustomerInvoice;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    //@BindView(R.id.tvCustomerTotal)
    public static TextView tvCustomerTotal;

    public static double grandTotal = 0;

    private CustomerInvoiceOutAdapter invoiceAdapter;
    private InvoiceOutTable invoiceOutTable;
    private ArrayList<InvoiceModel> arrInvoiceItems;

    public InvoiceOutFragment() {
        // Required empty public constructor
    }

    public static InvoiceOutFragment newInstance(String customer_id, String customer_name, ArrayList<InvoiceModel> arrayList) {
        InvoiceOutFragment fragment = new InvoiceOutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        bundle.putSerializable(INVOICE_LIST, arrayList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        grandTotal = 0;
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
            arrInvoiceItems = (ArrayList<InvoiceModel>) getArguments().getSerializable(INVOICE_LIST);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_invoice_out;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        tvCustomerTotal = (TextView) view.findViewById(R.id.tvCustomerTotal);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerInvoice.setLayoutManager(linearLayoutManager);

        invoiceOutTable = new InvoiceOutTable(getContext());

        invoiceAdapter = new CustomerInvoiceOutAdapter(getActivity(), arrInvoiceItems);
        rvCustomerInvoice.setAdapter(invoiceAdapter);

        setTitle("Today's Sale Summary");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Saving data to local database under process.", Toast.LENGTH_SHORT).show();

                // move to next fragment to review user order with the items ordered
                invoiceOutTable.insertInvoiceOut(arrInvoiceItems, customer_id);

                // start rejection fragment
                CustomerRejectionFragment rejectionFragment = CustomerRejectionFragment.newInstance(customer_id, customer_name);
                String fragClassName = rejectionFragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                //FragmentManager fragmentManager = (getChildFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, rejectionFragment)
                        .addToBackStack(fragClassName)
                        .commit();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < arrInvoiceItems.size(); i++) {
            double itemOrrderAmount = arrInvoiceItems.get(i).getOrderAmount();
            grandTotal += itemOrrderAmount;
        }

        tvCustomerTotal.setText(getResources().getString(R.string.strRupee) + String.valueOf(Utility.roundTwoDecimals(grandTotal)));
        if (arrInvoiceItems.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerInvoice.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerInvoice.setVisibility(View.VISIBLE);
        }
    }

    public void setInvoiceTotal(String invoiceAmount) {
        tvCustomerTotal.setText(invoiceAmount);
    }

}
