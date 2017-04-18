package com.hgil.siconprocess_view.activity.fragments.outletLevel.remark;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkModel;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutletRemarkFragment extends Route_Base_Fragment {

    @BindView(R.id.etOutletRemark)
    EditText etOutletRemark;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    private OutletRemarkTable outletRemarkTable;

    public OutletRemarkFragment() {
        // Required empty public constructor
    }

    public static OutletRemarkFragment newInstance(String customer_id, String customer_name) {
        OutletRemarkFragment fragment = new OutletRemarkFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_outlet_remark;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSaveButton();
        setTitle("Route Plan");

        outletRemarkTable = new OutletRemarkTable(getContext());

        final OutletRemarkModel routePlanModel = outletRemarkTable.getOutletRemark(getRouteId(), customer_id);

        etOutletRemark.setText(routePlanModel.getRemark());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routePlanModel.setRoute_id(getRouteId());
                routePlanModel.setRoute_name(getRouteName());
                routePlanModel.setOutlet_id(customer_id);
                routePlanModel.setOutlet_name(customer_name);
                routePlanModel.setRemark(etOutletRemark.getText().toString().trim());
                routePlanModel.setRemark_date(Utility.getCurDate());
                outletRemarkTable.insertOutletRemark(routePlanModel);

                // snackbar to show plan updated
                showSnackbar(getView(), "Outlet Remark Updated");

                //now finish this fragment
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etOutletRemark.setText("");
                etOutletRemark.clearFocus();

                //now finish this fragment
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}