package com.hgil.siconprocess.activity.fragments.invoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.HomeInvoiceActivity;
import com.hgil.siconprocess.activity.fragments.invoice.rejActivities.ProductListSelectActivity;
import com.hgil.siconprocess.adapter.invoiceRejection.CRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.FreshRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.InvoiceRejectionAdapter;
import com.hgil.siconprocess.adapter.invoiceRejection.MarketRejectionModel;
import com.hgil.siconprocess.adapter.productSelection.ProductSelectModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerRejectionFragment extends BaseFragment {

    public static int FRESH_REJECTION_ID = 0;
    public static int MARKET_REJECTION_ID = 0;
    private static int REJECTION_LIST = 121;
    private static String customerName;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerRejection)
    RecyclerView rvCustomerRejection;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.btnAddItems)
    Button btnAddItems;
    private InvoiceRejectionAdapter rejectionAdapter;
    private CustomerRejectionTable rejectionTable;
    private DepotInvoiceView depotInvoiceView;
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

    public static String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public static int getFreshRejectionId() {
        return FRESH_REJECTION_ID;
    }

    public static void setFreshRejectionId(int id) {
        FRESH_REJECTION_ID = id;
    }

    public static int getMarketRejectionId() {
        return MARKET_REJECTION_ID;
    }

    public static void setMarketRejectionId(int id) {
        MARKET_REJECTION_ID = id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
            setCustomerName(customer_name);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_customer_rejection;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerRejection.setLayoutManager(linearLayoutManager);

        depotInvoiceView = new DepotInvoiceView(getContext());
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
                rejectionTable.insertCustRejections(arrRejection, customer_id);

                // show snackbar message
                showSnackbar(getView(), "Rejected items details saved successfully to Invoice.");

                // now move to next fragment to make payment and display the user payment
                // start rejection fragment
                CustomerPaymentFragment fragment = CustomerPaymentFragment.newInstance(customer_id, customer_name);
                launchInvoiceFragment(fragment);
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
                ((HomeInvoiceActivity) getContext()).overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshScreen();
        updateSaveIcon();
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
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();

            if (requestCode == REJECTION_LIST) {
                ArrayList<ProductSelectModel> arrProduct = (ArrayList<ProductSelectModel>) bundle.getSerializable("selected_products");
                for (int i = 0; i < arrProduct.size(); i++) {
                    ProductSelectModel pModel = arrProduct.get(i);
                    CRejectionModel rejectionModel = new CRejectionModel();

                    //TODO --GET product customer invoice no.

                    rejectionModel.setCashier_code(depotInvoiceView.getRouteCashierCode());

                    rejectionModel.setItem_id(pModel.getItem_id());
                    rejectionModel.setItem_name(pModel.getItem_name());
                    rejectionModel.setCustomer_id(customer_id);
                    rejectionModel.setCustomer_name(customer_name);
                    rejectionModel.setPrice(pModel.getItem_price());
                    if (pModel.isSelected())
                        arrRejection.add(rejectionModel);
                }
            }
            // market rejection details entered
            else if (requestCode == MARKET_REJECTION_ID) {
                arrRejection.get(getMarketRejectionId()).setMarketRejection((MarketRejectionModel) bundle.getSerializable("marketRejection"));
            }
            // fresh rejection details entered
            else if (requestCode == FRESH_REJECTION_ID) {
                arrRejection.get(getFreshRejectionId()).setFreshRejection((FreshRejectionModel) bundle.getSerializable("freshRejection"));
            }
            rejectionAdapter.notifyDataSetChanged();
            refreshScreen();
        }
    }
}
