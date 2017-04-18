package com.hgil.siconprocess_view.activity.fragments.baseLevel.planner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;
import com.hgil.siconprocess_view.database.localDb.PlannerTable;
import com.hgil.siconprocess_view.utils.Utility;

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

    private PlannerTable plannerTable;

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

        hideSyncButton();
        setTitle("User Plan");

        plannerTable = new PlannerTable(getContext());

        final PlanModel planModel = plannerTable.getUserPlan(getLoginId(), Utility.getCurDate());

        etRoutePlan.setText(planModel.getUserPlan());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planModel.setUserId(getLoginId());
                planModel.setUserPlan(etRoutePlan.getText().toString().trim());
                planModel.setPlanDate(Utility.getCurDate());
                plannerTable.insertUserPlan(planModel);

                // snackbar to show plan updated
                showSnackbar(getView(), "Your Plan Updated");

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