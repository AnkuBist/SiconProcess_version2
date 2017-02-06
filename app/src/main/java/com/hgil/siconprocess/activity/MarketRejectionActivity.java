package com.hgil.siconprocess.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseToolbarActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarketRejectionActivity extends BaseToolbarActivity {

    @Nullable
    @BindView(R.id.etDamaged)
    EditText etDamaged;
    @Nullable
    @BindView(R.id.etExpired)
    EditText etExpired;
    @Nullable
    @BindView(R.id.etRatEaten)
    EditText etRatEaten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_rejection);

        ButterKnife.bind(this);
        setTitle("Goods Return");
        showSaveBtn();
    }
}
