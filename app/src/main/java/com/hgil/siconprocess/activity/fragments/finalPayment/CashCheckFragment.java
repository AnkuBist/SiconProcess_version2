package com.hgil.siconprocess.activity.fragments.finalPayment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.fragments.finalPayment.cashierSync.CashierSyncModel;
import com.hgil.siconprocess.activity.fragments.invoiceSyncModel.CashCheck;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CashCheckFragment extends BaseFragment {

    @BindView(R.id.etCashCollected)
    EditText etCashCollected;
    @BindView(R.id.etCashReceived)
    EditText etCashReceived;

    public CashCheckFragment() {
        // Required empty public constructor
    }

    public static CashCheckFragment newInstance() {
        CashCheckFragment fragment = new CashCheckFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_cash_check;
    }

    private double amount_collected, amount_delivered_by_cashier;
    private CashierSyncModel cashierSyncModel;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cashierSyncModel = new CashierSyncModel();

        showSaveButton();

        PaymentTable paymentTable = new PaymentTable(getContext());

        // cashier total verification
        amount_collected = paymentTable.routeTotalAmountCollection();
        etCashCollected.setText(String.valueOf(amount_collected));
        etCashReceived.setText(String.valueOf(amount_delivered_by_cashier));

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount_delivered_by_cashier = Utility.getDouble(etCashReceived.getText().toString());

                CashCheck cashCheck = new CashCheck();
                cashCheck.setAmount_collected(amount_collected);
                cashCheck.setAmount_delivered(amount_delivered_by_cashier);

                cashierSyncModel.setCashCheck(cashCheck);

                CrateCheckFragment fragment = CrateCheckFragment.newInstance(cashierSyncModel);
                launchNavFragment(fragment);
            }
        });

    }

}
