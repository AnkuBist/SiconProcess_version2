package com.hgil.siconprocess_view.database.localDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;
import com.hgil.siconprocess_view.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 15-04-2017.
 */

public class RouteRemarkTable extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Sicon_Route_Remark_db";
    private static final String TABLE_NAME = "Route_Remark_Table";

    private static final String USER_ID = "User_id";
    private static final String ROUTE_ID = "Route_id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String ROUTE_REMARK = "route_remark";
    private static final String REMARK_DATE = "remark_date";

    private Context mContext;

    public RouteRemarkTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + USER_ID + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, "
                + ROUTE_NAME + " TEXT NULL, " + ROUTE_REMARK + " TEXT NULL, " + REMARK_DATE + " TEXT NULL)");
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
    public boolean insertRouteRemark(RouteRemarkModel routeRemarkModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, routeRemarkModel.getUser_id());
        contentValues.put(ROUTE_ID, routeRemarkModel.getRoute_id());
        contentValues.put(ROUTE_NAME, routeRemarkModel.getRoute_name());
        contentValues.put(ROUTE_REMARK, routeRemarkModel.getRoute_remark());
        contentValues.put(REMARK_DATE, routeRemarkModel.getRemark_date());
        if (hasObject(db, routeRemarkModel.getUser_id(), routeRemarkModel.getRoute_id(), routeRemarkModel.getRemark_date()))
            db.update(TABLE_NAME, contentValues, USER_ID + "=? AND " + ROUTE_ID + "=? AND " + REMARK_DATE + "=?",
                    new String[]{routeRemarkModel.getUser_id(), routeRemarkModel.getRoute_id(), routeRemarkModel.getRemark_date()});
        else
            db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertRouteRemark(List<RouteRemarkModel> arrRouteRemark) {
        SQLiteDatabase db = this.getWritableDatabase();

     /*   for (int i = 0; i < arrRouteRemark.size(); i++) {
            RouteRemarkModel routeRemarkModel = arrRouteRemark.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_ID, routeRemarkModel.getUser_id());
            contentValues.put(ROUTE_ID, routeRemarkModel.getRoute_id());
            contentValues.put(ROUTE_NAME, routeRemarkModel.getRoute_name());
            contentValues.put(OUTLET_ID, routeRemarkModel.getOutlet_id());
            contentValues.put(OUTLET_NAME, routeRemarkModel.getOutlet_name());
            contentValues.put(REMARK, routeRemarkModel.getRemark());
            contentValues.put(REMARK_DATE, Utility.getCurDate());
           *//* if (hasObject(db, routeRemarkModel.getUser_id(), routeRemarkModel.getRoute_id(), routeRemarkModel.getOutlet_id(), routeRemarkModel.getRemark_date()))
                db.update(TABLE_NAME, contentValues, USER_ID + "=? AND " + ROUTE_ID + "=? AND " + OUTLET_ID + "=? AND " + REMARK_DATE + "=?",
                        new String[]{routeRemarkModel.getUser_id(), routeRemarkModel.getRoute_id(), routeRemarkModel.getOutlet_id(), routeRemarkModel.getRemark_date()});
            else*//*
                db.insert(TABLE_NAME, null, contentValues);
        }*/

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int userIdColumn = ih.getColumnIndex(USER_ID);
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int routeNameColumn = ih.getColumnIndex(ROUTE_NAME);
        final int routeRemarkColumn = ih.getColumnIndex(ROUTE_REMARK);
        final int remarkDateColumn = ih.getColumnIndex(REMARK_DATE);

        try {
            db.beginTransaction();
            for (RouteRemarkModel routeRemarkModel : arrRouteRemark) {
                ih.prepareForInsert();

                ih.bind(userIdColumn, routeRemarkModel.getUser_id());
                ih.bind(routeIdColumn, routeRemarkModel.getRoute_id());
                ih.bind(routeNameColumn, routeRemarkModel.getRoute_name());
                ih.bind(routeRemarkColumn, routeRemarkModel.getRoute_remark());
                ih.bind(remarkDateColumn, routeRemarkModel.getRemark_date());

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
    public boolean hasObject(SQLiteDatabase db, String user_id, String route_id, String remark_date) {
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " +
                ROUTE_ID + "=? AND " + REMARK_DATE + "=?";

        Cursor cursor = db.rawQuery(selectString, new String[]{user_id, route_id, remark_date});

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

        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " +//+ USER_ID + "=? AND " +
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

    // get all route remarks
    public ArrayList<RouteRemarkModel> getAllRouteRemark() {
        ArrayList<RouteRemarkModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteRemarkModel routeRemarkModel = new RouteRemarkModel();
                routeRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
                routeRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeRemarkModel.setRoute_remark(res.getString(res.getColumnIndex(ROUTE_REMARK)));
                routeRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
                array_list.add(routeRemarkModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get remark by route*/
    public ArrayList<RouteRemarkModel> getRouteRemarks(String user_id, String route_id) {
        ArrayList<RouteRemarkModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " + ROUTE_ID + "=?",
                new String[]{user_id, route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteRemarkModel routeRemarkModel = new RouteRemarkModel();
                routeRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
                routeRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeRemarkModel.setRoute_remark(res.getString(res.getColumnIndex(ROUTE_REMARK)));
                routeRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
                array_list.add(routeRemarkModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get remark by route and outlet*/
    public RouteRemarkModel getRouteRemark(String user_id, String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        RouteRemarkModel routeRemarkModel = new RouteRemarkModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND "
                        + ROUTE_ID + "=? AND " + REMARK_DATE + "=?",
                new String[]{user_id, route_id, Utility.getCurDate()});
        if (res.moveToFirst()) {
            routeRemarkModel.setUser_id(res.getString(res.getColumnIndex(USER_ID)));
            routeRemarkModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeRemarkModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
            routeRemarkModel.setRoute_remark(res.getString(res.getColumnIndex(ROUTE_REMARK)));
            routeRemarkModel.setRemark_date(res.getString(res.getColumnIndex(REMARK_DATE)));
        }
        res.close();
        db.close();
        return routeRemarkModel;
    }
}