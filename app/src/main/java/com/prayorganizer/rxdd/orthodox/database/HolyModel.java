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
 *todo select psalt_ru_line from psalt_main where _id_psalt in(select * from psalt_fav)
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
                " WHERE " + Tables.PRAYS_SLAVE + "." + Columns.PRAY_SLAVE_CATNAME + "='" + slaveCategory+"'";
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(slaveIdQuery);
        String slaveId = "0";
        if (cursor.moveToFirst()) {
            do {
                slaveId = cursor.getString(cursor.getColumnIndex(Columns.PRAY_SLAVE_ID));
            } while (cursor.moveToNext());
            cursor.close();
        }
        
        System.out.println(slaveId);
        String selectQuery = "SELECT *"+
                " FROM " + Tables.PRAYS_MAIN +
                " LEFT JOIN " + Tables.PRAYS_CATS +
                " ON " + Tables.PRAYS_MAIN + "." + Columns.PRAY_ID + "=" + Tables.PRAYS_CATS + "." + Columns.PRAY_SLAVE_ID +
                " WHERE " + Tables.PRAYS_CATS + "." + Columns.PRAY_SLAVE_ID + "=" + slaveId;

        System.out.println(selectQuery);
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

    public Psalm getSinglePsalm(String masterId){

        String selectPsalmQuery =
        "SELECT * FROM " + Tables.PSALM_MAIN +
                " WHERE " + Columns.PSALM_ID +"="+"'"+masterId+"'";

        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectPsalmQuery);
        List<String> ruPsalmLines = new ArrayList<>();
        List<String> cslPsalmLines = new ArrayList<>();
        String headRu = null;
        String headCsl = null;
        if (cursor.moveToFirst()) {
            do {

                          ruPsalmLines.add(cursor.getString(cursor.getColumnIndex(Columns.PSALM_RU)));
                          cslPsalmLines.add(cursor.getString(cursor.getColumnIndex(Columns.PSALM_CSL)));
                          headRu = cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_RU));
                          headCsl = cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_CSL));


            } while (cursor.moveToNext());
            cursor.close();

        }

        mDatabaseHelper.close();

        return new Psalm(
                ruPsalmLines.toArray(new String[ruPsalmLines.size()]),
                cslPsalmLines.toArray(new String[cslPsalmLines.size()]),
                headRu, headCsl
                );
    }


    public List<IconsMain> getIconsData() {
        List<IconsMain> iconsMains = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + Tables.ICONS_WEB;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                IconsMain icons;
                icons = new IconsMain(
                        cursor.getString(cursor.getColumnIndex(Columns.ICON_NAME)),
                        cursor.getString(cursor.getColumnIndex(Columns.ICON_URL))
                );
                iconsMains.add(icons);

            } while (cursor.moveToNext());
            cursor.close();

        }

        mDatabaseHelper.close();
        return iconsMains;
    }
}