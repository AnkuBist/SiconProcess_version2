package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.adapter.invoiceRejection.CRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.FreshRejectionModel;
import com.hgil.siconprocess.adapter.invoiceRejection.MarketRejectionModel;
import com.hgil.siconprocess.database.dbModels.ChequeDetailsModel;
import com.hgil.siconprocess.database.dbModels.CrateDetailModel;
import com.hgil.siconprocess.database.dbModels.PaymentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 08-02-2017.
 */

public class PaymentTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "payment_db";
    private static final String TABLE_NAME = "payment_table";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
    private static final String SALE_AMOUNT = "sale_amount";
    private static final String CASH_PAID = "cash_paid";
    private static final String TOTAL_PAID_AMOUNT = "total_paid_amount";

    // cheque details
    private static final String CHEQUE_NUMBER = "chequeNumber";
    private static final String CHEQUE_DATE = "chequeDate";
    private static final String CHEQUE_AMOUNT = "chequeAmount";
    private static final String BANK_NAME = "bankName";
    private static final String BANK_BRANCH = "bankBranch";
    private static final String INVOICE_ID = "invoiceId";

    // crate details
    private static final String ISSUED_CRATES = "issuedCrates";
    private static final String RECEIVED_CRATES = "receivedCrates";

    public PaymentTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + CUSTOMER_ID + " TEXT NOT NULL, "
            + CUSTOMER_NAME + " TEXT NOT NULL, " + SALE_AMOUNT + " REAL NULL, "
            + CASH_PAID + " REAL NULL, " + TOTAL_PAID_AMOUNT + " REAL NULL, " + CHEQUE_NUMBER + " TEXT NULL, "
            + CHEQUE_DATE + " TEXT NULL, " + CHEQUE_AMOUNT + " REAL NULL, "
            + BANK_NAME + " TEXT NULL, " + BANK_BRANCH + " TEXT NULL, " + INVOICE_ID + " TEXT NULL, "
            + ISSUED_CRATES + " INTEGER NULL, " + RECEIVED_CRATES + " INTEGER NULL)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void eraseTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

    // insert multiple rejections to table for selected user at once
    public void insertCustPayment(PaymentModel paymentModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_ID, paymentModel.getCustomerId());
        contentValues.put(CUSTOMER_NAME, paymentModel.getCustomerName());
        contentValues.put(SALE_AMOUNT, paymentModel.getSaleAmount());
        contentValues.put(CASH_PAID, paymentModel.getCashPaid());
        contentValues.put(TOTAL_PAID_AMOUNT, paymentModel.getTotalPaidAmount());

        ChequeDetailsModel chequeDetailsModel = paymentModel.getChequeDetail();

        if (chequeDetailsModel != null && paymentModel.getCashPaid() != paymentModel.getTotalPaidAmount()) {
            contentValues.put(CHEQUE_NUMBER, chequeDetailsModel.getChequeNumber());
            contentValues.put(CHEQUE_DATE, chequeDetailsModel.getChequeDate());
            contentValues.put(CHEQUE_AMOUNT, chequeDetailsModel.getChequeAmount());
            contentValues.put(BANK_NAME, chequeDetailsModel.getBankName());
            contentValues.put(BANK_BRANCH, chequeDetailsModel.getBankBranch());
            contentValues.put(INVOICE_ID, chequeDetailsModel.getInvoiceId());
        }

        // check if row exists for the same user or not
        // if yes then update the same or simply insert data
        if (Exists(db, paymentModel.getCustomerId()))
            db.update(TABLE_NAME, contentValues, CUSTOMER_ID + "=?", new String[]{paymentModel.getCustomerId()});
        else
            db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public void insertCustomerCrates(CrateDetailModel crateDetailModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_ID, crateDetailModel.getCustomer_id());
        contentValues.put(ISSUED_CRATES, crateDetailModel.getIssuedCrates());
        contentValues.put(RECEIVED_CRATES, crateDetailModel.getReceivedCrates());

        //db.insert(TABLE_NAME, null, contentValues);
        db.update(TABLE_NAME, contentValues, CUSTOMER_ID + "=?", new String[]{crateDetailModel.getCustomer_id()});
        db.close();
    }

    // erase customer rejections
    public void eraseCustPayment(SQLiteDatabase db, String customer_id) {
        db.delete(TABLE_NAME, CUSTOMER_ID + "=?", new String[]{customer_id});
    }

    //here need to implement the update process for the existing user.
    public boolean Exists(SQLiteDatabase db, String customer_id) {

        String[] columns = {CUSTOMER_ID};
        String selection = CUSTOMER_ID + " =?";
        String[] selectionArgs = {customer_id};
        String limit = "1";

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // get user all rejections by date
    // till now user has not restriction to make rejections.
    public PaymentModel getCustomerPaymentInfo(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        PaymentModel paymentModel = new PaymentModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "='" + customer_id + "'", null);
        if (res.moveToFirst()) {
            paymentModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            paymentModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            paymentModel.setSaleAmount(res.getDouble(res.getColumnIndex(SALE_AMOUNT)));
            paymentModel.setCashPaid(res.getDouble(res.getColumnIndex(CASH_PAID)));
            paymentModel.setTotalPaidAmount(res.getDouble(res.getColumnIndex(TOTAL_PAID_AMOUNT)));

            // cheque details
            ChequeDetailsModel chequeDetailsModel = new ChequeDetailsModel();
            chequeDetailsModel.setChequeNumber(res.getString(res.getColumnIndex(CHEQUE_NUMBER)));
            chequeDetailsModel.setChequeDate(res.getString(res.getColumnIndex(CHEQUE_DATE)));
            chequeDetailsModel.setChequeAmount(res.getDouble(res.getColumnIndex(CHEQUE_AMOUNT)));
            chequeDetailsModel.setBankName(res.getString(res.getColumnIndex(BANK_NAME)));
            chequeDetailsModel.setBankBranch(res.getString(res.getColumnIndex(BANK_BRANCH)));
            chequeDetailsModel.setInvoiceId(res.getString(res.getColumnIndex(INVOICE_ID)));
            
            paymentModel.setChequeDetail(chequeDetailsModel);
        }
        res.close();
        db.close();
        return paymentModel;
    }

    // get crate info
    public CrateDetailModel getCustomerCrateInfo(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        CrateDetailModel crateDetailModel = new CrateDetailModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "='" + customer_id + "'", null);
        if (res.moveToFirst()) {
            crateDetailModel.setCustomer_id(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            crateDetailModel.setIssuedCrates(res.getInt(res.getColumnIndex(ISSUED_CRATES)));
            crateDetailModel.setReceivedCrates(res.getInt(res.getColumnIndex(RECEIVED_CRATES)));
        }
        res.close();
        db.close();
        return crateDetailModel;
    }

  /*  // check if the product is in customer rejection list exists or not
    public int custRejExists(String customer_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, CUSTOMER_ID + "=? AND " + ITEM_ID + "=?", new String[]{customer_id, item_id});
        db.close();
        return numRows;
    }*/

    //TODO
  /*  // get grand total for customer rejections
    public double custRejTotalAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(" + GRAND_TOTAL + ") as total " +
                "from " + TABLE_NAME + " where " + CUSTOMER_ID + "=?";
        Cursor res = db.rawQuery(query, new String[]{customer_id});

        double amount = 0;
        if (res.moveToFirst()) {
            amount = res.getInt(res.getColumnIndex("total"));
        }
        res.close();
        db.close();
        return amount;
    }*/

}