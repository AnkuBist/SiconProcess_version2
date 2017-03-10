package com.hgil.siconprocess.activity.fragments.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaySummaryFragment extends BaseFragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.tvTargetCalls)
    TextView tvTargetCalls;
    @BindView(R.id.tvTotalCalls)
    TextView tvTotalCalls;
    @BindView(R.id.tvProductiveCalls)
    TextView tvProductiveCalls;
    @BindView(R.id.tvTotalBillValue)
    TextView tvTotalBillValue;
    @BindView(R.id.tvAvgBillValue)
    TextView tvAvgBillValue;

    public DaySummaryFragment() {
        // Required empty public constructor
    }

    public static DaySummaryFragment newInstance() {
        DaySummaryFragment fragment = new DaySummaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_day_summary;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        String routeName = getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);

        setTitle("Day Summary");
        hideSaveButton();

        // count total outlets in the route
        CustomerRouteMappingView routeMappingView = new CustomerRouteMappingView(getContext());
        int targetCalls = routeMappingView.numberOfRows();
        tvTargetCalls.setText(String.valueOf(targetCalls));

        PaymentTable paymentTable = new PaymentTable(getContext());
        // get total outlet calls made
        int totalCalls = paymentTable.dataCount();
        tvTotalCalls.setText(String.valueOf(totalCalls));
        tvProductiveCalls.setText(String.valueOf(totalCalls));

        // get total bill value
        double totalBillValue = paymentTable.getRouteSale();
        tvTotalBillValue.setText(String.valueOf(Utility.roundOff(totalBillValue)));

        // calculate average bill value
        double avgBill = 0;

        avgBill = totalBillValue / totalCalls;

        if (Double.isNaN(avgBill))
            avgBill = 0;
        tvAvgBillValue.setText(String.valueOf(Utility.roundOff(avgBill)));
    }
}
