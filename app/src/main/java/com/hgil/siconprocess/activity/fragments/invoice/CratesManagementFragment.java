package com.hgil.siconprocess.activity.fragments.invoice;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.dbModels.CrateDetailModel;
import com.hgil.siconprocess.database.masterTables.CrateOpeningTable;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CrateOpeningModel;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CratesManagementFragment extends BaseFragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.etOpeningCrates)
    EditText etOpeningCrates;
    @BindView(R.id.etIssuedCrates)
    EditText etIssuedCrates;
    @BindView(R.id.etReceivedCrates)
    EditText etReceivedCrates;
    @BindView(R.id.etBalanceCrates)
    EditText etBalanceCrates;

    private PaymentTable paymentTable;
    private int crateOpening, issuedCrate, receivedCrate, balanceCrate;

    public CratesManagementFragment() {
        // Required empty public constructor
    }

    public static CratesManagementFragment newInstance(String customer_id, String customer_name) {
        CratesManagementFragment fragment = new CratesManagementFragment();
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
        return R.layout.fragment_crates_management;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        // do the rest stuff here only
        paymentTable = new PaymentTable(getContext());
        CrateOpeningTable crateOpeningTable = new CrateOpeningTable(getContext());
        crateOpening = crateOpeningTable.custCreditCrates(customer_id);

        CrateDetailModel crateInfo = paymentTable.getCustomerCrateInfo(customer_id);
        issuedCrate = crateInfo.getIssuedCrates();
        receivedCrate = crateInfo.getReceivedCrates();
        balanceCrate = crateOpening - issuedCrate + receivedCrate;

        etOpeningCrates.setText(String.valueOf(crateOpening));
        etIssuedCrates.setText(String.valueOf(issuedCrate));
        etReceivedCrates.setText(String.valueOf(receivedCrate));
        etBalanceCrates.setText(String.valueOf(balanceCrate));

        etIssuedCrates.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                issuedCrate = Utility.getInteger(etIssuedCrates.getText().toString());
                etBalanceCrates.setText(String.valueOf(crateOpening - issuedCrate + receivedCrate));
            }
        });

        etReceivedCrates.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                receivedCrate = Utility.getInteger(etReceivedCrates.getText().toString());
                etBalanceCrates.setText(String.valueOf(crateOpening - issuedCrate + receivedCrate));
            }
        });

        setTitle("Crates Management");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the crate details to the database
                CrateDetailModel crateDetailModel = new CrateDetailModel();
                crateDetailModel.setCustomer_id(customer_id);
                crateDetailModel.setIssuedCrates(Utility.getInteger(etIssuedCrates.getText().toString()));
                crateDetailModel.setReceivedCrates(Utility.getInteger(etReceivedCrates.getText().toString()));

                paymentTable.insertCustomerCrates(crateDetailModel);

                //move to next fragment
                TomorrowOrderFragment rejectionFragment = TomorrowOrderFragment.newInstance(customer_id, customer_name);
                String fragClassName = rejectionFragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, rejectionFragment)
                        .addToBackStack(fragClassName)
                        .commit();
            }
        });
    }
}
