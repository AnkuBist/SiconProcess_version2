package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CustomerItemPriceModel;

import java.util.List;

/**
 * Created by mohan.giri on 07-02-2017.
 */

public class CustomerItemPriceTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_item_price_db";
    private static final String TABLE_NAME = "customer_item_price_table";

    private static final String ITEM_ID = "Item_Id";
    private static final String CUSTOMER_ID = "Customer_id";
    private static final String ITEM_PRICE = "item_price";
    private static final String DISCOUNT_PRICE = "discount_price";
    private static final String DISCOUNT_PERCENTAGE = "discount_percentage";
    private static final String DISCOUNT_TYPE = "discountType";
    private static final String DISCOUNTED_PRICE = "discounted_prc";


    private Context mContext;

    public CustomerItemPriceTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ITEM_ID + " TEXT NOT NULL, "
                + CUSTOMER_ID + " TEXT NOT NULL, " + ITEM_PRICE + " REAL NOT NULL, "
                + DISCOUNT_PRICE + " REAL NOT NULL, " + DISCOUNT_PERCENTAGE + " REAL NOT NULL, "
                + DISCOUNT_TYPE + " TEXT NOT NULL, "
                + DISCOUNTED_PRICE + " REAL NOT NULL)");
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
    public boolean insertCustomerItemPrice(CustomerItemPriceModel customerItemPriceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_ID, customerItemPriceModel.getItemId());
        contentValues.put(CUSTOMER_ID, customerItemPriceModel.getCustomerId());
        contentValues.put(ITEM_PRICE, customerItemPriceModel.getItemPrice());
        contentValues.put(DISCOUNT_PRICE, customerItemPriceModel.getDiscountPrice());
        contentValues.put(DISCOUNT_PERCENTAGE, customerItemPriceModel.getDiscountPercentage());
        contentValues.put(DISCOUNT_TYPE, customerItemPriceModel.getDiscountType());
        contentValues.put(DISCOUNTED_PRICE, customerItemPriceModel.getDiscountedPrice());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple items price
    public boolean insertCustomerItemPrice(List<CustomerItemPriceModel> arrCustomerItemPrice) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrCustomerItemPrice.size(); i++) {
            CustomerItemPriceModel customerItemPriceModel = arrCustomerItemPrice.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ITEM_ID, customerItemPriceModel.getItemId());
            contentValues.put(CUSTOMER_ID, customerItemPriceModel.getCustomerId());
            contentValues.put(ITEM_PRICE, customerItemPriceModel.getItemPrice());
            contentValues.put(DISCOUNT_PRICE, customerItemPriceModel.getDiscountPrice());
            contentValues.put(DISCOUNT_PERCENTAGE, customerItemPriceModel.getDiscountPercentage());
            contentValues.put(DISCOUNT_TYPE, customerItemPriceModel.getDiscountType());
            contentValues.put(DISCOUNTED_PRICE, customerItemPriceModel.getDiscountedPrice());
            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    public double getItemPriceById(String item_id, String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + DISCOUNTED_PRICE + " FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "=? and " + CUSTOMER_ID + "=?", new String[]{item_id, customer_id});

        double price = 0;
        if (res.moveToFirst()) {
            price = res.getDouble(res.getColumnIndex(DISCOUNTED_PRICE));
        }
        res.close();
        db.close();
        return price;
    }

    // get required details for the product for a customer on route
    public CustomerItemPriceModel getItemPriceDiscById(String customer_id, String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID + "=? AND " + ITEM_ID + "=?", new String[]{customer_id, item_id});

        CustomerItemPriceModel itemPriceModel = new CustomerItemPriceModel();
        if (res.moveToFirst()) {
            itemPriceModel.setItemPrice(res.getDouble(res.getColumnIndex(ITEM_PRICE)));
            itemPriceModel.setDiscountPrice(res.getDouble(res.getColumnIndex(DISCOUNT_PRICE)));
            itemPriceModel.setDiscountPercentage(res.getDouble(res.getColumnIndex(DISCOUNT_PERCENTAGE)));
            itemPriceModel.setDiscountType(res.getString(res.getColumnIndex(DISCOUNT_TYPE)));
            itemPriceModel.setDiscountedPrice(res.getDouble(res.getColumnIndex(DISCOUNTED_PRICE)));
        }
        res.close();
        db.close();
        return itemPriceModel;
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

    /*check product exists or not*/
   /* public boolean checkProduct(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        *//*Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "='" + item_id + "'", null);
        //res.moveToFirst();
        boolean exists = (res.getCount() > 0);
        res.close();
        db.close();*//*

        String[] columns = {ITEM_NAME};
        String selection = ITEM_ID + " =?";
        String[] selectionArgs = {item_id};
        String limit = "1";

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);

        return exists;
    }*/

    // get all products loaded in van
   /* public ArrayList<CustomerItemPriceModel> getAllProducts() {
        ArrayList<CustomerItemPriceModel> array_list = new ArrayList<CustomerItemPriceModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                CustomerItemPriceModel customerItemPriceModel = new CustomerItemPriceModel();
                customerItemPriceModel.setITEMSEQUENCE(res.getInt(res.getColumnIndex(ITEMSEQUENCE)));
                customerItemPriceModel.setPRODUCTRANKING(res.getInt(res.getColumnIndex(PRODUCTRANKING)));
                customerItemPriceModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                customerItemPriceModel.setItemShrtName(res.getString(res.getColumnIndex(ITEM_SHORT_NAME)));
                customerItemPriceModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                customerItemPriceModel.setItemDescription(res.getString(res.getColumnIndex(ITEM_DESCRIPTION)));
                customerItemPriceModel.setDATAAREAID(res.getString(res.getColumnIndex(DATAAREAID)));
                customerItemPriceModel.setNETWEIGHT(res.getFloat(res.getColumnIndex(NETWEIGHT)));
                customerItemPriceModel.setITEMGROUPID(res.getString(res.getColumnIndex(ITEMGROUPID)));
                customerItemPriceModel.setFLAG(res.getInt(res.getColumnIndex(FLAG)));

                array_list.add(customerItemPriceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    // get all products loaded in van for customer to select for rejection....this has to be filtered with user rejection list if exists
    public ArrayList<ProductSelectModel> getRejectionProductsAvailable(String customer_id, ArrayList<String> alreadyRejected) {
        ArrayList<ProductSelectModel> array_list = new ArrayList<ProductSelectModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ProductSelectModel pSelectModel = new ProductSelectModel();
                pSelectModel.setItem_id(res.getString(res.getColumnIndex(ITEM_ID)));
                pSelectModel.setItem_name(res.getString(res.getColumnIndex(ITEM_NAME)));

               *//* CustomerRejectionTable rejectionTable = new CustomerRejectionTable(mContext);
                // get here if this product exists in the table of not
                if (rejectionTable.custRejExists(customer_id, pSelectModel.getItem_id()) > 0) {
                    // do nothing
                } else {
                    array_list.add(pSelectModel);
                }*//*

                if (alreadyRejected.contains(pSelectModel.getItem_id())) {
                    // do not add this product
                } else {
                    array_list.add(pSelectModel);
                }
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    *//*get product data for van stock*//*
    public ArrayList<VanStockModel> getVanStock() {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        DepotInvoiceView depotInvoiceView = new DepotInvoiceView(mContext);
        FixedSampleTable fixedSampleTable = new FixedSampleTable(mContext);
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(mContext);

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                VanStockModel vanStockModel = new VanStockModel();
                vanStockModel.setItem_id(res.getString(res.getColumnIndex(ITEM_ID)));
                vanStockModel.setItem_name(res.getString(res.getColumnIndex(ITEM_NAME)));

                int loadingQty = depotInvoiceView.getLoadingCount(vanStockModel.getItem_id());
                int saleQty = invoiceOutTable.getItemOrderQty(vanStockModel.getItem_id());
                int sampleQty = fixedSampleTable.getSampleCount(vanStockModel.getItem_id());

                // get product total stock in van
                vanStockModel.setLoadQty(loadingQty);
                // total product quantity sold
                vanStockModel.setGross_sale(saleQty);
                // van sample loads
                vanStockModel.setSample(sampleQty);


                int leftOver = loadingQty - saleQty - sampleQty;
                vanStockModel.setLeft_over(leftOver);

                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }

        res.close();
        db.close();
        return array_list;
    }*/

}