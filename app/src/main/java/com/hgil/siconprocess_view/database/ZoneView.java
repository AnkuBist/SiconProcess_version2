package com.hgil.siconprocess_view.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hgil.siconprocess_view.adapter.depotList.DepotModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.ZoneModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohan.giri on 27-04-2017.
 */

public class ZoneView extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NAME = "Sicon_Zone";
    private static final String TABLE_NAME = "Zone_Master_table";

    private static final String ZONE_NAME = "zoneName";
    private static final String DEPOT_ID = "depotId";
    private static final String DEPOT_NAME = "Depot_Name";
    private static final String ZONE_SEQUENCE = "zoneSequence";

    private Context mContext;

    public ZoneView(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + ZONE_NAME + " TEXT NULL, "
                + DEPOT_ID + " TEXT NULL, " + DEPOT_NAME + " TEXT NULL, " + ZONE_SEQUENCE + " INTEGER NULL)");
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
    public boolean insertZone(List<ZoneModel> arrList) {
        SQLiteDatabase db = this.getWritableDatabase();
        final long startTime = System.currentTimeMillis();

        DatabaseUtils.InsertHelper ih = new DatabaseUtils.InsertHelper(db, TABLE_NAME);

        // Get the numeric indexes for each of the columns that we're updating
        final int zoneNameColumn = ih.getColumnIndex(ZONE_NAME);
        final int depotIdColumn = ih.getColumnIndex(DEPOT_ID);
        final int depotNameColumn = ih.getColumnIndex(DEPOT_NAME);
        final int zoneSequenceColumn = ih.getColumnIndex(ZONE_SEQUENCE);

        try {
            db.beginTransaction();
            for (ZoneModel zoneModel : arrList) {
                ih.prepareForInsert();

                ih.bind(zoneNameColumn, zoneModel.getZoneName());
                ih.bind(depotIdColumn, zoneModel.getDepotId());
                ih.bind(depotNameColumn, zoneModel.getDepotName());
                ih.bind(zoneSequenceColumn, zoneModel.getZoneSequence());

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

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return numRows;
    }

    /*get unique zones for login user*/
    public ArrayList<String> getZoneList() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + ZONE_NAME + " FROM " + TABLE_NAME + " ORDER BY " + ZONE_SEQUENCE + " ASC", null);
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                String zoneName = (res.getString(res.getColumnIndex(ZONE_NAME)));
                array_list.add(zoneName);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

    /*DISTINCT DEPOTS ON SELECTED ZONE*/
    /*get unique depots for login user*/
    public ArrayList<DepotModel> getDepotList(String zoneName) {
        ArrayList<DepotModel> array_list = new ArrayList<DepotModel>();

        RouteView routeView = new RouteView(mContext);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT distinct " + DEPOT_ID + ", " + DEPOT_NAME + " FROM " + TABLE_NAME + " where " + ZONE_NAME + "=?",
                new String[]{zoneName});
        if (res.moveToFirst()) {
            while (res.isAfterLast() == false) {
                DepotModel depotModel = new DepotModel();
                depotModel.setDepot_id(res.getString(res.getColumnIndex(DEPOT_ID)));
                depotModel.setDepot_name(res.getString(res.getColumnIndex(DEPOT_NAME)));
                // get depot leftover and rej prct
                DepotModel tempModel = routeView.depotLOnRejPrct(depotModel.getDepot_id());
                depotModel.setDepot_leftover(tempModel.getDepot_leftover());
                depotModel.setDepot_rej_prct(tempModel.getDepot_rej_prct());

                array_list.add(depotModel);
                res.moveToNext();
            }
        }
        res.close();
        db.close();
        return array_list;
    }

}