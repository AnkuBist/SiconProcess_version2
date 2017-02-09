package com.hgil.siconprocess.activity.fragments.invoice.makeSaleInvoice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.invoice.invoiceSale.CustomerInvoiceAdapter;
import com.hgil.siconprocess.adapter.invoice.invoiceSale.InvoiceModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerInvoiceFragment extends BaseFragment {
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerInvoice)
    RecyclerView rvCustomerInvoice;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    //@BindView(R.id.tvCustomerTotal)
    public static TextView tvInvoiceTotal;

    public static double grandTotal = 0;


    private CustomerInvoiceAdapter invoiceAdapter;
    private DepotInvoiceView customerInvoice;
    private InvoiceOutTable invoiceOutTable;
    private ArrayList<InvoiceModel> arrInvoiceItems = new ArrayList<>();

    public CustomerInvoiceFragment() {
        // Required empty public constructor
    }

    public static CustomerInvoiceFragment newInstance(String customer_id, String customer_name) {
        Log.e("TAG", "newInstance");
        CustomerInvoiceFragment fragment = new CustomerInvoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate");
        grandTotal = 0;
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }

        // initialise the values at first time
        customerInvoice = new DepotInvoiceView(getContext());
        invoiceOutTable = new InvoiceOutTable(getContext());

        invoiceAdapter = new CustomerInvoiceAdapter(getActivity(), arrInvoiceItems);

        if (arrInvoiceItems != null)
            arrInvoiceItems.clear();
        arrInvoiceItems.addAll(customerInvoice.getCustomerInvoice(customer_id));

        // if there is no invoice data exists for the user then get all available stock to the user
        if (arrInvoiceItems.size() == 0) {
            arrInvoiceItems.addAll(customerInvoice.getCustomerInvoiceOff(customer_id));
        }

        /*get customer existing or saved invoice from the outlet*/
       /* ArrayList<InvoiceModel> tempInvoice = invoiceOutTable.getCustomerInvoice(customer_id);
        for ()*/


        for (int i = 0; i < arrInvoiceItems.size(); i++) {
            double itemOrderAmount = arrInvoiceItems.get(i).getOrderAmount();
            grandTotal += itemOrderAmount;
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_customer_invoice;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    boolean mAlreadyLoaded;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        // Do this code only first time, not after rotation or reuse fragment from backstack
        tvInvoiceTotal = (TextView) view.findViewById(R.id.tvInvoiceTotal);
        onFragmentStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    String TAG = getClass().getName();

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, "onDestroyView");
    }

    private void onFragmentStart() {
        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerInvoice.setLayoutManager(linearLayoutManager);

        rvCustomerInvoice.setAdapter(invoiceAdapter);

        setTitle("Today's Sale");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<InvoiceModel> reviewOrderData = new ArrayList<InvoiceModel>();
                for (int i = 0; i < arrInvoiceItems.size(); i++) {
                    InvoiceModel invoiceModel = arrInvoiceItems.get(i);
                    if (invoiceModel.getOrderAmount() > 0 && invoiceModel.getDemandTargetQty() > 0) {
                        reviewOrderData.add(invoiceModel);
                    }
                }

                // here simply forward the collected array data to the next fragmen to let the user choose whether to save invoice or not
                InvoiceOutFragment invoiceOutFragment = InvoiceOutFragment.newInstance(customer_id, customer_name, reviewOrderData);
                String fragClassName = invoiceOutFragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, invoiceOutFragment)
                        .addToBackStack(fragClassName)
                        .commit();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        tvInvoiceTotal.setText(strRupee + String.valueOf(Utility.roundTwoDecimals(grandTotal)));
        if (arrInvoiceItems.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerInvoice.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerInvoice.setVisibility(View.VISIBLE);
        }
    }

    public void setInvoiceTotal(String invoiceAmount) {
        tvInvoiceTotal.setText(invoiceAmount);
    }
}
