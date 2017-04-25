package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.skuOutletSale.SkuSaleDetailModel;
import com.hgil.siconprocess_view.adapter.vanStock.VanStkModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.VanStockModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class VanStockView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Sicon_Van_Stock_db";
    private static final String TABLE_NAME = "V_Van_Stock_Table";

    private static final String ROUTE_ID = "Route_id";
    private static final String ITEM_ID = "Item_id";
    private static final String ITEM_QTY = "Item_qty";

    private Context mContext;

    public VanStockView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ROUTE_ID + " TEXT NULL, "
                + ITEM_ID + " TEXT NULL, " + ITEM_QTY + " INTEGER NULL)");
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
        contentValues.put(ROUTE_ID, vanStockModel.getRouteId());
        contentValues.put(ITEM_ID, vanStockModel.getItemId());
        contentValues.put(ITEM_QTY, vanStockModel.getItemQty());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertVanStock(List<VanStockModel> arrVanStock) {
        SQLiteDatabase db = this.getWritableDatabase();

        /*for (int i = 0; i < arrOutletSale.size(); i++) {
            VanStockModel vanStockModel = arrOutletSale.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, vanStockModel.getRouteId());
            contentValues.put(ITEM_ID, vanStockModel.getItemId());
            contentValues.put(ITEM_QTY, vanStockModel.getItemQty());
            db.insert(TABLE_NAME, null, contentValues);
        }*/

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int itemIdColumn = ih.getColumnIndex(ITEM_ID);
        final int itemQtyColumn = ih.getColumnIndex(ITEM_QTY);

        try {
            db.beginTransaction();
            for (VanStockModel vanStockModel : arrVanStock) {
                ih.prepareForInsert();

                ih.bind(routeIdColumn, vanStockModel.getRouteId());
                ih.bind(itemIdColumn, vanStockModel.getItemId());
                ih.bind(itemQtyColumn, vanStockModel.getItemQty());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
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
    public ArrayList<VanStockModel> getAllVanStock() {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                VanStockModel vanStockModel = new VanStockModel();
                vanStockModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                vanStockModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                vanStockModel.setItemQty(res.getInt(res.getColumnIndex(ITEM_QTY)));
                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    public ArrayList<VanStkModel> getVanStockByRoute(String route_id) {
        ArrayList<VanStkModel> array_list = new ArrayList<>();
        ItemDetailView itemDetailView = new ItemDetailView(mContext);
        TodaySaleView todaySaleView = new TodaySaleView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? and "
                + ITEM_ID + " not like '%CR100001%'", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                VanStkModel vanStockModel = new VanStkModel();
                //vanStockModel.set(res.getString(res.getColumnIndex(ROUTE_ID)));
                String item_id = res.getString(res.getColumnIndex(ITEM_ID));
                vanStockModel.setItem_id(item_id);
                vanStockModel.setLoadQty(res.getInt(res.getColumnIndex(ITEM_QTY)));
                vanStockModel.setItem_name(itemDetailView.getItemName(item_id));
                vanStockModel.setItem_sequence(itemDetailView.getItemSequence(item_id));

                TodaySaleModel todaySaleModel = todaySaleView.getRouteItemSale(route_id, item_id);
                vanStockModel.setGross_sale(todaySaleModel.getLoading());
                vanStockModel.setSample(todaySaleModel.getSampleQty());
                vanStockModel.setMarket_rejection(todaySaleModel.getOtherRej());
                vanStockModel.setFresh_rejection(todaySaleModel.getFreshRej());
                vanStockModel.setLeft_over(vanStockModel.getLoadQty() - vanStockModel.getGross_sale());
                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        ArrayList<VanStkModel> sortedArrayList = new ArrayList<VanStkModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<VanStkModel>() {
            public int compare(VanStkModel p1, VanStkModel p2) {
                return Integer.valueOf(p1.getItem_sequence()).compareTo(p2.getItem_sequence());
            }
        });

        return sortedArrayList;
    }

    /*get user crate loading*/
    public int getRouteCrateInfo(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String crate_id = "CR100001";
        Cursor res = db.rawQuery("SELECT " + ITEM_QTY + " FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "=? and " + ITEM_ID + "=?",
                new String[]{route_id, crate_id});

        int crate_loading = 0;
        if (res.moveToFirst()) {
            crate_loading = res.getInt(res.getColumnIndex(ITEM_QTY));
        }
        res.close();
        db.close();
        return crate_loading;
    }

    /* van route items loaded count*/
    public int routeItemLoadCount(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT count(" + ITEM_ID + ") AS items FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "=?",
                new String[]{route_id});

        int item_count = 0;
        if (res.moveToFirst()) {
            item_count = res.getInt(res.getColumnIndex("items"));
        }
        res.close();
        db.close();
        return item_count;
    }

    /*items sold and van stock over route*/
    public ArrayList<SkuSaleDetailModel> getRouteCustomersItemSale(String route_id) {
        ArrayList<SkuSaleDetailModel> array_list = new ArrayList<SkuSaleDetailModel>();
        TodaySaleView todaySaleView = new TodaySaleView(mContext);
        ItemDetailView itemDetailView = new ItemDetailView(mContext);

        int total_customers = new OutletView(mContext).routeTotalCustomersCount(route_id);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? and "
                + ITEM_ID + " not like '%CR100001%'", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SkuSaleDetailModel itemSaleVarianceModel = new SkuSaleDetailModel();
                String item_id = (res.getString(res.getColumnIndex(ITEM_ID)));

                itemSaleVarianceModel.setItem_name(itemDetailView.getItemName(item_id));
                itemSaleVarianceModel.setTotal_customers(total_customers);
                itemSaleVarianceModel.setItem_access_count(todaySaleView.itemPurchaseCustomerCount(route_id, item_id));
                itemSaleVarianceModel.setItem_sequence(itemDetailView.getItemSequence(item_id));
                array_list.add(itemSaleVarianceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        ArrayList<SkuSaleDetailModel> sortedArrayList = new ArrayList<SkuSaleDetailModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<SkuSaleDetailModel>() {
            public int compare(SkuSaleDetailModel p1, SkuSaleDetailModel p2) {
                return Integer.valueOf(p1.getItem_sequence()).compareTo(p2.getItem_sequence());
            }
        });

        return sortedArrayList;
    }
}