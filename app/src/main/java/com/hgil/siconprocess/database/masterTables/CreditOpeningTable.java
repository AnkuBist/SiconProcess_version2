package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CreditOpeningModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class CreditOpeningTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_credit_opening";
    private static final String TABLE_NAME = "SD_CreditOpening_Master";
    private static final String RCE_ID = "Rce_id";
    private static final String SUBCOMPANY_ID = "Subcompany_id";
    private static final String DEPOT_ID = "Depot_Id";
    private static final String SUBDEPOT_ID = "Subdepot_id";
    private static final String ROUTE_ID = "Route_id";
    private static final String CUSTOMER_ID = "Customer_id";
    private static final String DDATE = "DDate";
    private static final String OPENING = "Opening";
    private static final String SALE_AMT = "Sale_Amt";
    private static final String RECEIVE_AMT = "Receive_Amt";
    private static final String DISCOUNT_AMT = "Discount_Amt";
    private static final String BALANCE = "Balance";
    private static final String UPDATEBY_PAYCODE = "updateby_paycode";
    private static final String UPDATEBY_DATE = "updateby_Date";
    private static final String UPDATED_IP = "updated_ip";
    private static final String CHEQUEAMT = "ChequeAmt";

    public CreditOpeningTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + RCE_ID + " NUMERIC NOT NULL, "
                + SUBCOMPANY_ID + " TEXT NULL, " + DEPOT_ID + " TEXT NULL, " + SUBDEPOT_ID + " TEXT NULL, "
                + ROUTE_ID + " TEXT NULL, " + CUSTOMER_ID + " TEXT NULL, " + DDATE + " TEXT NULL, "
                + OPENING + " REAL NULL, " + SALE_AMT + " REAL NULL, " + RECEIVE_AMT + " REAL NULL, "
                + DISCOUNT_AMT + " REAL NULL, " + BALANCE + " REAL NULL, " + UPDATEBY_PAYCODE + " TEXT NOT NULL, "
                + UPDATEBY_DATE + " TEXT NOT NULL, " + UPDATED_IP + " TEXT NOT NULL, " + CHEQUEAMT + " REAL NULL)");
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
    public boolean insertCreditOpening(CreditOpeningModel creditOpeningModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RCE_ID, creditOpeningModel.getRceId());
        contentValues.put(SUBCOMPANY_ID, creditOpeningModel.getSubcompanyId());
        contentValues.put(DEPOT_ID, creditOpeningModel.getDepotId());
        //contentValues.put(SUBDEPOT_ID, creditOpeningModel.getSubdepotid());
        contentValues.put(ROUTE_ID, creditOpeningModel.getRouteId());
        contentValues.put(CUSTOMER_ID, creditOpeningModel.getCustomerId());
        contentValues.put(DDATE, creditOpeningModel.getDDate());
        contentValues.put(OPENING, creditOpeningModel.getOpening());
        contentValues.put(SALE_AMT, creditOpeningModel.getSaleAmt());
        contentValues.put(RECEIVE_AMT, creditOpeningModel.getReceiveAmt());
        contentValues.put(DISCOUNT_AMT, creditOpeningModel.getDiscountAmt());
        contentValues.put(BALANCE, creditOpeningModel.getBalance());
        contentValues.put(UPDATEBY_PAYCODE, creditOpeningModel.getUpdatebyPaycode());
        contentValues.put(UPDATEBY_DATE, creditOpeningModel.getUpdatebyDate());
        contentValues.put(UPDATED_IP, creditOpeningModel.getUpdatedIp());
        contentValues.put(CHEQUEAMT, creditOpeningModel.getChequeAmt());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertCreditOpening(List<CreditOpeningModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < arrList.size(); i++) {
            CreditOpeningModel creditOpeningModel = arrList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(RCE_ID, creditOpeningModel.getRceId());
            contentValues.put(SUBCOMPANY_ID, creditOpeningModel.getSubcompanyId());
            contentValues.put(DEPOT_ID, creditOpeningModel.getDepotId());
            //contentValues.put(SUBDEPOT_ID, creditOpeningModel.getSubdepotid());
            contentValues.put(ROUTE_ID, creditOpeningModel.getRouteId());
            contentValues.put(CUSTOMER_ID, creditOpeningModel.getCustomerId());
            contentValues.put(DDATE, creditOpeningModel.getDDate());
            contentValues.put(OPENING, creditOpeningModel.getOpening());
            contentValues.put(SALE_AMT, creditOpeningModel.getSaleAmt());
            contentValues.put(RECEIVE_AMT, creditOpeningModel.getReceiveAmt());
            contentValues.put(DISCOUNT_AMT, creditOpeningModel.getDiscountAmt());
            contentValues.put(BALANCE, creditOpeningModel.getBalance());
            contentValues.put(UPDATEBY_PAYCODE, creditOpeningModel.getUpdatebyPaycode());
            contentValues.put(UPDATEBY_DATE, creditOpeningModel.getUpdatebyDate());
            contentValues.put(UPDATED_IP, creditOpeningModel.getUpdatedIp());
            contentValues.put(CHEQUEAMT, creditOpeningModel.getChequeAmt());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public CreditOpeningModel getCreditOpeningByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        CreditOpeningModel creditOpeningModel = new CreditOpeningModel();
        if (res.moveToFirst()) {
            creditOpeningModel.setRceId(res.getLong(res.getColumnIndex(RCE_ID)));
            creditOpeningModel.setSubcompanyId(res.getString(res.getColumnIndex(SUBCOMPANY_ID)));
            creditOpeningModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            //creditOpeningModel.setSubdepotid(res.getString(res.getColumnIndex(SUBDEPOT_ID)));
            creditOpeningModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            creditOpeningModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            creditOpeningModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
            creditOpeningModel.setOpening(res.getFloat(res.getColumnIndex(OPENING)));
            creditOpeningModel.setSaleAmt(res.getFloat(res.getColumnIndex(SALE_AMT)));
            creditOpeningModel.setReceiveAmt(res.getFloat(res.getColumnIndex(RECEIVE_AMT)));
            creditOpeningModel.setDiscountAmt(res.getFloat(res.getColumnIndex(DISCOUNT_AMT)));
            creditOpeningModel.setBalance(res.getFloat(res.getColumnIndex(BALANCE)));
            creditOpeningModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
            creditOpeningModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
            creditOpeningModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));
            creditOpeningModel.setChequeAmt(res.getFloat(res.getColumnIndex(CHEQUEAMT)));
        }
        res.close();
        db.close();
        return creditOpeningModel;
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

    public ArrayList<CreditOpeningModel> getAllCreditOpening() {
        ArrayList<CreditOpeningModel> array_list = new ArrayList<CreditOpeningModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                CreditOpeningModel creditOpeningModel = new CreditOpeningModel();
                creditOpeningModel.setRceId(res.getLong(res.getColumnIndex(RCE_ID)));
                creditOpeningModel.setSubcompanyId(res.getString(res.getColumnIndex(SUBCOMPANY_ID)));
                creditOpeningModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                //creditOpeningModel.setSubdepotid(res.getString(res.getColumnIndex(SUBDEPOT_ID)));
                creditOpeningModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                creditOpeningModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                creditOpeningModel.setDDate(res.getString(res.getColumnIndex(DDATE)));
                creditOpeningModel.setOpening(res.getFloat(res.getColumnIndex(OPENING)));
                creditOpeningModel.setSaleAmt(res.getFloat(res.getColumnIndex(SALE_AMT)));
                creditOpeningModel.setReceiveAmt(res.getFloat(res.getColumnIndex(RECEIVE_AMT)));
                creditOpeningModel.setDiscountAmt(res.getFloat(res.getColumnIndex(DISCOUNT_AMT)));
                creditOpeningModel.setBalance(res.getFloat(res.getColumnIndex(BALANCE)));
                creditOpeningModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
                creditOpeningModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
                creditOpeningModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));
                creditOpeningModel.setChequeAmt(res.getFloat(res.getColumnIndex(CHEQUEAMT)));

                array_list.add(creditOpeningModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }


    // customer credit balance
    // get customer invoice total
    public double custCreditAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select " + OPENING + " from " + TABLE_NAME + " where " + CUSTOMER_ID + "=?";
        Cursor res = db.rawQuery(query, new String[]{customer_id});

        double amount = 0;
        if (res.moveToFirst()) {
            amount = res.getInt(res.getColumnIndex(OPENING));
        }
        res.close();
        db.close();
        return amount;
    }
}