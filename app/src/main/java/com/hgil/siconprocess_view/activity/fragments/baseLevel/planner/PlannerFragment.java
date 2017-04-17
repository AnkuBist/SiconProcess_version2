package com.hgil.siconprocess_view.activity.fragments.baseLevel.planner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.RoutePlanModel;
import com.hgil.siconprocess_view.database.localDb.RoutePlannerTable;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlannerFragment extends Base_Fragment {

    @BindView(R.id.etRoutePlan)
    EditText etRoutePlan;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.btnCancel)
    Button btnCancel;

    private RoutePlannerTable routePlannerTable;

    public PlannerFragment() {
        // Required empty public constructor
    }

    public static PlannerFragment newInstance() {
        PlannerFragment fragment = new PlannerFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_planner;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSaveButton();
        setTitle("Route Plan");

        routePlannerTable = new RoutePlannerTable(getContext());

        final RoutePlanModel routePlanModel = routePlannerTable.getRoutePlan(getRouteId());

        etRoutePlan.setText(routePlanModel.getRoute_plan());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                routePlanModel.setRoute_id(getRouteId());
                routePlanModel.setRoute_plan(etRoutePlan.getText().toString().trim());
                routePlannerTable.insertRoutePlan(routePlanModel);

                // snackbar to show plan updated
                showSnackbar(getView(), "Route Plan Updated");

                //now finish this fragment
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etRoutePlan.setText("");
                etRoutePlan.clearFocus();

                //now finish this fragment
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
    }
}