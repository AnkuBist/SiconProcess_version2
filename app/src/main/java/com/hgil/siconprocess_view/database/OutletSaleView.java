package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletSaleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletSaleView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Customer_db";
    private static final String TABLE_NAME = "V_SD_Customer_Route_Mapping";

    private static final String CASHIER_ID = "CashierId";
    private static final String CASHIER_NAME = "CashierName";
    private static final String BEAT_CODE = "beat_code";
    private static final String INVOICE_DATE = "Inv_date";
    private static final String INVOICE_DATE_TIME = "Inv_Datetime";
    private static final String INVOICE_ID = "inv_id";
    private static final String ITEM_COUNT = "item_count";
    private static final String REASON = "Reason";
    private static final String OUTLET_CODE = "outlet_code";

    private Context mContext;

    public OutletSaleView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + CASHIER_ID + " TEXT NULL, "
                + CASHIER_NAME + " TEXT NULL, " + BEAT_CODE + " TEXT NULL, " + INVOICE_DATE + " TEXT NULL, "
                + INVOICE_DATE_TIME + " TEXT NULL, " + INVOICE_ID + " TEXT NULL, " + ITEM_COUNT + " INTEGER NULL, "
                + REASON + " TEXT NULL, " + OUTLET_CODE + " TEXT NULL)");
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
    public boolean insertOutletSale(OutletSaleModel outletSaleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CASHIER_ID, outletSaleModel.getCashierId());
        contentValues.put(CASHIER_NAME, outletSaleModel.getCashierName());
        contentValues.put(BEAT_CODE, outletSaleModel.getBeatCode());
        contentValues.put(INVOICE_DATE, outletSaleModel.getInvDate());
        contentValues.put(INVOICE_DATE_TIME, outletSaleModel.getInvDatetime());
        contentValues.put(INVOICE_ID, outletSaleModel.getInvId());
        contentValues.put(ITEM_COUNT, outletSaleModel.getItemCount());
        contentValues.put(REASON, outletSaleModel.getReason());
        contentValues.put(OUTLET_CODE, outletSaleModel.getOutletCode());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple outlets
    public boolean insertOutletSale(List<OutletSaleModel> arrOutletSale) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrOutletSale.size(); i++) {
            OutletSaleModel outletSaleModel = arrOutletSale.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(CASHIER_ID, outletSaleModel.getCashierId());
            contentValues.put(CASHIER_NAME, outletSaleModel.getCashierName());
            contentValues.put(BEAT_CODE, outletSaleModel.getBeatCode());
            contentValues.put(INVOICE_DATE, outletSaleModel.getInvDate());
            contentValues.put(INVOICE_DATE_TIME, outletSaleModel.getInvDatetime());
            contentValues.put(INVOICE_ID, outletSaleModel.getInvId());
            contentValues.put(ITEM_COUNT, outletSaleModel.getItemCount());
            contentValues.put(REASON, outletSaleModel.getReason());
            contentValues.put(OUTLET_CODE, outletSaleModel.getOutletCode());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
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
    public ArrayList<OutletSaleModel> getAllOutletSale() {
        ArrayList<OutletSaleModel> array_list = new ArrayList<OutletSaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletSaleModel outletSaleModel = new OutletSaleModel();
                outletSaleModel.setCashierId(res.getString(res.getColumnIndex(CASHIER_ID)));
                outletSaleModel.setCashierName(res.getString(res.getColumnIndex(CASHIER_NAME)));
                outletSaleModel.setBeatCode(res.getString(res.getColumnIndex(BEAT_CODE)));
                outletSaleModel.setInvDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
                outletSaleModel.setInvDatetime(res.getString(res.getColumnIndex(INVOICE_DATE_TIME)));
                outletSaleModel.setInvId(res.getString(res.getColumnIndex(INVOICE_ID)));
                outletSaleModel.setItemCount(res.getInt(res.getColumnIndex(ITEM_COUNT)));
                outletSaleModel.setReason(res.getString(res.getColumnIndex(REASON)));
                outletSaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                array_list.add(outletSaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    public ArrayList<OutletSaleModel> getSaleByOutlet(String outlet_id) {
        ArrayList<OutletSaleModel> array_list = new ArrayList<OutletSaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletSaleModel outletSaleModel = new OutletSaleModel();
                outletSaleModel.setCashierId(res.getString(res.getColumnIndex(CASHIER_ID)));
                outletSaleModel.setCashierName(res.getString(res.getColumnIndex(CASHIER_NAME)));
                outletSaleModel.setBeatCode(res.getString(res.getColumnIndex(BEAT_CODE)));
                outletSaleModel.setInvDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
                outletSaleModel.setInvDatetime(res.getString(res.getColumnIndex(INVOICE_DATE_TIME)));
                outletSaleModel.setInvId(res.getString(res.getColumnIndex(INVOICE_ID)));
                outletSaleModel.setItemCount(res.getInt(res.getColumnIndex(ITEM_COUNT)));
                outletSaleModel.setReason(res.getString(res.getColumnIndex(REASON)));
                outletSaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                array_list.add(outletSaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}