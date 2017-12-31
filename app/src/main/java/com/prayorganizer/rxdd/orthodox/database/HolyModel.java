package com.prayorganizer.rxdd.orthodox.database;

import android.database.Cursor;

import com.prayorganizer.rxdd.orthodox.AppContext;
import com.prayorganizer.rxdd.orthodox.content.Pray;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.database.DatabaseSchema.Tables;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rexedead on 30.12.2017.
 *
 */

public class HolyModel {

    private DatabaseHelper sDatabaseHelper = new DatabaseHelper(AppContext.getAppContext());
    private static final HolyModel ourInstance = new HolyModel();

    public static HolyModel getInstance() {
        return ourInstance;
    }

    private HolyModel() {
    }





    public List<PraysCategories> getMasterCategories() {
        List<PraysCategories> praysMasterCategories = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Tables.TABLE_PRAYS_MASTER;
        Cursor cursor = sDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
                do {
                    PraysCategories prays_cats;
                    prays_cats = new PraysCategories(
                            cursor.getString(cursor.getColumnIndex(Tables.COLUMN_MASTER_CATNAME)),
                            cursor.getString(cursor.getColumnIndex(Tables.COLUMN_MASTER_ID))
                    );
                    praysMasterCategories.add(prays_cats);
                } while (cursor.moveToNext());
                cursor.close();
            }

        return praysMasterCategories;
    }





    public List<PraysCategories> getSlaveCategories(String masterCategory) {
        List<PraysCategories> praysSlaveCategories = new ArrayList<>();

       Cursor cursor;

        String masterIdQuery = "SELECT "+Tables.COLUMN_MASTER_ID +
                " FROM " + Tables.TABLE_PRAYS_MASTER +
                " WHERE "+Tables.TABLE_PRAYS_MASTER+"."+Tables.COLUMN_MASTER_CATNAME+"="+ masterCategory;

        cursor = sDatabaseHelper.getCursor(masterIdQuery);
        String masterId = cursor.getString(Integer.parseInt(Tables.COLUMN_MASTER_ID));


        String selectQuery = "SELECT "+Tables.COLUMN_SLAVE_CATNAME +
                " FROM "+Tables.TABLE_PRAYS_SLAVE+
                " LEFT JOIN "+Tables.TABLE_PRAYS_CATS+
                " ON "+Tables.TABLE_PRAYS_SLAVE+"."+Tables.COLUMN_SLAVE_ID+"="+Tables.TABLE_PRAYS_CATS+"."+Tables.COLUMN_MASTER_ID+
                " WHERE "+Tables.TABLE_PRAYS_CATS+"."+Tables.COLUMN_MASTER_ID+"="+ masterId;


        cursor = sDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                PraysCategories prays_cats;
                prays_cats = new PraysCategories(
                        cursor.getString(cursor.getColumnIndex(Tables.COLUMN_MASTER_CATNAME)),
                        cursor.getString(cursor.getColumnIndex(Tables.COLUMN_MASTER_ID))
                );
                praysSlaveCategories.add(prays_cats);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return praysSlaveCategories;
    }




    public List<Pray> getPrays(String slaveCategory) {
        List<Pray> praysText = new ArrayList<>();
        Cursor cursor;
        String slaveIdQuery = "SELECT "+Tables.COLUMN_SLAVE_ID +
                " FROM " + Tables.TABLE_PRAYS_SLAVE +
                " WHERE "+Tables.TABLE_PRAYS_SLAVE+"."+Tables.COLUMN_SLAVE_CATNAME+"="+ slaveCategory;

        cursor = sDatabaseHelper.getCursor(slaveIdQuery);
        String slaveId = cursor.getString(Integer.parseInt(Tables.COLUMN_SLAVE_ID));

        String selectQuery = "SELECT "+ Tables.COLUMN_PRAY_TEXT+
        " FROM "+ Tables.TABLE_PRAYS_MAIN+
        " LEFT JOIN "+Tables.TABLE_PRAYS_CATS+
        " ON "+ Tables.TABLE_PRAYS_MAIN+"."+Tables.COLUMN_PRAY_ID+"="+Tables.TABLE_PRAYS_CATS+"."+Tables.COLUMN_SLAVE_ID +
        " WHERE "+ Tables.TABLE_PRAYS_CATS+"."+Tables.COLUMN_SLAVE_ID+"="+ slaveId;


        cursor = sDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                Pray pray_single;
                pray_single = new Pray(
                        cursor.getString(cursor.getColumnIndex(Tables.COLUMN_MASTER_CATNAME))
                );
                praysText.add(pray_single);
            } while (cursor.moveToNext());
            cursor.close();
        }


        return praysText;
    }



}
