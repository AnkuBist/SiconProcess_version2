package com.hgil.siconprocess_view.activity.fragments.outletLevel;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.outletLevel.outletInfo.OutletInfoFragment;
import com.hgil.siconprocess_view.activity.fragments.outletLevel.outletSale.OutletSaleFragment;
import com.hgil.siconprocess_view.activity.fragments.outletLevel.remark.OutletRemarkFragment;
import com.hgil.siconprocess_view.activity.fragments.outletLevel.saleRejHistory.SaleRejHistoryFragment;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.NavRouteBaseActivity;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Activity;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.BindView;

public class OutletHomeActivity extends Route_Base_Activity {

    @BindView(R.id.tvNavTitle)
    public TextView tvNavTitle;
    @BindView(R.id.tvNavDate)
    public TextView tvNavDate;
    @BindView(R.id.imgSync)
    public ImageView imgSync;

    protected String customer_id;
    protected String customer_name;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;
    @BindView(R.id.flInvoiceContent)
    FrameLayout containerFrame;

    private ActionBarDrawerToggle drawerToggle;

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

        //header setting
        View navHeaderView = nvDrawer.getHeaderView(0);
        ImageView imgNavIcon = (ImageView) navHeaderView.findViewById(R.id.imgNavIcon);
        TextView tvNavDepot = (TextView) navHeaderView.findViewById(R.id.tvNavDepot);
        TextView tvNavHeader = (TextView) navHeaderView.findViewById(R.id.tvNavHeader);

        imgNavIcon.setImageResource(R.mipmap.harvest_logo);
        if (getDepotName() != null)
            tvNavDepot.setText(getDepotName());

        if (getRouteName() != null)
            tvNavHeader.setText(getRouteName());

        // nav footer
        NavigationView temp_nv = (NavigationView) mDrawer.findViewById(R.id.temp_nv);

        TextView textName = (TextView) temp_nv.findViewById(R.id.tvCashierName);
        if (getCashierName() != null && !getCashierName().matches(""))
            textName.setText(getCashierName());
        else
            textName.setVisibility(View.GONE);

        TextView textPhone = (TextView) temp_nv.findViewById(R.id.tvCashierNo);
        textPhone.setVisibility(View.GONE);

        MenuItem menuItem = nvDrawer.getMenu().findItem(R.id.nav_today_sale);
        OutletSaleFragment fragment = OutletSaleFragment.newInstance(customer_id, customer_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.flInvoiceContent, fragment).commit();

        tvNavTitle.setText(menuItem.getTitle());
        tvNavDate.setText(Utility.getDateMonth());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_outlet_home);

        if (getIntent() != null) {
            customer_id = getIntent().getStringExtra("customer_id");
            customer_name = getIntent().getStringExtra("customer_name");
        }
        setup();
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
            case R.id.nav_route_home:
                // start nav base activity here only
                Intent intent = new Intent(this, NavRouteBaseActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                // from right to left
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                break;
            case R.id.nav_today_sale:
                fragment = OutletSaleFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_sale_rej_history:
                fragment = SaleRejHistoryFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_remark:
                fragment = OutletRemarkFragment.newInstance(customer_id, customer_name);
                break;
            case R.id.nav_outlet_info:
                fragment = OutletInfoFragment.newInstance(customer_id, customer_name);
                break;
            default:
                fragment = OutletSaleFragment.newInstance(customer_id, customer_name);
                break;
        }

        if (fragment != null) {
            String fragClassName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            // Insert the fragment by replacing any existing fragment
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragClassName, 0);
            if (!fragmentPopped) {
                ft.replace(R.id.flInvoiceContent, fragment);
                ft.addToBackStack(fragClassName);
            }

            ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
            ft.commit();

            // Set action bar title
            tvNavTitle.setText(menuItem.getTitle());
            imgSync.setVisibility(View.GONE);
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

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            //close drawer first
            mDrawer.closeDrawers();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                super.onBackPressed();
                overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
            } else {
                super.onBackPressed();
                overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get current fragment in container
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flInvoiceContent);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    /*permission check redirect*/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // get current fragment in container
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flInvoiceContent);
        fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}