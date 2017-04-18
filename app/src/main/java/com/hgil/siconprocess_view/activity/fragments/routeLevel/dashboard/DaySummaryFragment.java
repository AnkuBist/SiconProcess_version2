package com.hgil.siconprocess_view.activity.fragments.routeLevel.dashboard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.PaymentView;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaySummaryFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    /*    @BindView(R.id.tvTargetCalls)
        TextView tvTargetCalls;*/
    @BindView(R.id.tvTotalCalls)
    TextView tvTotalCalls;
    @BindView(R.id.tvProductiveCalls)
    TextView tvProductiveCalls;
    @BindView(R.id.tvTotalBillValue)
    TextView tvTotalBillValue;
    /*  @BindView(R.id.tvAvgBillValue)
      TextView tvAvgBillValue;*/
    @BindView(R.id.tvTodayCollection)
    TextView tvTodayCollection;
    @BindView(R.id.tvTotalOS)
    TextView tvTotalOS;

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
        hideSyncButton();

        OutletView outletView = new OutletView(getContext());
        PaymentView paymentView = new PaymentView(getContext());

        // route target calls
        int target_calls = outletView.routeTargetCalls(getRouteId());

        // route productive calls
        int productive_calls = outletView.routeProductiveCalls(getRouteId());

        // total route collection amount
        double route_total_collection = paymentView.routeTotalSale(getRouteId());

        //  total route sale amount
        double route_total_sale = outletView.routeTotalSale(getRouteId());

        double route_outstanding = outletView.routeOutstanding(getRouteId());

        // count total outlets in the route
        //tvTargetCalls.setText(String.valueOf(target_calls));

        // get total outlet calls made
        tvTotalCalls.setText(String.valueOf(target_calls));
        tvProductiveCalls.setText(String.valueOf(productive_calls));

        // get total bill value
        tvTotalBillValue.setText(strRupee + Utility.roundOff(route_total_sale));

        // calculate average bill value
        /*double avgBill = 0;

        avgBill = route_total_os / productive_calls;
        if (Double.isNaN(avgBill))
            avgBill = 0;
        tvAvgBillValue.setText(strRupee + Utility.roundOff(avgBill));*/

        tvTodayCollection.setText(strRupee + Utility.roundOff(route_total_collection));

        tvTotalOS.setText(strRupee + Utility.roundOff(route_outstanding + route_total_sale - route_total_collection));
    }
}
