package com.hgil.siconprocess.activity.navFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.SiconApp;
import com.hgil.siconprocess.adapter.TabPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
     private TabPagerAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new TabPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        // set route name to the route
        String routeName = SiconApp.getInstance().getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);

        return view;
    }
/*

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
*/

        /*ButterKnife.bind(this, view);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        TabPagerAdapter adapter = new TabPagerAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        // set route name to the route
        String routeName = SiconApp.getInstance().getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);*/

        // deprecated source
        //Adding onTabSelectedListener to swipe views
        //tabLayout.setOnTabSelectedListener(this);
 /*   }*/

    @Override
    public void onResume() {
        super.onResume();
        //updateTabSelection(0);
        //tabLayout.getTabAt(2).select();
       // viewPager.setCurrentItem(0);
    }

    // if you've set a custom view
    void updateTabSelection(int position) {
        // get the position of the currently selected tab and set selected to false
        //tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getCustomView().setSelected(false);
        // set selected to true on the desired tab
        tabLayout.getTabAt(position).getCustomView();
        //.setSelected(true);
        // move the selection indicator
        tabLayout.setScrollPosition(position, 0, true);

        // ... your logic to swap out your fragments
    }

    void selectPage(int pageIndex) {
        tabLayout.setScrollPosition(pageIndex, 0f, true);
        viewPager.setCurrentItem(pageIndex);
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
