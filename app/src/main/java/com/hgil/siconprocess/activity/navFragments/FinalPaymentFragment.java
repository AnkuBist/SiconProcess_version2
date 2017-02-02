package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

public class FinalPaymentFragment extends BaseFragment{

    public FinalPaymentFragment() {
        // Required empty public constructor
    }

    public static FinalPaymentFragment newInstance() {
        FinalPaymentFragment fragment = new FinalPaymentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_final_payment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideSaveButton();
    }

}
