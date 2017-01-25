package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;
import com.hgil.siconprocess.utils.Constant;

import java.util.ArrayList;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class RouteView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_route";
    private static final String TABLE_NAME = "V_SD_Route_Master";
    private static final String SUB_COMPANY_ID = "Sub_Company_id";
    private static final String DEPOT_ID = "Depot_id";
    private static final String SUBDEPOT_ID = "SubDepot_id";
    private static final String ROUTE_ID = "Route_Id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String ROUTE_DESCRIPTION = "Route_Description";
    private static final String SALE_DATE_PARAMETER = "Sale_Date_Parameter";
    private static final String LOADING_TYPE = "Loaing_Type";
    private static final String TCC = "TCC";
    private static final String MAINDEPOT = "MainDepot";
    private static final String FLAG = "Flag";

    public RouteView(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + SUB_COMPANY_ID + " TEXT NULL, "
                + DEPOT_ID + " TEXT NULL, " + SUBDEPOT_ID + " TEXT NOT NULL, "
                + ROUTE_ID + " TEXT NULL, " + ROUTE_NAME + " TEXT NULL, " + ROUTE_DESCRIPTION + " TEXT NULL, "
                + SALE_DATE_PARAMETER + " TEXT NULL, " + LOADING_TYPE + " TEXT NULL, " + TCC + " INTEGER NULL, "
                + MAINDEPOT + " TEXT NULL, " + FLAG + " INTEGER NULL)");
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
        contentValues.put(SUB_COMPANY_ID, routeModel.getSubCompanyId());
        contentValues.put(DEPOT_ID, routeModel.getDepotId());
        contentValues.put(SUBDEPOT_ID, routeModel.getSubDepotId());
        contentValues.put(ROUTE_ID, routeModel.getRouteId());
        contentValues.put(ROUTE_NAME, routeModel.getRouteName());
        contentValues.put(ROUTE_DESCRIPTION, routeModel.getRouteDescription());
        contentValues.put(SALE_DATE_PARAMETER, routeModel.getSaleDateParameter());
        contentValues.put(LOADING_TYPE, routeModel.getLoadingType());
        contentValues.put(TCC, routeModel.getTCC());
        //contentValues.put(MAINDEPOT, routeModel.getMainDepot());
        contentValues.put(FLAG, routeModel.getFlag());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public RouteModel geRouteInfoByRouteId(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        RouteModel routeModel = new RouteModel();
        if (res.moveToFirst()) {
            routeModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setSubDepotId(res.getString(res.getColumnIndex(SUBDEPOT_ID)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            routeModel.setRouteDescription(res.getString(res.getColumnIndex(ROUTE_DESCRIPTION)));
            routeModel.setSaleDateParameter(res.getString(res.getColumnIndex(SALE_DATE_PARAMETER)));
            routeModel.setLoadingType(res.getString(res.getColumnIndex(LOADING_TYPE)));
            routeModel.setTCC(res.getInt(res.getColumnIndex(TCC)));
            //routeModel.setMainDepot(res.getString(res.getColumnIndex(MAINDEPOT)));
            routeModel.setFlag(res.getInt(res.getColumnIndex(FLAG)));
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

    public RouteModel getRoute() {
        RouteModel routeModel = new RouteModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            //while (res.isAfterLast() == false) {
            routeModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setSubDepotId(res.getString(res.getColumnIndex(SUBDEPOT_ID)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            routeModel.setRouteDescription(res.getString(res.getColumnIndex(ROUTE_DESCRIPTION)));
            routeModel.setSaleDateParameter(res.getString(res.getColumnIndex(SALE_DATE_PARAMETER)));
            routeModel.setLoadingType(res.getString(res.getColumnIndex(LOADING_TYPE)));
            routeModel.setTCC(res.getInt(res.getColumnIndex(TCC)));
            //routeModel.setMainDepot(res.getString(res.getColumnIndex(MAINDEPOT)));
            routeModel.setFlag(res.getInt(res.getColumnIndex(FLAG)));
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
                routeModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                routeModel.setSubDepotId(res.getString(res.getColumnIndex(SUBDEPOT_ID)));
                routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeModel.setRouteDescription(res.getString(res.getColumnIndex(ROUTE_DESCRIPTION)));
                routeModel.setSaleDateParameter(res.getString(res.getColumnIndex(SALE_DATE_PARAMETER)));
                routeModel.setLoadingType(res.getString(res.getColumnIndex(LOADING_TYPE)));
                routeModel.setTCC(res.getInt(res.getColumnIndex(TCC)));
                //routeModel.setMainDepot(res.getString(res.getColumnIndex(MAINDEPOT)));
                routeModel.setFlag(res.getInt(res.getColumnIndex(FLAG)));

                array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}
