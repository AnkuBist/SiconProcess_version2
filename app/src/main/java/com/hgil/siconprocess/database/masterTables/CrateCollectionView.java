package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CrateCollectionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class CrateCollectionView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sicon_crate_collection";
    private static final String TABLE_NAME = "V_SD_Crate_Collection_Master";
    // private static final String REC_ID = "Rec_id";
    private static final String DDATE = "DDate";
    private static final String SUB_COMPANY_ID = "Sub_Company_id";
    private static final String DEPOT_ID = "Depot_id";
    private static final String SUB_DEPOT_ID = "Sub_depot_id";
    private static final String ROUTE_ID = "Route_id";
    private static final String ROUTE_MANAGEMENT_ID = "Route_Management_id";
    private static final String ROUTE_MANAGEMENT_DATE = "Route_Management_Date";
    private static final String CUSTOMER_ID = "Customer_id";
    private static final String INVOICE_NO = "Invoice_No";
    private static final String INVOICE_DATE = "Invoice_Date";
    private static final String CRATE_ID = "Crate_Id";
    private static final String CRATE_QTY = "Crate_Qty";
    private static final String RECEIVE_QTY = "Receive_Qty";
    private static final String BALANCE_QTY = "Balance_Qty";
    private static final String CASHIER_PAYCODE = "Cashier_paycode";
    private static final String LOADING_PAYCODE = "Loading_paycode";
    private static final String ACTIVE = "Active";
    private static final String UPDATEBY_PAYCODE = "updateby_paycode";
    private static final String UPDATEBY_DATE = "updateby_Date";
    private static final String UPDATED_IP = "updated_ip";

    public CrateCollectionView(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" //+ REC_ID + " NUMERIC NOT NULL, "
                + DDATE + " TEXT NULL, " + SUB_COMPANY_ID + " TEXT NULL, " + DEPOT_ID + " TEXT NULL, "
                + SUB_DEPOT_ID + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, " + ROUTE_MANAGEMENT_ID + " TEXT NULL, "
                + ROUTE_MANAGEMENT_DATE + " TEXT NULL, " + CUSTOMER_ID + " TEXT NULL, " + INVOICE_NO + " TEXT NULL, " + INVOICE_DATE + " TEXT NULL, "
                + CRATE_ID + " TEXT NULL, " + CRATE_QTY + " REAL NULL, " + RECEIVE_QTY + " REAL NULL, "
                + BALANCE_QTY + " REAL NULL, " + CASHIER_PAYCODE + " TEXT NULL, " + LOADING_PAYCODE + " TEXT NULL, "
                + ACTIVE + " TEXT NULL, " + UPDATEBY_PAYCODE + " TEXT NULL, "
                + UPDATEBY_DATE + " TEXT NULL, " + UPDATED_IP + " TEXT NULL)");
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
    public boolean insertCrateCollection(CrateCollectionModel crateCollectionModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // contentValues.put(REC_ID, crateCollectionModel.getRecId());
        contentValues.put(DDATE, crateCollectionModel.getDDate());
        contentValues.put(SUB_COMPANY_ID, crateCollectionModel.getSubCompanyId());
        contentValues.put(DEPOT_ID, crateCollectionModel.getDepotId());
        //contentValues.put(SUB_DEPOT_ID, crateCollectionModel.getSubDepotId());
        contentValues.put(ROUTE_ID, crateCollectionModel.getRouteId());
        contentValues.put(ROUTE_MANAGEMENT_ID, crateCollectionModel.getRouteManagementId());
        contentValues.put(ROUTE_MANAGEMENT_DATE, crateCollectionModel.getRouteManagementDate());
        contentValues.put(CUSTOMER_ID, crateCollectionModel.getCustomerId());
        contentValues.put(INVOICE_NO, crateCollectionModel.getInvoiceNo());
        contentValues.put(INVOICE_DATE, crateCollectionModel.getInvoiceDate());
        contentValues.put(CRATE_ID, crateCollectionModel.getCrateId());
        contentValues.put(CRATE_QTY, crateCollectionModel.getCrateQty());
        contentValues.put(RECEIVE_QTY, crateCollectionModel.getReceiveQty());
        contentValues.put(BALANCE_QTY, crateCollectionModel.getBalanceQty());
        contentValues.put(CASHIER_PAYCODE, crateCollectionModel.getCashierPaycode());
        contentValues.put(LOADING_PAYCODE, crateCollectionModel.getLoadingPaycode());
        contentValues.put(ACTIVE, crateCollectionModel.getActive());
        contentValues.put(UPDATEBY_PAYCODE, crateCollectionModel.getUpdatebyPaycode());
        contentValues.put(UPDATEBY_DATE, crateCollectionModel.getUpdatebyDate());
        contentValues.put(UPDATED_IP, crateCollectionModel.getUpdatedIp());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertCrateCollection(List<CrateCollectionModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < arrList.size(); i++) {
            CrateCollectionModel crateCollectionModel = arrList.get(i);
            ContentValues contentValues = new ContentValues();
            // contentValues.put(REC_ID, crateCollectionModel.getRecId());
            contentValues.put(DDATE, crateCollectionModel.getDDate());
            contentValues.put(SUB_COMPANY_ID, crateCollectionModel.getSubCompanyId());
            contentValues.put(DEPOT_ID, crateCollectionModel.getDepotId());
            //contentValues.put(SUB_DEPOT_ID, crateCollectionModel.getSubDepotId());
            contentValues.put(ROUTE_ID, crateCollectionModel.getRouteId());
            contentValues.put(ROUTE_MANAGEMENT_ID, crateCollectionModel.getRouteManagementId());
            contentValues.put(ROUTE_MANAGEMENT_DATE, crateCollectionModel.getRouteManagementDate());
            contentValues.put(CUSTOMER_ID, crateCollectionModel.getCustomerId());
            contentValues.put(INVOICE_NO, crateCollectionModel.getInvoiceNo());
            contentValues.put(INVOICE_DATE, crateCollectionModel.getInvoiceDate());
            contentValues.put(CRATE_ID, crateCollectionModel.getCrateId());
            contentValues.put(CRATE_QTY, crateCollectionModel.getCrateQty());
            contentValues.put(RECEIVE_QTY, crateCollectionModel.getReceiveQty());
            contentValues.put(BALANCE_QTY, crateCollectionModel.getBalanceQty());
            contentValues.put(CASHIER_PAYCODE, crateCollectionModel.getCashierPaycode());
            contentValues.put(LOADING_PAYCODE, crateCollectionModel.getLoadingPaycode());
            contentValues.put(ACTIVE, crateCollectionModel.getActive());
            contentValues.put(UPDATEBY_PAYCODE, crateCollectionModel.getUpdatebyPaycode());
            contentValues.put(UPDATEBY_DATE, crateCollectionModel.getUpdatebyDate());
            contentValues.put(UPDATED_IP, crateCollectionModel.getUpdatedIp());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public CrateCollectionModel getCrateCollectionByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        CrateCollectionModel crateCollectionModel = new CrateCollectionModel();
        if (res.moveToFirst()) {
            // crateCollectionModel.setRecId(res.getLong(res.getColumnIndex(REC_ID)));
            crateCollectionModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
            crateCollectionModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
            crateCollectionModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            //crateCollectionModel.setSubDepotId(res.getString(res.getColumnIndex(SUB_DEPOT_ID)));
            crateCollectionModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            crateCollectionModel.setRouteManagementId(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_ID)));
            crateCollectionModel.setRouteManagementDate(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_DATE)));
            crateCollectionModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            crateCollectionModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
            crateCollectionModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
            crateCollectionModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
            crateCollectionModel.setCrateQty(res.getFloat(res.getColumnIndex(CRATE_QTY)));
            crateCollectionModel.setReceiveQty(res.getFloat(res.getColumnIndex(RECEIVE_QTY)));
            crateCollectionModel.setBalanceQty(res.getFloat(res.getColumnIndex(BALANCE_QTY)));
            crateCollectionModel.setCashierPaycode(res.getString(res.getColumnIndex(CASHIER_PAYCODE)));
            crateCollectionModel.setLoadingPaycode(res.getString(res.getColumnIndex(LOADING_PAYCODE)));
            crateCollectionModel.setActive(res.getString(res.getColumnIndex(ACTIVE)));
            crateCollectionModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
            crateCollectionModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
            crateCollectionModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));
        }
        res.close();
        db.close();
        return crateCollectionModel;
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

    public ArrayList<CrateCollectionModel> getAllCrateCollection() {
        ArrayList<CrateCollectionModel> array_list = new ArrayList<CrateCollectionModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                CrateCollectionModel crateCollectionModel = new CrateCollectionModel();
                //   crateCollectionModel.setRecId(res.getLong(res.getColumnIndex(REC_ID)));
                crateCollectionModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
                crateCollectionModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                crateCollectionModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                //crateCollectionModel.setSubDepotId(res.getString(res.getColumnIndex(SUB_DEPOT_ID)));
                crateCollectionModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                crateCollectionModel.setRouteManagementId(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_ID)));
                crateCollectionModel.setRouteManagementDate(res.getString(res.getColumnIndex(ROUTE_MANAGEMENT_DATE)));
                crateCollectionModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                crateCollectionModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
                crateCollectionModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
                crateCollectionModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
                crateCollectionModel.setCrateQty(res.getFloat(res.getColumnIndex(CRATE_QTY)));
                crateCollectionModel.setReceiveQty(res.getFloat(res.getColumnIndex(RECEIVE_QTY)));
                crateCollectionModel.setBalanceQty(res.getFloat(res.getColumnIndex(BALANCE_QTY)));
                crateCollectionModel.setCashierPaycode(res.getString(res.getColumnIndex(CASHIER_PAYCODE)));
                crateCollectionModel.setLoadingPaycode(res.getString(res.getColumnIndex(LOADING_PAYCODE)));
                crateCollectionModel.setActive(res.getString(res.getColumnIndex(ACTIVE)));
                crateCollectionModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
                crateCollectionModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
                crateCollectionModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));

                array_list.add(crateCollectionModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get van crate total*/
    public int vanTotalCrate() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(" + CRATE_QTY + ") as total_crates " +
                "from " + TABLE_NAME;
        Cursor res = db.rawQuery(query, null);

        int total_crates = 0;
        if (res.moveToFirst()) {
            total_crates = res.getInt(res.getColumnIndex("total_crates"));
        }
        res.close();
        db.close();
        return total_crates;
    }
}