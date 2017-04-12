package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.VanStockModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class VanStockView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GOGB_Van_Stock_db";
    private static final String TABLE_NAME = "Van_Stock_table";

    private static final String LOCATION_CODE = "location_code";
    private static final String ROUTE_MANAGEMENT_ID = "RouteManagementId";
    private static final String STOCK_DATE = "stock_date";
    private static final String OUTLET_CODE = "outlet_code";
    private static final String ROUTE_ID = "route_id";
    private static final String ITEM_ID = "item_id";
    private static final String ITEM_NAME = "item_name";
    private static final String LOADING = "loading";
    private static final String OTHER_REJ = "otherRej";
    private static final String FRESH_REJ = "freshRej";
    private static final String SAMPLE_QTY = "sampleQty";
    private static final String NET_SALE = "netSale";
    private static final String INVOICE_ID = "invoice_id";
    private static final String INVOICE_AMT = "invoice_amt";

    private Context mContext;

    public VanStockView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + LOCATION_CODE + " TEXT NULL, "
                + ROUTE_MANAGEMENT_ID + " TEXT NULL, " + STOCK_DATE + " TEXT NULL, " + OUTLET_CODE + " TEXT NULL, "
                + ROUTE_ID + " TEXT NULL, " + ITEM_ID + " TEXT NULL, " + ITEM_NAME + " TEXT NULL, "
                + LOADING + " INTEGER NULL, " + OTHER_REJ + " INTEGER NULL, " + FRESH_REJ + " INTEGER NULL, "
                + SAMPLE_QTY + " INTEGER NULL, " + NET_SALE + " INTEGER NULL, " + INVOICE_ID + " TEXT NULL, "
                + INVOICE_AMT + " REAL NULL)");
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
    public boolean insertVanStock(VanStockModel vanStockModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_CODE, vanStockModel.getLocationCode());
        contentValues.put(ROUTE_MANAGEMENT_ID, vanStockModel.getRouteManagementId());
        contentValues.put(STOCK_DATE, vanStockModel.getStockDate());
        contentValues.put(OUTLET_CODE, vanStockModel.getOutletCode());
        contentValues.put(ROUTE_ID, vanStockModel.getRouteId());
        contentValues.put(ITEM_ID, vanStockModel.getItemId());
        contentValues.put(ITEM_NAME, vanStockModel.getItemName());
        contentValues.put(LOADING, vanStockModel.getLoading());
        contentValues.put(OTHER_REJ, vanStockModel.getOtherRej());
        contentValues.put(FRESH_REJ, vanStockModel.getFreshRej());
        contentValues.put(SAMPLE_QTY, vanStockModel.getSampleQty());
        contentValues.put(NET_SALE, vanStockModel.getNetSale());
        contentValues.put(INVOICE_ID, vanStockModel.getInvoiceId());
        contentValues.put(INVOICE_AMT, vanStockModel.getInvoiceAmt());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertVanStock(List<VanStockModel> arrVanStock) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrVanStock.size(); i++) {
            VanStockModel vanStockModel = arrVanStock.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(LOCATION_CODE, vanStockModel.getLocationCode());
            contentValues.put(ROUTE_MANAGEMENT_ID, vanStockModel.getRouteManagementId());
            contentValues.put(STOCK_DATE, vanStockModel.getStockDate());
            contentValues.put(OUTLET_CODE, vanStockModel.getOutletCode());
            contentValues.put(ROUTE_ID, vanStockModel.getRouteId());
            contentValues.put(ITEM_ID, vanStockModel.getItemId());
            contentValues.put(ITEM_NAME, vanStockModel.getItemName());
            contentValues.put(LOADING, vanStockModel.getLoading());
            contentValues.put(OTHER_REJ, vanStockModel.getOtherRej());
            contentValues.put(FRESH_REJ, vanStockModel.getFreshRej());
            contentValues.put(SAMPLE_QTY, vanStockModel.getSampleQty());
            contentValues.put(NET_SALE, vanStockModel.getNetSale());
            contentValues.put(INVOICE_ID, vanStockModel.getInvoiceId());
            contentValues.put(INVOICE_AMT, vanStockModel.getInvoiceAmt());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    /*public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + TIME_STAMP + " FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "=?", new String[]{customer_id});

        String contact = "";
        if (res.moveToFirst()) {
            contact = res.getString(res.getColumnIndex(TIME_STAMP));
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
    public ArrayList<VanStockModel> getAllVanStock() {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                VanStockModel vanStockModel = new VanStockModel();
                vanStockModel.setLocationCode(res.getString(res.getColumnIndex(LOCATION_CODE)));
                vanStockModel.setRouteManagementId(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_ID)));
                vanStockModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                vanStockModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                vanStockModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                vanStockModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                vanStockModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                vanStockModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                vanStockModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                vanStockModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                vanStockModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                vanStockModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                vanStockModel.setInvoiceId(res.getString(res.getColumnIndex(INVOICE_ID)));
                vanStockModel.setInvoiceAmt(res.getDouble(res.getColumnIndex(INVOICE_AMT)));
                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    public ArrayList<VanStockModel> getVanStockByOutlet(String outlet_id) {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                VanStockModel vanStockModel = new VanStockModel();
                vanStockModel.setLocationCode(res.getString(res.getColumnIndex(LOCATION_CODE)));
                vanStockModel.setRouteManagementId(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_ID)));
                vanStockModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                vanStockModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                vanStockModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                vanStockModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                vanStockModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                vanStockModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                vanStockModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                vanStockModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                vanStockModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                vanStockModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                vanStockModel.setInvoiceId(res.getString(res.getColumnIndex(INVOICE_ID)));
                vanStockModel.setInvoiceAmt(res.getDouble(res.getColumnIndex(INVOICE_AMT)));
                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get invoice amount*/
    public double outletSaleAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + INVOICE_AMT + " FROM " + TABLE_NAME + " WHERE " + OUTLET_CODE + "=?", new String[]{customer_id});

        double sale_amt = 0.00;
        if (res.moveToFirst()) {
            sale_amt = res.getDouble(res.getColumnIndex(INVOICE_AMT));
        }
        res.close();
        db.close();
        return sale_amt;
    }
}