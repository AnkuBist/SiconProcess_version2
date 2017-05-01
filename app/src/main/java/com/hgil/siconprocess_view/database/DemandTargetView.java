package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.routeTarget.RouteTargetModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.DemandTargetModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class DemandTargetView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Sicon_Demand_Target_db";
    private static final String TABLE_NAME = "V_Demand_Target_Table";

    private static final String ROUTE_ID = "Route_id";
    private static final String ITEM_ID = "Item_id";
    private static final String TARGET_QTY = "Target_Qty";

    private Context mContext;

    public DemandTargetView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ROUTE_ID + " TEXT NULL, "
                + ITEM_ID + " TEXT NULL, " + TARGET_QTY + " INTEGER NULL)");
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
    public boolean insertDemandTarget(List<DemandTargetModel> arrDemandTarget) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int itemIdColumn = ih.getColumnIndex(ITEM_ID);
        final int targetQtyColumn = ih.getColumnIndex(TARGET_QTY);

        try {
            db.beginTransaction();
            for (DemandTargetModel demandTargetModel : arrDemandTarget) {
                ih.prepareForInsert();

                ih.bind(routeIdColumn, demandTargetModel.getRouteId());
                ih.bind(itemIdColumn, demandTargetModel.getItemId());
                ih.bind(targetQtyColumn, demandTargetModel.getTargetQty());

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
    public ArrayList<DemandTargetModel> getAllDemandTarget() {
        ArrayList<DemandTargetModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                DemandTargetModel demandTargetModel = new DemandTargetModel();
                demandTargetModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                demandTargetModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                demandTargetModel.setTargetQty(res.getInt(res.getColumnIndex(TARGET_QTY)));
                array_list.add(demandTargetModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get demand target by route*/
    public ArrayList<RouteTargetModel> getDemandTargetByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TodaySaleView todaySaleView = new TodaySaleView(mContext);
        ItemDetailView itemDetailView = new ItemDetailView(mContext);

        ArrayList<RouteTargetModel> array_list = new ArrayList<>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + " like '" + route_id + "'", null); //new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteTargetModel demandTargetModel = new RouteTargetModel();
                String item_id = res.getString(res.getColumnIndex(ITEM_ID));
                demandTargetModel.setItemId(item_id);
                demandTargetModel.setTarget(res.getInt(res.getColumnIndex(TARGET_QTY)));

                demandTargetModel.setItem_name(itemDetailView.getItemName(item_id));
                demandTargetModel.setItem_sequence(itemDetailView.getItemSequence(item_id));
                demandTargetModel.setAchieved(todaySaleView.routeItemSaleQty(route_id, demandTargetModel.getItemId()));
                demandTargetModel.setVariance(demandTargetModel.getAchieved() - demandTargetModel.getTarget());

                array_list.add(demandTargetModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        ArrayList<RouteTargetModel> sortedArrayList = new ArrayList<RouteTargetModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<RouteTargetModel>() {
            public int compare(RouteTargetModel p1, RouteTargetModel p2) {
                return Integer.valueOf(p1.getItem_sequence()).compareTo(p2.getItem_sequence());
            }
        });
        return sortedArrayList;
    }
}