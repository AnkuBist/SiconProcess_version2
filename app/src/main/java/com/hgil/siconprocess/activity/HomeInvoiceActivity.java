package com.hgil.siconprocess.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.fragments.VanInventoryFragment;
import com.hgil.siconprocess.activity.fragments.invoice.CratesManagementFragment;
import com.hgil.siconprocess.activity.fragments.invoice.CustomerPaymentFragment;
import com.hgil.siconprocess.activity.fragments.invoice.CustomerRejectionFragment;
import com.hgil.siconprocess.activity.fragments.invoice.FinalInvoiceFragment;
import com.hgil.siconprocess.activity.fragments.invoice.makeOrderInvoice.TomorrowOrderFragment;
import com.hgil.siconprocess.activity.fragments.invoice.makeSaleInvoice.CustomerInvoiceFragment;
import com.hgil.siconprocess.activity.navFragments.DashboardFragment;
import com.hgil.siconprocess.activity.navFragments.FinalPaymentFragment;
import com.hgil.siconprocess.activity.navFragments.HomeFragment;
import com.hgil.siconprocess.activity.navFragments.OutletInfoFragment;
import com.hgil.siconprocess.activity.navFragments.SyncFragment;
import com.hgil.siconprocess.base.BaseActivity;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

public class HomeInvoiceActivity extends BaseActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvNavTitle)
    public TextView tvNavTitle;
    @BindView(R.id.tvNavDate)
    public TextView tvNavDate;
    @BindView(R.id.imgSave)
    public ImageView imgSave;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.flInvoiceContent)
    FrameLayout containerFrame;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getLayoutInflater().inflate(layoutResID, containerFrame, true);
    }

    public void setup() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDefaultDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayUseLogoEnabled(false);
        actionbar.setHomeButtonEnabled(false);

        // drawer hamburger icons
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        MenuItem menuItem = nvDrawer.getMenu().findItem(R.id.nav_today_sale);

        CustomerInvoiceFragment fragment = CustomerInvoiceFragment.newInstance(customer_id, customer_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.flInvoiceContent, fragment).commit();

        menuItem.setChecked(true);

        tvNavTitle.setText(menuItem.getTitle());
        tvNavDate.setText(Utility.getDateMonth());
        // firstLaunch();
    }

    protected String customer_id;
    protected String customer_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_home_invoice);

        if (getIntent() != null) {
            customer_id = getIntent().getStringExtra("customer_id");
            customer_name = getIntent().getStringExtra("customer_name");
        }

        setup();
    }

    // set default home nav item selected and launch this on first view
    private void firstLaunch() {
        nvDrawer.getMenu().performIdentifierAction(R.id.nav_today_sale, 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                //fragment = HomeFragment.newInstance();
                // start nav base activity here only
                Intent intent = new Intent(this, NavBaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.nav_today_sale:
                fragment = CustomerInvoiceFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_goods_return:
                fragment = CustomerRejectionFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_payment:
                fragment = CustomerPaymentFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_crate_management:
                fragment = CratesManagementFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_tomorrow_order:
                fragment = TomorrowOrderFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_final_invoice:
                fragment = FinalInvoiceFragment.newInstance(customer_id, customer_name);
                break;
            default:
                fragment = HomeFragment.newInstance();
        }

        if (fragment != null) {
            String fragClassName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (menuItem.getItemId() != R.id.nav_home) {
                // Insert the fragment by replacing any existing fragment
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragClassName, 0);
                if (!fragmentPopped) {
                    ft.replace(R.id.flInvoiceContent, fragment);
                }
            } else {
                ft.replace(R.id.flInvoiceContent, fragment);
            }
            //fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(fragClassName);
            ft.commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);

            // Set action bar title
            tvNavTitle.setText(menuItem.getTitle());
            imgSave.setVisibility(View.GONE);
        }
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                containerFrame.setTranslationX(slideOffset * drawerView.getWidth());
                toolbar.setTranslationX(slideOffset * drawerView.getWidth());
                mDrawer.bringChildToFront(drawerView);
                mDrawer.requestLayout();
                //below line used to remove shadow of drawer
                mDrawer.setScrimColor(Color.TRANSPARENT);
            }//this method helps you to aside menu drawer
        };
        return toggle;

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get current fragment in container
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flInvoiceContent);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}