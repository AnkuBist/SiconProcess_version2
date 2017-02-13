package com.hgil.siconprocess.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindString;
import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 02-02-2017.
 */

public abstract class BaseFragment extends Fragment {
    protected static final String CUSTOMER_ID = "customer_id";
    protected static final String CUSTOMER_NAME = "customer_name";
    protected String customer_id, customer_name;

    protected TextView tvNavTitle, tvNavDate;
    protected ImageView imgSave;
    protected String routeId, routeName;

    @BindString(R.string.strRupee)
    protected String strRupee;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setRetainInstance(true);
        routeId = SiconApp.getInstance().getRouteId();
        routeName = SiconApp.getInstance().getRouteName();
    }

   /* @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        injectDependencies();
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
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
        imgSave = (ImageView) getActivity().findViewById(R.id.imgSave);
    }

    protected abstract int getFragmentLayout();

    /* private void injectDependencies() {
         ((BaseActivity) getActivity()).bind(this);
     }
 */
    private void bindViews(final View view) {
        ButterKnife.bind(this, view);
    }

    protected void launchInvoiceFragment(Fragment fragment) {
        String fragClassName = fragment.getClass().getName();
        FragmentManager fragmentManager = (getActivity().getSupportFragmentManager());
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left, R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
        transaction.replace(R.id.flInvoiceContent, fragment)
                .addToBackStack(fragClassName)
                .commit();
    }

    /*handling views using common method for fragments*/
    public void setTitle(String title) {
        tvNavTitle.setText(title);
    }

    public void showSaveButton() {
        imgSave.setVisibility(View.VISIBLE);
    }

    public void hideSaveButton() {
        imgSave.setVisibility(View.GONE);
    }

    /*public void onDestroyView(){
        // do nothing
    }*/

    public String getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }

}
