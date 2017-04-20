package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ItemDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class ItemDetailView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Godb_item_detail";
    private static final String TABLE_NAME = "item_detail_table";

    private static final String ITEM_CODE = "item_code";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_DESC = "item_desc";
    private static final String SEQUENCE = "sequence";
    private static final String ITEM_PRICE = "item_price";

    private Context mContext;

    public ItemDetailView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ITEM_NAME + " TEXT NULL, "
                + ITEM_DESC + " TEXT NULL, " + ITEM_CODE + " TEXT NULL, " + SEQUENCE + " INTEGER NULL, "
                + ITEM_PRICE + " REAL NULL)");
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
    public boolean insertItemInfo(ItemDetailModel itemDetailModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_CODE, itemDetailModel.getItemCode());
        contentValues.put(ITEM_NAME, itemDetailModel.getItemName());
        contentValues.put(ITEM_DESC, itemDetailModel.getItemDesc());
        contentValues.put(SEQUENCE, itemDetailModel.getSequence());
        contentValues.put(ITEM_PRICE, itemDetailModel.getItemPrice());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertItemInfo(List<ItemDetailModel> arrItemDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrItemDetail.size(); i++) {
            ItemDetailModel itemDetailModel = arrItemDetail.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ITEM_CODE, itemDetailModel.getItemCode());
            contentValues.put(ITEM_NAME, itemDetailModel.getItemName());
            contentValues.put(ITEM_DESC, itemDetailModel.getItemDesc());
            contentValues.put(SEQUENCE, itemDetailModel.getSequence());
            contentValues.put(ITEM_PRICE, itemDetailModel.getItemPrice());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    /*public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + TIME_STAMP + " FROM " + TABLE_NAME + " WHERE " + SEQUENCE + "=?", new String[]{customer_id});

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
    public ArrayList<ItemDetailModel> getAllItemDetails() {
        ArrayList<ItemDetailModel> array_list = new ArrayList<ItemDetailModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ItemDetailModel itemDetailModel = new ItemDetailModel();
                itemDetailModel.setItemCode(res.getString(res.getColumnIndex(ITEM_CODE)));
                itemDetailModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                itemDetailModel.setItemDesc(res.getString(res.getColumnIndex(ITEM_DESC)));
                itemDetailModel.setSequence(res.getInt(res.getColumnIndex(SEQUENCE)));
                itemDetailModel.setItemPrice(res.getDouble(res.getColumnIndex(ITEM_PRICE)));
                array_list.add(itemDetailModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get outlets/customers linked to route_id*/
    /*public ArrayList<ItemDetailModel> outletSaleHistory(String outlet_id) {
        ArrayList<ItemDetailModel> array_list = new ArrayList<ItemDetailModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ITEM_DESC + "=?", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ItemDetailModel itemDetailModel = new ItemDetailModel();
                itemDetailModel.setRouteId(res.getString(res.getColumnIndex(ITEM_CODE)));
                itemDetailModel.setStockDate(res.getString(res.getColumnIndex(ITEM_NAME)));
                itemDetailModel.setOutletCode(res.getString(res.getColumnIndex(ITEM_DESC)));
                itemDetailModel.setOutletName(res.getString(res.getColumnIndex(SEQUENCE)));
                itemDetailModel.setLoading(res.getInt(res.getColumnIndex(ITEM_PRICE)));
                itemDetailModel.setOtherRej(res.getInt(res.getColumnIndex(OTHER_REJ)));
                itemDetailModel.setFreshRej(res.getInt(res.getColumnIndex(FRESH_REJ)));
                itemDetailModel.setSampleQty(res.getInt(res.getColumnIndex(SAMPLE_QTY)));
                itemDetailModel.setNetSale(res.getInt(res.getColumnIndex(NET_SALE)));
                itemDetailModel.setSALEAMT(res.getDouble(res.getColumnIndex(SALEAMT)));
                array_list.add(itemDetailModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
*/

    public ItemDetailModel itemByItemId(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ItemDetailModel itemDetailModel = new ItemDetailModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ITEM_CODE + "=?", new String[]{item_id});
        if (res.moveToFirst()) {
            itemDetailModel.setItemCode(res.getString(res.getColumnIndex(ITEM_CODE)));
            itemDetailModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
            itemDetailModel.setItemDesc(res.getString(res.getColumnIndex(ITEM_DESC)));
            itemDetailModel.setSequence(res.getInt(res.getColumnIndex(SEQUENCE)));
            itemDetailModel.setItemPrice(res.getDouble(res.getColumnIndex(ITEM_PRICE)));
        }
        res.close();
        db.close();
        return itemDetailModel;
    }

  /*  *//*get invoice amount*//*
    public double outletSaleAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + SALEAMT + " FROM " + TABLE_NAME + " WHERE " + ITEM_DESC + "=?", new String[]{customer_id});

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
                + ITEM_CODE + "=? and " + SEQUENCE + "=?", new String[]{route_id, item_id});
        int sale_qty = 0;
        if (res.moveToFirst()) {
            sale_qty = res.getInt(res.getColumnIndex(NET_SALE));
        }
        res.close();
        db.close();
        return sale_qty;
    }
*/

    /*get item name*/
    public String getItemName(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + ITEM_NAME + " FROM " + TABLE_NAME + " WHERE "
                + ITEM_CODE + "=?", new String[]{item_id});
        String item_name = "";
        if (res.moveToFirst()) {
            item_name = res.getString(res.getColumnIndex(ITEM_NAME));
        }
        res.close();
        db.close();
        return item_name;
    }

}