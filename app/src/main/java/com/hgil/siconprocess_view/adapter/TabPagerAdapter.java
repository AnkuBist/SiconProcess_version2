package com.hgil.siconprocess_view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs.RouteHomeAllFragment;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs.RouteHomeCompleteFragment;
import com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs.RouteHomePendingFragment;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                RouteHomeAllFragment tab1 = RouteHomeAllFragment.newInstance();
                return tab1;
            case 1:
                RouteHomePendingFragment tab2 = RouteHomePendingFragment.newInstance();
                return tab2;
            case 2:
                RouteHomeCompleteFragment tab3 = RouteHomeCompleteFragment.newInstance();
                return tab3;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //this is where you set the titles
        switch (position) {
            case 0:
                return "All";
            case 1:
                return "Pending";
            case 2:
                return "Complete";
        }
        return null;
    }
}