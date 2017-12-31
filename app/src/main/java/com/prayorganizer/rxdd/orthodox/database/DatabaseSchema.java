package com.prayorganizer.rxdd.orthodox.database;

/**
 * Created by 22757 on 30.12.2017.
 */

public class DatabaseSchema {
    public static final class Tables {
        public static final String TABLE_PRAYS_MAIN = "prays_main";
        public static final String TABLE_PRAYS_CATS = "prays_cats";
        public static final String TABLE_PRAYS_MASTER = "prays_cats_id_master";
        public static final String TABLE_PRAYS_SLAVE = "prays_cats_id_slave";
        public static final String TABLE_PRAYS_KONTAK = "prays_kontakion";
        public static final String TABLE_PRAYS_TROP = "prays_troparion";
        public static final String COLUMN_PRAY_ID = "_pray_id";
        public static final String COLUMN_PRAY_TEXT = "praytext";
        public static final String COLUMN_PRAY_TITLE = "title";
        public static final String COLUMN_PRAY_FAV = "fav";
        public static final String COLUMN_PRAY_TAG = "tags";
        public static final String COLUMN_CAT_ID = "_cat_id";
        public static final String COLUMN_MASTER_ID = "_master_id";
        public static final String COLUMN_MASTER_CATNAME = "cat_master";
        public static final String COLUMN_SLAVE_ID = "_slave_id";
        public static final String COLUMN_SLAVE_CATNAME = "cat_slave";
    }
}