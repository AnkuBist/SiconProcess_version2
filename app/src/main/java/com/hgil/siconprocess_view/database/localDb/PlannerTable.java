package com.hgil.siconprocess_view.database.localDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 15-04-2017.
 */

public class PlannerTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Sicon_Planner_db";
    private static final String TABLE_NAME = "Planner_Table";

    private static final String USER_ID = "login_id";
    private static final String PLAN = "plan";
    private static final String PLAN_DATE = "plan_date";

    private Context mContext;

    public PlannerTable(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + USER_ID + " TEXT NULL, "
                + PLAN + " TEXT NULL, " + PLAN_DATE + " TEXT NULL)");
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
    public boolean insertUserPlan(PlanModel planModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID, planModel.getUserId());
        contentValues.put(PLAN, planModel.getUserPlan());
        contentValues.put(PLAN_DATE, planModel.getPlanDate());
        if (hasObject(db, planModel.getUserId(), planModel.getPlanDate()))
            db.update(TABLE_NAME, contentValues, USER_ID + "=? AND " + PLAN_DATE + "=?", new String[]{planModel.getUserId(), planModel.getPlanDate()});
        else
            db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    // insert multiple
    public boolean insertUserPlan(List<PlanModel> arrUserPlan) {
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i = 0; i < arrUserPlan.size(); i++) {
            PlanModel planModel = arrUserPlan.get(i);
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_ID, planModel.getUserId());
            contentValues.put(PLAN, planModel.getUserPlan());
            contentValues.put(PLAN_DATE, planModel.getPlanDate());
            if (hasObject(db, planModel.getUserId(), planModel.getPlanDate()))
                db.update(TABLE_NAME, contentValues, USER_ID + "=? AND " + PLAN_DATE + "=?", new String[]{planModel.getUserId(), planModel.getPlanDate()});
            else
                db.insert(TABLE_NAME, null, contentValues);
        }
        db.close();
        return true;
    }

    // check if the record exists or not
    public boolean hasObject(SQLiteDatabase db, String user_id, String plan_date) {
        String selectString = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " + PLAN_DATE + "=?";

        Cursor cursor = db.rawQuery(selectString, new String[]{user_id, plan_date});

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;
        }
        cursor.close();          // Don't forget to close your cursor
        return hasObject;
    }

   /* public String getCustomerContact(String customer_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT " + CONTACT_NO + " FROM " + TABLE_NAME + " WHERE " + INVOICE_ID + "=?", new String[]{customer_id});

        String contact = "";
        if (res.moveToFirst()) {
            contact = res.getString(res.getColumnIndex(CONTACT_NO));
        }
        res.close();
        db.close();
        return contact;
    }*/

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    // get all customers
    public ArrayList<PlanModel> getAllPlan() {
        ArrayList<PlanModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (res.moveToFirst()) {
            PlanModel planModel = new PlanModel();
            planModel.setUserId(res.getString(res.getColumnIndex(USER_ID)));
            planModel.setUserPlan(res.getString(res.getColumnIndex(PLAN)));
            planModel.setPlanDate(res.getString(res.getColumnIndex(PLAN_DATE)));
            array_list.add(planModel);
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get user plans*/
    public ArrayList<PlanModel> getUserPlan(String user_id) {
        ArrayList<PlanModel> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where " + USER_ID + "=?", new String[]{user_id});
        if (res.moveToFirst()) {
            PlanModel planModel = new PlanModel();
            planModel.setUserId(res.getString(res.getColumnIndex(USER_ID)));
            planModel.setUserPlan(res.getString(res.getColumnIndex(PLAN)));
            planModel.setPlanDate(res.getString(res.getColumnIndex(PLAN_DATE)));
            array_list.add(planModel);
        }
        res.close();
        db.close();
        return array_list;
    }

    /*get demand target by route*/
    public PlanModel getUserPlan(String user_id, String plan_date) {
        SQLiteDatabase db = this.getReadableDatabase();
        PlanModel planModel = new PlanModel();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + USER_ID + "=? AND " + PLAN_DATE + "=?",
                new String[]{user_id, plan_date});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                planModel.setUserId(res.getString(res.getColumnIndex(USER_ID)));
                planModel.setUserPlan(res.getString(res.getColumnIndex(PLAN)));
                planModel.setPlanDate(res.getString(res.getColumnIndex(PLAN_DATE)));
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return planModel;
    }
}