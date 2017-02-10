package com.hgil.siconprocess.activity.fragments.invoice.makeOrderInvoice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.fragments.invoice.FinalInvoiceFragment;
import com.hgil.siconprocess.adapter.nextDayOrder.DisplayNextDayOrderAdapter;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.dbModels.NextDayOrderModel;
import com.hgil.siconprocess.database.tables.NextDayOrderTable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayTomorrowOrderFragment extends BaseFragment {
    private static final String ORDER_LIST = "order_list";

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerOrder)
    RecyclerView rvCustomerOrder;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private DisplayNextDayOrderAdapter orderAdapter;
    private NextDayOrderTable orderTable;
    private ArrayList<NextDayOrderModel> arrOrder = new ArrayList<>();

    public DisplayTomorrowOrderFragment() {
        // Required empty public constructor
    }

    public static DisplayTomorrowOrderFragment newInstance(String customer_id, String customer_name, ArrayList<NextDayOrderModel> arrayList) {
        DisplayTomorrowOrderFragment fragment = new DisplayTomorrowOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        bundle.putSerializable(ORDER_LIST, arrayList);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
            arrOrder.addAll((ArrayList<NextDayOrderModel>) getArguments().getSerializable(ORDER_LIST));
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_diplay_tomorrow_order;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        // do the rest stuff here only
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerOrder.setLayoutManager(linearLayoutManager);

        orderTable = new NextDayOrderTable(getContext());
        orderAdapter = new DisplayNextDayOrderAdapter(getActivity(), arrOrder);
        rvCustomerOrder.setAdapter(orderAdapter);

        setTitle("Order Summary");
        showSaveButton();
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // save the crate details to the database
                orderTable.insertNextOrder(arrOrder, customer_id);

                //move to next fragment
                FinalInvoiceFragment fragment = FinalInvoiceFragment.newInstance(customer_id, customer_name);
                launchInvoiceFragment(fragment);
                /*String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment)
                        .addToBackStack(fragClassName)
                        .commit();*/
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshScreen();
    }

    // refresh screen
    private void refreshScreen() {
        if (arrOrder.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerOrder.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerOrder.setVisibility(View.VISIBLE);
        }
    }
}
