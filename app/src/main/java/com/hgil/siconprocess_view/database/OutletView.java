package com.hgil.siconprocess_view.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletModel;
import com.hgil.siconprocess_view.utils.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "Sicon_Customer_db";
    private static final String TABLE_NAME = "V_SD_Customer_Route_Mapping";

    // private static final String SUB_COMPANY_ID = "Sub_Company_id";
    //private static final String DEPOT = "Depot";
    private static final String ROUTE_ID = "Route_Id";
    //private static final String ROUTE_NAME = "Route_Name";
    //private static final String PSMID = "PSMID";
    private static final String CUSTOMER_ID = "Customer_Id";
    private static final String CUSTOMER_NAME = "Customer_Name";
    //private static final String PRICEGROUP = "PRICEGROUP";
    //private static final String LINEDISC = "LINEDISC";
    //private static final String C_TYPE = "C_Type";
    private static final String SALE_STATUS = "sale_status";
    //private static final String ACCOUNTNUM = "ACCOUNTNUM";
    private static final String CONTACT_NO = "ContactNo";
    private static final String OUTSTANDING = "outstanding";
    private static final String INV_AMOUNT = "inv_amount";
    private static final String NET_AMOUNT = "net_amount";
    private static final String CASH_PAYMENT = "cash_payment";
    private static final String INV_TIME = "inv_time";

    private Context mContext;

    public OutletView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" //+
                //SUB_COMPANY_ID + " TEXT NULL, "
                // + DEPOT + " TEXT NULL, "
                + ROUTE_ID + " TEXT NULL, "
                //+ ROUTE_NAME + " TEXT NULL, "
                //+ PSMID + " TEXT NULL, "
                + CUSTOMER_ID + " TEXT NULL, " + CUSTOMER_NAME + " TEXT NULL, "
                // + PRICEGROUP + " TEXT NULL, " + LINEDISC + " TEXT NULL, " + C_TYPE + " TEXT NULL, "
                + SALE_STATUS + " TEXT NULL, "
                //+ ACCOUNTNUM + " TEXT NULL, "
                + CONTACT_NO + " TEXT NULL, "
                + OUTSTANDING + " REAL NULL, " + INV_AMOUNT + " REAL NULL, " + NET_AMOUNT + " REAL NULL, "
                + CASH_PAYMENT + " REAL NULL, " + INV_TIME + " TEXT NULL)");
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
        //contentValues.put(SUB_COMPANY_ID, outletModel.getSubCompanyId());
        //contentValues.put(DEPOT, outletModel.getDepot());
        contentValues.put(ROUTE_ID, outletModel.getRouteId());
        //contentValues.put(ROUTE_NAME, outletModel.getRouteName());
        //contentValues.put(PSMID, outletModel.getPSMID());
        contentValues.put(CUSTOMER_ID, outletModel.getCustomerId());
        contentValues.put(CUSTOMER_NAME, outletModel.getCustomerName());
        //contentValues.put(PRICEGROUP, outletModel.getPRICEGROUP());
        //contentValues.put(LINEDISC, outletModel.getLINEDISC());
        //contentValues.put(C_TYPE, outletModel.getCType());

        if ((outletModel.getSale_status()).matches("SMS_SENT"))
            contentValues.put(SALE_STATUS, "Completed");
        else
            contentValues.put(SALE_STATUS, "Pending");

        // contentValues.put(ACCOUNTNUM, outletModel.getACCOUNTNUM());
        contentValues.put(CONTACT_NO, outletModel.getContactNo());
        contentValues.put(OUTSTANDING, outletModel.getOutstanding());
        contentValues.put(INV_AMOUNT, outletModel.getInv_amount());
        contentValues.put(NET_AMOUNT, outletModel.getNet_amount());
        contentValues.put(CASH_PAYMENT, outletModel.getCash_payment());
        contentValues.put(INV_TIME, outletModel.getInv_time());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple outlets
    public boolean insertOutlet(List<OutletModel> arrOutlets) {
        SQLiteDatabase db = this.getWritableDatabase();

       /* for (int i = 0; i < arrOutlets.size(); i++) {
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

            if ((outletModel.getSale_status()).matches("SMS_SENT"))
                contentValues.put(SALE_STATUS, "Completed");
            else
                contentValues.put(SALE_STATUS, "Pending");

            contentValues.put(ACCOUNTNUM, outletModel.getACCOUNTNUM());
            contentValues.put(CONTACT_NO, outletModel.getContactNo());
            contentValues.put(OUTSTANDING, outletModel.getOutstanding());
            contentValues.put(INV_AMOUNT, outletModel.getInv_amount());
            contentValues.put(NET_AMOUNT, outletModel.getNet_amount());
            contentValues.put(CASH_PAYMENT, outletModel.getCash_payment());
            contentValues.put(INV_TIME, outletModel.getInv_time());
            db.insert(TABLE_NAME, null, contentValues);
        }*/

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        //final int subCompanyIdColumn = ih.getColumnIndex(SUB_COMPANY_ID);
        //final int depotColumn = ih.getColumnIndex(DEPOT);
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        // final int routeNameColumn = ih.getColumnIndex(ROUTE_NAME);
        //final int psmIdColumn = ih.getColumnIndex(PSMID);
        final int customerIdColumn = ih.getColumnIndex(CUSTOMER_ID);
        final int customerNameColumn = ih.getColumnIndex(CUSTOMER_NAME);
        //final int priceGroupColumn = ih.getColumnIndex(PRICEGROUP);
        //final int lineDiscColumn = ih.getColumnIndex(LINEDISC);
        //final int cTypeColumn = ih.getColumnIndex(C_TYPE);
        final int saleStatusColumn = ih.getColumnIndex(SALE_STATUS);
        //final int accountNumColumn = ih.getColumnIndex(ACCOUNTNUM);
        final int contactNoColumn = ih.getColumnIndex(CONTACT_NO);
        final int outstandingColumn = ih.getColumnIndex(OUTSTANDING);
        final int invAmountColumn = ih.getColumnIndex(INV_AMOUNT);
        final int netAmountColumn = ih.getColumnIndex(NET_AMOUNT);
        final int cashPaymentColumn = ih.getColumnIndex(CASH_PAYMENT);
        final int invTimeColumn = ih.getColumnIndex(INV_TIME);

        try {
            db.beginTransaction();
            for (OutletModel outletModel : arrOutlets) {
                ih.prepareForInsert();

                //ih.bind(subCompanyIdColumn, outletModel.getSubCompanyId());
                //ih.bind(depotColumn, outletModel.getDepot());
                ih.bind(routeIdColumn, outletModel.getRouteId());
                //ih.bind(routeNameColumn, outletModel.getRouteName());
                //ih.bind(psmIdColumn, outletModel.getPSMID());
                ih.bind(customerIdColumn, outletModel.getCustomerId());
                ih.bind(customerNameColumn, outletModel.getCustomerName());
                //ih.bind(priceGroupColumn, outletModel.getPRICEGROUP());
                //ih.bind(lineDiscColumn, outletModel.getLINEDISC());
                //ih.bind(cTypeColumn, outletModel.getCType());
                if ((outletModel.getSale_status()).matches("SMS_SENT"))
                    ih.bind(saleStatusColumn, "Completed");
                else
                    ih.bind(saleStatusColumn, "Pending");
                //ih.bind(accountNumColumn, outletModel.getACCOUNTNUM());
                ih.bind(contactNoColumn, outletModel.getContactNo());
                ih.bind(outstandingColumn, outletModel.getOutstanding());
                ih.bind(invAmountColumn, outletModel.getInv_amount());
                ih.bind(netAmountColumn, outletModel.getNet_amount());
                ih.bind(cashPaymentColumn, outletModel.getCash_payment());
                ih.bind(invTimeColumn, outletModel.getInv_time());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
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
                //outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                //outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
                outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                //outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                //outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                //outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                //outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
                //outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                outletModel.setOutstanding(res.getDouble(res.getColumnIndex(OUTSTANDING)));
                outletModel.setInv_amount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                outletModel.setNet_amount(res.getDouble(res.getColumnIndex(NET_AMOUNT)));
                outletModel.setCash_payment(res.getDouble(res.getColumnIndex(CASH_PAYMENT)));
                outletModel.setInv_time(res.getString(res.getColumnIndex(INV_TIME)));
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
        RouteView routeView = new RouteView(mContext);

        OutletModel outletModel = new OutletModel();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + CUSTOMER_ID + "=?", new String[]{customer_id});
        if (res.moveToFirst()) {
            //outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
            //outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
            outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            outletModel.setRouteName(routeView.getRouteName(outletModel.getRouteId()));
            //outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            //outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
            outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
            //outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
            //outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
            //outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
            outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
            //outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
            outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
            outletModel.setOutstanding(res.getDouble(res.getColumnIndex(OUTSTANDING)));
            outletModel.setInv_amount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
            outletModel.setNet_amount(res.getDouble(res.getColumnIndex(NET_AMOUNT)));
            outletModel.setCash_payment(res.getDouble(res.getColumnIndex(CASH_PAYMENT)));
            outletModel.setInv_time(res.getString(res.getColumnIndex(INV_TIME)));
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
                //outletModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                //outletModel.setDepot(res.getString(res.getColumnIndex(DEPOT)));
                outletModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //outletModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                //outletModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                outletModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                outletModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                //outletModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                //outletModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                //outletModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                outletModel.setSale_status(res.getString(res.getColumnIndex(SALE_STATUS)));
                //outletModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                outletModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                outletModel.setOutstanding(res.getDouble(res.getColumnIndex(OUTSTANDING)));
                outletModel.setInv_amount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                outletModel.setNet_amount(res.getDouble(res.getColumnIndex(NET_AMOUNT)));
                outletModel.setCash_payment(res.getDouble(res.getColumnIndex(CASH_PAYMENT)));
                outletModel.setInv_time(res.getString(res.getColumnIndex(INV_TIME)));
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
        TodaySaleView todaySaleView = new TodaySaleView(mContext);

        int van_sku_count = new VanStockView(mContext).routeItemLoadCount(route_id);

        String date1 = "00:00", date2 = "00:00";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? ORDER BY " + INV_TIME, new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                routeCustomerModel.setSaleAmount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                routeCustomerModel.setSale_time(res.getString(res.getColumnIndex(INV_TIME)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));

                routeCustomerModel.setVan_total_sku(van_sku_count);
                routeCustomerModel.setOutlet_purchased_sku(todaySaleView.routeOutletItemSaleCount(
                        routeCustomerModel.getRouteId(), routeCustomerModel.getCustomerId()));

                /* calculate time difference between users*/
                String time_diff = "00:00";
                date1 = date2;
                date2 = routeCustomerModel.getSale_time();
                if (date2 != null && !date2.matches("00:00") && !date1.matches("00:00")) {
                    time_diff = Utility.timeVariance(date1, date2);
                }
                routeCustomerModel.setTime_diff(time_diff);

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

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? and " + SALE_STATUS + "=?",
                new String[]{route_id, "Pending"});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));
                routeCustomerModel.setSaleAmount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));
                array_list.add(routeCustomerModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<RouteCustomerModel> getRouteCompletedCustomers(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();
        TodaySaleView todaySaleView = new TodaySaleView(mContext);

        int van_sku_count = new VanStockView(mContext).routeItemLoadCount(route_id);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? AND " + SALE_STATUS + "=? ORDER BY " + INV_TIME,
                new String[]{route_id, "Completed"});

        String date1 = "00:00", date2 = "00:00";
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                routeCustomerModel.setSaleAmount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                routeCustomerModel.setSale_time(res.getString(res.getColumnIndex(INV_TIME)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));

                routeCustomerModel.setVan_total_sku(van_sku_count);
                routeCustomerModel.setOutlet_purchased_sku(todaySaleView.routeOutletItemSaleCount(routeCustomerModel.getRouteId(), routeCustomerModel.getCustomerId()));

                String time_diff = "00:00";
                date1 = date2;
                date2 = res.getString(res.getColumnIndex(INV_TIME));
                if (date2 != null && !date2.matches("00:00") && !date1.matches("00:00")) {
                    time_diff = Utility.timeVariance(date1, date2);
                }
                routeCustomerModel.setTime_diff(time_diff);

                array_list.add(routeCustomerModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        /*sort outlet by sale time*/
       /* ArrayList<RouteCustomerModel> sortedArrayList = new ArrayList<RouteCustomerModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<RouteCustomerModel>() {
            public int compare(RouteCustomerModel p1, RouteCustomerModel p2) {
                return Integer.valueOf(p1.getSale_time()).compareTo(p2.getSale_time());
            }
        });*/

        return array_list;
    }

    /*customer with sale less than 100*/
    public ArrayList<RouteCustomerModel> getCustomerSaleLT100(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                double sale_amt = res.getDouble(res.getColumnIndex(INV_AMOUNT));
                routeCustomerModel.setCustStatus("");
                routeCustomerModel.setSaleAmount(sale_amt);
                routeCustomerModel.setSale_time(res.getString(res.getColumnIndex(INV_TIME)));
                if (sale_amt < 100)
                    array_list.add(routeCustomerModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*completed route time*/
    public ArrayList<RouteCustomerModel> getSaleTimeCompletedOutlets(String route_id) {
        ArrayList<RouteCustomerModel> array_list = new ArrayList<RouteCustomerModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        String date1 = "00:00", date2 = "00:00";
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? ORDER BY " + INV_TIME, new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteCustomerModel routeCustomerModel = new RouteCustomerModel();
                routeCustomerModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                //routeCustomerModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeCustomerModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                routeCustomerModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                routeCustomerModel.setSaleAmount(res.getDouble(res.getColumnIndex(INV_AMOUNT)));
                routeCustomerModel.setSale_time(res.getString(res.getColumnIndex(INV_TIME)));
                routeCustomerModel.setCash_received(res.getDouble(res.getColumnIndex(CASH_PAYMENT)));
                routeCustomerModel.setCustStatus(res.getString(res.getColumnIndex(SALE_STATUS)));

                String time_diff = "00:00";
                date1 = date2;
                date2 = res.getString(res.getColumnIndex(INV_TIME));
                if (date2 != null && !date2.matches("00:00") && !date1.matches("00:00")) {
                    time_diff = Utility.timeVariance(date1, date2);
                    routeCustomerModel.setTime_diff(time_diff);
                }

                if (res.getDouble(res.getColumnIndex(INV_AMOUNT)) > 0)
                    array_list.add(routeCustomerModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get route item variance sale*/
    /*public ArrayList<SkuSaleDetailModel> getRouteCustomersItemVariance(String
                                                                                        route_id) {
        ArrayList<SkuSaleDetailModel> array_list = new ArrayList<SkuSaleDetailModel>();
        TodaySaleView todaySaleView = new TodaySaleView(mContext);

        int route_items_loaded = new VanStockView(mContext).routeItemLoadCount(route_id);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SkuSaleDetailModel itemSaleVarianceModel = new SkuSaleDetailModel();
                itemSaleVarianceModel.setOutlet_name(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                itemSaleVarianceModel.setItem_loading(route_items_loaded);
                String outlet_id = res.getString(res.getColumnIndex(CUSTOMER_ID));
                itemSaleVarianceModel.setItem_buy(todaySaleView.routeOutletItemSaleCount(route_id, outlet_id));
                array_list.add(itemSaleVarianceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }*/

    /*route sale value*/

    public double routeTotalSale(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + INV_AMOUNT + ") AS " + INV_AMOUNT + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});

        double sale_amt = 0.00;
        if (res.moveToFirst()) {
            sale_amt = res.getDouble(res.getColumnIndex(INV_AMOUNT));
        }
        res.close();
        db.close();
        return sale_amt;
    }

    /*route outstanding*/
    public double routeOutstanding(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + OUTSTANDING + ") AS " + OUTSTANDING + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        double sale_amt = 0.00;
        if (res.moveToFirst()) {
            sale_amt = (res.getDouble(res.getColumnIndex(OUTSTANDING)));
        }
        res.close();
        db.close();
        return sale_amt;
    }

    /*route cash collection value*/
    public double routeCashCollection(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + CASH_PAYMENT + ") AS " + CASH_PAYMENT + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});

        double cash_payment = 0.00;
        if (res.moveToFirst()) {
            cash_payment = res.getDouble(res.getColumnIndex(CASH_PAYMENT));
        }
        res.close();
        db.close();
        return cash_payment;
    }

    /*route net sale value*/
    public double routeNetSale(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + NET_AMOUNT + ") AS " + NET_AMOUNT + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});

        double cash_payment = 0.00;
        if (res.moveToFirst()) {
            cash_payment = res.getDouble(res.getColumnIndex(NET_AMOUNT));
        }
        res.close();
        db.close();
        return cash_payment;
    }

    /*target calls*/
    public int routeTargetCalls(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME, ROUTE_ID + "=?", new String[]{route_id});
        db.close();
        return numRows;
    }

   /* public int routeProductiveCalls(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? AND " + SALE_STATUS + "=?", new String[]{route_id, "Completed"});
        int count = res.getCount();
        res.close();
        db.close();
        return count;
    }*/

    //productive call reference changed
    public int routeProductiveCalls(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? AND CAST(" + INV_TIME + " as time)>0",
                new String[]{route_id});
        int count = res.getCount();
        res.close();
        db.close();
        return count;
    }

    public int routeTotalCustomersCount(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT count(" + CUSTOMER_ID + ") AS customers FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "=?",
                new String[]{route_id});

        int item_count = 0;
        if (res.moveToFirst()) {
            item_count = res.getInt(res.getColumnIndex("customers"));
        }
        res.close();
        db.close();
        return item_count;
    }
}