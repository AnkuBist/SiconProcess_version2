package com.hgil.siconprocess.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.utils.Utility;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 02-02-2017.
 */

public abstract class BaseFragment extends Fragment {

    protected TextView tvNavTitle, tvNavDate;
    protected ImageView imgSave;
    protected String routeId, routeName;

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
