package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.depotList.DepotModel;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class RouteView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_route";
    private static final String TABLE_NAME = "V_SD_Route_Master";

    private static final String DEPOT_ID = "Depot_id";
    private static final String DEPOT_NAME = "Depot_Name";
    private static final String ROUTE_ID = "Route_Id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String PSMID = "PSMID";
    //private static final String CUSTOMER_ID = "Customer_id";
    // private static final String CUSTOMER_NAME = "Customer_name";
    // private static final String CONTACT_NO = "Contact_no";

    public RouteView(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + //DEPOT_ID + " TEXT NULL, "
                DEPOT_ID + " TEXT NULL, " + DEPOT_NAME + " TEXT NULL, " +
                ROUTE_ID + " TEXT NULL, " + ROUTE_NAME + " TEXT NULL)"); //+ PSMID + " TEXT NULL)");
        //+ CUSTOMER_ID + " TEXT NULL, " + CUSTOMER_NAME + " TEXT NULL, "
        // + CONTACT_NO + " TEXT NULL)");
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
    public boolean insertRoute(RouteModel routeModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DEPOT_ID, routeModel.getDepotId());
        contentValues.put(DEPOT_NAME, routeModel.getDepotName());
        contentValues.put(ROUTE_ID, routeModel.getRouteId());
        contentValues.put(ROUTE_NAME, routeModel.getRouteName());
        //contentValues.put(PSMID, routeModel.getPSMID());
        //contentValues.put(CUSTOMER_ID, routeModel.getCustomerId());
        //contentValues.put(CUSTOMER_NAME, routeModel.getCustomerName());
        //contentValues.put(CONTACT_NO, routeModel.getContactNo());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple routes
    public boolean insertRoutes(List<RouteModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (RouteModel routeModel : arrList) {
            ContentValues contentValues = new ContentValues();
            //contentValues.put(DEPOT_ID, routeModel.getDepot());
            contentValues.put(DEPOT_ID, routeModel.getDepotId());
            contentValues.put(DEPOT_NAME, routeModel.getDepotName());
            contentValues.put(ROUTE_ID, routeModel.getRouteId());
            contentValues.put(ROUTE_NAME, routeModel.getRouteName());
            //contentValues.put(PSMID, routeModel.getPSMID());
            //contentValues.put(CUSTOMER_ID, routeModel.getCustomerId());
            //contentValues.put(CUSTOMER_NAME, routeModel.getCustomerName());
            //contentValues.put(CONTACT_NO, routeModel.getContactNo());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public RouteModel geRouteInfoByRouteId(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        RouteModel routeModel = new RouteModel();
        if (res.moveToFirst()) {
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setDepotName(res.getString(res.getColumnIndex(DEPOT_NAME)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            //routeModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
            //routeModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            //routeModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            //routeModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
        }
        res.close();
        db.close();
        return routeModel;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    /*
        contentValues.put(IP, userRoleMapModel.getIp());
        contentValues.put(U_TS, userRoleMapModel.getU_ts());
        db.update(TABLE_NAME, contentValues, USER_ROLE_ID + "= ? ", new String[]{Integer.toString(userRoleMapModel.getUser_role_id())});
        db.close();
        return true;
    }*/

 /*   public Integer deleteUserRoleMapById(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, USER_ROLE_ID + "= ? ", new String[]{Integer.toString(id)});
    }*/

    public RouteModel getRouteById(String route_id) {
        RouteModel routeModel = new RouteModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            //while (res.isAfterLast() == false) {
            //routeModel.setDepot(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setDepotName(res.getString(res.getColumnIndex(DEPOT_NAME)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            //routeModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
            //routeModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            //routeModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            //routeModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
            //}
        }
        res.close();
        db.close();
        return routeModel;
    }

    public ArrayList<RouteModel> getAllRoutes() {
        ArrayList<RouteModel> array_list = new ArrayList<RouteModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteModel routeModel = new RouteModel();
                ///routeModel.setDepot(res.getString(res.getColumnIndex(DEPOT_ID)));
                routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                routeModel.setDepotName(res.getString(res.getColumnIndex(DEPOT_NAME)));
                routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                //.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                //routeModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                //routeModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                //routeModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));

                array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<RouteListModel> getRouteList() {
        ArrayList<RouteListModel> array_list = new ArrayList<RouteListModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteListModel routeModel = new RouteListModel();
                routeModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get unique depots for login user*/
    public ArrayList<DepotModel> getDepotList() {
        ArrayList<DepotModel> array_list = new ArrayList<DepotModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + DEPOT_ID + ", " + DEPOT_NAME + " FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                DepotModel depotModel = new DepotModel();
                depotModel.setDepot_id(res.getString(res.getColumnIndex(DEPOT_ID)));
                depotModel.setDepot_name(res.getString(res.getColumnIndex(DEPOT_NAME)));
                array_list.add(depotModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*GET DEPOT ROUTES*/
    public ArrayList<RouteListModel> getDepotRouteList(String depot_id) {
        ArrayList<RouteListModel> array_list = new ArrayList<RouteListModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + DEPOT_ID + "=?", new String[]{depot_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteListModel routeModel = new RouteListModel();
                routeModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

}
