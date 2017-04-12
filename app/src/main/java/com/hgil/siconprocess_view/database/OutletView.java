package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Customer_db";
    private static final String TABLE_NAME = "V_SD_Customer_Route_Mapping";

    private static final String SUB_COMPANY_ID = "Sub_Company_id";
    private static final String DEPOT = "Depot";
    private static final String ROUTE_ID = "Route_Id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String PSMID = "PSMID";
    private static final String CUSTOMER_ID = "Customer_Id";
    private static final String CUSTOMER_NAME = "Customer_Name";
    private static final String PRICEGROUP = "PRICEGROUP";
    private static final String LINEDISC = "LINEDISC";
    private static final String C_TYPE = "C_Type";
    private static final String SALE_STATUS = "sale_status";
    private static final String ACCOUNTNUM = "ACCOUNTNUM";
    private static final String CONTACT_NO = "ContactNo";

    private Context mContext;

    public OutletView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + SUB_COMPANY_ID + " TEXT NULL, "
                + DEPOT + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, " + ROUTE_NAME + " TEXT NULL, "
                + PSMID + " TEXT NULL, " + CUSTOMER_ID + " TEXT NULL, " + CUSTOMER_NAME + " TEXT NULL, "
                + PRICEGROUP + " TEXT NULL, " + LINEDISC + " TEXT NULL, " + C_TYPE + " TEXT NULL, "
                + SALE_STATUS + " TEXT NULL, "
                + ACCOUNTNUM + " TEXT NULL, " + CONTACT_NO + " TEXT NULL)");
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
    public boolean insertOutlet(OutletModel outletModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUB_COMPANY_ID, outletModel.getSubCompanyId());
        contentValues.put(DEPOT, outletModel.getDepot());
        contentValues.put(ROUTE_ID, outletModel.getRouteId());
        contentValues.put(ROUTE_NAME, outletModel.getRouteName());
        contentValues.put(PSMID, outletModel.getPSMID());
        contentValues.put(CUSTOMER_ID, outletModel.getCustomerId());
        contentValues.put(CUSTOMER_NAME, outletModel.getCustomerName());
        contentValues.put(PRICEGROUP, outletModel.getPRICEGROUP());
        contentValues.put(LINEDISC, outletModel.getLINEDISC());
        contentValues.put(C_TYPE, outletModel.getCType());
        contentValues.put(SALE_STATUS, outletModel.getSale_status());
        contentValues.put(ACCOUNTNUM, outletModel.getACCOUNTNUM());
        contentValues.put(CONTACT_NO, outletModel.getContactNo());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple outlets
    public boolean insertOutlet(List<OutletModel> arrOutlets) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrOutlets.size(); i++) {
            OutletModel outletModel = arrOutlets.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(SUB_COMPANY_ID, outletModel.getSubCompanyId());
            contentValues.put(DEPOT, outletModel.getDepot());
            contentValues.put(ROUTE_ID, outletModel.getRouteId());
            contentValues.put(ROUTE_NAME, outletModel.getRouteName());
            contentValues.put(PSMID, outletModel.getPSMID());
            contentValues.put(CUSTOMER_ID, outletModel.getCustomerId());
            contentValues.put(CUSTOMER_NAME, outletModel.getCustomerName());
            contentValues.put(PRICEGROUP, outletModel.getPRICEGROUP());
            contentValues.put(LINEDISC, outletModel.getLINEDISC());
            contentValues.put(C_TYPE, outletModel.getCType());
            contentValues.put(SALE_STATUS, outletModel.getSale_status());
            contentValues.put(ACCOUNTNUM, outletModel.getACCOUNTNUM());
            contentValues.put(CONTACT_NO, outletModel.getContactNo());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + CONTACT_NO + " FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "=?", new String[]{customer_id});

        String contact = "";
        if (res.moveToFirst()) {
            contact = res.getString(res.getColumnIndex(CONTACT_NO));
        }
        res.close();
        db.close();
        return contact;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    // get all customers
    public ArrayList<OutletModel> getAllCustomers() {
        ArrayList<OutletModel> array_list = new ArrayList<OutletModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletModel outletModel = new OutletModel();
                outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
                outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
                outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                array_list.add(outletModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    // get outlet by customer id
    public OutletModel getOutletInfo(String customer_id) {
        OutletModel outletModel = new OutletModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + CUSTOMER_ID + "=?", new String[]{customer_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
                outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
                outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return outletModel;
    }

    /*get outlets/customers linked to route_id*/
    public ArrayList<OutletModel> getOutletsByRoute(String route_id) {
        ArrayList<OutletModel> array_list = new ArrayList<OutletModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                OutletModel outletModel = new OutletModel();
                outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
                outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
                outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                array_list.add(outletModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<RouteCustomerModel> getRouteCustomers(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();
        VanStockView vanStockView = new VanStockView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                double sale_amt = vanStockView.outletSaleAmount(routeCustomerModel.getCustomerId());
                if (sale_amt > 0)
                    routeCustomerModel.setCustStatus("Completed");
                else
                    routeCustomerModel.setCustStatus("Pending");
                routeCustomerModel.setSaleAmount(sale_amt);
                array_list.add(routeCustomerModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }


    public ArrayList<RouteCustomerModel> getRoutePendingCustomers(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();
        VanStockView vanStockView = new VanStockView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));
                double sale_amt = vanStockView.outletSaleAmount(routeCustomerModel.getCustomerId());
                if (sale_amt > 0) {
                    routeCustomerModel.setCustStatus("Completed");
                    // do nothing--PENDING CASE
                } else {
                    routeCustomerModel.setCustStatus("Pending");
                    routeCustomerModel.setSaleAmount(sale_amt);
                    array_list.add(routeCustomerModel);
                }
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<RouteCustomerModel> getRouteCompletedCustomers(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();
        VanStockView vanStockView = new VanStockView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                double sale_amt = vanStockView.outletSaleAmount(routeCustomerModel.getCustomerId());
                if (sale_amt > 0) {
                    routeCustomerModel.setCustStatus("Completed");
                    routeCustomerModel.setSaleAmount(sale_amt);
                    array_list.add(routeCustomerModel);
                } else {
                    routeCustomerModel.setCustStatus("Pending");
                    //do nothing --COMPLETED CASE
                }
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}