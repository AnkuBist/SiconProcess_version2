package com.hgil.siconprocess.activity.temp_activities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;

public class SaleRejHistoryFragment extends BaseFragment {

    @Nullable
    @BindView(R.id.etFromDate)
    EditText etFromDate;
    @Nullable
    @BindView(R.id.etToDate)
    EditText etToDate;
    @Nullable
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.rvSaleRej)
    RecyclerView rvSaleRej;

    private Calendar myCalendar;
    private DatePickerDialog.OnDateSetListener date;

    public SaleRejHistoryFragment() {
        // Required empty public constructor
    }

    public static SaleRejHistoryFragment newInstance() {
        SaleRejHistoryFragment fragment = new SaleRejHistoryFragment();
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
        return R.layout.fragment_route_history;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        //tvRouteName.setText(getRouteName());
        setTitle("Sale & Rejection History");
        hideSaveButton();

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

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                rvSaleRej.setLayoutManager(linearLayoutManager);

                ArrayList<SaleRejModel> arrSaleRej = new ArrayList<>();
                //arrSaleRej.addAll(routeMap.getRouteCustomers());

                for (int i = 0; i < 10; i++) {
                    SaleRejModel saleRejModel = new SaleRejModel();
                    saleRejModel.setStr_date("" + (i + 01) + "/03/2017");
                    saleRejModel.setSale_amt((1000 + (1000 * i) + i) + (i * 10));
                    saleRejModel.setRej_prct((10 + i) + (i / 100));
                    arrSaleRej.add(saleRejModel);
                }

                SaleRejAdapter saleRejAdapter = new SaleRejAdapter(getContext(), arrSaleRej);
                rvSaleRej.setAdapter(saleRejAdapter);
            }
        });
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
