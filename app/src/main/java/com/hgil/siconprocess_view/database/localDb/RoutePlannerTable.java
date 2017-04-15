package com.hgil.siconprocess_view.database.localDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.routeTarget.RouteTargetModel;
import com.hgil.siconprocess_view.database.OutletSaleView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RoutePlanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 15-04-2017.
 */

public class RoutePlannerTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Route_Planner_db";
    private static final String TABLE_NAME = "Route_Planner_Table";

    private static final String ROUTE_ID = "Route_id";
    private static final String PLAN = "route_plan";

    private Context mContext;

    public RoutePlannerTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ROUTE_ID + " TEXT NULL, "
                + PLAN + " TEXT NULL)");
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
    public boolean insertRoutePlan(RoutePlanModel routePlanModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROUTE_ID, routePlanModel.getRoute_id());
        contentValues.put(PLAN, routePlanModel.getRoute_plan());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertDemandTarget(List<RoutePlanModel> arrRoutePlan) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrRoutePlan.size(); i++) {
            RoutePlanModel routePlanModel = arrRoutePlan.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ROUTE_ID, routePlanModel.getRoute_id());
            contentValues.put(PLAN, routePlanModel.getRoute_plan());
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
    public ArrayList<RoutePlanModel> getAllDemandTarget() {
        ArrayList<RoutePlanModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RoutePlanModel routePlanModel = new RoutePlanModel();
                routePlanModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routePlanModel.setItemId(res.getString(res.getColumnIndex(PLAN)));
                routePlanModel.setTargetQty(res.getInt(res.getColumnIndex(TARGET_QTY)));
                array_list.add(routePlanModel);
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
        OutletSaleView outletSaleView = new OutletSaleView(mContext);
        ArrayList<RouteTargetModel> array_list = new ArrayList<>();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + " like '" + route_id + "'", null); //new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteTargetModel routePlanModel = new RouteTargetModel();
                routePlanModel.setItemId(res.getString(res.getColumnIndex(PLAN)));
                routePlanModel.setTarget(res.getInt(res.getColumnIndex(TARGET_QTY)));

                routePlanModel.setItem_name(outletSaleView.getItemName(routePlanModel.getItemId()));
                routePlanModel.setAchieved(outletSaleView.routeItemSaleQty(route_id, routePlanModel.getItemId()));
                routePlanModel.setVariance(routePlanModel.getTarget() - routePlanModel.getAchieved());

                array_list.add(routePlanModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}