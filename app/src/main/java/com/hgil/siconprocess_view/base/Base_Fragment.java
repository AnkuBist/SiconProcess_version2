package com.hgil.siconprocess_view.base;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.RouteListActivity;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public abstract class Base_Fragment extends Fragment {
    // protected static final String ROUTE_ID = "route_id";
    //protected static final String ROUTE_NAME = "route_name";
    // protected String customer_id, customer_name;

    protected TextView tvNavTitle, tvNavDate;
    protected ImageView imgSync;
    protected RouteModel routeModel;
    protected String depotName, routeId, routeName, loginId;

    @BindString(R.string.strRupee)
    protected String strRupee;
    @BindString(R.string.strBullet)
    protected String strBullet;

    // sample snackbar
    public static void showSnackbar(View view, String message) {
        // make snackbar
        Snackbar mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        // get snackbar view
        View mView = mSnackbar.getView();
        // get textview inside snackbar view
        TextView mTextView = (TextView) mView.findViewById(android.support.design.R.id.snackbar_text);
        // set text to center
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        else
            mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        // show the snackbar
        mSnackbar.show();
    }

   /* @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        injectDependencies();
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance(true);
        routeModel = SiconApp.getInstance().getRouteModel();
        depotName = SiconApp.getInstance().getDepotName();
        routeId = SiconApp.getInstance().getRouteId();
        routeName = SiconApp.getInstance().getRouteName();
        loginId = SiconApp.getInstance().getLoginId();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // setRetainInstance(true);
        Utility.closeKeyboard(getActivity(), getView());
        bindViews(view);
        getToolbarView();
    }

    private void getToolbarView() {
        tvNavTitle = (TextView) getActivity().findViewById(R.id.tvNavTitle);
        tvNavDate = (TextView) getActivity().findViewById(R.id.tvNavDate);
        imgSync = (ImageView) getActivity().findViewById(R.id.imgSync);
    }

    protected abstract int getFragmentLayout();

    /* private void injectDependencies() {
         ((Route_Base_Activity) getActivity()).bind(this);
     }
 */
    private void bindViews(final View view) {
        ButterKnife.bind(this, view);
    }

    /*handling views using common method for fragments*/
    public void setTitle(String title) {
        tvNavTitle.setText(title);
    }

    public void showSyncButton() {
        imgSync.setVisibility(View.VISIBLE);
    }

    /*public void onDestroyView(){
        // do nothing
    }*/

    public void hideSyncButton() {
        imgSync.setVisibility(View.GONE);
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }
    
    public String getDepotName() {
        return depotName;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getLoginId() {
        return loginId;
    }

/*
    protected void launchInvoiceFragment(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();
        FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
                .replace(R.id.flInvoiceContent, fragment)
                .addToBackStack(fragClassName)
                .commit();
    }*/

    protected void launchRouteFragment(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();
        FragmentManager fragmentManager = ((RouteListActivity) getActivity()).getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right)
                .replace(R.id.base_frame, fragment)
                .addToBackStack(fragClassName)
                .commit();
    }

}