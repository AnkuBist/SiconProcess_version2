package com.hgil.siconprocess.activity.base_frame;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CustomerRouteMapModel;

import butterknife.BindView;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public class OutletInfoFragment extends BaseFragment {

    @BindView(R.id.etDealerName)
    TextView etDealerName;
    @BindView(R.id.etDealerCode)
    TextView etDealerCode;
    @BindView(R.id.etRouteName)
    TextView etRouteName;
    @BindView(R.id.etDealerType)
    TextView etDealerType;
    @BindView(R.id.etDealerAcntNum)
    TextView etDealerAcntNum;

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
        return R.layout.activity_selected_outlet_info;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitle("Outlet Info");
        hideSaveButton();

        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }

        // get customer details here only
        CustomerRouteMappingView customerRouteMappingView = new CustomerRouteMappingView(getContext());
        CustomerRouteMapModel customerRouteMapModel = customerRouteMappingView.getCustomerRouteMapByCustomer(customer_id);

        //display customer information
        etDealerName.setText(customerRouteMapModel.getCustomerName());
        etDealerCode.setText(customerRouteMapModel.getCustomerId());
        etRouteName.setText(customerRouteMapModel.getRouteName());
        etDealerType.setText(customerRouteMapModel.getCType());
        etDealerAcntNum.setText(customerRouteMapModel.getACCOUNTNUM());
    }
}