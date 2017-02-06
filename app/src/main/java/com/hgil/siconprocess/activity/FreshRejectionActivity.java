package com.hgil.siconprocess.activity;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseToolbarActivity;
import com.hgil.siconprocess.base.SiconApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FreshRejectionActivity extends BaseToolbarActivity {
    @Nullable
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @Nullable
    @BindView(R.id.etMShaped)
    EditText etMShaped;
    @Nullable
    @BindView(R.id.etTornPolly)
    EditText etTornPolly;
    @Nullable
    @BindView(R.id.etFungus)
    EditText etFungus;
    @Nullable
    @BindView(R.id.etWetBread)
    EditText etWetBread;
    @Nullable
    @BindView(R.id.etOthers)
    EditText etOthers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_rejection);

        ButterKnife.bind(this);

        tvCustomerName.setText(SiconApp.getInstance().getRouteName());
        setTitle("Goods Return");
        showSaveBtn();


    }


}
