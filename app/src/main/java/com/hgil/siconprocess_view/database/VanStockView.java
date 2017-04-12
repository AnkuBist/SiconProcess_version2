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

    private static final String DATABASE_NAME = "Sicon_Customer_db";
    private static final String TABLE_NAME = "V_SD_Customer_Route_Mapping";

    private static final String ROUTE_ID = "Route_id";
    private static final String ITEM_ID = "Item_id";
    private static final String ITEM_QTY = "Item_qty";

    private Context mContext;

    public VanStockView(Context context) {
        super(context, DATABASE_NAME, null, 1);
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
    public boolean insertVanStock(List<VanStockModel> arrOutletSale) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrOutletSale.size(); i++) {
            VanStockModel vanStockModel = arrOutletSale.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, vanStockModel.getRouteId());
            contentValues.put(ITEM_ID, vanStockModel.getItemId());
            contentValues.put(ITEM_QTY, vanStockModel.getItemQty());
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
    public ArrayList<VanStockModel> getVanStockByRoute(String route_id) {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
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
}