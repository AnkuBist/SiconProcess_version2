package com.hgil.siconprocess.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

/**
 * Created by mohan.giri on 06-02-2017.
 */

public abstract class BaseToolbarActivity extends BaseActivity {
    protected static final String CUSTOMER_ID = "customer_id";
    protected static final String CUSTOMER_NAME = "customer_name";
    protected String customer_id, customer_name;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvNavTitle)
    public TextView tvNavTitle;
    @BindView(R.id.tvNavDate)
    public TextView tvNavDate;
    @BindView(R.id.imgSave)
    public ImageView imgSave;
    @BindView(R.id.baseContent)
    FrameLayout containerFrame;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, containerFrame, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);

        setNavDate(Utility.getDateMonth());
    }

    protected void showSaveBtn() {
        imgSave.setVisibility(View.VISIBLE);
    }

    protected void hideSaveBtn() {
        imgSave.setVisibility(View.GONE);
    }

    protected void setNavDate(String date) {
        tvNavDate.setText(date);
    }

    protected void setNavTitle(String title) {
        tvNavTitle.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
