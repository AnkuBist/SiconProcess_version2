package com.hgil.siconprocess_view.activity.fragments.outletLevel.saleRejHistory;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.saleRej.SaleRejAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.SaleHistoryView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class SaleRejHistoryFragment extends Route_Base_Fragment {

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @BindView(R.id.etToDate)
    EditText etToDate;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.rvSaleRej)
    RecyclerView rvSaleRej;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private SaleHistoryView saleHistoryView;
    private SaleRejAdapter saleRejAdapter;
    private ArrayList<SaleHistoryModel> arrSaleRej = new ArrayList<>();

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

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

        myCalendar = Calendar.getInstance();
        updateToDate();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        etFromDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_UP == event.getAction()) {
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH));//.show();
                    datePickerDialog.getDatePicker().setMinDate(myCalendar.getTimeInMillis());
                    datePickerDialog.show();
                }
                return false;
            }
        });

        etToDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // do nothing
                return false;
            }
        });

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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 /*String from_date = etFromDate.getText().toString();
        String to_date = etToDate.getText().toString();

        if (from_date.isEmpty()) {
            etFromDate.requestFocus();
            Toast.makeText(this, "Choose start date", Toast.LENGTH_SHORT).show();
        } else if (to_date.isEmpty()) {
            etToDate.requestFocus();
            Toast.makeText(this, "Choose to date", Toast.LENGTH_SHORT).show();
        } else {
            // make server request or local data request
            //TODO
        }*/

                //simly udpated the recycler view to display dummy data
                //TODO
                arrSaleRej.clear();
                arrSaleRej.addAll(saleHistoryView.outletSaleHistory(customer_id));
                saleRejAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrSaleRej.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvSaleRej.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvSaleRej.setVisibility(View.VISIBLE);
        }
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etFromDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateToDate() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Calendar today = Calendar.getInstance();
        etToDate.setText(sdf.format(today.getTime()));
    }

}
