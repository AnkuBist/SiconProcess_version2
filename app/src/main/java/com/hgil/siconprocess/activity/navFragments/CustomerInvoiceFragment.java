package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.CustomerInvoiceAdapter;
import com.hgil.siconprocess.database.tables.DepotInvoiceView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInvoiceFragment extends Fragment {

    private static final String CUSTOMER_ID = "customer_id";
    private String customer_id;

    @BindView(R.id.rvCustomerInvoice)
    RecyclerView rvCustomerInvoice;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private CustomerInvoiceAdapter invoiceAdapter;
    private DepotInvoiceView customerInvoice;
    private ArrayList<InvoiceDetailModel> arrInvoiceItems;

    public CustomerInvoiceFragment() {
        // Required empty public constructor
    }

    public static CustomerInvoiceFragment newInstance(String customer_id) {
        CustomerInvoiceFragment fragment = new CustomerInvoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            customer_id = getArguments().getString(CUSTOMER_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_invoice, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerInvoice.setLayoutManager(linearLayoutManager);

        customerInvoice = new DepotInvoiceView(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrInvoiceItems != null)
            arrInvoiceItems.clear();
        arrInvoiceItems = customerInvoice.getAllCustomerInvoice(customer_id);
        invoiceAdapter = new CustomerInvoiceAdapter(getActivity(), arrInvoiceItems);
        rvCustomerInvoice.setAdapter(invoiceAdapter);
        if (arrInvoiceItems.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerInvoice.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerInvoice.setVisibility(View.VISIBLE);
        }
    }
}
