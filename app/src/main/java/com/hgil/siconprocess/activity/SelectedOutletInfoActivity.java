package com.hgil.siconprocess.activity;

import android.os.Bundle;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseToolbarActivity;

import butterknife.ButterKnife;

public class SelectedOutletInfoActivity extends BaseToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_outlet_info);

        ButterKnife.bind(this);
        setNavTitle("Outlet Info");
        hideSaveBtn();

        if (getIntent() != null) {
            customer_id = getIntent().getStringExtra(CUSTOMER_ID);
        }

        // get customer details here only
    }
}
