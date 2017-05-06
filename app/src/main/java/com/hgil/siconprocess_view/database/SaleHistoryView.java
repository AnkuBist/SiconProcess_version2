package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.SaleHistoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class SaleHistoryView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "GoDb_Sale_History_db";
    private static final String TABLE_NAME = "Outlet_Sale_History_table";

    private static final String ROUTE_ID = "route_id";
    private static final String STOCK_DATE = "stock_date";
    private static final String OUTLET_CODE = "outlet_code";

    private static final String ITEMS_SOLD = "items_sold";
    private static final String GROSS_SALE = "gross_sale";
    private static final String NET_SALE = "net_sale";

    private Context mContext;

    public SaleHistoryView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + STOCK_DATE + " TEXT NULL, "
                + OUTLET_CODE + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, " + ITEMS_SOLD + " INTEGER NULL, "
                + GROSS_SALE + " REAL NULL, " + NET_SALE + " REAL NULL)");
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

    // insert multiple
    public boolean insertSaleHistory(List<SaleHistoryModel> arrSaleHistory) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int routeIdColumn = ih.getColumnIndex(ROUTE_ID);
        final int stockDateColumn = ih.getColumnIndex(STOCK_DATE);
        final int outletCodeColumn = ih.getColumnIndex(OUTLET_CODE);
        final int itemsSoldColumn = ih.getColumnIndex(ITEMS_SOLD);
        final int grossSaleColumn = ih.getColumnIndex(GROSS_SALE);
        final int netSaleColumn = ih.getColumnIndex(NET_SALE);

        try {
            db.beginTransaction();
            for (SaleHistoryModel saleHistoryModel : arrSaleHistory) {
                ih.prepareForInsert();

                ih.bind(routeIdColumn, saleHistoryModel.getRouteId());
                ih.bind(stockDateColumn, saleHistoryModel.getStockDate());
                ih.bind(outletCodeColumn, saleHistoryModel.getOutletCode());
                ih.bind(itemsSoldColumn, saleHistoryModel.getItemsSold());
                ih.bind(grossSaleColumn, saleHistoryModel.getGrossSale());
                ih.bind(netSaleColumn, saleHistoryModel.getNetSale());

                ih.execute();
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        db.close();
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    // get all customers
    public ArrayList<SaleHistoryModel> getAllRouteSaleHistory() {
        ArrayList<SaleHistoryModel> array_list = new ArrayList<SaleHistoryModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SaleHistoryModel saleHistoryModel = new SaleHistoryModel();
                saleHistoryModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                saleHistoryModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                saleHistoryModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                saleHistoryModel.setItemsSold(res.getInt(res.getColumnIndex(ITEMS_SOLD)));
                saleHistoryModel.setGrossSale(res.getDouble(res.getColumnIndex(GROSS_SALE)));
                saleHistoryModel.setNetSale(res.getDouble(res.getColumnIndex(NET_SALE)));
                array_list.add(saleHistoryModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ArrayList<SaleHistoryModel> outletSaleHistory(String outlet_id) {
        ArrayList<SaleHistoryModel> array_list = new ArrayList<SaleHistoryModel>();
        SHVanLoadingView shVanLoadingView = new SHVanLoadingView(mContext);
        SHOutletSaleView shOutletSaleView = new SHOutletSaleView(mContext);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + OUTLET_CODE + "=? ORDER BY "
                + STOCK_DATE + " DESC", new String[]{outlet_id});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                SaleHistoryModel saleHistoryModel = new SaleHistoryModel();
                saleHistoryModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                saleHistoryModel.setStockDate(res.getString(res.getColumnIndex(STOCK_DATE)));
                saleHistoryModel.setOutletCode(res.getString(res.getColumnIndex(OUTLET_CODE)));
                saleHistoryModel.setItemsSold(res.getInt(res.getColumnIndex(ITEMS_SOLD)));

                double grossSale = res.getDouble(res.getColumnIndex(GROSS_SALE));
                double netSale = res.getDouble(res.getColumnIndex(NET_SALE));

                saleHistoryModel.setGrossSale(grossSale);
                saleHistoryModel.setNetSale(netSale);

                //updating new values
                double rejPrct = 0.00;
                if (grossSale > 0)
                    rejPrct = (((grossSale - netSale) / grossSale) * 100);

                saleHistoryModel.setRejPrct(rejPrct);
                saleHistoryModel.setRoute_van_stock(shVanLoadingView.routeVanLoadingHistory(saleHistoryModel.getRouteId(), saleHistoryModel.getStockDate()));
                saleHistoryModel.setOutlet_sale_items(shOutletSaleView.outletSaleHistory(saleHistoryModel.getOutletCode(), saleHistoryModel.getStockDate()));

                array_list.add(saleHistoryModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*average outlet sale*/
    public double avgCustomerNetSale(String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + NET_SALE + ") as " + NET_SALE + " FROM " + TABLE_NAME
                        + " where " + ROUTE_ID + "=? AND " + OUTLET_CODE + "=? GROUP BY " + ROUTE_ID + "," + OUTLET_CODE,
                new String[]{route_id, outlet_id});
        double net_sale = 0;
        if (res.moveToFirst()) {
            net_sale = (res.getDouble(res.getColumnIndex(NET_SALE)));
        }

        res.close();
        db.close();
        return net_sale;
    }

    /*average outlet sale*/
    public double avgCustomerGrossSale(String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + GROSS_SALE + ") as " + GROSS_SALE + " FROM " + TABLE_NAME
                        + " where " + ROUTE_ID + "=? AND " + OUTLET_CODE + "=? GROUP BY " + ROUTE_ID + "," + OUTLET_CODE,
                new String[]{route_id, outlet_id});
        double net_sale = 0;
        if (res.moveToFirst()) {
            net_sale = (res.getDouble(res.getColumnIndex(GROSS_SALE)));
        }

        res.close();
        db.close();
        return net_sale;
    }

    /*average outlet rejection percentage*/
    public long avgCustomerRejPrct(String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT sum(" + GROSS_SALE + ") as " + GROSS_SALE
                        + ", sum(" + NET_SALE + ") as " + NET_SALE + " FROM " + TABLE_NAME
                        + " where " + ROUTE_ID + "=? AND " + OUTLET_CODE + "=? GROUP BY " + ROUTE_ID + "," + OUTLET_CODE,
                new String[]{route_id, outlet_id});
        long rej_prct = 0;
       /* long avgRejPrct = 0;*/
        if (res.moveToFirst()) {
            double gross_sale = (res.getDouble(res.getColumnIndex(GROSS_SALE)));
            double net_sale = (res.getDouble(res.getColumnIndex(NET_SALE)));
            //  double rej_prct = 0;
            if (gross_sale > 0)
                rej_prct = Math.round(((gross_sale - net_sale) / gross_sale) * 100);
            /*avgRejPrct = Math.round(rej_prct / customerRowCount(route_id, outlet_id));*/
        }

        res.close();
        db.close();
        return rej_prct;
    }

    //get customer row count
    public int customerRowCount(String route_id, String outlet_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + STOCK_DATE + " FROM " + TABLE_NAME + " where " + ROUTE_ID + "=? AND " + OUTLET_CODE + "=?",
                new String[]{route_id, outlet_id});

        int rowCount = res.getCount();

        res.close();
        db.close();
        return rowCount;
    }
}