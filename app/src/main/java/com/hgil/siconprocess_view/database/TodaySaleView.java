package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class TodaySaleView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Godb_Today_Sale";
    private static final String TABLE_NAME = "Today_Sale_table";

    private static final String ROUTE_ID = "route_id";
    private static final String OUTLET_CODE = "outlet_code";
    private static final String ITEM_ID = "item_id";
    private static final String LOADING = "loading";
    private static final String OTHER_REJ = "otherRej";
    private static final String FRESH_REJ = "freshRej";
    private static final String SAMPLE_QTY = "sampleQty";

    private Context mContext;

    public TodaySaleView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ROUTE_ID + " TEXT NULL, "
                + OUTLET_CODE + " TEXT NULL, " + ITEM_ID + " TEXT NULL, " + LOADING + " INTEGER NULL, "
                + OTHER_REJ + " INTEGER NULL, " + FRESH_REJ + " INTEGER NULL, " + SAMPLE_QTY + " INTEGER NULL)");
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
    public boolean insertTodaySale(TodaySaleModel todaySaleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_ID, todaySaleModel.getRouteId());
        contentValues.put(OUTLET_CODE, todaySaleModel.getOutletCode());
        contentValues.put(ITEM_ID, todaySaleModel.getItemCode());
        contentValues.put(LOADING, todaySaleModel.getLoading());
        contentValues.put(OTHER_REJ, todaySaleModel.getOtherRej());
        contentValues.put(FRESH_REJ, todaySaleModel.getFreshRej());
        contentValues.put(SAMPLE_QTY, todaySaleModel.getSampleQty());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertTodaySale(List<TodaySaleModel> arrTodaySale) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrTodaySale.size(); i++) {
            TodaySaleModel todaySaleModel = arrTodaySale.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, todaySaleModel.getRouteId());
            contentValues.put(OUTLET_CODE, todaySaleModel.getOutletCode());
            contentValues.put(ITEM_ID, todaySaleModel.getItemCode());
            contentValues.put(LOADING, todaySaleModel.getLoading());
            contentValues.put(OTHER_REJ, todaySaleModel.getOtherRej());
            contentValues.put(FRESH_REJ, todaySaleModel.getFreshRej());
            contentValues.put(SAMPLE_QTY, todaySaleModel.getSampleQty());
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
    public ArrayList<TodaySaleModel> getAllRouteSale() {
        ArrayList<TodaySaleModel> array_list = new ArrayList<TodaySaleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                TodaySaleModel todaySaleModel = new TodaySaleModel();
                todaySaleModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                todaySaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                todaySaleModel.setItemCode(res.getString(res.getColumnIndex(ITEM_ID)));
                todaySaleModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                todaySaleModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                todaySaleModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                todaySaleModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                array_list.add(todaySaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    public ArrayList<TodaySaleModel> getRouteSaleByOutlet(String outlet_id) {
        ArrayList<TodaySaleModel> array_list = new ArrayList<TodaySaleModel>();
        ItemDetailView itemDetailView = new ItemDetailView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                TodaySaleModel todaySaleModel = new TodaySaleModel();
                todaySaleModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                todaySaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                String item_code = res.getString(res.getColumnIndex(ITEM_ID));
                todaySaleModel.setItemCode(itemDetailView.getItemName(item_code));
                todaySaleModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
                todaySaleModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                todaySaleModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                todaySaleModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                array_list.add(todaySaleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get invoice amount*/
    /*public double outletSaleAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + INVOICE_AMT + " FROM " + TABLE_NAME + " WHERE " + OUTLET_CODE + "=?", new String[]{customer_id});

        double sale_amt = 0.00;
        if (res.moveToFirst()) {
            sale_amt = res.getDouble(res.getColumnIndex(INVOICE_AMT));
        }
        res.close();
        db.close();
        return sale_amt;
    }*/

    /*outlet sale time*/
  /*  public String outletSaleTime(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + INVOICE_TIME + " FROM " + TABLE_NAME + " WHERE " + OUTLET_CODE + "=?", new String[]{customer_id});

        String sale_time = "";
        if (res.moveToFirst()) {
            sale_time = res.getString(res.getColumnIndex(INVOICE_TIME));
        }
        res.close();
        db.close();
        return sale_time;
    }*/

    /*prepare van stock data*/
   /* public TodaySaleModel getRouteItemSale(String route_id, String item_id) {
        TodaySaleModel todaySaleModel = new TodaySaleModel();

        SQLiteDatabase db = this.getReadableDatabase();

      *//*  Cursor query (String table,
                String[] columns,
                String selection,
                String[] selectionArgs,
                String groupBy,
                String having,
                String orderBy)*//*
        Cursor res = db.query(TABLE_NAME, new String[]{ITEM_NAME
                , "sum(" + LOADING + ") as " + LOADING,
                "sum(" + OTHER_REJ + ") as " + OTHER_REJ,
                "sum(" + FRESH_REJ + ") as " + FRESH_REJ,
                "sum(" + SAMPLE_QTY + ") as " + SAMPLE_QTY,
                "sum(" + NET_SALE + ") as " + NET_SALE,
        }, ROUTE_ID + "=? AND " + ITEM_ID + "=?", new String[]{route_id, item_id}, ITEM_NAME, null, ITEM_ID);

    *//*    Cursor res = db.rawQuery("SELECT "+ROUTE_ID+",  FROM " + TABLE_NAME + " where "
                + ROUTE_ID + "=? AND " + ITEM_ID + "=?", new String[]{route_id, item_id});*//*
        if (res.moveToFirst()) {
            //while (res.isAfterLast() == false) {
            //todaySaleModel.setLocationCode(res.getString(res.getColumnIndex(LOCATION_CODE)));
            //todaySaleModel.setRouteManagementId(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_ID)));
            //todaySaleModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
            //todaySaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
            //todaySaleModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            //todaySaleModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            todaySaleModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
            todaySaleModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
            todaySaleModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
            todaySaleModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
            todaySaleModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
            todaySaleModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
            //todaySaleModel.setInvoiceId(res.getString(res.getColumnIndex(INVOICE_ID)));
            //todaySaleModel.setInvoiceAmt(res.getDouble(res.getColumnIndex(INVOICE_AMT)));
            // res.moveToNext();
            //}
        }
        res.close();
        db.close();
        return todaySaleModel;
    }*/

    /*item target sale over route*/
    /*public int routeItemSaleQty(String route_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + NET_SALE + ") AS " + NET_SALE + " FROM " + TABLE_NAME + " WHERE "
                + ROUTE_ID + "=? and " + ITEM_ID + "=?", new String[]{route_id, item_id});
        int sale_qty = 0;
        if (res.moveToFirst()) {
            sale_qty = res.getInt(res.getColumnIndex(NET_SALE));
        }
        res.close();
        db.close();
        return sale_qty;
    }*/

    //TODO---temporary
    /*get item name from this table*/
/*    public String getItemName(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + ITEM_NAME + " FROM " + TABLE_NAME + " WHERE "
                + ITEM_ID + "=?", new String[]{item_id});
        String item_name = "";
        if (res.moveToFirst()) {
            item_name = res.getString(res.getColumnIndex(ITEM_NAME));
        }
        res.close();
        db.close();
        return item_name;
    }*/


}