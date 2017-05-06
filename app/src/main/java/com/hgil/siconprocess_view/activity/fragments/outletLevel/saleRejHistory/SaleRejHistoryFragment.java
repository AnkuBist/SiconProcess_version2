package com.hgil.siconprocess_view.activity.fragments.outletLevel.saleRejHistory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.saleRej.SaleRejAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.SaleHistoryView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;

import java.util.ArrayList;

import butterknife.BindView;

public class SaleRejHistoryFragment extends Route_Base_Fragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.layoutAvg)
    LinearLayout layoutAvg;
    @BindView(R.id.tvAvgSale)
    TextView tvAvgSale;
    @BindView(R.id.tvAvgRej)
    TextView tvAvgRej;
    @BindView(R.id.rvSaleRej)
    RecyclerView rvSaleRej;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private SaleHistoryView saleHistoryView;
    private SaleRejAdapter saleRejAdapter;
    private ArrayList<SaleHistoryModel> arrSaleRej = new ArrayList<>();

    public SaleRejHistoryFragment() {
        // Required empty public constructor
    }

    public static SaleRejHistoryFragment newInstance(String customer_id, String customer_name) {
        SaleRejHistoryFragment fragment = new SaleRejHistoryFragment();
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
        return R.layout.fragment_route_history;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTitle(getString(R.string.str_nav_sale_rej_history));
        hideSyncButton();

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSaleRej.setLayoutManager(linearLayoutManager);
        saleHistoryView = new SaleHistoryView(getContext());

        if (arrSaleRej != null)
            arrSaleRej.clear();
        else
            arrSaleRej = new ArrayList<>();

        arrSaleRej.addAll(saleHistoryView.outletSaleHistory(customer_id));
        saleRejAdapter = new SaleRejAdapter(getContext(), arrSaleRej);
        rvSaleRej.setAdapter(saleRejAdapter);

        if (arrSaleRej.size() > 0) {
            double avgNetSale = saleHistoryView.avgCustomerNetSale(getRouteId(), customer_id) / arrSaleRej.size();
            /*double rejPrct = 0;
            for (SaleHistoryModel saleHistoryModel : arrSaleRej)
                rejPrct += saleHistoryModel.getRejPrct();
*/
            tvAvgSale.setText("Avg. Sale: " + strRupee + Math.round(avgNetSale));
            //tvAvgRej.setText("Avg. Rej: " + Math.round(rejPrct / arrSaleRej.size()) + "%");
            //tvAvgRej.setText("Avg. Rej: " + saleHistoryView.avgCustomerRejPrct(getRouteId(), customer_id) + "%");
        }
        tvAvgRej.setText("Avg. Rej: " + saleHistoryView.avgCustomerRejPrct(getRouteId(), customer_id) + "%");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrSaleRej.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvSaleRej.setVisibility(View.GONE);
            layoutAvg.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvSaleRej.setVisibility(View.VISIBLE);
            layoutAvg.setVisibility(View.VISIBLE);
        }
    }
}
