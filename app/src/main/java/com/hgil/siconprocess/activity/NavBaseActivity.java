package com.hgil.siconprocess.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.SiconApp;
import com.hgil.siconprocess.activity.navFragments.DashboardFragment;
import com.hgil.siconprocess.activity.navFragments.FinalPaymentFragment;
import com.hgil.siconprocess.activity.navFragments.HomeFragment;
import com.hgil.siconprocess.activity.navFragments.OutletInfoFragment;
import com.hgil.siconprocess.activity.navFragments.SyncFragment;
import com.hgil.siconprocess.activity.navFragments.VanInventoryFragment;
import com.hgil.siconprocess.database.tables.RouteView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavBaseActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tvNavTitle)
    TextView tvNavTitle;
    @BindView(R.id.tvNavDate)
    TextView tvNavDate;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.flContent)
    FrameLayout containerFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_base);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();

        actionbar.setDefaultDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayUseLogoEnabled(false);
        actionbar.setHomeButtonEnabled(false);
       /* getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        toolbar.setContentInsetsAbsolute(0, 0);
*/
        // drawer hamburger icons
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        tvNavDate.setText(Utility.getDateMonth());

        // call the home item selected and activated on first launch
        firstLaunch();
    }

    // set default home nav item selected and launch this on first view
    private void firstLaunch() {
        nvDrawer.getMenu().performIdentifierAction(R.id.nav_home, 0);
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
        //  Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = HomeFragment.newInstance();
                break;
            case R.id.nav_dashboard:
                fragment = DashboardFragment.newInstance();
                break;
            case R.id.nav_van_inventory:
                fragment = VanInventoryFragment.newInstance();
                break;
            case R.id.nav_final_payment:
                fragment = FinalPaymentFragment.newInstance();
                break;
            case R.id.nav_outlet_info:
                fragment = OutletInfoFragment.newInstance();
                break;
            case R.id.nav_sync:
                fragment = SyncFragment.newInstance();
                break;
            case R.id.nav_logout:
                // put the code to logout user from application

                break;
            default:
                fragment = HomeFragment.newInstance();
        }

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            String fragClassName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragClassName, 0);
            if (!fragmentPopped) {
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment);
            }
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(fragClassName).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            //setTitle(menuItem.getTitle());
            tvNavTitle.setText(menuItem.getTitle());
        }
        // Close the navigation drawer
        mDrawer.closeDrawers();

        initiateAppInstance();
    }

    private void initiateAppInstance() {
        RouteView routeView = new RouteView(this);
        RouteModel routeModel = routeView.getRoute();
        SiconApp.getInstance().setRouteModel(routeModel);
        SiconApp.getInstance().setRouteId(routeModel.getRouteId());
        SiconApp.getInstance().setRouteName(routeModel.getRouteName());
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

    /**
     * called in extending activities instead of setContentView...
     *
     * @param layoutId The content Layout Id of extending activities
     */
   /* public void setContentView(int layoutResID) {
        *//*LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);*//*
        //View contentView = inflater.inflate(layoutId, null, false);
        FrameLayout baseFrame = (FrameLayout) (getLayoutInflater().inflate(R.layout.activity_nav_base, null)).findViewById(R.id.flContent);
        getLayoutInflater().inflate(layoutResID, baseFrame, true);
        //flContent.addView(contentView, 0);
    }*/

    boolean doubleBackToExitPressedOnce = false;

    private final String HOME_FRAGMENT = "com.hgil.siconprocess.activity.navFragments.HomeFragment";

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1 && (getSupportFragmentManager().getBackStackEntryAt(0).getName()).matches(HOME_FRAGMENT)) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    finish();
                    //return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                // do nothing
                super.onBackPressed();
                //getFragmentManager().popBackStack();
                //finish();
            }
        }
    }
}
