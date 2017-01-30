package com.hgil.siconprocess.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.ProductModel;
import com.hgil.siconprocess.utils.Constant;

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


    public ProductView(Context context) {
        super(context, DATABASE_NAME, null, 1);
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
}