package com.hgil.siconprocess.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.SiconApp;
import com.hgil.siconprocess.activity.navFragments.HomeFragment;
import com.hgil.siconprocess.adapter.TabPagerAdapter;
import com.hgil.siconprocess.database.tables.CrateCollectionView;
import com.hgil.siconprocess.database.tables.CrateOpeningTable;
import com.hgil.siconprocess.database.tables.CreditOpeningTable;
import com.hgil.siconprocess.database.tables.CustomerRouteMappingView;
import com.hgil.siconprocess.database.tables.DemandTargetTable;
import com.hgil.siconprocess.database.tables.DepotEmployeeView;
import com.hgil.siconprocess.database.tables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.FixedSampleTable;
import com.hgil.siconprocess.database.tables.PriceGroupView;
import com.hgil.siconprocess.database.tables.RejectionTargetTable;
import com.hgil.siconprocess.database.tables.RouteView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends NavBaseActivity {
        //implements TabLayout.OnTabSelectedListener {

/*    @Nullable
    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @Nullable
    @BindView(R.id.pager)
    ViewPager viewPager;
    @Nullable
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private TabPagerAdapter adapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //ButterKnife.bind(this);

        //Adding the tabs using addTab() method
        /*tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        // set route name to the route
        String routeName = SiconApp.getInstance().getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);*/

        HomeFragment fragment = HomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                //.addToBackStack(null)
                .replace(R.id.homeFrame, fragment).commit();

    }

  /*  @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }*/

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            super.onBackPressed();
        } else {
            if (doubleBackToExitPressedOnce) {
                //finish();
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
}
