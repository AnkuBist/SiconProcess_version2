package com.hgil.siconprocess_view.database.localDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;
import com.hgil.siconprocess_view.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 15-04-2017.
 */

public class OutletRemarkTable extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Sicon_Outlet_Remark_db";
    private static final String TABLE_NAME = "Outlet_Remark_Table";

    private static final String USER_ID = "User_id";
    private static final String ROUTE_ID = "Route_id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String OUTLET_ID = "Outlet_id";
    private static final String OUTLET_NAME = "Outlet_Name";
    private static final String REMARK = "outlet_remark";
    private static final String REMARK_DATE = "remark_date";

    private Context mContext;

    public OutletRemarkTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + USER_ID + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, "
                + ROUTE_NAME + " TEXT NULL, " + OUTLET_ID + " TEXT NULL, " + OUTLET_NAME + " TEXT NULL, "
                + REMARK + " TEXT NULL, " + REMARK_DATE + " TEXT NULL)");
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
    public boolean insertOutletRemark(OutletRemarkModel outletRemarkModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, outletRemarkModel.getUser_id());
        contentValues.put(ROUTE_ID, outletRemarkModel.getRoute_id());
        contentValues.put(ROUTE_NAME, outletRemarkModel.getRoute_name());
        contentValues.put(OUTLET_ID, outletRemarkModel.getOutlet_id());
        contentValues.put(OUTLET_NAME, outletRemarkModel.getOutlet_name());
        contentValues.put(REMARK, outletRemarkModel.getRemark());
        contentValues.put(REMARK_DATE, outletRemarkModel.getRemark_date());
        if (hasObject(db, outletRemarkModel.getUser_id(), outletRemarkModel.getRoute_id(), outletRemarkModel.getOutlet_id(), outletRemarkModel.getRemark_date()))
            db.update(TABLE_NAME, contentValues, USER_ID + "=? AND " + ROUTE_ID + "=? AND " + OUTLET_ID + "=? AND " + REMARK_DATE + "=?",
                    new String[]{outletRemarkModel.getUser_id(), outletRemarkModel.getRoute_id(), outletRemarkModel.getOutlet_id(), outletRemarkModel.getRemark_date()});
        else
            db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertOutletRemark(List<OutletRemarkModel> arrOutletRemark) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int userIdColumn = ih.getColumnIndex(USER_ID);
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int routeNameColumn = ih.getColumnIndex(ROUTE_NAME);
        final int outletIdColumn = ih.getColumnIndex(OUTLET_ID);
        final int outletNameColumn = ih.getColumnIndex(OUTLET_NAME);
        final int remarkColumn = ih.getColumnIndex(REMARK);
        final int remarkDateColumn = ih.getColumnIndex(REMARK_DATE);

        try {
            db.beginTransaction();
            for (OutletRemarkModel outletRemarkModel : arrOutletRemark) {
                ih.prepareForInsert();

                ih.bind(userIdColumn, outletRemarkModel.getUser_id());
                ih.bind(routeIdColumn, outletRemarkModel.getRoute_id());
                ih.bind(routeNameColumn, outletRemarkModel.getRoute_name());
                ih.bind(outletIdColumn, outletRemarkModel.getOutlet_id());
                ih.bind(outletNameColumn, outletRemarkModel.getOutlet_name());
                ih.bind(remarkColumn, outletRemarkModel.getRemark());
                ih.bind(remarkDateColumn, outletRemarkModel.getRemark_date());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        db.close();
        return true;
    }

    // check if the record exists or not
    public boolean hasObject(SQLiteDatabase db, String user_id, String route_id, String outlet_id, String remark_date) {
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " +
                ROUTE_ID + "=? AND " + OUTLET_ID + "=? AND " + REMARK_DATE + "=?";

        Cursor cursor = db.rawQuery(selectString, new String[]{user_id, route_id, outlet_id, remark_date});
        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }

        cursor.close();          // Don't forget to close your cursor
        return hasObject;
    }

    /*check has route*/
    // check if the record exists or not
    public boolean hasRoute(String route_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " +
                ROUTE_ID + "=?";
        Cursor cursor = db.rawQuery(selectString, new String[]{route_id});

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }
        cursor.close();          // Don't forget to close your cursor
        db.close();
        return hasObject;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    // get all customers
    public ArrayList<OutletRemarkModel> getAllRemark() {
        ArrayList<OutletRemarkModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletRemarkModel outletRemarkModel = new OutletRemarkModel();
                outletRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
                outletRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                outletRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                outletRemarkModel.setOutlet_id(res.getString(res.getColumnIndex(OUTLET_ID)));
                outletRemarkModel.setOutlet_name(res.getString(res.getColumnIndex(OUTLET_NAME)));
                outletRemarkModel.setRemark(res.getString(res.getColumnIndex(REMARK)));
                outletRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
                array_list.add(outletRemarkModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get remark by route*/
    public ArrayList<OutletRemarkModel> getRouteRemarks(String user_id, String route_id) {
        ArrayList<OutletRemarkModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " + ROUTE_ID + "=?",
                new String[]{user_id, route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletRemarkModel outletRemarkModel = new OutletRemarkModel();
                outletRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
                outletRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                outletRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                outletRemarkModel.setOutlet_id(res.getString(res.getColumnIndex(OUTLET_ID)));
                outletRemarkModel.setOutlet_name(res.getString(res.getColumnIndex(OUTLET_NAME)));
                outletRemarkModel.setRemark(res.getString(res.getColumnIndex(REMARK)));
                outletRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
                array_list.add(outletRemarkModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get remark by route and outlet*/
    public OutletRemarkModel getOutletRemark(String user_id, String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        OutletRemarkModel outletRemarkModel = new OutletRemarkModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND "
                        + ROUTE_ID + "=? AND " + OUTLET_ID + "=? AND " + REMARK_DATE + "=?",
                new String[]{user_id, route_id, outlet_id, Utility.getCurDate()});
        if (res.moveToFirst()) {
            outletRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
            outletRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
            outletRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
            outletRemarkModel.setOutlet_id(res.getString(res.getColumnIndex(OUTLET_ID)));
            outletRemarkModel.setOutlet_name(res.getString(res.getColumnIndex(OUTLET_NAME)));
            outletRemarkModel.setRemark(res.getString(res.getColumnIndex(REMARK)));
            outletRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
        }
        res.close();
        db.close();
        return outletRemarkModel;
    }
}