package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.FixedSampleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class FixedSampleTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_fixed_sample";
    private static final String TABLE_NAME = "SD_FixedSample_Master";
    private static final String ID = "ID";
    private static final String STARTDT = "StartDt";
    private static final String DDAY = "DDay";
    private static final String DEPOTID = "DepotID";
    private static final String ROUTE = "Route";
    private static final String CUSTOMER_ID = "Customer_id";
    private static final String ITEM_ID = "Item_id";
    private static final String SQTY = "SQty";
    private static final String ENDDT = "EndDt";
    private static final String UPDATEBY_PAYCODE = "updateby_paycode";
    private static final String UPDATEBY_DATE = "updateby_Date";
    private static final String UPDATED_IP = "updated_ip";

    public FixedSampleTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ID + " INTEGER NOT NULL, "
                + STARTDT + " TEXT NOT NULL, " + DDAY + " TEXT NOT NULL, " + DEPOTID + " TEXT NOT NULL, "
                + ROUTE + " TEXT NOT NULL, " + CUSTOMER_ID + " TEXT NOT NULL, " + ITEM_ID + " TEXT NOT NULL, "
                + SQTY + " INTEGER NOT NULL, " + ENDDT + " TEXT NULL, " + UPDATEBY_PAYCODE + " TEXT NOT NULL, "
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
    public boolean insertFixedSample(FixedSampleModel fixedSampleModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, fixedSampleModel.getID());
        contentValues.put(STARTDT, fixedSampleModel.getStartDt());
        contentValues.put(DDAY, fixedSampleModel.getDDay());
        contentValues.put(DEPOTID, fixedSampleModel.getDepotID());
        contentValues.put(ROUTE, fixedSampleModel.getRoute());
        contentValues.put(CUSTOMER_ID, fixedSampleModel.getCustomerId());
        contentValues.put(ITEM_ID, fixedSampleModel.getItemId());
        contentValues.put(SQTY, fixedSampleModel.getSQty());
        //contentValues.put(ENDDT, fixedSampleModel.getEndDt());
        contentValues.put(UPDATEBY_PAYCODE, fixedSampleModel.getUpdatebyPaycode());
        contentValues.put(UPDATEBY_DATE, fixedSampleModel.getUpdatebyDate());
        contentValues.put(UPDATED_IP, fixedSampleModel.getUpdatedIp());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertFixedSample(List<FixedSampleModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < arrList.size(); i++) {
            FixedSampleModel fixedSampleModel = arrList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ID, fixedSampleModel.getID());
            contentValues.put(STARTDT, fixedSampleModel.getStartDt());
            contentValues.put(DDAY, fixedSampleModel.getDDay());
            contentValues.put(DEPOTID, fixedSampleModel.getDepotID());
            contentValues.put(ROUTE, fixedSampleModel.getRoute());
            contentValues.put(CUSTOMER_ID, fixedSampleModel.getCustomerId());
            contentValues.put(ITEM_ID, fixedSampleModel.getItemId());
            contentValues.put(SQTY, fixedSampleModel.getSQty());
            //contentValues.put(ENDDT, fixedSampleModel.getEndDt());
            contentValues.put(UPDATEBY_PAYCODE, fixedSampleModel.getUpdatebyPaycode());
            contentValues.put(UPDATEBY_DATE, fixedSampleModel.getUpdatebyDate());
            contentValues.put(UPDATED_IP, fixedSampleModel.getUpdatedIp());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public FixedSampleModel getFixedSampleItem(String item_id, String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "=? and " + CUSTOMER_ID + "=?", new String[]{item_id, customer_id});

        FixedSampleModel fixedSampleModel = new FixedSampleModel();
        if (res.moveToFirst()) {
            fixedSampleModel.setID(res.getInt(res.getColumnIndex(ID)));
            fixedSampleModel.setStartDt(res.getString(res.getColumnIndex(STARTDT)));
            fixedSampleModel.setDDay(res.getString(res.getColumnIndex(DDAY)));
            fixedSampleModel.setDepotID(res.getString(res.getColumnIndex(DEPOTID)));
            fixedSampleModel.setRoute(res.getString(res.getColumnIndex(ROUTE)));
            fixedSampleModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            fixedSampleModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            fixedSampleModel.setSQty(res.getInt(res.getColumnIndex(SQTY)));
            //fixedSampleModel.setEndDt(res.getString(res.getColumnIndex(ENDDT)));
            fixedSampleModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
            fixedSampleModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
            fixedSampleModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));
        }
        res.close();
        db.close();
        return fixedSampleModel;
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

    public ArrayList<FixedSampleModel> getAllFixedSample() {
        ArrayList<FixedSampleModel> array_list = new ArrayList<FixedSampleModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                FixedSampleModel fixedSampleModel = new FixedSampleModel();
                fixedSampleModel.setID(res.getInt(res.getColumnIndex(ID)));
                fixedSampleModel.setStartDt(res.getString(res.getColumnIndex(STARTDT)));
                fixedSampleModel.setDDay(res.getString(res.getColumnIndex(DDAY)));
                fixedSampleModel.setDepotID(res.getString(res.getColumnIndex(DEPOTID)));
                fixedSampleModel.setRoute(res.getString(res.getColumnIndex(ROUTE)));
                fixedSampleModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                fixedSampleModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                fixedSampleModel.setSQty(res.getInt(res.getColumnIndex(SQTY)));
                //fixedSampleModel.setEndDt(res.getString(res.getColumnIndex(ENDDT)));
                fixedSampleModel.setUpdatebyPaycode(res.getString(res.getColumnIndex(UPDATEBY_PAYCODE)));
                fixedSampleModel.setUpdatebyDate(res.getString(res.getColumnIndex(UPDATEBY_DATE)));
                fixedSampleModel.setUpdatedIp(res.getString(res.getColumnIndex(UPDATED_IP)));

                array_list.add(fixedSampleModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}