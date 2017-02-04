package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.adapter.invoiceRejection.CRejectionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 04-02-2017.
 */

public class CustomerRejectionTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rej_db";
    private static final String TABLE_NAME = "rej_table";
    private static final String ITEM_ID = "item_id";
    private static final String ITEM_NAME = "item_name";
    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
    private static final String VAN_STOCK = "van_stock";
    private static final String VAN_QTY = "van_qty";
    private static final String REJ_QTY = "rej_qty";
    private static final String PRICE = "price";
    private static final String TOTAL = "total";

    public CustomerRejectionTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ITEM_ID + " TEXT NOT NULL, " + ITEM_NAME + " TEXT NOT NULL, "
            + CUSTOMER_ID + " TEXT NOT NULL, " + CUSTOMER_NAME + " TEXT NOT NULL, " + VAN_STOCK + " INTEGER NOT NULL, " //+ VAN_QTY + " INTEGER NOT NULL, "
            + REJ_QTY + " INTEGER NOT NULL, " + PRICE + " REAL NOT NULL, " + TOTAL + " TEXT NOT NULL)";

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
    public void insertCustRejections(List<CRejectionModel> arrList, String customer_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // first erase data belong to the same user
        eraseCustRejections(db, customer_id);

        for (int i = 0; i < arrList.size(); i++) {
            CRejectionModel rejectionModel = arrList.get(i);
            if (rejectionModel.getTotal() > 0 && rejectionModel.getRej_qty() > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ITEM_ID, rejectionModel.getItem_id());
                contentValues.put(ITEM_NAME, rejectionModel.getItem_name());
                contentValues.put(CUSTOMER_ID, rejectionModel.getCustomer_id());
                contentValues.put(CUSTOMER_NAME, rejectionModel.getCustomer_name());
                contentValues.put(VAN_STOCK, rejectionModel.getVan_stock());
                contentValues.put(REJ_QTY, rejectionModel.getRej_qty());
                contentValues.put(PRICE, rejectionModel.getPrice());
                contentValues.put(TOTAL, rejectionModel.getTotal());

                db.insert(TABLE_NAME, null, contentValues);
            }
        }
        db.close();

    }

    // erase customer rejections
    public void eraseCustRejections(SQLiteDatabase db, String customer_id) {
        db.delete(TABLE_NAME, CUSTOMER_ID + "=?", new String[]{customer_id});
    }

    // get user all rejections by date
    // till now user has not restriction to make rejections.
    public ArrayList<CRejectionModel> getCustomerRejections(String customer_id) {
        ArrayList<CRejectionModel> array_list = new ArrayList<CRejectionModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "='" + customer_id + "'", null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                CRejectionModel rejectionModel = new CRejectionModel();
                rejectionModel.setItem_id(res.getString(res.getColumnIndex(ITEM_ID)));
                rejectionModel.setItem_name(res.getString(res.getColumnIndex(ITEM_NAME)));
                rejectionModel.setCustomer_id(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                rejectionModel.setCustomer_name(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                rejectionModel.setVan_stock(res.getInt(res.getColumnIndex(VAN_STOCK)));
                rejectionModel.setRej_qty(res.getInt(res.getColumnIndex(REJ_QTY)));
                rejectionModel.setPrice(res.getDouble(res.getColumnIndex(PRICE)));
                rejectionModel.setTotal(res.getDouble(res.getColumnIndex(TOTAL)));
                array_list.add(rejectionModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    // check if the product is in customer rejection list exists or not
    public int custRejExists(String customer_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, CUSTOMER_ID + "=? AND " + ITEM_ID + "=?", new String[]{customer_id, item_id});
        db.close();
        return numRows;
    }

    //TODO
    // get grand total for customer rejections
    public double custRejTotalAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(" + REJ_QTY + "*" + PRICE + ") as total " +
                "from " + TABLE_NAME + " where " + CUSTOMER_ID + "=?";
        Cursor res = db.rawQuery(query, new String[]{customer_id});

        double amount = 0;
        if (res.moveToFirst()) {
            amount = res.getInt(res.getColumnIndex("total"));
        }
        res.close();
        db.close();
        return amount;
    }

}

