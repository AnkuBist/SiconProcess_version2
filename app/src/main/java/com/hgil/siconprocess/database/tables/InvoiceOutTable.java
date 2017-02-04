package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.adapter.invoice.invoiceSale.InvoiceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 02-02-2017.
 */

public class InvoiceOutTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "invoice_out_db";
    private static final String TABLE_NAME = "depot_invoice_out";

    private static final String INVOICE_NO = "Invoice_No";
    private static final String INVOICE_DATE = "Invoice_Date";
    private static final String CUSTOMER_ID = "Customer_id";
    private static final String ROUTE_ID = "Route_Id";
    private static final String VEHICLE_NO = "Vehicle_No";
    private static final String ITEM_ID = "Item_id";
    private static final String CRATE_ID = "Crate_id";
    private static final String INVQTY_CR = "InvQty_Cr";
    private static final String INVQTY_PS = "InvQty_ps";
    private static final String ITEM_RATE = "Item_Rate";
    private static final String TOTAL_AMOUNT = "Total_Amount";

    /*new added columns only merged to this invoice out table*/
    private static final String FIXED_SAMPLE = "fixedSample";
    private static final String DEMAND_TARGET_QUANTITY = "demandTargetQty";
    private static final String ORDER_AMOUNT = "orderAmount";
    private static final String STOCK_AVAIL = "stockAvail";
    private static final String TEMP_STOCK = "tempStock";
    private static final String ITEM_NAME = "itemName";
