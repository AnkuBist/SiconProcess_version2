package com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.TabPagerAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteHomeFragment extends Route_Base_Fragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private TabPagerAdapter adapter;

    public RouteHomeFragment() {
        // Required empty public constructor
    }

    public static RouteHomeFragment newInstance() {
        RouteHomeFragment fragment = new RouteHomeFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new TabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        // set route name to the route
        tvRouteName.setText(getRouteName());

        setTitle("Route");
        hideSaveButton();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_home;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
