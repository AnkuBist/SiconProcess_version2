package com.hgil.siconprocess.activity.fragments.invoice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseToolbarActivity;
import com.hgil.siconprocess.database.dbModels.ChequeDetailsModel;
import com.hgil.siconprocess.utils.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChequeDetailsActivity extends BaseToolbarActivity {

    @Nullable
    @BindView(R.id.etChequeNumber)
    EditText etChequeNumber;
    @Nullable
    @BindView(R.id.etChequeDate)
    EditText etChequeDate;
    @Nullable
    @BindView(R.id.etChequeAmount)
    EditText etChequeAmount;
    @Nullable
    @BindView(R.id.etBankName)
    EditText etBankName;
    @Nullable
    @BindView(R.id.etBankBranch)
    EditText etBankBranch;
    @Nullable
    @BindView(R.id.etInvoiceId)
    EditText etInvoiceId;

   /* @Nullable
    @BindView(R.id.etChequeNumber)
    Button btnSubmit;
    @Nullable
    @BindView(R.id.etChequeNumber)
    EditText etChequeNumber;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_details);

        ButterKnife.bind(this);

        setNavTitle("Cheque Details");
        hideSaveBtn();
    }

    @Nullable
    @OnClick(R.id.btnSubmit)
    public void onSubmit(View view) {
        //Perform some action
        String chequeNumber = etChequeNumber.getText().toString().trim();
        String chequeDate = etChequeDate.getText().toString().trim();
        String chequeAmount = etChequeAmount.getText().toString().trim();
        String bankName = etBankName.getText().toString().trim();
        String bankBranch = etBankBranch.getText().toString().trim();
        String invoiceId = etInvoiceId.getText().toString().trim();

        // first mandate all fields are required to fill
        if (chequeNumber == null && chequeNumber.isEmpty()) {
            etChequeNumber.requestFocus();
            Toast.makeText(this, "Enter cheque number", Toast.LENGTH_SHORT).show();
        } else if (chequeDate == null && chequeDate.isEmpty()) {
            etChequeDate.requestFocus();
            Toast.makeText(this, "Enter date on cheque", Toast.LENGTH_SHORT).show();
        } else if (chequeAmount == null && chequeAmount.isEmpty()) {
            etChequeAmount.requestFocus();
            Toast.makeText(this, "Enter date on cheque", Toast.LENGTH_SHORT).show();
        } else if (bankName == null && bankName.isEmpty()) {
            etBankName.requestFocus();
            Toast.makeText(this, "Enter bank name on cheque", Toast.LENGTH_SHORT).show();
        } else if (bankBranch == null && bankBranch.isEmpty()) {
            etBankBranch.requestFocus();
            Toast.makeText(this, "Enter bank branch", Toast.LENGTH_SHORT).show();
        } else if (invoiceId == null && invoiceId.isEmpty()) {
            etInvoiceId.requestFocus();
            Toast.makeText(this, "Enter invoice ID", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            ChequeDetailsModel chequeDetail = new ChequeDetailsModel();
            // convert amount to double before setting into model
            double amount = Utility.getDouble(chequeAmount);

            chequeDetail.setChequeNumber(chequeNumber);
            chequeDetail.setChequeDate(chequeDate);
            chequeDetail.setChequeAmount(amount);
            chequeDetail.setBankName(bankName);
            chequeDetail.setBankBranch(bankBranch);
            chequeDetail.setInvoiceId(invoiceId);

            Bundle bundle = new Bundle();
            bundle.putSerializable("freshRejection", chequeDetail);
            resultIntent.putExtras(bundle);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }

    @Nullable
    @OnClick(R.id.btnCancel)
    public void onCancel(View view) {
        //Perform some action
        super.onBackPressed();
    }


}
