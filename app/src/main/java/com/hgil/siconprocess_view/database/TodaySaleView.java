package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class TodaySaleView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

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
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    // insert multiple
    public boolean insertTodaySale(List<TodaySaleModel> arrTodaySale) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int outletCodeColumn = ih.getColumnIndex(OUTLET_CODE);
        final int itemIdColumn = ih.getColumnIndex(ITEM_ID);
        final int loadingColumn = ih.getColumnIndex(LOADING);
        final int otherRejColumn = ih.getColumnIndex(OTHER_REJ);
        final int freshRejColumn = ih.getColumnIndex(FRESH_REJ);
        final int sampleQtyColumn = ih.getColumnIndex(SAMPLE_QTY);

        try {
            db.beginTransaction();
            for (TodaySaleModel todaySaleModel : arrTodaySale) {
                ih.prepareForInsert();

                ih.bind(routeIdColumn, todaySaleModel.getRouteId());
                ih.bind(outletCodeColumn, todaySaleModel.getOutletCode());
                ih.bind(itemIdColumn, todaySaleModel.getItemCode());
                ih.bind(loadingColumn, todaySaleModel.getLoading());
                ih.bind(otherRejColumn, todaySaleModel.getOtherRej());
                ih.bind(freshRejColumn, todaySaleModel.getFreshRej());
                ih.bind(sampleQtyColumn, todaySaleModel.getSampleQty());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        db.close();
        return true;
    }

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

                String item_code = res.getString(res.getColumnIndex(ITEM_ID));
                todaySaleModel.setItem_name(itemDetailView.getItemName(item_code));
                todaySaleModel.setItem_sequence(itemDetailView.getItemSequence(item_code));

                todaySaleModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                todaySaleModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
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

        ArrayList<TodaySaleModel> sortedArrayList = new ArrayList<TodaySaleModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<TodaySaleModel>() {
            public int compare(TodaySaleModel p1, TodaySaleModel p2) {
                return Integer.valueOf(p1.getItem_sequence()).compareTo(p2.getItem_sequence());
            }
        });

        return sortedArrayList;
    }

    /*prepare van stock data*/
    public TodaySaleModel getRouteItemSale(String route_id, String item_id) {
        TodaySaleModel todaySaleModel = new TodaySaleModel();

        SQLiteDatabase db = this.getReadableDatabase();

      /*  Cursor query (String table,
                String[] columns,
                String selection,
                String[] selectionArgs,
                String groupBy,
                String having,
                String orderBy)*/
        Cursor res = db.query(TABLE_NAME, new String[]{ITEM_ID
                , "sum(" + LOADING + ") as " + LOADING,
                "sum(" + OTHER_REJ + ") as " + OTHER_REJ,
                "sum(" + FRESH_REJ + ") as " + FRESH_REJ,
                "sum(" + SAMPLE_QTY + ") as " + SAMPLE_QTY
        }, ROUTE_ID + "=? AND " + ITEM_ID + "=?", new String[]{route_id, item_id}, ITEM_ID, null, ITEM_ID);

        if (res.moveToFirst()) {
            todaySaleModel.setLoading(res.getInt(res.getColumnIndex(LOADING)));
            todaySaleModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
            todaySaleModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
            todaySaleModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
        }
        res.close();
        db.close();
        return todaySaleModel;
    }

    /*item target sale over route*/
    public int routeItemSaleQty(String route_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + LOADING + "-" + OTHER_REJ + ") AS " + LOADING + " FROM " + TABLE_NAME + " WHERE "
                + ROUTE_ID + "=? and " + ITEM_ID + "=?", new String[]{route_id, item_id});
        int sale_qty = 0;
        if (res.moveToFirst()) {
            sale_qty = res.getInt(res.getColumnIndex(LOADING));
        }
        res.close();
        db.close();
        return sale_qty;
    }

    /*calculate rej amount over route*/
    public double getRouteRejAmount(String route_id) {
        ItemDetailView itemDetailView = new ItemDetailView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(TABLE_NAME, new String[]{ITEM_ID,
                "sum(" + OTHER_REJ + ") as " + OTHER_REJ,
                "sum(" + FRESH_REJ + ") as " + FRESH_REJ
        }, ROUTE_ID + "=?", new String[]{route_id}, ITEM_ID, null, ITEM_ID);

        double net_rej_amount = 0.00;
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                String item_id = res.getString(res.getColumnIndex(ITEM_ID));
                int o_rej = res.getInt(res.getColumnIndex(OTHER_REJ));
                int f_rej = res.getInt(res.getColumnIndex(FRESH_REJ));
                double item_price = itemDetailView.getItemPrice(item_id);
                net_rej_amount += ((o_rej + f_rej) * item_price);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return net_rej_amount;
    }

    /* van route items loaded count*/
    public int routeOutletItemSaleCount(String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT count(" + ITEM_ID + ") AS items FROM " + TABLE_NAME + " WHERE "
                        + LOADING + ">0 and " + ROUTE_ID + "=? and " + OUTLET_CODE + "=?",
                new String[]{route_id, outlet_id});
        int item_count = 0;
        if (res.moveToFirst()) {
            item_count = res.getInt(res.getColumnIndex("items"));
        }
        res.close();
        db.close();
        return item_count;
    }

    /*item purchased by customer*/
    public int itemPurchaseCustomerCount(String route_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT count(" + OUTLET_CODE + ") AS customers FROM " + TABLE_NAME + " WHERE "
                + LOADING + ">0 and " + ROUTE_ID + "=? and " + ITEM_ID + "=?", new String[]{route_id, item_id});
        int item_count = 0;
        if (res.moveToFirst()) {
            item_count = res.getInt(res.getColumnIndex("customers"));
        }
        res.close();
        db.close();
        return item_count;
    }

    /*route leftover items in pieces*/
      /*route total item loading in pieces*/
    public int routeLeftOver(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + LOADING + "+" + FRESH_REJ + ") AS item_qty FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "=?",
                new String[]{route_id});

        int item_count = 0;
        if (res.moveToFirst()) {
            item_count = res.getInt(res.getColumnIndex("item_qty"));
        }
        res.close();
        db.close();
        return item_count;
    }

}