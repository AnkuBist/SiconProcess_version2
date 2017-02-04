package com.hgil.siconprocess.activity.navFragments.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.ProductListSelectActivity;
import com.hgil.siconprocess.adapter.invoiceRejection.CRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.InvoiceRejectionAdapter;
import com.hgil.siconprocess.adapter.productSelection.ProductSelectModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerRejectionFragment extends BaseFragment {
    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
    private String customer_id, customer_name;

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerRejection)
    RecyclerView rvCustomerRejection;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.btnAddItems)
    Button btnAddItems;

    private static int REJECTION_LIST = 121;

    private InvoiceRejectionAdapter rejectionAdapter;
    private CustomerRejectionTable rejectionTable;
    private ArrayList<CRejectionModel> arrRejection;

    public CustomerRejectionFragment() {
        // Required empty public constructor
    }

    public static CustomerRejectionFragment newInstance(String customer_id, String customer_name) {
        CustomerRejectionFragment fragment = new CustomerRejectionFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_customer_rejection;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerRejection.setLayoutManager(linearLayoutManager);

        rejectionTable = new CustomerRejectionTable(getContext());
        arrRejection = rejectionTable.getCustomerRejections(customer_id);

        rejectionAdapter = new InvoiceRejectionAdapter(getActivity(), arrRejection);
        rvCustomerRejection.setAdapter(rejectionAdapter);

        setTitle("Goods Return");
        showSaveButton();

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save rejections to table here only and move to the final payment fragment

            }
        });

        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductListSelectActivity.class);
                intent.putExtra("customer_id", customer_id);
                intent.putExtra("customer_name", customer_name);
                ArrayList<String> arrItems = new ArrayList<String>();
                for (int i = 0; i < arrRejection.size(); i++) {
                    arrItems.add(arrRejection.get(i).getItem_id());
                }
                intent.putStringArrayListExtra("rejected_items", arrItems);

                startActivityForResult(intent, REJECTION_LIST);
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
            rvCustomerRejection.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerRejection.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REJECTION_LIST && resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();

            ArrayList<ProductSelectModel> arrProduct = (ArrayList<ProductSelectModel>) bundle.getSerializable("selected_products");
            for (int i = 0; i < arrProduct.size(); i++) {
                ProductSelectModel pModel = arrProduct.get(i);
                CRejectionModel rejectionModel = new CRejectionModel();
                rejectionModel.setItem_id(pModel.getItem_id());
                rejectionModel.setItem_name(pModel.getItem_name());
                rejectionModel.setCustomer_id(customer_id);
                rejectionModel.setCustomer_name(customer_name);
                if (pModel.isSelected())
                    arrRejection.add(rejectionModel);
            }
            rejectionAdapter.notifyDataSetChanged();
            refreshScreen();
        }
    }
}
