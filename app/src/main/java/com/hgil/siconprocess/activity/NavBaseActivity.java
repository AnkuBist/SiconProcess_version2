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
import com.hgil.siconprocess.activity.navFragments.DashboardFragment;
import com.hgil.siconprocess.activity.navFragments.FinalPaymentFragment;
import com.hgil.siconprocess.activity.navFragments.HomeFragment;
import com.hgil.siconprocess.activity.navFragments.OutletInfoFragment;
import com.hgil.siconprocess.activity.navFragments.SyncFragment;
import com.hgil.siconprocess.activity.navFragments.fragments.CustomerRejectionFragment;
import com.hgil.siconprocess.activity.navFragments.fragments.VanInventoryFragment;
import com.hgil.siconprocess.base.BaseActivity;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

public class NavBaseActivity extends BaseActivity {

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

    @BindView(R.id.flContent)
    FrameLayout containerFrame;

    private final String HOME_FRAGMENT = "com.hgil.siconprocess.activity.navFragments.HomeFragment";

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

        MenuItem menuItem = nvDrawer.getMenu().findItem(R.id.nav_home);

        HomeFragment fragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();

        menuItem.setChecked(true);

        tvNavTitle.setText(menuItem.getTitle());

        tvNavDate.setText(Utility.getDateMonth());
        // firstLaunch();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_nav_base);
        setup();
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
            String fragClassName = fragment.getClass().getName();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (menuItem.getItemId() != R.id.nav_home) {
                // Insert the fragment by replacing any existing fragment
                boolean fragmentPopped = fragmentManager.popBackStackImmediate(fragClassName, 0);
                if (!fragmentPopped) {
                    ft.replace(R.id.flContent, fragment);
                }
            } else {
                ft.replace(R.id.flContent, fragment);
            }
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
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


/*    @Override
    public void onBackPressed() {
        super.onBackPressed();*/
       /* if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.executePendingTransactions();
            if (fragmentManager.getBackStackEntryCount() < 1) {
                //super.onBackPressed();
                finish();
            } else {
                fragmentManager.executePendingTransactions();
                fragmentManager.popBackStack();
                fragmentManager.executePendingTransactions();
                if (fragmentManager.getBackStackEntryCount() < 1) {
                    drawerToggle.setDrawerIndicatorEnabled(true);
                }
            }*/


            /*if (getSupportFragmentManager().getBackStackEntryCount() == 1 && (getSupportFragmentManager().getBackStackEntryAt(0).getName()).matches(HOME_FRAGMENT)) {
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
            }*/
    //}
    //}


    //boolean doubleBackToExitPressedOnce = false;

  /*  @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }*/

    //TODO
    /*correct method*/
    /*   @Override
    public void onBackPressed() {

        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);

        } else {
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();

            if (backStackCount >= 1) {
                getSupportFragmentManager().popBackStack();
                // Change to hamburger icon if at bottom of stack
                if(backStackCount == 1){
                    showUpButton(false);
                }
            } else {
                super.onBackPressed();
            }
        }
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
