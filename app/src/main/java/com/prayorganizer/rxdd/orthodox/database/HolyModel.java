package com.prayorganizer.rxdd.orthodox.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
 * todo select psalt_ru_line from psalt_main where _id_psalt in(select * from psalt_fav)
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


    public List<PraysCategories> getAllSlaveCategoriesOfPrays(){
        List<PraysCategories> praysSlaveCategoriesAll = new ArrayList<>();
        String Query = "SELECT * FROM "+ Tables.PRAYS_SLAVE;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(Query);
        if (cursor.moveToFirst()) {
            do {
                PraysCategories prays_slave;
                prays_slave = new PraysCategories(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_SLAVE_CATNAME)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_SLAVE_IMAGE))
                );
                praysSlaveCategoriesAll.add(prays_slave);
            } while (cursor.moveToNext());
            cursor.close();
        }
        mDatabaseHelper.close();
        return praysSlaveCategoriesAll;
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

        String selectQuery = "SELECT " + Columns.PRAY_ID + ", " + Columns.PRAY_TITLE+ ", " + Columns.PRAY_TEXT+
                " FROM " + Tables.PRAYS_MAIN +
                " WHERE " + Columns.PRAY_SLAVE_ID + "='" + slaveId+"'";

        cursor = mDatabaseHelper.getCursor(selectQuery);
        if (cursor.moveToFirst()) {
            do {
                Pray pray_single;
                pray_single = new Pray(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TEXT)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TITLE)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_ID))
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
                " WHERE " + Columns.PSALM_ID +" = "+ masterId;
        Psalm mPsalm = null;
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(selectPsalmQuery);
        if (cursor.moveToFirst()) {
            do {

                    String psalmRu[] = cursor.getString(cursor.getColumnIndex(Columns.PSALM_RU)).split("\n");
                    String psalmCsl[] = cursor.getString(cursor.getColumnIndex(Columns.PSALM_CSL)).split("\n");


                mPsalm = new Psalm(
                            psalmRu,
                            psalmCsl,
                    cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_RU)),
                    cursor.getString(cursor.getColumnIndex(Columns.PSALM_HEAD_CSL))
                );

            } while (cursor.moveToNext());
            cursor.close();

        }

        mDatabaseHelper.close();

        return mPsalm;
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


    public boolean checkFav(String id, String Table){
        String Query = "SELECT "+Columns.FAV_ID+ " FROM "+Table+" WHERE "+Columns.FAV_ID+" = "+id;
        mDatabaseHelper.openDB();
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            db.close();
            mDatabaseHelper.close();
            return false;
        }
        cursor.close();
        db.close();
        mDatabaseHelper.close();
        return true;

    }


    public void setFav(String id, String Table){ //Tables.FAV_PRAYS
        ContentValues cv = new ContentValues();
        cv.put(Columns.FAV_ID, id);
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        db.insert(Table, null, cv);
        mDatabaseHelper.close();
        db.close();
        cv.clear();
    }


    public void removeFav(String id, String Table){
        SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
        db.delete(Table, Columns.FAV_ID+"=" + id, null);
        mDatabaseHelper.close();
        db.close();
    }

    public List<Pray> getFavsOfPrays(){
        List<Pray> praysFavorites = new ArrayList<>();
        String Query = "SELECT * FROM " + Tables.PRAYS_MAIN +" WHERE "+Columns.PRAY_ID+" IN (SELECT * FROM "+Tables.FAV_PRAYS+")";
        mDatabaseHelper.openDB();
        Cursor cursor = mDatabaseHelper.getCursor(Query);
        if (cursor.moveToFirst()) {
            do {
                Pray prays_fav;
                prays_fav = new Pray(
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TEXT)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_TITLE)),
                        cursor.getString(cursor.getColumnIndex(Columns.PRAY_ID))
                );
                praysFavorites.add(prays_fav);
            } while (cursor.moveToNext());
            cursor.close();
        }
        mDatabaseHelper.close();
        return praysFavorites;
    }

}