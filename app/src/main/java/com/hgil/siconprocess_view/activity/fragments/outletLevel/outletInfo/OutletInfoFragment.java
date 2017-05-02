package com.hgil.siconprocess_view.activity.fragments.outletLevel.outletInfo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletModel;

import butterknife.BindView;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class OutletInfoFragment extends Route_Base_Fragment {

    @BindView(R.id.etDealerName)
    TextView etDealerName;
    @BindView(R.id.etDealerCode)
    TextView etDealerCode;
    @BindView(R.id.etRouteName)
    TextView etRouteName;
    @BindView(R.id.etDealerContact)
    TextView etDealerContact;

    public OutletInfoFragment() {
        // Required empty public constructor
    }

    public static OutletInfoFragment newInstance(String customer_id, String customer_name) {
        OutletInfoFragment fragment = new OutletInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_outlet_info;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitle("Outlet Info");
        hideSyncButton();

        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }

        // get customer details here only
        OutletView outletView = new OutletView(getContext());
        OutletModel outletModel = outletView.getOutletInfo(customer_id);

        //display customer information
        etDealerName.setText(outletModel.getCustomerName());
        etDealerCode.setText(outletModel.getCustomerId());
        etRouteName.setText(outletModel.getRouteName());
        etDealerContact.setText(outletModel.getContactNo());
    }
}