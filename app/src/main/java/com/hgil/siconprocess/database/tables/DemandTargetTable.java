package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.DemandTargetModel;
import com.hgil.siconprocess.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class DemandTargetTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_demand_target";
    private static final String TABLE_NAME = "SD_DemandTarget_Master";
    private static final String RCE_ID = "Rce_id";
    private static final String DDATE = "DDate";
    private static final String DDAY = "DDay";
    private static final String MMONTH = "MMonth";
    private static final String DEPOT_ID = "Depot_ID";
    private static final String PSM_ID = "PSM_ID";
    private static final String ROUTE_ID = "Route_ID";
    private static final String CUSTOMER_ID = "Customer_ID";
    private static final String ITEM_ID = "Item_id";
    private static final String TARGET_QTY = "Target_Qty";
    private static final String ACTIVE = "Active";
    private static final String UPDATEBY_PAYCODE = "updateby_paycode";
    private static final String UPDATEBY_DATE = "updateby_Date";
    private static final String UPDATED_IP = "updated_ip";

    public DemandTargetTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" (" + RCE_ID + " NUMERIC NOT NULL, "
                + DDATE + " TEXT NOT NULL, " + DDAY + " TEXT NOT NULL, " + MMONTH + " INTEGER NOT NULL, "
                + DEPOT_ID + " TEXT NOT NULL, " + PSM_ID + " TEXT NOT NULL, " + ROUTE_ID + " TEXT NOT NULL, "
                + CUSTOMER_ID + " TEXT NULL, " + ITEM_ID + " TEXT NOT NULL, " + TARGET_QTY + " REAL NOT NULL, "
                + ACTIVE + " TEXT NULL, " + UPDATEBY_PAYCODE + " TEXT NOT NULL, "
                + UPDATEBY_DATE + " TEXT NOT NULL, " + UPDATED_IP + " TEXT NOT NULL)");
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
    public boolean insertDemandTarget(DemandTargetModel demandTargetModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RCE_ID, demandTargetModel.getRecId());
        contentValues.put(DDATE, demandTargetModel.getDDate());
        contentValues.put(DDAY, demandTargetModel.getDDay());
        contentValues.put(MMONTH, demandTargetModel.getMMonth());
        contentValues.put(DEPOT_ID, demandTargetModel.getDepotID());
        contentValues.put(PSM_ID, demandTargetModel.getPSMID());
        contentValues.put(ROUTE_ID, demandTargetModel.getRouteID());
        contentValues.put(CUSTOMER_ID, demandTargetModel.getCustomerID());
        contentValues.put(ITEM_ID, demandTargetModel.getItemId());
        contentValues.put(TARGET_QTY, demandTargetModel.getTargetQty());
        contentValues.put(ACTIVE, demandTargetModel.getActive());
        contentValues.put(UPDATEBY_PAYCODE, demandTargetModel.getUpdatebyPaycode());
        contentValues.put(UPDATEBY_DATE, demandTargetModel.getUpdatebyDate());
        contentValues.put(UPDATED_IP, demandTargetModel.getUpdatedIp());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertDemandTarget(List<DemandTargetModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < arrList.size(); i++) {
            DemandTargetModel demandTargetModel = arrList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(RCE_ID, demandTargetModel.getRecId());
            contentValues.put(DDATE, demandTargetModel.getDDate());
            contentValues.put(DDAY, demandTargetModel.getDDay());
            contentValues.put(MMONTH, demandTargetModel.getMMonth());
            contentValues.put(DEPOT_ID, demandTargetModel.getDepotID());
            contentValues.put(PSM_ID, demandTargetModel.getPSMID());
            contentValues.put(ROUTE_ID, demandTargetModel.getRouteID());
            contentValues.put(CUSTOMER_ID, demandTargetModel.getCustomerID());
            contentValues.put(ITEM_ID, demandTargetModel.getItemId());
            contentValues.put(TARGET_QTY, demandTargetModel.getTargetQty());
            contentValues.put(ACTIVE, demandTargetModel.getActive());
            contentValues.put(UPDATEBY_PAYCODE, demandTargetModel.getUpdatebyPaycode());
            contentValues.put(UPDATEBY_DATE, demandTargetModel.getUpdatebyDate());
            contentValues.put(UPDATED_IP, demandTargetModel.getUpdatedIp());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public DemandTargetModel getDemandTargetByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        DemandTargetModel demandTargetModel = new DemandTargetModel();
        if (res.moveToFirst()) {
            demandTargetModel.setRecId(res.getLong(res.getColumnIndex(RCE_ID)));
            demandTargetModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
            demandTargetModel.setDDay(res.getString(res.getColumnIndex(DDAY)));
            demandTargetModel.setMMonth(res.getInt(res.getColumnIndex(MMONTH)));
            demandTargetModel.setDepotID(res.getString(res.getColumnIndex(DEPOT_ID)));
            demandTargetModel.setPSMID(res.getString(res.getColumnIndex(PSM_ID)));
            demandTargetModel.setRouteID(res.getString(res.getColumnIndex(ROUTE_ID)));
            demandTargetModel.setCustomerID(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            demandTargetModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            demandTargetModel.setTargetQty(res.getFloat(res.getColumnIndex(TARGET_QTY)));
            demandTargetModel.setActive(res.getString(res.getColumnIndex(ACTIVE)));
            demandTargetModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
            demandTargetModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
            demandTargetModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));
        }
        res.close();
        db.close();
        return demandTargetModel;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    /*public boolean updateUserRoleMap(UserRoleMapModel userRoleMapModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EMAIL, userRoleMapModel.getEmail());
        contentValues.put(ROLE_ID, userRoleMapModel.getRole_id());
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

    public ArrayList<DemandTargetModel> getAllDemandTarget() {
        ArrayList<DemandTargetModel> array_list = new ArrayList<DemandTargetModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                DemandTargetModel demandTargetModel = new DemandTargetModel();
                demandTargetModel.setRecId(res.getLong(res.getColumnIndex(RCE_ID)));
                demandTargetModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
                demandTargetModel.setDDay(res.getString(res.getColumnIndex(DDAY)));
                demandTargetModel.setMMonth(res.getInt(res.getColumnIndex(MMONTH)));
                demandTargetModel.setDepotID(res.getString(res.getColumnIndex(DEPOT_ID)));
                demandTargetModel.setPSMID(res.getString(res.getColumnIndex(PSM_ID)));
                demandTargetModel.setRouteID(res.getString(res.getColumnIndex(ROUTE_ID)));
                demandTargetModel.setCustomerID(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                demandTargetModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                demandTargetModel.setTargetQty(res.getFloat(res.getColumnIndex(TARGET_QTY)));
                demandTargetModel.setActive(res.getString(res.getColumnIndex(ACTIVE)));
                demandTargetModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
                demandTargetModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
                demandTargetModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));

                array_list.add(demandTargetModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}