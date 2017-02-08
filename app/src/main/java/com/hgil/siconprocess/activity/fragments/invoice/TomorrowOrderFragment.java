package com.hgil.siconprocess.activity.fragments.invoice;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.fragments.invoice.rejActivities.ProductListSelectActivity;
import com.hgil.siconprocess.adapter.invoiceRejection.CRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.InvoiceRejectionAdapter;
import com.hgil.siconprocess.adapter.productSelection.ProductSelectModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.dbModels.ChequeDetailsModel;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TomorrowOrderFragment extends BaseFragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerOrder)
    RecyclerView rvCustomerOrder;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.btnAddItems)
    Button btnAddItems;

    private InvoiceRejectionAdapter rejectionAdapter;
    private CustomerRejectionTable rejectionTable;
    private ArrayList<CRejectionModel> arrRejection;

    public TomorrowOrderFragment() {
        // Required empty public constructor
    }

    public static TomorrowOrderFragment newInstance(String customer_id, String customer_name) {
        TomorrowOrderFragment fragment = new TomorrowOrderFragment();
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
        return R.layout.fragment_tomorrow_order;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        // do the rest stuff here only
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerOrder.setLayoutManager(linearLayoutManager);

        rejectionTable = new CustomerRejectionTable(getContext());
        arrRejection = rejectionTable.getCustomerRejections(customer_id);

        rejectionAdapter = new InvoiceRejectionAdapter(getActivity(), arrRejection);
        rvCustomerOrder.setAdapter(rejectionAdapter);

        setTitle("Tomorrow's Order");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the crate details to the database


                //move to next fragment
                FinalInvoiceFragment fragment = FinalInvoiceFragment.newInstance(customer_id, customer_name);
                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                        .addToBackStack(fragClassName)
                        .commit();
            }
        });

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TomorrowOrderProductListActivity.class);
                intent.putExtra("customer_id", customer_id);
                intent.putExtra("customer_name", customer_name);
                ArrayList<String> arrItems = new ArrayList<String>();
                for (int i = 0; i < arrRejection.size(); i++) {
                    arrItems.add(arrRejection.get(i).getItem_id());
                }
                intent.putStringArrayListExtra("existing_items", arrItems);

                startActivityForResult(intent, PRODUCT_LIST);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshScreen();
    }

    // refresh screen
    private void refreshScreen() {
        if (arrRejection.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerOrder.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerOrder.setVisibility(View.VISIBLE);
        }
    }

    private static int PRODUCT_LIST = 121;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();

            if (requestCode == PRODUCT_LIST) {
                ArrayList<ProductSelectModel> arrProduct = (ArrayList<ProductSelectModel>) bundle.getSerializable("selected_products");
                for (int i = 0; i < arrProduct.size(); i++) {
                    ProductSelectModel pModel = arrProduct.get(i);
                    CRejectionModel rejectionModel = new CRejectionModel();
                    rejectionModel.setItem_id(pModel.getItem_id());
                    rejectionModel.setItem_name(pModel.getItem_name());
                    rejectionModel.setCustomer_id(customer_id);
                    rejectionModel.setCustomer_name(customer_name);
                    rejectionModel.setPrice(pModel.getItem_price());
                    if (pModel.isSelected())
                        arrRejection.add(rejectionModel);
                }
                rejectionAdapter.notifyDataSetChanged();
                refreshScreen();
            }
        }
    }
}
