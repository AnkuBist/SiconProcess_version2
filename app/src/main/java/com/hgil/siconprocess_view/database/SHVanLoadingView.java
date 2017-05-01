package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SHRouteVanLoadingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class SHVanLoadingView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "GODb_SH_van_loading";
    private static final String TABLE_NAME = "Route_SH_Van_Loadin_table";

    private static final String ROUTE_ID = "route_id";
    private static final String STOCK_DATE = "stock_date";
    private static final String COUNT = "stock";

    private Context mContext;

    public SHVanLoadingView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + STOCK_DATE + " TEXT NULL, "
                + ROUTE_ID + " TEXT NULL, " + COUNT + " INTEGER NULL)");
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
    public boolean insertSHRouteVanLoading(List<SHRouteVanLoadingModel> arrRouteVanLoading) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int stockDateColumn = ih.getColumnIndex(STOCK_DATE);
        final int countColumn = ih.getColumnIndex(COUNT);

        try {
            db.beginTransaction();
            for (SHRouteVanLoadingModel shRouteVanLoadingModel : arrRouteVanLoading) {
                ih.prepareForInsert();

                ih.bind(routeIdColumn, shRouteVanLoadingModel.getRouteId());
                ih.bind(stockDateColumn, shRouteVanLoadingModel.getStockDate());
                ih.bind(countColumn, shRouteVanLoadingModel.getCount());

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
    public ArrayList<SHRouteVanLoadingModel> getAllVanLoadinHistory() {
        ArrayList<SHRouteVanLoadingModel> array_list = new ArrayList<SHRouteVanLoadingModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SHRouteVanLoadingModel shRouteVanLoadingModel = new SHRouteVanLoadingModel();
                shRouteVanLoadingModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                shRouteVanLoadingModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                shRouteVanLoadingModel.setCount(res.getInt(res.getColumnIndex(COUNT)));
                array_list.add(shRouteVanLoadingModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<SHRouteVanLoadingModel> routeVanLoadingHistory(String route_id) {
        ArrayList<SHRouteVanLoadingModel> array_list = new ArrayList<SHRouteVanLoadingModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? ORDER BY " + STOCK_DATE + " DESC", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SHRouteVanLoadingModel shRouteVanLoadingModel = new SHRouteVanLoadingModel();
                shRouteVanLoadingModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                shRouteVanLoadingModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                shRouteVanLoadingModel.setCount(res.getInt(res.getColumnIndex(COUNT)));
                array_list.add(shRouteVanLoadingModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*van loading by route and date*/
    public int routeVanLoadingHistory(String route_id, String stock_date) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? AND " + STOCK_DATE + "=?",
                new String[]{route_id, stock_date});
        int value = 0;
        if (res.moveToFirst()) {
            value = (res.getInt(res.getColumnIndex(COUNT)));
        }
        res.close();
        db.close();
        return value;
    }
}