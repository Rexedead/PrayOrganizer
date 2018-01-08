package com.prayorganizer.rxdd.orthodox.database;

import android.database.Cursor;

import com.prayorganizer.rxdd.orthodox.AppContext;
import com.prayorganizer.rxdd.orthodox.content.IconsMain;
import com.prayorganizer.rxdd.orthodox.content.Pray;
import com.prayorganizer.rxdd.orthodox.content.PraysCategories;
import com.prayorganizer.rxdd.orthodox.content.Psalm;
import com.prayorganizer.rxdd.orthodox.content.PsalmsCategories;
import com.prayorganizer.rxdd.orthodox.database.DatabaseSchema.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rexedead on 30.12.2017.
 *
 */

public class HolyModel {

    private static final HolyModel sInstance = new HolyModel();
    private DatabaseHelper mDatabaseHelper;

    public static HolyModel getInstance() {
        return sInstance;
    }
    

    private HolyModel() {
               mDatabaseHelper = new DatabaseHelper(AppContext.getAppContext());
    }


    public List<PraysCategories> getMasterCategoriesOfPrays() {
        List<PraysCategories> praysMasterCategories = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + Tables.PRAYS_MASTER;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                PraysCategories prays_cats;
                prays_cats = new PraysCategories(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_MASTER_CATNAME)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_MASTER_IMAGE)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_MASTER_TAG))
                );
                praysMasterCategories.add(prays_cats);

            } while (cursor.moveToNext());
            cursor.close();

        }
        mDatabaseHelper.close();
        return praysMasterCategories;
    }

    public List<PraysCategories> getSlaveCategoriesOfPrays(String masterCategory) {
        List<PraysCategories> praysSlaveCategories = new ArrayList<>();
        String masterId ="";
        String masterIdQuery =
                "SELECT " + Columns.PRAY_MASTER_ID +
                " FROM " + Tables.PRAYS_MASTER +
                " WHERE " + Tables.PRAYS_MASTER + "." + Columns.PRAY_MASTER_CATNAME + "='" + masterCategory+"'";
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(masterIdQuery);

        if (cursor.moveToFirst()) {
            do {
                masterId = cursor.getString(cursor.getColumnIndex(Columns.PRAY_MASTER_ID));
            } while (cursor.moveToNext());
            cursor.close();
        }


        String selectQuery =
                "SELECT " + Columns.PRAY_SLAVE_CATNAME+"," +Columns.PRAY_SLAVE_IMAGE+
                " FROM " + Tables.PRAYS_SLAVE +
                " LEFT JOIN " + Tables.PRAYS_CATS +
                " ON " + Tables.PRAYS_SLAVE + "." + Columns.PRAY_SLAVE_ID + "=" + Tables.PRAYS_CATS + "." + Columns.PRAY_SLAVE_ID +
                " WHERE " + Tables.PRAYS_CATS + "." + Columns.PRAY_MASTER_ID + "=" + masterId;


        cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                PraysCategories prays_cats;
                prays_cats = new PraysCategories(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_SLAVE_CATNAME)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_SLAVE_IMAGE))
                );
                praysSlaveCategories.add(prays_cats);
            } while (cursor.moveToNext());
            cursor.close();
        }
        mDatabaseHelper.close();
        return praysSlaveCategories;
    }

    public List<Pray> getPrays(String slaveCategory) {
        List<Pray> praysText = new ArrayList<>();
        String slaveIdQuery = "SELECT " + Columns.PRAY_SLAVE_ID +
                " FROM " + Tables.PRAYS_SLAVE +
                " WHERE " + Tables.PRAYS_SLAVE + "." + Columns.PRAY_SLAVE_CATNAME + "=" + slaveCategory;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(slaveIdQuery);
        String slaveId = cursor.getString(Integer.parseInt(Columns.PRAY_SLAVE_ID));

        String selectQuery = "SELECT *"+
                " FROM " + Tables.PRAYS_MAIN +
                " LEFT JOIN " + Tables.PRAYS_CATS +
                " ON " + Tables.PRAYS_MAIN + "." + Columns.PRAY_ID + "=" + Tables.PRAYS_CATS + "." + Columns.PRAY_SLAVE_ID +
                " WHERE " + Tables.PRAYS_CATS + "." + Columns.PRAY_SLAVE_ID + "=" + slaveId;


        cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                Pray pray_single;
                pray_single = new Pray(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TEXT)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_FAV)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TITLE))
                );
                praysText.add(pray_single);
            } while (cursor.moveToNext());
            cursor.close();
        }

        mDatabaseHelper.close();
        return praysText;
    }

    public List<PsalmsCategories> getMasterCategoriesOfPsalms() {
        List<PsalmsCategories> psalmsMasterCategories = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Tables.PSALMS_MASTER;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                PsalmsCategories psalms_cats;
                psalms_cats = new PsalmsCategories(
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_CAT_ID)),
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_KAF_ID))
                );
                psalmsMasterCategories.add(psalms_cats);

            } while (cursor.moveToNext());
            cursor.close();

        }

        mDatabaseHelper.close();
        return psalmsMasterCategories;
    }

    public List<Psalm> getSinglePsalm(String masterId){
        List<Psalm> psalmText = new ArrayList<>();
        String selectPsalmQuery =
        "SELECT "+ Columns.PSALM_RU+","+Columns.PSALM_CSL+","+Columns.PSALM_LINE_NUM +","+Columns.PSALM_HEAD_CSL+","+Columns.PSALM_HEAD_RU+
        " FROM "+Tables.PSALM_MAIN+
        " LEFT JOIN "+Tables.PSALM_HEAD +
        " ON "+Columns.PSALM_HEAD_ID+"="+Columns.PSALM_CAT_HEAD_ID+
        " WHERE "+Tables.PSALM_MAIN+"."+Columns.PSALM_CAT_HEAD_ID +"="+ masterId;

        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectPsalmQuery);

        if (cursor.moveToFirst()) {
            do {
                Psalm psalm_single;
                psalm_single = new Psalm(
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_RU)),
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_CSL)),
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_RU)),
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_CSL)),
                        cursor.getString(cursor.getColumnIndex(Columns.PSALM_LINE_NUM))
                );
                psalmText.add(psalm_single);

            } while (cursor.moveToNext());
            cursor.close();

        }

        mDatabaseHelper.close();
        return psalmText;
    }


    public List<IconsMain> getIconsData() {
        List<IconsMain> mm = new ArrayList<>();
        IconsMain m = new IconsMain("ss");
        mm.add(m);
        return mm;
    }
}