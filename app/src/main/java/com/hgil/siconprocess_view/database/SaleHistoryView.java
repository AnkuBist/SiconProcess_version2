package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class SaleHistoryView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GOGB_Sale_Hisotry_db";
    private static final String TABLE_NAME = "Outlet_Sale_History_table";

    private static final String ROUTE_ID = "route_id";
    private static final String STOCK_DATE = "stock_date";
    private static final String OUTLET_CODE = "outlet_code";
    private static final String OUTLET_NAME = "outlet_name";
    private static final String LOADING = "loading";
    private static final String OTHER_REJ = "otherRej";
    private static final String FRESH_REJ = "freshRej";
    private static final String SAMPLE_QTY = "sampleQty";
    private static final String NET_SALE = "netSale";
    private static final String SALEAMT = "sale_Amt";
    private static final String REJECTION_PERCENTAGE = "rej_prct";

    private Context mContext;

    public SaleHistoryView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + STOCK_DATE + " TEXT NULL, "
                + OUTLET_CODE + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, " + OUTLET_NAME + " TEXT NULL, "
                + LOADING + " INTEGER NULL, " + OTHER_REJ + " INTEGER NULL, " + FRESH_REJ + " INTEGER NULL, "
                + SAMPLE_QTY + " INTEGER NULL, " + NET_SALE + " INTEGER NULL, "
                + SALEAMT + " REAL NULL, " + REJECTION_PERCENTAGE + " REAL NULL)");
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
    public boolean insertSaleHistory(SaleHistoryModel saleHistoryModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_ID, saleHistoryModel.getRouteId());
        contentValues.put(STOCK_DATE, saleHistoryModel.getStockDate());
        contentValues.put(OUTLET_CODE, saleHistoryModel.getOutletCode());
        contentValues.put(OUTLET_NAME, saleHistoryModel.getOutletName());
        contentValues.put(LOADING, saleHistoryModel.getLoading());
        contentValues.put(OTHER_REJ, saleHistoryModel.getOtherRej());
        contentValues.put(FRESH_REJ, saleHistoryModel.getFreshRej());
        contentValues.put(SAMPLE_QTY, saleHistoryModel.getSampleQty());
        contentValues.put(NET_SALE, saleHistoryModel.getNetSale());
        contentValues.put(SALEAMT, saleHistoryModel.getSALEAMT());
        contentValues.put(REJECTION_PERCENTAGE, saleHistoryModel.getRejPrct());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertSaleHistory(List<SaleHistoryModel> arrSaleHistory) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrSaleHistory.size(); i++) {
            SaleHistoryModel saleHistoryModel = arrSaleHistory.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, saleHistoryModel.getRouteId());
            contentValues.put(STOCK_DATE, saleHistoryModel.getStockDate());
            contentValues.put(OUTLET_CODE, saleHistoryModel.getOutletCode());
            contentValues.put(OUTLET_NAME, saleHistoryModel.getOutletName());
            contentValues.put(LOADING, saleHistoryModel.getLoading());
            contentValues.put(OTHER_REJ, saleHistoryModel.getOtherRej());
            contentValues.put(FRESH_REJ, saleHistoryModel.getFreshRej());
            contentValues.put(SAMPLE_QTY, saleHistoryModel.getSampleQty());
            contentValues.put(NET_SALE, saleHistoryModel.getNetSale());
            contentValues.put(SALEAMT, saleHistoryModel.getSALEAMT());
            contentValues.put(REJECTION_PERCENTAGE, saleHistoryModel.getRejPrct());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    /*public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + TIME_STAMP + " FROM " + TABLE_NAME + " WHERE " + OUTLET_NAME + "=?", new String[]{customer_id});

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
    public ArrayList<SaleHistoryModel> getAllRouteSaleHistory() {
        ArrayList<SaleHistoryModel> array_list = new ArrayList<SaleHistoryModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SaleHistoryModel saleHistoryModel = new SaleHistoryModel();
                saleHistoryModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                saleHistoryModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                saleHistoryModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                saleHistoryModel.setOutletName(res.getString(res.getColumnIndex(OUTLET_NAME)));
                saleHistoryModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                saleHistoryModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                saleHistoryModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                saleHistoryModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                saleHistoryModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                saleHistoryModel.setSALEAMT(res.getDouble(res.getColumnIndex(SALEAMT)));
                saleHistoryModel.setRejPrct(res.getDouble(res.getColumnIndex(REJECTION_PERCENTAGE)));
                array_list.add(saleHistoryModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    /*public ArrayList<SaleHistoryModel> outletSaleHistory(String outlet_id) {
        ArrayList<SaleHistoryModel> array_list = new ArrayList<SaleHistoryModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SaleHistoryModel saleHistoryModel = new SaleHistoryModel();
                saleHistoryModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                saleHistoryModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                saleHistoryModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                saleHistoryModel.setOutletName(res.getString(res.getColumnIndex(OUTLET_NAME)));
                saleHistoryModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                saleHistoryModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                saleHistoryModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                saleHistoryModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                saleHistoryModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                saleHistoryModel.setSALEAMT(res.getDouble(res.getColumnIndex(SALEAMT)));
                array_list.add(saleHistoryModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
*/

    public ArrayList<SaleHistoryModel> outletSaleHistory(String outlet_id) {
        ArrayList<SaleHistoryModel> array_list = new ArrayList<SaleHistoryModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SaleHistoryModel saleHistoryModel = new SaleHistoryModel();
                saleHistoryModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                saleHistoryModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                saleHistoryModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                saleHistoryModel.setOutletName(res.getString(res.getColumnIndex(OUTLET_NAME)));
                saleHistoryModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                saleHistoryModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                saleHistoryModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                saleHistoryModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                saleHistoryModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                saleHistoryModel.setSALEAMT(res.getDouble(res.getColumnIndex(SALEAMT)));
                saleHistoryModel.setRejPrct(res.getDouble(res.getColumnIndex(REJECTION_PERCENTAGE)));
                array_list.add(saleHistoryModel);
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
        Cursor res = db.rawQuery("SELECT " + SALEAMT + " FROM " + TABLE_NAME + " WHERE " + OUTLET_CODE + "=?", new String[]{customer_id});

        double sale_amt = 0.00;
        if (res.moveToFirst()) {
            sale_amt = res.getDouble(res.getColumnIndex(SALEAMT));
        }
        res.close();
        db.close();
        return sale_amt;
    }

    /*item target sale over route*/
    public int routeItemSaleQty(String route_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + NET_SALE + ") AS " + NET_SALE + " FROM " + TABLE_NAME + " WHERE "
                + ROUTE_ID + "=? and " + OUTLET_NAME + "=?", new String[]{route_id, item_id});
        int sale_qty = 0;
        if (res.moveToFirst()) {
            sale_qty = res.getInt(res.getColumnIndex(NET_SALE));
        }
        res.close();
        db.close();
        return sale_qty;
    }


}