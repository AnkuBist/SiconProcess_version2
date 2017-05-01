package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ItemDetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class ItemDetailView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Godb_item_detail";
    private static final String TABLE_NAME = "item_detail_table";

    private static final String ITEM_CODE = "item_code";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_DESC = "item_desc";
    private static final String SEQUENCE = "sequence";
    private static final String ITEM_PRICE = "item_price";

    private Context mContext;

    public ItemDetailView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ITEM_NAME + " TEXT NULL, "
                + ITEM_DESC + " TEXT NULL, " + ITEM_CODE + " TEXT NULL, " + SEQUENCE + " INTEGER NULL, "
                + ITEM_PRICE + " REAL NULL)");
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
    public boolean insertItemInfo(List<ItemDetailModel> arrItemDetail) {
        SQLiteDatabase db = this.getWritableDatabase();
        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int itemCodeColumn = ih.getColumnIndex(ITEM_CODE);
        final int itemNameColumn = ih.getColumnIndex(ITEM_NAME);
        final int itemDescColumn = ih.getColumnIndex(ITEM_DESC);
        final int sequenceColumn = ih.getColumnIndex(SEQUENCE);
        final int itemPriceColumn = ih.getColumnIndex(ITEM_PRICE);

        try {
            db.beginTransaction();
            for (ItemDetailModel itemDetailModel : arrItemDetail) {
                ih.prepareForInsert();

                ih.bind(itemCodeColumn, itemDetailModel.getItemCode());
                ih.bind(itemNameColumn, itemDetailModel.getItemName());
                ih.bind(itemDescColumn, itemDetailModel.getItemDesc());
                ih.bind(sequenceColumn, itemDetailModel.getSequence());
                ih.bind(itemPriceColumn, itemDetailModel.getItemPrice());

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
    public ArrayList<ItemDetailModel> getAllItemDetails() {
        ArrayList<ItemDetailModel> array_list = new ArrayList<ItemDetailModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                ItemDetailModel itemDetailModel = new ItemDetailModel();
                itemDetailModel.setItemCode(res.getString(res.getColumnIndex(ITEM_CODE)));
                itemDetailModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
                itemDetailModel.setItemDesc(res.getString(res.getColumnIndex(ITEM_DESC)));
                itemDetailModel.setSequence(res.getInt(res.getColumnIndex(SEQUENCE)));
                itemDetailModel.setItemPrice(res.getDouble(res.getColumnIndex(ITEM_PRICE)));
                array_list.add(itemDetailModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    public ItemDetailModel itemByItemId(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ItemDetailModel itemDetailModel = new ItemDetailModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + ITEM_CODE + "=?", new String[]{item_id});
        if (res.moveToFirst()) {
            itemDetailModel.setItemCode(res.getString(res.getColumnIndex(ITEM_CODE)));
            itemDetailModel.setItemName(res.getString(res.getColumnIndex(ITEM_NAME)));
            itemDetailModel.setItemDesc(res.getString(res.getColumnIndex(ITEM_DESC)));
            itemDetailModel.setSequence(res.getInt(res.getColumnIndex(SEQUENCE)));
            itemDetailModel.setItemPrice(res.getDouble(res.getColumnIndex(ITEM_PRICE)));
        }
        res.close();
        db.close();
        return itemDetailModel;
    }

    /*get item name*/
    public String getItemName(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + ITEM_NAME + " FROM " + TABLE_NAME + " WHERE "
                + ITEM_CODE + "=?", new String[]{item_id});
        String item_name = "";
        if (res.moveToFirst()) {
            item_name = res.getString(res.getColumnIndex(ITEM_NAME));
        }
        res.close();
        db.close();
        return item_name;
    }

    /*get item price*/
    public double getItemPrice(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + ITEM_PRICE + " FROM " + TABLE_NAME + " WHERE "
                + ITEM_CODE + "=?", new String[]{item_id});
        double item_price = 0.00;
        if (res.moveToFirst()) {
            item_price = res.getDouble(res.getColumnIndex(ITEM_PRICE));
        }
        res.close();
        db.close();
        return item_price;
    }

    /*get item sequence*/
    public int getItemSequence(String item_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + SEQUENCE + " FROM " + TABLE_NAME + " WHERE "
                + ITEM_CODE + "=?", new String[]{item_id});
        int item_sequence = 0;
        if (res.moveToFirst()) {
            item_sequence = res.getInt(res.getColumnIndex(SEQUENCE));
        }
        res.close();
        db.close();
        return item_sequence;
    }
}