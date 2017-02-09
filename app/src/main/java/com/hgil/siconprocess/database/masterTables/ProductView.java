package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.adapter.productSelection.ProductSelectModel;
import com.hgil.siconprocess.adapter.vanStock.VanStockModel;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.ProductModel;

import java.util.ArrayList;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class ProductView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_product";
    private static final String TABLE_NAME = "V_Item_Master";
    private static final String ITEMSEQUENCE = "ITEMSEQUENCE";
    private static final String PRODUCTRANKING = "PRODUCTRANKING";
    private static final String ITEM_ID = "Item_id";
    private static final String ITEM_SHORT_NAME = "Item_Shrt_Name";
    private static final String ITEM_NAME = "Item_Name";
    private static final String ITEM_DESCRIPTION = "Item_Description";
    private static final String DATAAREAID = "DATAAREAID";
    private static final String NETWEIGHT = "NETWEIGHT";
    private static final String ITEMGROUPID = "ITEMGROUPID";
    private static final String FLAG = "FLAG";

    private Context mContext;

    public ProductView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ITEMSEQUENCE + " INTEGER NOT NULL, "
                + PRODUCTRANKING + " INTEGER NULL, " + ITEM_ID + " TEXT NULL, " + ITEM_SHORT_NAME + " TEXT NULL, "
                + ITEM_NAME + " TEXT NULL, " + ITEM_DESCRIPTION + " TEXT NULL, " + DATAAREAID + " TEXT NULL, "
                + NETWEIGHT + " REAL NULL, " + ITEMGROUPID + " TEXT NULL, " + FLAG + " INTEGER NULL)");
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
    public boolean insertProduct(ProductModel productModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEMSEQUENCE, productModel.getITEMSEQUENCE());
        contentValues.put(PRODUCTRANKING, productModel.getPRODUCTRANKING());
        contentValues.put(ITEM_ID, productModel.getItemId());
        contentValues.put(ITEM_SHORT_NAME, productModel.getItemShrtName());
        contentValues.put(ITEM_NAME, productModel.getItemName());
        contentValues.put(ITEM_DESCRIPTION, productModel.getItemDescription());
        contentValues.put(DATAAREAID, productModel.getDATAAREAID());
        contentValues.put(NETWEIGHT, productModel.getNETWEIGHT());
        contentValues.put(ITEMGROUPID, productModel.getITEMGROUPID());
        contentValues.put(FLAG, productModel.getFLAG());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public ProductModel getProductById(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "='" + item_id + "'", null);

        ProductModel productModel = new ProductModel();
        if (res.moveToFirst()) {
            productModel.setITEMSEQUENCE(res.getInt(res.getColumnIndex(ITEMSEQUENCE)));
            productModel.setPRODUCTRANKING(res.getInt(res.getColumnIndex(PRODUCTRANKING)));
            productModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
            productModel.setItemShrtName(res.getString(res.getColumnIndex(ITEM_SHORT_NAME)));
            productModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
            productModel.setItemDescription(res.getString(res.getColumnIndex(ITEM_DESCRIPTION)));
            productModel.setDATAAREAID(res.getString(res.getColumnIndex(DATAAREAID)));
            productModel.setNETWEIGHT(res.getFloat(res.getColumnIndex(NETWEIGHT)));
            productModel.setITEMGROUPID(res.getString(res.getColumnIndex(ITEMGROUPID)));
            productModel.setFLAG(res.getInt(res.getColumnIndex(FLAG)));
        }
        res.close();
        db.close();
        return productModel;
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
    public boolean checkProduct(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        /*Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + ITEM_ID + "='" + item_id + "'", null);
        //res.moveToFirst();
        boolean exists = (res.getCount() > 0);
        res.close();
        db.close();*/

        String[] columns = {ITEM_NAME};
        String selection = ITEM_ID + " =?";
        String[] selectionArgs = {item_id};
        String limit = "1";

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);

        return exists;
    }

    // get all products loaded in van
    public ArrayList<ProductModel> getAllProducts() {
        ArrayList<ProductModel> array_list = new ArrayList<ProductModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ProductModel productModel = new ProductModel();
                productModel.setITEMSEQUENCE(res.getInt(res.getColumnIndex(ITEMSEQUENCE)));
                productModel.setPRODUCTRANKING(res.getInt(res.getColumnIndex(PRODUCTRANKING)));
                productModel.setItemId(res.getString(res.getColumnIndex(ITEM_ID)));
                productModel.setItemShrtName(res.getString(res.getColumnIndex(ITEM_SHORT_NAME)));
                productModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                productModel.setItemDescription(res.getString(res.getColumnIndex(ITEM_DESCRIPTION)));
                productModel.setDATAAREAID(res.getString(res.getColumnIndex(DATAAREAID)));
                productModel.setNETWEIGHT(res.getFloat(res.getColumnIndex(NETWEIGHT)));
                productModel.setITEMGROUPID(res.getString(res.getColumnIndex(ITEMGROUPID)));
                productModel.setFLAG(res.getInt(res.getColumnIndex(FLAG)));

                array_list.add(productModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    // get all products loaded in van for customer to select for rejection....this has to be filtered with user rejection list if exists
    public ArrayList<ProductSelectModel> getAvailableProducts(String customer_id, ArrayList<String> alreadyExists) {
        ArrayList<ProductSelectModel> array_list = new ArrayList<ProductSelectModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        CustomerItemPriceTable dbPriceTable = new CustomerItemPriceTable(mContext);

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ProductSelectModel pSelectModel = new ProductSelectModel();
                pSelectModel.setItem_id(res.getString(res.getColumnIndex(ITEM_ID)));
                pSelectModel.setItem_name(res.getString(res.getColumnIndex(ITEM_NAME)));
                pSelectModel.setItem_price(dbPriceTable.getItemPriceById(pSelectModel.getItem_id(), customer_id));

               /* CustomerRejectionTable rejectionTable = new CustomerRejectionTable(mContext);
                // get here if this product exists in the table of not
                if (rejectionTable.custRejExists(customer_id, pSelectModel.getItem_id()) > 0) {
                    // do nothing
                } else {
                    array_list.add(pSelectModel);
                }*/

                if (alreadyExists.contains(pSelectModel.getItem_id())) {
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

    /*get product data for van stock*/
    public ArrayList<VanStockModel> getVanStock() {
        ArrayList<VanStockModel> array_list = new ArrayList<VanStockModel>();

        SQLiteDatabase db = this.getReadableDatabase();

        DepotInvoiceView depotInvoiceView = new DepotInvoiceView(mContext);
        FixedSampleTable fixedSampleTable = new FixedSampleTable(mContext);
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(mContext);
        CustomerRejectionTable customerRejTable = new CustomerRejectionTable(mContext);

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

                // get product market and fresh rejection total
                int marketRejection = customerRejTable.productMarketRejection(vanStockModel.getItem_id());
                int freshRejection = customerRejTable.productFreshRejection(vanStockModel.getItem_id());

                vanStockModel.setMarket_rejection(marketRejection);
                vanStockModel.setFresh_rejection(freshRejection);

                int leftOver = loadingQty - saleQty - sampleQty;
                vanStockModel.setLeft_over(leftOver);

                array_list.add(vanStockModel);
                res.moveToNext();
            }
        }

        res.close();
        db.close();
        return array_list;
    }

}