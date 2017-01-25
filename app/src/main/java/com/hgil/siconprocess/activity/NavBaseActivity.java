package com.hgil.siconprocess.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavBaseActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nvView)
    NavigationView nvDrawer;

    // Make sure to be using android.support.v7.app.ActionBarDrawerToggle version.
    // The android.support.v4.app.ActionBarDrawerToggle has been deprecated.
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.flContent)
    FrameLayout containerFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_base);

        ButterKnife.bind(this);
        // Set a Toolbar to replace the ActionBar.
        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //containerFrame = (FrameLayout) findViewById(R.id.flContent);

        // Find our drawer view
        // mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        // drawer hamburger icons
        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        // ...From section above...
        // Find our drawer view
        //nvDrawer = (NavigationView) findViewById(R.id.nvView);

        // Inflate the header view at runtime
        //View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
// We can now look up items within the header if needed
        //ImageView ivHeaderPhoto = (ImageView) headerLayout.findViewById(R.id.imageView);

        // Setup drawer view
        setupDrawerContent(nvDrawer);

        // call the home item selected and activated on first launch
        firstLaunch();
    }

    // set default home nav item selected and launch this on first view
    private void firstLaunch() {
        /* default home item checked*/
        //nvDrawer.setCheckedItem(R.id.nav_home);

        // call the home fragment
       /* Fragment fragment = HomeFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();*/

        // method 1
        /*if (nvDrawer.getMenu().findItem(R.id.nav_home).isChecked())
            setTitle(nvDrawer.getMenu().findItem(R.id.nav_home).getTitle().toString());*/

        // method 2
        //if (nvDrawer.getMenu().findItem(R.id.nav_home).isChecked())
        nvDrawer.getMenu().performIdentifierAction(R.id.nav_home, 0);

        //onNavigationItemSelected(nvDrawer.getMenu().findItem(R.id.nav_home));

        //setTitle(getResources().getString(R.string.str_nav_home));
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

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

     /*   try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            setTitle(menuItem.getTitle());
            // Close the navigation drawer
        }
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
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        //return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);

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

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE 1: Make sure to override the method with only a single `Bundle` argument
    // Note 2: Make sure you implement the correct `onPostCreate(Bundle savedInstanceState)` method.
    // There are 2 signatures and only `onPostCreate(Bundle state)` shows the hamburger icon.
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
}
