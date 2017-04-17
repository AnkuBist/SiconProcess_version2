package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PaymentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 17-04-2017.
 */

public class PaymentView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Payment_db";
    private static final String TABLE_NAME = "Payment_View";

    private static final String ROUTE_ID = "Route_id";
    private static final String OUTLET_CODE = "outlet_code";
    private static final String TOTAL_AMOUNT = "total_amount";

    private Context mContext;

    public PaymentView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ROUTE_ID + " TEXT NULL, "
                + OUTLET_CODE + " TEXT NULL, " + TOTAL_AMOUNT + " REAL NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void eraseTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME); //delete all rows in a table
        db.close();
    }

    //insert single
    public boolean insertPayment(PaymentModel paymentModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_ID, paymentModel.getRouteId());
        contentValues.put(OUTLET_CODE, paymentModel.getOutletCode());
        contentValues.put(TOTAL_AMOUNT, paymentModel.getTotalAmount());
        // if (hasObject(db, paymentModel.getRoute_id()))
        //    db.update(TABLE_NAME, contentValues, ROUTE_ID + "=?", new String[]{paymentModel.getRoute_id()});
        //  else
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertPayment(List<PaymentModel> arrPayment) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrPayment.size(); i++) {
            PaymentModel paymentModel = arrPayment.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, paymentModel.getRouteId());
            contentValues.put(OUTLET_CODE, paymentModel.getOutletCode());
            contentValues.put(TOTAL_AMOUNT, paymentModel.getTotalAmount());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    // check if the record exists or not
    public boolean hasObject(SQLiteDatabase db, String id) {
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + " =?";

        Cursor cursor = db.rawQuery(selectString, new String[]{id});

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }
        cursor.close();          // Don't forget to close your cursor
        return hasObject;
    }

   /* public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + CONTACT_NO + " FROM " + TABLE_NAME + " WHERE " + INVOICE_ID + "=?", new String[]{customer_id});

        String contact = "";
        if (res.moveToFirst()) {
            contact = res.getString(res.getColumnIndex(CONTACT_NO));
        }
        res.close();
        db.close();
        return contact;
    }*/

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    // get all customers
    public ArrayList<PaymentModel> getAllPayment() {
        ArrayList<PaymentModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            PaymentModel paymentModel = new PaymentModel();
            paymentModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            paymentModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
            paymentModel.setTotalAmount(res.getDouble(res.getColumnIndex(TOTAL_AMOUNT)));
            array_list.add(paymentModel);
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get payment route*/
    public ArrayList<PaymentModel> getRoutePayment(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<PaymentModel> array_list = new ArrayList<>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "=?", new String[]{route_id}); //new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                PaymentModel paymentModel = new PaymentModel();
                paymentModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                paymentModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                paymentModel.setTotalAmount(res.getDouble(res.getColumnIndex(TOTAL_AMOUNT)));
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get route total collection*/
    /*route sale value*/
    public double routeTotalSale(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + TOTAL_AMOUNT + ") AS total_amount FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});

        double total_collection = 0.00;
        if (res.moveToFirst()) {
            total_collection = (res.getDouble(res.getColumnIndex(TOTAL_AMOUNT)));
        }
        res.close();
        db.close();
        return total_collection;
    }


}