/*    private static final String REJECTION_QTY = "rejectionQty";
    private static final String REJECTION_TOTAL_AMOUNT = "rejTotalAmount";*/

    private Context mContext;

    public InvoiceOutTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + INVOICE_NO + " TEXT NULL, "
                + INVOICE_DATE + " TEXT NULL, " + CUSTOMER_ID + " TEXT NULL, " + ROUTE_ID + " TEXT NULL, "
                + VEHICLE_NO + " TEXT NULL, " + ITEM_ID + " TEXT NULL, "
                + CRATE_ID + " TEXT NULL, " + INVQTY_CR + " REAL NULL, " + INVQTY_PS + " REAL NULL, "
                + ITEM_RATE + " REAL NULL, " + TOTAL_AMOUNT + " REAL NULL, "
                + FIXED_SAMPLE + " INTEGER NULL, " + DEMAND_TARGET_QUANTITY + " REAL NULL, " + ORDER_AMOUNT + " REAL NULL, "
                + STOCK_AVAIL + " INTEGER NULL, " + TEMP_STOCK + " INETEGER NULL, " + ITEM_NAME + " TEXT NULL)");
               /* + REJECTION_QTY + " INTEGER NULL, " + REJECTION_TOTAL_AMOUNT + " REAL NULL)");*/
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
    public boolean insertInvoice(InvoiceModel invoiceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INVOICE_NO, invoiceModel.getInvoiceNo());
        contentValues.put(INVOICE_DATE, invoiceModel.getInvoiceDate());
        contentValues.put(CUSTOMER_ID, invoiceModel.getCustomerId());
        contentValues.put(ROUTE_ID, invoiceModel.getRouteId());
        contentValues.put(VEHICLE_NO, invoiceModel.getVehicleNo());
        contentValues.put(ITEM_ID, invoiceModel.getItemId());
        contentValues.put(CRATE_ID, invoiceModel.getCrateId());
        contentValues.put(INVQTY_CR, invoiceModel.getInvQtyCr());
        contentValues.put(INVQTY_PS, invoiceModel.getInvQtyPs());
        contentValues.put(ITEM_RATE, invoiceModel.getItemRate());
        contentValues.put(TOTAL_AMOUNT, invoiceModel.getTotalAmount());
        contentValues.put(FIXED_SAMPLE, invoiceModel.getFixedSample());
        contentValues.put(DEMAND_TARGET_QUANTITY, invoiceModel.getDemandTargetQty());
        contentValues.put(TOTAL_AMOUNT, invoiceModel.getOrderAmount());
        contentValues.put(TOTAL_AMOUNT, invoiceModel.getStockAvail());
        contentValues.put(TOTAL_AMOUNT, invoiceModel.getTempStock());
        contentValues.put(TOTAL_AMOUNT, invoiceModel.getItemName());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertInvoiceOut(List<InvoiceModel> arrList, String customer_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // first erase the recent invoice belong to the same user
        eraseInvoiceUser(db, customer_id);

        for (int i = 0; i < arrList.size(); i++) {
            InvoiceModel invoiceModel = arrList.get(i);
            if (invoiceModel.getOrderAmount() > 0 && invoiceModel.getDemandTargetQty() > 0) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(INVOICE_NO, invoiceModel.getInvoiceNo());
                contentValues.put(INVOICE_DATE, invoiceModel.getInvoiceDate());
                contentValues.put(CUSTOMER_ID, invoiceModel.getCustomerId());
                contentValues.put(ROUTE_ID, invoiceModel.getRouteId());
                contentValues.put(VEHICLE_NO, invoiceModel.getVehicleNo());
                contentValues.put(ITEM_ID, invoiceModel.getItemId());
                contentValues.put(CRATE_ID, invoiceModel.getCrateId());
                contentValues.put(INVQTY_CR, invoiceModel.getInvQtyCr());
                contentValues.put(INVQTY_PS, invoiceModel.getInvQtyPs());
                contentValues.put(ITEM_RATE, invoiceModel.getItemRate());
                contentValues.put(TOTAL_AMOUNT, invoiceModel.getTotalAmount());
                contentValues.put(FIXED_SAMPLE, invoiceModel.getFixedSample());
                contentValues.put(DEMAND_TARGET_QUANTITY, invoiceModel.getDemandTargetQty());
                contentValues.put(TOTAL_AMOUNT, invoiceModel.getOrderAmount());
                contentValues.put(TOTAL_AMOUNT, invoiceModel.getStockAvail());
                contentValues.put(TOTAL_AMOUNT, invoiceModel.getTempStock());
                contentValues.put(TOTAL_AMOUNT, invoiceModel.getItemName());
                db.insert(TABLE_NAME, null, contentValues);
            }
        }
        db.close();
        return true;
    }

    /* erase customer recent invoice items*/
    private void eraseInvoiceUser(SQLiteDatabase db, String customer_id) {
        db.delete(TABLE_NAME, CUSTOMER_ID + " = ?", new String[]{customer_id});
    }

    public InvoiceModel getDepotInvoiceByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + INVOICE_DATE + "='" + route_id + "'", null);

        InvoiceModel invoiceModel = new InvoiceModel();
        if (res.moveToFirst()) {
            invoiceModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
            invoiceModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
            invoiceModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            invoiceModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            invoiceModel.setVehicleNo(res.getString(res.getColumnIndex(VEHICLE_NO)));
            invoiceModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            invoiceModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
            invoiceModel.setInvQtyCr(res.getFloat(res.getColumnIndex(INVQTY_CR)));
            invoiceModel.setInvQtyPs(res.getFloat(res.getColumnIndex(INVQTY_PS)));
            invoiceModel.setItemRate(res.getFloat(res.getColumnIndex(ITEM_RATE)));
            invoiceModel.setTotalAmount(res.getFloat(res.getColumnIndex(TOTAL_AMOUNT)));
            invoiceModel.setFixedSample(res.getInt(res.getColumnIndex(FIXED_SAMPLE)));
            invoiceModel.setDemandTargetQty(res.getFloat(res.getColumnIndex(DEMAND_TARGET_QUANTITY)));
            invoiceModel.setOrderAmount(res.getDouble(res.getColumnIndex(ORDER_AMOUNT)));
            invoiceModel.setStockAvail(res.getInt(res.getColumnIndex(STOCK_AVAIL)));
            invoiceModel.setTempStock(res.getInt(res.getColumnIndex(TEMP_STOCK)));
            invoiceModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
        }
        res.close();
        db.close();
        return invoiceModel;
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

    public ArrayList<InvoiceModel> getCustomerAllInvoiceOut(String customer_id) {
        ArrayList<InvoiceModel> array_list = new ArrayList<InvoiceModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "='" + customer_id + "'", null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                InvoiceModel invoiceModel = new InvoiceModel();
                invoiceModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
                invoiceModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
                invoiceModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                invoiceModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                invoiceModel.setVehicleNo(res.getString(res.getColumnIndex(VEHICLE_NO)));
                invoiceModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                invoiceModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
                invoiceModel.setInvQtyCr(res.getFloat(res.getColumnIndex(INVQTY_CR)));
                invoiceModel.setInvQtyPs(res.getFloat(res.getColumnIndex(INVQTY_PS)));
                invoiceModel.setItemRate(res.getFloat(res.getColumnIndex(ITEM_RATE)));
                invoiceModel.setTotalAmount(res.getFloat(res.getColumnIndex(TOTAL_AMOUNT)));
                invoiceModel.setFixedSample(res.getInt(res.getColumnIndex(FIXED_SAMPLE)));
                invoiceModel.setDemandTargetQty(res.getFloat(res.getColumnIndex(DEMAND_TARGET_QUANTITY)));
                invoiceModel.setOrderAmount(res.getDouble(res.getColumnIndex(ORDER_AMOUNT)));
                invoiceModel.setStockAvail(res.getInt(res.getColumnIndex(STOCK_AVAIL)));
                invoiceModel.setTempStock(res.getInt(res.getColumnIndex(TEMP_STOCK)));
                invoiceModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                array_list.add(invoiceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }


    /*required details by the application side in single pojo*/
    public ArrayList<InvoiceModel> getCustomerInvoice(String customer_id) {
        ArrayList<InvoiceModel> array_list = new ArrayList<InvoiceModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "='" + customer_id + "'", null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                InvoiceModel invoiceModel = new InvoiceModel();
                invoiceModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
                invoiceModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
                invoiceModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                invoiceModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
                invoiceModel.setVehicleNo(res.getString(res.getColumnIndex(VEHICLE_NO)));
                invoiceModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                invoiceModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
                invoiceModel.setInvQtyCr(res.getFloat(res.getColumnIndex(INVQTY_CR)));
                invoiceModel.setInvQtyPs(res.getFloat(res.getColumnIndex(INVQTY_PS)));
                invoiceModel.setItemRate(res.getFloat(res.getColumnIndex(ITEM_RATE)));
                invoiceModel.setTotalAmount(res.getFloat(res.getColumnIndex(TOTAL_AMOUNT)));
                invoiceModel.setFixedSample(res.getInt(res.getColumnIndex(FIXED_SAMPLE)));
                invoiceModel.setDemandTargetQty(res.getFloat(res.getColumnIndex(DEMAND_TARGET_QUANTITY)));
                invoiceModel.setOrderAmount(res.getDouble(res.getColumnIndex(ORDER_AMOUNT)));
                invoiceModel.setStockAvail(res.getInt(res.getColumnIndex(STOCK_AVAIL)));
                invoiceModel.setTempStock(res.getInt(res.getColumnIndex(TEMP_STOCK)));
                invoiceModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                array_list.add(invoiceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    // get item demand for customer
    public int getDemandQuantity(String customer_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select distinct " + DEMAND_TARGET_QUANTITY + " from " + TABLE_NAME + " where " + ITEM_ID + "=? AND " + CUSTOMER_ID + "=?";
        Cursor res = db.rawQuery(query, new String[]{item_id, customer_id});

        int demandQty = 0;
        if (res.moveToFirst()) {
            demandQty = res.getInt(res.getColumnIndex(DEMAND_TARGET_QUANTITY));
        }
        res.close();
        db.close();
        return demandQty;
    }

    // get van loading count
    public int getItemOrderQty(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select distinct " + ITEM_ID + ", sum(" + DEMAND_TARGET_QUANTITY + ") as loading_qty " +
                "from " + TABLE_NAME + " where " + ITEM_ID + "=? group by " + ITEM_ID;
        Cursor res = db.rawQuery(query, new String[]{item_id});

        int demandQty = 0;
        if (res.moveToFirst()) {
            demandQty = res.getInt(res.getColumnIndex("loading_qty"));
        }
        res.close();
        db.close();
        return demandQty;
    }

    //TODO
    // get customer grand total for invoice prepared.
    public double custOrderTotalAmount(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select sum(" + DEMAND_TARGET_QUANTITY + "*" + TOTAL_AMOUNT + ") as total " +
                "from " + TABLE_NAME + " where " + CUSTOMER_ID + "=?";
        Cursor res = db.rawQuery(query, new String[]{customer_id});

        double amount = 0;
        if (res.moveToFirst()) {
            amount = res.getInt(res.getColumnIndex("total"));
        }
        res.close();
        db.close();
        return amount;
    }

    // this function is to display the invoice is there is not invoice exists for the customer.
    // this will calculate the available items in stock and display to the user.
    public ArrayList<InvoiceModel> getCustomerInvoiceOff(String customer_id) {
        ArrayList<InvoiceModel> array_list = new ArrayList<InvoiceModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        /*select sum(InvQty_ps) as loading_qty, Item_id
from V_SD_DepotInvoice_Master where Route_managemnet_Date='2017-01-30' and Route_id='R/mom/0041' group by Item_id*/

        Cursor res = db.rawQuery("SELECT sum(" + INVQTY_PS + ") as loading_qty, " + ITEM_ID + " FROM " + TABLE_NAME + " GROUP BY " + ITEM_ID, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                String item_id = res.getString(res.getColumnIndex(ITEM_ID));
                int stock = res.getInt(res.getColumnIndex("loading_qty"));
                if (stock > 0) {
                    InvoiceModel invoiceModel = getDepotInvoiceByItemId(customer_id, item_id);
                    if (invoiceModel != null) {
                        invoiceModel.setStockAvail(stock);
                        invoiceModel.setTempStock(stock);
                        invoiceModel.setCustomerId(customer_id);
                        array_list.add(invoiceModel);
                    }
                }
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }


    public InvoiceModel getDepotInvoiceByItemId(String customer_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "='" + item_id + "'", null);

        InvoiceModel invoiceModel = new InvoiceModel();
        if (res.moveToFirst()) {
            invoiceModel.setInvoiceNo(res.getString(res.getColumnIndex(INVOICE_NO)));
            invoiceModel.setInvoiceDate(res.getString(res.getColumnIndex(INVOICE_DATE)));
            invoiceModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
            invoiceModel.setRouteId(res.getString(res.getColumnIndex(ROUTE_ID)));
            invoiceModel.setVehicleNo(res.getString(res.getColumnIndex(VEHICLE_NO)));
            invoiceModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            invoiceModel.setCrateId(res.getString(res.getColumnIndex(CRATE_ID)));
            invoiceModel.setInvQtyCr(res.getFloat(res.getColumnIndex(INVQTY_CR)));
            invoiceModel.setInvQtyPs(res.getFloat(res.getColumnIndex(INVQTY_PS)));
            invoiceModel.setItemRate(res.getFloat(res.getColumnIndex(ITEM_RATE)));
            invoiceModel.setTotalAmount(res.getFloat(res.getColumnIndex(TOTAL_AMOUNT)));
            invoiceModel.setFixedSample(res.getInt(res.getColumnIndex(FIXED_SAMPLE)));
            invoiceModel.setDemandTargetQty(res.getFloat(res.getColumnIndex(DEMAND_TARGET_QUANTITY)));
            invoiceModel.setOrderAmount(res.getDouble(res.getColumnIndex(ORDER_AMOUNT)));
            invoiceModel.setStockAvail(res.getInt(res.getColumnIndex(STOCK_AVAIL)));
            invoiceModel.setTempStock(res.getInt(res.getColumnIndex(TEMP_STOCK)));
            invoiceModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
        }
        res.close();
        db.close();
        return invoiceModel;
    }

}