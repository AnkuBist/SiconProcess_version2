package com.hgil.siconprocess_view.activity.fragments.routeLevel.routeRemark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.RouteRemarkTable;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRouteRemarkFragment extends Route_Base_Fragment {

    @BindView(R.id.etRouteRemark)
    EditText etRouteRemark;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    private RouteRemarkTable routeRemarkTable;

    public AddRouteRemarkFragment() {
        // Required empty public constructor
    }

    public static AddRouteRemarkFragment newInstance() {
        AddRouteRemarkFragment fragment = new AddRouteRemarkFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_add_route_remark;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSyncButton();
        setTitle("User Route Remark");

        routeRemarkTable = new RouteRemarkTable(getContext());

        final RouteRemarkModel routeRemarkModel = routeRemarkTable.getRouteRemark(getLoginId(), getRouteId());

        etRouteRemark.setText(routeRemarkModel.getRoute_remark());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRemark = etRouteRemark.getText().toString().trim();
                if (strRemark.isEmpty()) {
                    Toast.makeText(getContext(), "Enter some remark before submit", Toast.LENGTH_LONG).show();
                } else {
                    routeRemarkModel.setUser_id(getLoginId());
                    routeRemarkModel.setRoute_id(getRouteId());
                    routeRemarkModel.setRoute_name(getRouteName());
                    routeRemarkModel.setRoute_remark(strRemark);
                    routeRemarkModel.setRemark_date(Utility.getCurDate());
                    routeRemarkTable.insertRouteRemark(routeRemarkModel);

                    // snackbar to show plan updated
                    showSnackbar(getView(), "Your Remark Updated");

                    //now finish this fragment
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRouteRemark.setText("");
                etRouteRemark.clearFocus();

                //now finish this fragment
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}