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
    @BindView(R.id.etChequeCollected)
    EditText etChequeCollected;
    @BindView(R.id.etChequeReceived)
    EditText etChequeReceived;


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

    private double cash_collected, cash_delivered_by_cashier, cheque_collected, cheque_amount_delivered;
    private CashierSyncModel cashierSyncModel;
    private CashCheck cashCheck;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cashierSyncModel = new CashierSyncModel();

        showSaveButton();

        PaymentTable paymentTable = new PaymentTable(getContext());

        // cashier total verification
        cashCheck = paymentTable.routeTotalAmountCollection();
        cash_collected = cashCheck.getCash_collected();
        cash_delivered_by_cashier = cashCheck.getCash_delivered();
        cheque_collected = cashCheck.getCheque_amount();
        cheque_amount_delivered = cashCheck.getCheque_amount_delivered();

        etCashCollected.setText(String.valueOf(cash_collected));
        etCashReceived.setText(String.valueOf(cash_delivered_by_cashier));
        etChequeCollected.setText(String.valueOf(cheque_collected));
        etChequeReceived.setText(String.valueOf(cheque_amount_delivered));

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cash_delivered_by_cashier = Utility.getDouble(etCashReceived.getText().toString());
                cheque_amount_delivered = Utility.getDouble(etChequeReceived.getText().toString());

                CashCheck cashCheck = new CashCheck();
                cashCheck.setCash_collected(cash_collected);
                cashCheck.setCash_delivered(cash_delivered_by_cashier);
                cashCheck.setCheque_amount(cheque_collected);
                cashCheck.setCheque_amount_delivered(cheque_amount_delivered);

                cashierSyncModel.setCashCheck(cashCheck);

                CrateCheckFragment fragment = CrateCheckFragment.newInstance(cashierSyncModel);
                launchNavFragment(fragment);
            }
        });
    }
}
