package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SHOutletItemSaleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class SHOutletSaleView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GODb_SH_outlet_sale";
    private static final String TABLE_NAME = "Route_SH_outlet_sale_table";

    private static final String OUTLET_ID = "outlet_id";
    private static final String STOCK_DATE = "stock_date";
    private static final String COUNT = "stock";

    private Context mContext;

    public SHOutletSaleView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + STOCK_DATE + " TEXT NULL, "
                + OUTLET_ID + " TEXT NULL, " + COUNT + " INTEGER NULL)");
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
    public boolean insertSHOutletSale(SHOutletItemSaleModel shOutletItemSaleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OUTLET_ID, shOutletItemSaleModel.getOutletCode());
        contentValues.put(STOCK_DATE, shOutletItemSaleModel.getStockDate());
        contentValues.put(COUNT, shOutletItemSaleModel.getCount());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertSHOutletSale(List<SHOutletItemSaleModel> arrOutletSaleHistory) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrOutletSaleHistory.size(); i++) {
            SHOutletItemSaleModel shOutletItemSaleModel = arrOutletSaleHistory.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(OUTLET_ID, shOutletItemSaleModel.getOutletCode());
            contentValues.put(STOCK_DATE, shOutletItemSaleModel.getStockDate());
            contentValues.put(COUNT, shOutletItemSaleModel.getCount());
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
    public ArrayList<SHOutletItemSaleModel> getAllOutletSaleHistory() {
        ArrayList<SHOutletItemSaleModel> array_list = new ArrayList<SHOutletItemSaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SHOutletItemSaleModel shOutletItemSaleModel = new SHOutletItemSaleModel();
                shOutletItemSaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_ID)));
                shOutletItemSaleModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                shOutletItemSaleModel.setCount(res.getInt(res.getColumnIndex(COUNT)));
                array_list.add(shOutletItemSaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    /*public ArrayList<SHOutletItemSaleModel> outletSaleHistory(String outlet_id) {
        ArrayList<SHOutletItemSaleModel> array_list = new ArrayList<SHOutletItemSaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SHOutletItemSaleModel shOutletItemSaleModel = new SHOutletItemSaleModel();
                shOutletItemSaleModel.setRouteId(res.getString(res.getColumnIndex(OUTLET_ID)));
                shOutletItemSaleModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                shOutletItemSaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                shOutletItemSaleModel.setOutletName(res.getString(res.getColumnIndex(OUTLET_NAME)));
                shOutletItemSaleModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                shOutletItemSaleModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                shOutletItemSaleModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                shOutletItemSaleModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                shOutletItemSaleModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                shOutletItemSaleModel.setSALEAMT(res.getDouble(res.getColumnIndex(SALEAMT)));
                array_list.add(shOutletItemSaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
*/

    public ArrayList<SHOutletItemSaleModel> outletSaleHistory(String route_id) {
        ArrayList<SHOutletItemSaleModel> array_list = new ArrayList<SHOutletItemSaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_ID + "=? ORDER BY " + STOCK_DATE + " DESC", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SHOutletItemSaleModel shOutletItemSaleModel = new SHOutletItemSaleModel();
                shOutletItemSaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_ID)));
                shOutletItemSaleModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                shOutletItemSaleModel.setCount(res.getInt(res.getColumnIndex(COUNT)));
                array_list.add(shOutletItemSaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*van loading by route and date*/
    public int outletSaleHistory(String outlet_id, String stock_date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_ID + "=? AND " + STOCK_DATE + "=?",
                new String[]{outlet_id, stock_date});
        int value = 0;
        if (res.moveToFirst()) {
            value = (res.getInt(res.getColumnIndex(COUNT)));
        }
        res.close();
        db.close();
        return value;
    }

  /*  *//*get invoice amount*//*
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

    *//*item target sale over route*//*
    public int routeItemSaleQty(String route_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + NET_SALE + ") AS " + NET_SALE + " FROM " + TABLE_NAME + " WHERE "
                + OUTLET_ID + "=? and " + OUTLET_NAME + "=?", new String[]{route_id, item_id});
        int sale_qty = 0;
        if (res.moveToFirst()) {
            sale_qty = res.getInt(res.getColumnIndex(NET_SALE));
        }
        res.close();
        db.close();
        return sale_qty;
    }
*/

}