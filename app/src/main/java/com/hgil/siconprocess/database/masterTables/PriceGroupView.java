package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.GroupPriceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 24-01-2017.
 */

public class PriceGroupView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_price_group";
    private static final String TABLE_NAME = "V_PriceGroupMaster";

    private static final String ACCOUNTCODE = "ACCOUNTCODE";
    private static final String ITEMRELATION = "ITEMRELATION";
    private static final String ACCOUNTRELATION = "ACCOUNTRELATION";
    private static final String QUANTITYAMOUNT = "QUANTITYAMOUNT";
    private static final String FROMDATE = "FROMDATE";
    private static final String TODATE = "TODATE";
    private static final String AMOUNT = "AMOUNT";
    private static final String CURRENCY = "CURRENCY";
    private static final String DISCOUNT = "Discount";
    private static final String DISCOUNT1 = "Discount1";
    private static final String PRICETYPE = "PriceType";
    private static final String UNITID = "UNITID";
    private static final String SURCHARGE = "Surcharge";
    private static final String MODULE = "MODULE";
    private static final String SUB_COMPANY_ID = "Sub_Company_id";

    private Context mContext;

    public PriceGroupView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ACCOUNTCODE + " NUMERIC NOT NULL, "
                + ITEMRELATION + " TEXT NULL, " + ACCOUNTRELATION + " TEXT NULL, " + QUANTITYAMOUNT + " TEXT NULL, "
                + FROMDATE + " TEXT NULL, " + TODATE + " TEXT NULL, " + AMOUNT + " TEXT NULL, "
                + CURRENCY + " REAL NULL, " + DISCOUNT + " REAL NULL, " + DISCOUNT1 + " REAL NULL, "
                + PRICETYPE + " REAL NULL, " + UNITID + " REAL NULL, " + SURCHARGE + " TEXT NOT NULL, "
                + MODULE + " TEXT NOT NULL, " + SUB_COMPANY_ID + " REAL NULL)");
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
    public boolean insertPrice(List<GroupPriceModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < arrList.size(); i++) {
            GroupPriceModel groupPriceModel = arrList.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(ACCOUNTCODE, groupPriceModel.getACCOUNTCODE());
            contentValues.put(ITEMRELATION, groupPriceModel.getITEMRELATION());
            contentValues.put(ACCOUNTRELATION, groupPriceModel.getACCOUNTRELATION());
            contentValues.put(QUANTITYAMOUNT, groupPriceModel.getQUANTITYAMOUNT());
            contentValues.put(FROMDATE, groupPriceModel.getFROMDATE());
            contentValues.put(TODATE, groupPriceModel.getTODATE());
            contentValues.put(AMOUNT, groupPriceModel.getAMOUNT());
            contentValues.put(CURRENCY, groupPriceModel.getCURRENCY());
            contentValues.put(DISCOUNT, groupPriceModel.getDiscount());
            contentValues.put(DISCOUNT1, groupPriceModel.getDiscount1());
            contentValues.put(PRICETYPE, groupPriceModel.getPriceType());
            contentValues.put(UNITID, groupPriceModel.getUNITID());
            contentValues.put(SURCHARGE, groupPriceModel.getSurcharge());
            contentValues.put(MODULE, groupPriceModel.getMODULE());
            contentValues.put(SUB_COMPANY_ID, groupPriceModel.getSubCompanyId());

            // insert product here only
            new ProductView(mContext).insertProduct(groupPriceModel.getItemObj());

            db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    //insert single
    public boolean insertPrice(GroupPriceModel groupPriceModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ACCOUNTCODE, groupPriceModel.getACCOUNTCODE());
        contentValues.put(ITEMRELATION, groupPriceModel.getITEMRELATION());
        contentValues.put(ACCOUNTRELATION, groupPriceModel.getACCOUNTRELATION());
        contentValues.put(QUANTITYAMOUNT, groupPriceModel.getQUANTITYAMOUNT());
        contentValues.put(FROMDATE, groupPriceModel.getFROMDATE());
        contentValues.put(TODATE, groupPriceModel.getTODATE());
        contentValues.put(AMOUNT, groupPriceModel.getAMOUNT());
        contentValues.put(CURRENCY, groupPriceModel.getCURRENCY());
        contentValues.put(DISCOUNT, groupPriceModel.getDiscount());
        contentValues.put(DISCOUNT1, groupPriceModel.getDiscount1());
        contentValues.put(PRICETYPE, groupPriceModel.getPriceType());
        contentValues.put(UNITID, groupPriceModel.getUNITID());
        contentValues.put(SURCHARGE, groupPriceModel.getSurcharge());
        contentValues.put(MODULE, groupPriceModel.getMODULE());
        contentValues.put(SUB_COMPANY_ID, groupPriceModel.getSubCompanyId());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public GroupPriceModel getPriceByRoute(String route_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + FROMDATE + "='" + route_id + "'", null);

        GroupPriceModel groupPriceModel = new GroupPriceModel();
        if (res.moveToFirst()) {
            groupPriceModel.setACCOUNTCODE(res.getInt(res.getColumnIndex(ACCOUNTCODE)));
            groupPriceModel.setITEMRELATION(res.getString(res.getColumnIndex(ITEMRELATION)));
            groupPriceModel.setACCOUNTRELATION(res.getString(res.getColumnIndex(ACCOUNTRELATION)));
            groupPriceModel.setQUANTITYAMOUNT(res.getInt(res.getColumnIndex(QUANTITYAMOUNT)));
            groupPriceModel.setFROMDATE(res.getString(res.getColumnIndex(FROMDATE)));
            groupPriceModel.setTODATE(res.getString(res.getColumnIndex(TODATE)));
            groupPriceModel.setAMOUNT(res.getDouble(res.getColumnIndex(AMOUNT)));
            groupPriceModel.setCURRENCY(res.getString(res.getColumnIndex(CURRENCY)));
            groupPriceModel.setDiscount(res.getInt(res.getColumnIndex(DISCOUNT)));
            groupPriceModel.setDiscount1(res.getInt(res.getColumnIndex(DISCOUNT1)));
            groupPriceModel.setPriceType(res.getString(res.getColumnIndex(PRICETYPE)));
            groupPriceModel.setUNITID(res.getString(res.getColumnIndex(UNITID)));
            groupPriceModel.setSurcharge(res.getFloat(res.getColumnIndex(SURCHARGE)));
            groupPriceModel.setMODULE(res.getInt(res.getColumnIndex(MODULE)));
            groupPriceModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
        }
        res.close();
        db.close();
        return groupPriceModel;
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

    public ArrayList<GroupPriceModel> getAllPrice() {
        ArrayList<GroupPriceModel> array_list = new ArrayList<GroupPriceModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                GroupPriceModel groupPriceModel = new GroupPriceModel();
                groupPriceModel.setACCOUNTCODE(res.getInt(res.getColumnIndex(ACCOUNTCODE)));
                groupPriceModel.setITEMRELATION(res.getString(res.getColumnIndex(ITEMRELATION)));
                groupPriceModel.setACCOUNTRELATION(res.getString(res.getColumnIndex(ACCOUNTRELATION)));
                groupPriceModel.setQUANTITYAMOUNT(res.getInt(res.getColumnIndex(QUANTITYAMOUNT)));
                groupPriceModel.setFROMDATE(res.getString(res.getColumnIndex(FROMDATE)));
                groupPriceModel.setTODATE(res.getString(res.getColumnIndex(TODATE)));
                groupPriceModel.setAMOUNT(res.getDouble(res.getColumnIndex(AMOUNT)));
                groupPriceModel.setCURRENCY(res.getString(res.getColumnIndex(CURRENCY)));
                groupPriceModel.setDiscount(res.getInt(res.getColumnIndex(DISCOUNT)));
                groupPriceModel.setDiscount1(res.getInt(res.getColumnIndex(DISCOUNT1)));
                groupPriceModel.setPriceType(res.getString(res.getColumnIndex(PRICETYPE)));
                groupPriceModel.setUNITID(res.getString(res.getColumnIndex(UNITID)));
                groupPriceModel.setSurcharge(res.getFloat(res.getColumnIndex(SURCHARGE)));
                groupPriceModel.setMODULE(res.getInt(res.getColumnIndex(MODULE)));
                groupPriceModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));

                array_list.add(groupPriceModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }
}
