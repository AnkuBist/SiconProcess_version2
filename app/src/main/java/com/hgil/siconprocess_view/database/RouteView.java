package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hgil.siconprocess_view.adapter.depotList.DepotModel;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class RouteView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;

    private static final String DATABASE_NAME = "Sicon_route";
    private static final String TABLE_NAME = "V_SD_Route_Master";

    private static final String DEPOT_ID = "Depot_id";
    private static final String ROUTE_ID = "Route_Id";
    private static final String ROUTE_NAME = "Route_Name";
    private static final String CASHIER_NAME = "Cashier_Name";
    private static final String PSMID = "PSMID";
    private static final String PSM_NAME = "PSM_Name";
    private static final String ROUTE_CLOSE_STATUS = "route_close_status";
    // private static final String CONTACT_NO = "Contact_no";

    private Context mContext;

    public RouteView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + DEPOT_ID + " TEXT NULL, "
                + ROUTE_ID + " TEXT NULL, " + ROUTE_NAME + " TEXT NULL, " + CASHIER_NAME + " TEXT NULL, "
                + PSMID + " TEXT NULL, " + PSM_NAME + " TEXT NULL, " + ROUTE_CLOSE_STATUS + " INTEGER NULL)");
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

    // insert multiple routes
    public boolean insertRoutes(List<RouteModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        final long startTime = System.currentTimeMillis();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int depotIdColumn = ih.getColumnIndex(DEPOT_ID);
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int routeNameColumn = ih.getColumnIndex(ROUTE_NAME);
        final int cashierNameColumn = ih.getColumnIndex(CASHIER_NAME);
        final int psmIdColumn = ih.getColumnIndex(PSMID);
        final int psmNameColumn = ih.getColumnIndex(PSM_NAME);
        final int routeCloseStatusColumn = ih.getColumnIndex(ROUTE_CLOSE_STATUS);

        try {
            db.beginTransaction();
            for (RouteModel routeModel : arrList) {
                ih.prepareForInsert();

                ih.bind(depotIdColumn, routeModel.getDepotId());
                ih.bind(routeIdColumn, routeModel.getRouteId());
                ih.bind(routeNameColumn, routeModel.getRouteName());
                ih.bind(cashierNameColumn, routeModel.getCashierName());
                ih.bind(psmIdColumn, routeModel.getPSMID());
                ih.bind(psmNameColumn, routeModel.getPSMName());
                ih.bind(routeCloseStatusColumn, routeModel.getRouteCloseStatus());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        final long endtime = System.currentTimeMillis();
        Log.i("Route Time: ", String.valueOf(endtime - startTime));


        db.close();

        return true;
    }

    public RouteModel geRouteInfoByRouteId(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ROUTE_ID + "='" + route_id + "'", null);

        RouteModel routeModel = new RouteModel();
        if (res.moveToFirst()) {
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            routeModel.setCashierName(res.getString(res.getColumnIndex(CASHIER_NAME)));
            routeModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
            routeModel.setPSMName(res.getString(res.getColumnIndex(PSM_NAME)));
            routeModel.setRouteCloseStatus(res.getInt(res.getColumnIndex(ROUTE_CLOSE_STATUS)));
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

    public RouteModel getRouteById(String route_id) {
        RouteModel routeModel = new RouteModel();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        if (res.moveToFirst()) {
            routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
            routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
            routeModel.setCashierName(res.getString(res.getColumnIndex(CASHIER_NAME)));
            routeModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
            routeModel.setPSMName(res.getString(res.getColumnIndex(PSM_NAME)));
            routeModel.setRouteCloseStatus(res.getInt(res.getColumnIndex(ROUTE_CLOSE_STATUS)));
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
                routeModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                routeModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRouteName(res.getString(res.getColumnIndex(ROUTE_NAME)));
                routeModel.setCashierName(res.getString(res.getColumnIndex(CASHIER_NAME)));
                routeModel.setPSMID(res.getString(res.getColumnIndex(PSMID)));
                routeModel.setPSMName(res.getString(res.getColumnIndex(PSM_NAME)));
                routeModel.setRouteCloseStatus(res.getInt(res.getColumnIndex(ROUTE_CLOSE_STATUS)));

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

        ArrayList<RouteListModel> sortedArrayList = new ArrayList<RouteListModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<RouteListModel>() {
            public int compare(RouteListModel p1, RouteListModel p2) {
                return String.valueOf(p1.getRoute_name()).compareTo(p2.getRoute_name());
            }
        });
        return sortedArrayList;
    }

    /*get routes for remarks*/
    public ArrayList<RouteListModel> getRemarkRouteList() {
        ArrayList<RouteListModel> array_list = new ArrayList<RouteListModel>();

        OutletRemarkTable remarkTable = new OutletRemarkTable(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteListModel routeModel = new RouteListModel();
                routeModel.setRoute_id(res.getString(res.getColumnIndex(ROUTE_ID)));
                routeModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));
                if (remarkTable.hasRoute(routeModel.getRoute_id()))
                    array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        ArrayList<RouteListModel> sortedArrayList = new ArrayList<RouteListModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<RouteListModel>() {
            public int compare(RouteListModel p1, RouteListModel p2) {
                return String.valueOf(p1.getRoute_name()).compareTo(p2.getRoute_name());
            }
        });
        return sortedArrayList;
    }

    /*GET DEPOT ROUTES*/
    public ArrayList<RouteListModel> getDepotRouteList(String depot_id) {
        ArrayList<RouteListModel> array_list = new ArrayList<RouteListModel>();
        OutletView outletView = new OutletView(mContext);
        TodaySaleView todaySaleView = new TodaySaleView(mContext);
        VanStockView vanStockView = new VanStockView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + DEPOT_ID + "=?", new String[]{depot_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                RouteListModel routeModel = new RouteListModel();
                String route_id = res.getString(res.getColumnIndex(ROUTE_ID));
                routeModel.setRoute_id(route_id);
                routeModel.setRoute_name(res.getString(res.getColumnIndex(ROUTE_NAME)));

                /*other route related information*/
                //route target calls
                int total_calls = outletView.routeTargetCalls(route_id);

                //route productive calls
                int productive_calls = outletView.routeProductiveCalls(route_id);

                //total route sale amount
                double route_total_sale = outletView.routeTotalSale(route_id);
                double route_rej_amount = todaySaleView.getRouteRejAmount(route_id);

                double rejPrct = 0.00;
                if (route_total_sale > 0)
                    rejPrct = (route_rej_amount / route_total_sale) * 100;

                int route_van_loading = vanStockView.routeTotalLoading(route_id);
                routeModel.setRoute_leftover(route_van_loading - todaySaleView.routeSalePieces(route_id));
                routeModel.setRoute_total_loading(vanStockView.routeTotalLoading(route_id));
                routeModel.setRoute_productive_calls(productive_calls);
                routeModel.setRoute_target_calls(total_calls);
                routeModel.setRoute_rej_prct((int) rejPrct);

                routeModel.setRouteCloseStatus(res.getInt(res.getColumnIndex(ROUTE_CLOSE_STATUS)));

                array_list.add(routeModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();

        ArrayList<RouteListModel> sortedArrayList = new ArrayList<RouteListModel>(array_list);
        Collections.sort(sortedArrayList, new Comparator<RouteListModel>() {
            public int compare(RouteListModel p1, RouteListModel p2) {
                return String.valueOf(p1.getRoute_name()).compareTo(p2.getRoute_name());
            }
        });
        return sortedArrayList;
    }

    /*get route name*/
    public String getRouteName(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + ROUTE_NAME + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=?", new String[]{route_id});
        String routeName = "";
        if (res.moveToFirst()) {
            routeName = res.getString(res.getColumnIndex(ROUTE_NAME));
        }
        res.close();
        db.close();
        return routeName;
    }

    /*depot rej prct and leftover*/
    public DepotModel depotLOnRejPrct(String depot_id) {
        OutletView outletView = new OutletView(mContext);
        TodaySaleView todaySaleView = new TodaySaleView(mContext);
        VanStockView vanStockView = new VanStockView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + DEPOT_ID + "=?", new String[]{depot_id});

        double rejPercentage = 0;
        int leftOver = 0;

        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                String route_id = res.getString(res.getColumnIndex(ROUTE_ID));

                //total route sale amount
                double route_total_sale = outletView.routeTotalSale(route_id);
                double route_rej_amount = todaySaleView.getRouteRejAmount(route_id);

                double rejPrct = 0.00;
                if (route_total_sale > 0)
                    rejPrct = (route_rej_amount / route_total_sale) * 100;

                int route_van_loading = vanStockView.routeTotalLoading(route_id);
                leftOver += (route_van_loading - todaySaleView.routeSalePieces(route_id));
                //routeModel.setRoute_total_loading(vanStockView.routeTotalLoading(route_id));

                rejPercentage += (rejPrct);
                res.moveToNext();
            }
        }

        DepotModel depotModel = new DepotModel();
        if (res.getColumnCount() > 0) {
            depotModel.setDepot_id(depot_id);
            depotModel.setDepot_leftover(leftOver);
            depotModel.setDepot_rej_prct((int) (rejPercentage / res.getColumnCount()));
        }
        res.close();
        db.close();
        return depotModel;
    }
}
