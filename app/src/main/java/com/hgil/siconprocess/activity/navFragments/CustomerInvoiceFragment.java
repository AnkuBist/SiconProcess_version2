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
import com.hgil.siconprocess.adapter.invoice.CustomerInvoiceAdapter;
import com.hgil.siconprocess.adapter.invoice.InvoiceModel;
import com.hgil.siconprocess.database.tables.DepotInvoiceView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.InvoiceDetailModel;
import com.hgil.siconprocess.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInvoiceFragment extends Fragment {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
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


    private CustomerInvoiceAdapter invoiceAdapter;
    private DepotInvoiceView customerInvoice;
    private ArrayList<InvoiceModel> arrInvoiceItems;

    public static ArrayList<Double> listItemOrderAmount;

    public CustomerInvoiceFragment() {
        // Required empty public constructor
    }

    public static CustomerInvoiceFragment newInstance(String customer_id, String customer_name) {
        CustomerInvoiceFragment fragment = new CustomerInvoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
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
        }
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

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        tvCustomerTotal = (TextView) view.findViewById(R.id.tvCustomerTotal);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerInvoice.setLayoutManager(linearLayoutManager);

        customerInvoice = new DepotInvoiceView(getActivity());

        arrInvoiceItems = new ArrayList<>();
        listItemOrderAmount = new ArrayList<>();
        invoiceAdapter = new CustomerInvoiceAdapter(getActivity(), arrInvoiceItems);
        rvCustomerInvoice.setAdapter(invoiceAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrInvoiceItems != null)
            arrInvoiceItems.clear();
        arrInvoiceItems.addAll(customerInvoice.getCustomerInvoice(customer_id));
        for (int i = 0; i < arrInvoiceItems.size(); i++) {
            double itemOrrderAmount = arrInvoiceItems.get(i).getOrderAmount();
            listItemOrderAmount.add(itemOrrderAmount);
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

    public void updateData(int position, InvoiceModel invoiceModel) {
        invoiceAdapter.notifyItemChanged(position, invoiceModel);
    }
}
