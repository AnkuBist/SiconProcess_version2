package com.hgil.siconprocess.database.masterTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CustomerInfoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 18-03-2017.
 */

public class CustomerInfoView extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Customer_db";
    private static final String TABLE_NAME = "V_Customer_Master";

    private static final String CUSTOMER_ID = "Customer_Id";
    private static final String CUSTOMER_NAME = "Customer_Name";
    private static final String C_TYPE = "C_Type";
    private static final String CUSTOMER_BILLING_ADDRESS = "Customer_Billing_Address";
    private static final String DEPOT_ID = "Depot_id";
    private static final String SUB_COMPANY_ID = "Sub_Company_id";
    private static final String INVOICEACCOUNT = "INVOICEACCOUNT";
    private static final String CUSTGROUP = "CUSTGROUP";
    private static final String LINEDISC = "LINEDISC";
    private static final String PAYMTERMID = "PAYMTERMID";
    private static final String CASHDISC = "CASHDISC";
    private static final String SALESGROUP = "SALESGROUP";
    private static final String BLOCKED = "BLOCKED";
    private static final String PRICEGROUP = "PRICEGROUP";
    private static final String MULTILINEDISC = "MULTILINEDISC";
    private static final String TAXGROUP = "TAXGROUP";
    private static final String COMMISSIONGROUP = "COMMISSIONGROUP";
    private static final String OURACCOUNTNUM = "OURACCOUNTNUM";
    private static final String NAMEALIAS = "NAMEALIAS";
    private static final String SH_NAME = "SH_NAME";
    private static final String ACCOUNTNUM = "ACCOUNTNUM";
    private static final String CUSTCLASSIFICATIONID = "CUSTCLASSIFICATIONID";
    private static final String CONTACT_NO = "ContactNo";
    private static final String FLAG = "flag";

    private Context mContext;

    public CustomerInfoView(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + CUSTOMER_ID + " TEXT NOT NULL, "
                + CUSTOMER_NAME + " TEXT NOT NULL, " + C_TYPE + " TEXT NOT NULL, " + CUSTOMER_BILLING_ADDRESS + " TEXT NOT NULL, "
                + DEPOT_ID + " TEXT NOT NULL, " + SUB_COMPANY_ID + " TEXT NOT NULL, " + INVOICEACCOUNT + " TEXT NOT NULL, "
                + CUSTGROUP + " TEXT NOT NULL, " + LINEDISC + " TEXT NOT NULL, " + PAYMTERMID + " TEXT NOT NULL, "
                + CASHDISC + " TEXT NOT NULL, " + SALESGROUP + " TEXT NOT NULL, " + BLOCKED + " INTEGER NOT NULL, "
                + PRICEGROUP + " TEXT NOT NULL, " + MULTILINEDISC + " TEXT NOT NULL, " + TAXGROUP + " TEXT NOT NULL, "
                + COMMISSIONGROUP + " TEXT NOT NULL, " + OURACCOUNTNUM + " TEXT NOT NULL, " + NAMEALIAS + " TEXT NOT NULL, "
                + SH_NAME + " TEXT NOT NULL, " + ACCOUNTNUM + " TEXT NOT NULL, " + CUSTCLASSIFICATIONID + " TEXT NULL, "
                + CONTACT_NO + " TEXT NULL, " + FLAG + " INTEGER NOT NULL)");
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
    public boolean insertCustomer(CustomerInfoModel customerInfoModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSTOMER_ID, customerInfoModel.getCustomerId());
        contentValues.put(CUSTOMER_NAME, customerInfoModel.getCustomerName());
        contentValues.put(C_TYPE, customerInfoModel.getCType());
        contentValues.put(CUSTOMER_BILLING_ADDRESS, customerInfoModel.getCustomerBillingAddress());
        contentValues.put(DEPOT_ID, customerInfoModel.getDepotId());
        contentValues.put(SUB_COMPANY_ID, customerInfoModel.getSubCompanyId());
        contentValues.put(INVOICEACCOUNT, customerInfoModel.getINVOICEACCOUNT());
        contentValues.put(CUSTGROUP, customerInfoModel.getCUSTGROUP());
        contentValues.put(LINEDISC, customerInfoModel.getLINEDISC());
        contentValues.put(PAYMTERMID, customerInfoModel.getPAYMTERMID());
        contentValues.put(CASHDISC, customerInfoModel.getCASHDISC());
        contentValues.put(SALESGROUP, customerInfoModel.getSALESGROUP());
        contentValues.put(BLOCKED, customerInfoModel.getBLOCKED());
        contentValues.put(PRICEGROUP, customerInfoModel.getPRICEGROUP());
        contentValues.put(MULTILINEDISC, customerInfoModel.getMULTILINEDISC());
        contentValues.put(TAXGROUP, customerInfoModel.getTAXGROUP());
        contentValues.put(COMMISSIONGROUP, customerInfoModel.getCOMMISSIONGROUP());
        contentValues.put(OURACCOUNTNUM, customerInfoModel.getOURACCOUNTNUM());
        contentValues.put(NAMEALIAS, customerInfoModel.getNAMEALIAS());
        contentValues.put(SH_NAME, customerInfoModel.getSHNAME());
        contentValues.put(ACCOUNTNUM, customerInfoModel.getACCOUNTNUM());
        contentValues.put(CUSTCLASSIFICATIONID, customerInfoModel.getCUSTCLASSIFICATIONID());
        contentValues.put(CONTACT_NO, customerInfoModel.getContactNo());
        contentValues.put(FLAG, customerInfoModel.getFlag());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple items price
    public boolean insertCustomer(List<CustomerInfoModel> arrCustomers) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrCustomers.size(); i++) {
            CustomerInfoModel customerInfoModel = arrCustomers.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(CUSTOMER_ID, customerInfoModel.getCustomerId());
            contentValues.put(CUSTOMER_NAME, customerInfoModel.getCustomerName());
            contentValues.put(C_TYPE, customerInfoModel.getCType());
            contentValues.put(CUSTOMER_BILLING_ADDRESS, customerInfoModel.getCustomerBillingAddress());
            contentValues.put(DEPOT_ID, customerInfoModel.getDepotId());
            contentValues.put(SUB_COMPANY_ID, customerInfoModel.getSubCompanyId());
            contentValues.put(INVOICEACCOUNT, customerInfoModel.getINVOICEACCOUNT());
            contentValues.put(CUSTGROUP, customerInfoModel.getCUSTGROUP());
            contentValues.put(LINEDISC, customerInfoModel.getLINEDISC());
            contentValues.put(PAYMTERMID, customerInfoModel.getPAYMTERMID());
            contentValues.put(CASHDISC, customerInfoModel.getCASHDISC());
            contentValues.put(SALESGROUP, customerInfoModel.getSALESGROUP());
            contentValues.put(BLOCKED, customerInfoModel.getBLOCKED());
            contentValues.put(PRICEGROUP, customerInfoModel.getPRICEGROUP());
            contentValues.put(MULTILINEDISC, customerInfoModel.getMULTILINEDISC());
            contentValues.put(TAXGROUP, customerInfoModel.getTAXGROUP());
            contentValues.put(COMMISSIONGROUP, customerInfoModel.getCOMMISSIONGROUP());
            contentValues.put(OURACCOUNTNUM, customerInfoModel.getOURACCOUNTNUM());
            contentValues.put(NAMEALIAS, customerInfoModel.getNAMEALIAS());
            contentValues.put(SH_NAME, customerInfoModel.getSHNAME());
            contentValues.put(ACCOUNTNUM, customerInfoModel.getACCOUNTNUM());
            contentValues.put(CUSTCLASSIFICATIONID, customerInfoModel.getCUSTCLASSIFICATIONID());
            contentValues.put(CONTACT_NO, customerInfoModel.getContactNo());
            contentValues.put(FLAG, customerInfoModel.getFlag());
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


    // get all products loaded in van
    public ArrayList<CustomerInfoModel> getAllCustomers() {
        ArrayList<CustomerInfoModel> array_list = new ArrayList<CustomerInfoModel>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                CustomerInfoModel customerInfoModel = new CustomerInfoModel();
                customerInfoModel.setCustomerId(res.getString(res.getColumnIndex(CUSTOMER_ID)));
                customerInfoModel.setCustomerName(res.getString(res.getColumnIndex(CUSTOMER_NAME)));
                customerInfoModel.setCType(res.getString(res.getColumnIndex(C_TYPE)));
                customerInfoModel.setCustomerBillingAddress(res.getString(res.getColumnIndex(CUSTOMER_BILLING_ADDRESS)));
                customerInfoModel.setDepotId(res.getString(res.getColumnIndex(DEPOT_ID)));
                customerInfoModel.setSubCompanyId(res.getString(res.getColumnIndex(SUB_COMPANY_ID)));
                customerInfoModel.setINVOICEACCOUNT(res.getString(res.getColumnIndex(INVOICEACCOUNT)));
                customerInfoModel.setCUSTGROUP(res.getString(res.getColumnIndex(CUSTGROUP)));
                customerInfoModel.setLINEDISC(res.getString(res.getColumnIndex(LINEDISC)));
                customerInfoModel.setPAYMTERMID(res.getString(res.getColumnIndex(PAYMTERMID)));
                customerInfoModel.setCASHDISC(res.getString(res.getColumnIndex(CASHDISC)));
                customerInfoModel.setSALESGROUP(res.getString(res.getColumnIndex(SALESGROUP)));
                customerInfoModel.setBLOCKED(res.getInt(res.getColumnIndex(BLOCKED)));
                customerInfoModel.setPRICEGROUP(res.getString(res.getColumnIndex(PRICEGROUP)));
                customerInfoModel.setMULTILINEDISC(res.getString(res.getColumnIndex(MULTILINEDISC)));
                customerInfoModel.setTAXGROUP(res.getString(res.getColumnIndex(TAXGROUP)));
                customerInfoModel.setCOMMISSIONGROUP(res.getString(res.getColumnIndex(COMMISSIONGROUP)));
                customerInfoModel.setOURACCOUNTNUM(res.getString(res.getColumnIndex(OURACCOUNTNUM)));
                customerInfoModel.setNAMEALIAS(res.getString(res.getColumnIndex(NAMEALIAS)));
                customerInfoModel.setSHNAME(res.getString(res.getColumnIndex(SH_NAME)));
                customerInfoModel.setACCOUNTNUM(res.getString(res.getColumnIndex(ACCOUNTNUM)));
                customerInfoModel.setCUSTCLASSIFICATIONID(res.getString(res.getColumnIndex(CUSTCLASSIFICATIONID)));
                customerInfoModel.setContactNo(res.getString(res.getColumnIndex(CONTACT_NO)));
                customerInfoModel.setFlag(res.getInt(res.getColumnIndex(FLAG)));

                array_list.add(customerInfoModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }


}
