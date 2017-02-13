package com.hgil.siconprocess.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.navFragments.HomeFragment;

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
                overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
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
